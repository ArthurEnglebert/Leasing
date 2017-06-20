package com.citobi.leasing.tools;

import org.springframework.security.core.Authentication;

/**
 * Facade to hide dependency of implementation of the authentication method from Controllers
 */
public interface AuthenticationFacade {
    Authentication getAuthentication();
}
