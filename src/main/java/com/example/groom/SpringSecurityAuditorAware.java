package com.example.groom;


import com.example.groom.entity.domain.auth.UserInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class SpringSecurityAuditorAware implements AuditorAware<UserInfo> {

    @Override
    public Optional<UserInfo> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map((Authentication t) -> t.getPrincipal()).map(id -> {
                    if (id.equals("anonymousUser")) return null;
                    return UserInfo.of((Long)id);
                });


    }
}