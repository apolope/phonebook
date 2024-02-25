package br.com.a3sitsolutions.repositories;

import br.com.a3sitsolutions.models.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public User create(User user) {
        em.persist(user);
        em.flush();
        return user;
    }

    public User findByEmail(String email) {
        return find("email", email).firstResult();
    }

    @Transactional
    public User update(User user) {
        em.merge(user);
        return user;
    }

}
