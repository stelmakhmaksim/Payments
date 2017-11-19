package com.epam.lab.payments.web.html;

import com.epam.lab.payments.dto.BankAccountDTO;
import com.epam.lab.payments.services.PaymentsService;
import com.epam.lab.payments.web.html.cosmetic.CardNumberAdjuster;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AccountDetailsController {
    private final PaymentsService paymentsService;

    @RequestMapping("/account/{id}")
    public ModelAndView accountDetails(@PathVariable("id") String accountId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();

        modelAndView.addObject("principal", principal.getName());
        modelAndView.addObject("accountId", accountId);
        modelAndView.addObject("accountNumber", CardNumberAdjuster.valueOf16Digits(accountId));

        Optional<BankAccountDTO> accountDto = paymentsService.findOneBankAccount(Integer.valueOf(accountId));
        modelAndView.addObject("balanceSum", accountDto.get().getBalance());

        modelAndView.setViewName("reports/accountDetails");
        return modelAndView;
    }
}
