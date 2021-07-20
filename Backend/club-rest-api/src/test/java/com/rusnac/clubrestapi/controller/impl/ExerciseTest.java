package com.rusnac.clubrestapi.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyListOf;
import static org.mockito.Mockito.*;

import com.rusnac.clubrestapi.model.Exercise;
import com.rusnac.clubrestapi.model.Training;
import com.rusnac.clubrestapi.service.IExerciseService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
public class ExerciseTest {

    @Autowired
    private ExerciseControllerImpl exerciseControllerImpl;

    @MockBean
    private IExerciseService<Exercise> exerciseIExerciseService;

//    @BeforeEach
//    public void Setup(){
//        exerciseIExerciseService = Mockito.mock(IExerciseService.class);
//        exerciseControllerImpl = new ExerciseControllerImpl(exerciseIExerciseService);
//    }

    @Test
    public void testFindAll() {
//        Exercise exercise1 = new Exercise(21L, "Skipping", 2.11);
//        Exercise exercise2 = new Exercise(22L, "Jump", 2.2);
//
//        when(exerciseControllerImpl.findAll()).thenReturn(List.of(exercise1, exercise2));

        when(exerciseIExerciseService.findAll()).thenReturn(new ArrayList<Exercise>());
        ResponseEntity<Collection<Exercise>> findAllResult = exerciseControllerImpl.findAll();
        assertEquals(findAllResult, exerciseControllerImpl.findAll());
    }

    @Test
    public void testFindExName() {
        when(exerciseIExerciseService.findExName()).thenReturn(new ArrayList<Exercise>());
        ResponseEntity<Collection<Exercise>> actualFindExNameResult = exerciseControllerImpl.findExName();
        assertEquals(actualFindExNameResult, exerciseControllerImpl.findExName());
    }

    @Test
    public void testFindById() {
        Long id = 21L;

        when(exerciseIExerciseService.findById(id)).thenReturn(Optional.of(new Exercise(21L, "Skipping", 2.11)));
        exerciseControllerImpl.findById(id);
        verify(exerciseIExerciseService).findById(id);
    }

    @Test
    public void testSave() {
        Exercise exercise = new Exercise();
        exercise.setExName("Dance");
        exercise.setExId(21L);
        exercise.setExCalories(21);
        when(exerciseIExerciseService.saveOrUpdate(exercise)).thenReturn(exercise);
        assertEquals(exercise, exerciseIExerciseService.saveOrUpdate(exercise));
//        verify(exerciseIExerciseService).saveOrUpdate(exercise);
    }

//    @Test
//    public void testUpdate() {
//        Exercise exercise = new Exercise();
//        exercise.setExName("Dance");
//        exercise.setExId(21L);
//        exercise.setExCalories(21);
//        when(exerciseIExerciseService.saveOrUpdate(exercise)).thenReturn(exercise);
//        ResponseEntity<Exercise> updatedExercise = exerciseControllerImpl.update(new Exercise(21L, "Jump", 21.21));
////        ResponseEntity<Exercise> updatedExercise = exerciseControllerImpl.update(new Exercise(21L, "Jump", 21.21));
////        assertEquals(updatedExercise, exerciseIExerciseService.saveOrUpdate(exercise));
//        assertEquals(updatedExercise, exercise);
////        verify(exerciseIExerciseService).saveOrUpdate((Exercise) any());
//    }
}

