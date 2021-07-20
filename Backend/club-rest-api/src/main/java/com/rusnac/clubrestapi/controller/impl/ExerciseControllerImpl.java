package com.rusnac.clubrestapi.controller.impl;

import com.rusnac.clubrestapi.controller.IExerciseController;
import com.rusnac.clubrestapi.model.Exercise;
import com.rusnac.clubrestapi.service.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/ex")
@CrossOrigin(origins="http://localhost:3000")
public class ExerciseControllerImpl implements IExerciseController<Exercise> {

    @Autowired
    private IExerciseService<Exercise> exerciseIExerciseService;

//    @Autowired
//    public ExerciseControllerImpl(IExerciseService<Exercise> exerciseIExerciseService) {
//        this.exerciseIExerciseService = exerciseIExerciseService;
//    }

    @Override
    public ResponseEntity<Collection<Exercise>> findAll() {
        return new ResponseEntity<>(exerciseIExerciseService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<Exercise>> findExName() {
        return new ResponseEntity<>(exerciseIExerciseService.findExName(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Exercise> findById(Long id) {
        return new ResponseEntity<>(exerciseIExerciseService.findById(id).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Exercise> save(Exercise exercise) {
        return new ResponseEntity<>(exerciseIExerciseService.saveOrUpdate(exercise), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Exercise> update(Exercise exercise) {
        return new ResponseEntity<>(exerciseIExerciseService.saveOrUpdate(exercise), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        return new ResponseEntity(exerciseIExerciseService.deleteById(id), HttpStatus.OK);
    }
}
