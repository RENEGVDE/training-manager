package com.rusnac.clubrestapi.service;

import com.rusnac.clubrestapi.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface IUserService<User> {

    Collection<User> findAll();

    Optional<User> findById(Long id);

    User saveOrUpdate(User user);

    User findByEmail(String email);

    CompletableFuture<String> deleteById(Long id);
}
