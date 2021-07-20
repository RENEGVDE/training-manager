package com.rusnac.clubrestapi.service;

import com.rusnac.clubrestapi.model.Exercise;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface IExerciseService<Exercise> {

    Collection<Exercise> findAll();

    Collection<Exercise> findExName();

    Optional<Exercise> findById(Long id);

    Exercise saveOrUpdate(Exercise exercise);

    CompletableFuture<String> deleteById(Long id);
}
