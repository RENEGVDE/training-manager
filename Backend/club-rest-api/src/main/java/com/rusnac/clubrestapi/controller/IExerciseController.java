package com.rusnac.clubrestapi.controller;

import com.rusnac.clubrestapi.model.Exercise;
import com.rusnac.clubrestapi.model.Training;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collection;
import java.util.List;

public interface IExerciseController<Exercise> {

    @GetMapping
    ResponseEntity<Collection<Exercise>> findAll();
    @GetMapping("/exnames")
    ResponseEntity<Collection<Exercise>> findExName();
    @GetMapping("{id}")
    ResponseEntity<Exercise> findById(Long id);
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Exercise> save(Exercise exercise);
    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Exercise> update(Exercise exercise);
    @DeleteMapping("{id}")
    ResponseEntity<String> deleteById(Long id);
}
