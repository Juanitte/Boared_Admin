package com.juanite.model.DAO;

import java.util.Set;

public interface DAO<T> extends AutoCloseable {

    Set<T> findAll() throws Exception;
    T find(String param) throws Exception;
    T save(T entity) throws Exception;
    void delete(T entity) throws Exception;

}
