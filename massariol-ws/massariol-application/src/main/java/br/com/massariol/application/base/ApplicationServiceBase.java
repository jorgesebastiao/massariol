package br.com.massariol.application.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicationServiceBase<T,ID> {

    T getById(ID id);

    void delete(ID id);

    Page<T> findAll(Pageable pageable);

}
