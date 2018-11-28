package com.epam.lab.payments.web;

import com.epam.lab.payments.dao.BankAccountRepository;
import com.epam.lab.payments.domain.BankAccountEntity;
import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.dto.BankAccountDTO;
import com.epam.lab.payments.dto.CreditCardDTO;
import com.epam.lab.payments.dto.OrderDTO;
import com.epam.lab.payments.dto.PaymentDTO;
import com.epam.lab.payments.dto.RefillDTO;
import com.epam.lab.payments.dto.UserDTO;
import com.epam.lab.payments.services.PaymentsService;
import com.epam.lab.payments.services.UserDetailsServiceImpl;
import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/refill")
public class RefillController {
  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  @Autowired
  private PaymentsService paymentsService;

  @Autowired
  private BankAccountRepository bankAccountRepository;

  @GetMapping
  public ModelAndView updateUser(UserDTO user, HttpServletRequest request) {

    ModelAndView modelAndView = new ModelAndView();
    Principal principal = request.getUserPrincipal();

    if (principal == null) {
      modelAndView.addObject("error", "Authorization Error");
      modelAndView.setViewName("error");
      return modelAndView;
    }
    modelAndView.addObject("principal", principal.getName());

    UserEntity principalEntity = userDetailsService.loadUserEntityByUsername(principal.getName());
    modelAndView.addObject("firstName", principalEntity.getFirstName());
    modelAndView.addObject("lastName", principalEntity.getLastName());

    List<Integer> cardNumbers = paymentsService.findCardsByUserId(principalEntity.getId()).stream()
                                               .map(c -> c.getId())
                                               .collect(Collectors.toList());
    modelAndView.addObject("cardNumbers", cardNumbers);

    if (checkUserRole()) {
      modelAndView.addObject("id", principalEntity.getId());
      modelAndView.setViewName("fillAccount");
    }
    else {
      modelAndView.addObject("error", "Authorization Error");
      modelAndView.setViewName("error");
    }

    return modelAndView;
  }

  @PostMapping
  @Transactional
  public ResponseEntity makePayment(@RequestBody RefillDTO refillDTO, HttpServletRequest request) {
    ModelAndView modelAndView = new ModelAndView();

    Principal principal = request.getUserPrincipal();
    if (principal == null) {
      return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
    UserEntity principalEntity = userDetailsService.loadUserEntityByUsername(principal.getName());

    if (checkUserRole()) {
      try {
        CreditCardDTO card = paymentsService.findCardById(refillDTO.getCardNumber());
        if(card.getExpiration().toLocalDate().isBefore(new Date(Calendar.getInstance().getTime().getTime()).toLocalDate())){
          return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        BankAccountDTO account = paymentsService.findCardById(refillDTO.getCardNumber()).getAccount();

        if (account.isBlocked()) {
          return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        BankAccountEntity bankAccountEntity = bankAccountRepository.findOne(account.getId());
        bankAccountEntity.setBalance(bankAccountEntity.getBalance() + refillDTO.getAmount());
        bankAccountRepository.save(bankAccountEntity);

      }catch (Exception e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity(HttpStatus.OK);
    }
    else {
      return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

  }

  private boolean checkUserRole() {
    return SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getAuthorities()
        .contains(new SimpleGrantedAuthority(Roles.USER.toString()));
  }

}
