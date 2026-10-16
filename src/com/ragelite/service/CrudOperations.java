package com.ragelite.service;

// Interface — defines contract for all CRUD-capable services
public interface CrudOperations<T> {
    void add(T item) throws Exception;
    void update(T item) throws Exception;
    void delete(int id) throws Exception;
    T getById(int id) throws Exception;
}
