package br.com.a3sitsolutions.services.impl;

import br.com.a3sitsolutions.execptions.ValidationException;
import br.com.a3sitsolutions.models.People;
import br.com.a3sitsolutions.models.User;
import br.com.a3sitsolutions.repositories.PeopleRepository;
import br.com.a3sitsolutions.repositories.UserRepository;
import br.com.a3sitsolutions.services.PeopleService;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;
import java.util.Collection;

@ApplicationScoped
public class PeopleServiceImpl implements PeopleService {

    @Inject
    PeopleRepository repository;

    @Inject
    UserRepository userRepository;

    @Inject
    JsonWebToken jwt;

    @Override
    public People create(People entity) {
        entity.setUser(getUserByToken());
        return repository.create(entity);
    }

    @Override
    public Boolean delete(Long id) {
        People entityFinded = validated(repository.findById(id));
        return repository.deleteById(entityFinded.getId());
    }

    @Override
    public Collection<People> getAll(Page page) {
        return repository.findAllByUserAndPage(getUserByToken(), page);
    }

    @Override
    public Collection<People> getAll(Page page, Sort sort) {
        return repository.findAllByUserAndPageAndSort(getUserByToken(), page, sort);
    }

    @Override
    public People update(People entity) {
        return validated(repository.update(entity));
    }

    @Override
    public People findById(Long id) {
        return validated(repository.findById(id));
    }

    @Override
    public People findByName(String name) {
        return validated(repository.findByName(name));
    }

    @Override
    public People findByEmail(String email) {
        return validated(repository.findByEmail(email));
    }

    @Override
    public People findByPhone(String phone) {
        return validated(repository.findByPhone(phone));
    }

    private People validated(People entity) {
        if (entity != null) {
            return entity;
        }
        throw new ValidationException("People can't be find", HttpResponseStatus.NOT_FOUND);
    }

    private User getUserByToken() {
        Long userId = 0L;
        try {
            userId = Long.parseLong(jwt.getClaim("id").toString());
        } catch (NumberFormatException e) {
            throw new ValidationException("JWT Token problem", HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }

        User user = userRepository.findById(userId);
        if (user != null) {
            return user;
        }
        throw new ValidationException("Please login", HttpResponseStatus.FORBIDDEN);
    }
}
