package br.com.a3sitsolutions.services;

import br.com.a3sitsolutions.models.User;

public interface SecurityService {

    String generateToken(String email, String password);

    User getUserByToken(String token);
}
