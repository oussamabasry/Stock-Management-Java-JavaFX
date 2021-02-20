package com.stock;

import java.util.List;

public interface IDao<T> {
    void add(T obj);

    void delete(long id);

    void update(T obj);

    T getOne(long id);

    List<T> getAll();
}
