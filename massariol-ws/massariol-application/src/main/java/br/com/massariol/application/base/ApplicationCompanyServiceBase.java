package br.com.massariol.application.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicationCompanyServiceBase<T,ID>  extends  ApplicationServiceBase<T,ID> {

    Page<T> findAllByCompanyId(Pageable pageable, String institutionId);
}
