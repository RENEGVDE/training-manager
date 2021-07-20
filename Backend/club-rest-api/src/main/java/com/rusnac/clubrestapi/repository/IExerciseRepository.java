package com.rusnac.clubrestapi.repository;

import com.rusnac.clubrestapi.model.Exercise;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

@Repository
public interface IExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query(value = "select e.ex_id, e.ex_name, e.ex_calories from exercise e", nativeQuery=true)
    Collection<Exercise> findExNames();

//    @Query(value = "SELECT e.ex_name FROM Exercise e", nativeQuery = true)
//    Collection<Exercise> findExNames();
}
