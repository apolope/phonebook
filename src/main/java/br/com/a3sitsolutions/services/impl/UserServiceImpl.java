package br.com.a3sitsolutions.services.impl;

import br.com.a3sitsolutions.execptions.ValidationException;
import br.com.a3sitsolutions.models.User;
import br.com.a3sitsolutions.repositories.UserRepository;
import br.com.a3sitsolutions.services.UserService;
import br.com.a3sitsolutions.utils.SecurityUtils;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository repository;

    @Override
    public User create(User entity) {
        entity.setPassword(SecurityUtils.hashPassword(entity.getPassword()));
        return repository.create(entity);
    }

    @Override
    public Boolean delete(Long id) {
        User entityFinded = validated(repository.findById(id));
        return repository.deleteById(entityFinded.getId());
    }

    @Override
    public Collection<User> getAll(Page page) {
        PanacheQuery<User> all = repository.findAll();
        return all.page(page).list();
    }

    @Override
    public Collection<User> getAll(Page page, Sort sort) {
        PanacheQuery<User> all = repository.findAll(sort);
        return all.page(page).list();
    }

    @Override
    public User update(User entity) {
        return validated(repository.update(entity));
    }

    @Override
    public User findById(Long id) {
        return validated(repository.findById(id));
    }

    @Override
    public User findByEmail(String email) {
        return validated(repository.findByEmail(email));
    }

    private User validated(User entity) {
        if (entity != null) {
            return entity;
        }
        throw new ValidationException("User can't be find", HttpResponseStatus.NOT_FOUND);
    }
}
