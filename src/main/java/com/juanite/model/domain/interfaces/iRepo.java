package com.juanite.model.domain.interfaces;

public interface iRepo<T> {

    boolean add(T entity);
    boolean contains(T entity);
    boolean remove(T entity);

}
