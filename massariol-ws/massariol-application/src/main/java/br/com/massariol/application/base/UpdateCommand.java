package br.com.massariol.application.base;

public interface UpdateCommand <T>{

    T getId();

    void setId(T id);
}
