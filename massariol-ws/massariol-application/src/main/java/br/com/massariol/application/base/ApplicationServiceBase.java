package br.com.massariol.application.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationServiceBase<T,ID> {

    T getById(ID id);

    void delete(ID id);

    Page<T> findAll(Pageable pageable);

}
