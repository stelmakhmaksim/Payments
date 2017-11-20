package com.epam.lab.payments.web.html;

import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.dto.UserDTO;
import com.epam.lab.payments.services.PaymentsService;
import com.epam.lab.payments.services.UserDetailsServiceImpl;
import com.epam.lab.payments.web.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserCardsController {
    private final UserDetailsServiceImpl userDetailsService;
    private final PaymentsService paymentsService;

    @RequestMapping("/userCards/{id}")
    public ModelAndView userCards(@PathVariable("id") String id, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("principal", principal.getName());

        // administrator's name
        UserEntity principalEntity = userDetailsService.loadUserEntityByUsername(principal.getName());
        modelAndView.addObject("firstName", principalEntity.getFirstName());
        modelAndView.addObject("lastName", principalEntity.getLastName());
        modelAndView.addObject("userId", id);

        // user's name
        Optional<UserDTO> userDto = paymentsService.findOneUser(Integer.valueOf(id));
        modelAndView.addObject("userFirstName", userDto.get().getFirstName());
        modelAndView.addObject("userLastName", userDto.get().getLastName());

        boolean isAdmin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(Roles.ADMIN.toString()));
        modelAndView.addObject("isAdmin", isAdmin ? true : false);

        modelAndView.setViewName("reports/userCards");
        return modelAndView;
    }
}
