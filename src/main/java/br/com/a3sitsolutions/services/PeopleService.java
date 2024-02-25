package br.com.a3sitsolutions.services;

import br.com.a3sitsolutions.models.People;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import java.awt.print.Pageable;
import java.util.Collection;
import java.util.Set;

public interface PeopleService {

    People create(People people);

    Boolean delete(People people);

    Collection<People> getAll(Page page);

    Collection<People> getAll(Page page, Sort sort);

    People update(People people);

    People findById(Long id);

    People findByName(String name);

    People findByEmail(String email);

    People findByPhone(String phone);
}
