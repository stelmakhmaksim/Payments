package com.epam.lab.payments.auditor;

import com.epam.lab.payments.domain.UserEntity;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {
    public String getCurrentAuditor() {
        return ((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getEmail();
    }
}
