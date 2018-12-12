package com.javacourse.model.dao;

import java.util.List;

/**
 * @author Yuliia Tesliuk
 */
public interface GenericDao<T> extends AutoCloseable{

    void create(T entity);
    T findByID(long id);
    List<T> findAll();
    void update(T entity);
    void delete(long id);
    void close();
}
