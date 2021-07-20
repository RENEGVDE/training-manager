package com.rusnac.clubrestapi.repository;

import com.rusnac.clubrestapi.model.Exercise;
import com.rusnac.clubrestapi.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ITrainingRepository extends JpaRepository<Training, Long> {

    @Query(value = "SELECT SUM(training.tr_duration * exercise.ex_calories) FROM training JOIN exercise on training.ex_id = exercise.ex_id", nativeQuery=true)
    double getAllCalories();

}
