package com.rusnac.clubrestapi.service;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface IService<T> {

    Collection<T> findAll();

    Optional<T> findById(Long id);

    T saveOrUpdate(T t);

    CompletableFuture<String> deleteById(Long id);
}
