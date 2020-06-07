package br.com.massariol.application.base;

import br.com.massariol.infrastructure.repositories.base.RepositoryBase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ApplicationServiceBaseImpl<T, ID extends Serializable> implements ApplicationServiceBase<T, ID> {

    private final RepositoryBase<T, ID> repositoryBase;

    protected ApplicationServiceBaseImpl(RepositoryBase<T, ID> repositoryBase) {
        this.repositoryBase = repositoryBase;
    }

    public T getById(ID id) {
        return this.repositoryBase.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public void delete(ID id) {
        this.repositoryBase.deleteById(id);
    }

    public Page<T> findAll(Pageable pageable) {
        return this.repositoryBase.findAll(pageable);
    }

    protected void copyNonNullProperties(Object source, Object destination) {
        BeanUtils.copyProperties(source, destination, getNullPropertyNames(source));
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set emptyNames = new HashSet();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return (String[]) emptyNames.toArray(result);
    }
}
