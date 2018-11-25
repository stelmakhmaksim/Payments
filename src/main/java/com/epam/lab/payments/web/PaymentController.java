package com.epam.lab.payments.web;

import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.dto.CreditCardDTO;
import com.epam.lab.payments.dto.UserDTO;
import com.epam.lab.payments.services.PaymentsService;
import com.epam.lab.payments.services.UserDetailsServiceImpl;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/payment")
public class PaymentController {
  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  @Autowired
  private PaymentsService paymentsService;

  @GetMapping
  public ModelAndView updateUser(UserDTO user, HttpServletRequest request) {
    boolean isUser = SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getAuthorities()
        .contains(new SimpleGrantedAuthority(Roles.USER.toString()));

    ModelAndView modelAndView = new ModelAndView();
    Principal principal = request.getUserPrincipal();
    modelAndView.addObject("principal", principal.getName());

    UserEntity principalEntity = userDetailsService.loadUserEntityByUsername(principal.getName());
    modelAndView.addObject("firstName", principalEntity.getFirstName());
    modelAndView.addObject("lastName", principalEntity.getLastName());

    List<Integer> cardNumbers = paymentsService.findCardsByUserId(principalEntity.getId()).stream().map(c -> c.getId())
        .collect(Collectors.toList());
    modelAndView.addObject("cardNumbers", cardNumbers);

    if (isUser) {
      modelAndView.addObject("id", principalEntity.getId());
      modelAndView.setViewName("payment");
    } else {
      modelAndView.addObject("error", "Authorization Error");
      modelAndView.setViewName("error");
    }

    return modelAndView;
  }
}
