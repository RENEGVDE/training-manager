package com.rusnac.clubrestapi.service;

import com.rusnac.clubrestapi.model.Training;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ITrainingService<Training> {
    //@Async
    Collection<Training> findAll();

    //@Async
    Optional<Training> findById(Long id);

    //@Async
    Training saveOrUpdate(Training training);

    double getAllCalories();

    @Async
    CompletableFuture<String> deleteById(Long id);
}
