package br.com.a3sitsolutions.repositories;

import br.com.a3sitsolutions.models.People;
import br.com.a3sitsolutions.models.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PeopleRepository implements PanacheRepository<People> {

    @PersistenceContext
    EntityManager em;


    @Transactional
    public People create(People people) {
        em.persist(people);
        em.flush();
        return people;
    }

    public People findByName(String name) {
        return find("name", name).firstResult();
    }

    public People findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public People findByPhone(String phone) {
        return find("phone", phone).firstResult();
    }

    public List<People> findAllByUserAndPage(User user, Page page) {
        return find("user", user).page(page).list();
    }

    public List<People> findAllByUserAndPageAndSort(User user, Page page, Sort sort) {
        return find("user", sort, user).page(page).list();
    }

    @Transactional
    public People update(People people) {

        People pp = em.find(People.class, people.getId());

        if (pp != null) {
            pp.setName(people.getName());
            pp.setEmail(people.getEmail());
            pp.setPhone(people.getPhone());
        }

        return pp;
    }
}
