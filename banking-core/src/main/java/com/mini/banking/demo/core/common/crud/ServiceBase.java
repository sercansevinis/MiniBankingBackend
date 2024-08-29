package com.mini.banking.demo.core.common.crud;

public interface ServiceBase<T> {
    T getById(int id);

    void create(T dto);

    void update(T dto);

    void delete(int id);
}
