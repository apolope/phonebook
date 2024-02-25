package br.com.a3sitsolutions.services.impl;

import br.com.a3sitsolutions.models.User;
import br.com.a3sitsolutions.services.SecurityService;
import br.com.a3sitsolutions.services.UserService;
import br.com.a3sitsolutions.utils.SecurityUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SecurityServiceImpl implements SecurityService {

    @Inject
    UserService userService;

    @Override
    public String generateToken(String email, String password) {

        User user = userService.findByEmail(email);

        if (user != null) {
            if (SecurityUtils.checkPassword(password, user.getPassword())) {
                return SecurityUtils.generateToken(user);
            }
        }

        return null;

    }

    @Override
    public User getUserByToken(String token) {
        return null;
    }
}
