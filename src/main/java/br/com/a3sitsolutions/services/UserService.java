package br.com.a3sitsolutions.services;

import br.com.a3sitsolutions.models.User;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import java.util.Collection;

public interface UserService {
    User create(User user);

    Boolean delete(Long id);

    Collection<User> getAll(Page page);

    Collection<User> getAll(Page page, Sort sort);

    User update(User user);

    User findById(Long id);

    User findByEmail(String email);
}
