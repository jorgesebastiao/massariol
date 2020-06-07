package br.com.massariol.infrastructure.repositories.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface RepositoryBase<TEntity, ID extends Serializable> extends JpaRepository<TEntity, ID> , JpaSpecificationExecutor<TEntity> {

    Page<TEntity> findAll(Pageable pageable);

}
