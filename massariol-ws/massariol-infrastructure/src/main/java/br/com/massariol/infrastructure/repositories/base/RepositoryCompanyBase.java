package br.com.massariol.infrastructure.repositories.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface RepositoryCompanyBase<TEntity, ID extends Serializable> extends RepositoryBase<TEntity, ID> {
    Page<TEntity> findAllByCompanyId(Pageable pageable, String institutionId);

    Optional<TEntity> findByIdAndCompanyId(String id, String companyId);
}
