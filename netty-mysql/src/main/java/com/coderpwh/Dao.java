package com.coderpwh;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public interface Dao<T> {

    List<T> find();

    T findById(Long id);

    ListenableFuture<T> findByIdAsync(Long id);

    T save(T t);

    boolean removeById(Long id);

}
