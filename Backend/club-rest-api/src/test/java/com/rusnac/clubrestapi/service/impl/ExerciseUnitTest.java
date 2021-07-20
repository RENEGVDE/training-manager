package com.rusnac.clubrestapi.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rusnac.clubrestapi.model.Exercise;
import com.rusnac.clubrestapi.repository.IExerciseRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.rusnac.clubrestapi.service.IExerciseService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
public class ExerciseUnitTest {
    @Autowired
    private IExerciseService exerciseService;

    @MockBean
    private IExerciseRepository exerciseRepository;

    @Test
    public void testFindAll() {
        Exercise exercise = new Exercise(21L, "Skipping", 2.11);
        Exercise exercise2 = new Exercise(22L, "Jump", 2.2);

        when(exerciseRepository.findAll()).thenReturn(List.of(exercise, exercise2));
        assertEquals(2, exerciseService.findAll().size());
    }


    @Test
    public void testFindById() {
        Exercise exercise = new Exercise();
        exercise.setExName("Dance");
        exercise.setExId(21L);
        exercise.setExCalories(21);

        when(exerciseRepository.findById(21L)).thenReturn(Optional.of(exercise));
        assertTrue(exerciseService.findById(21L).isPresent());
    }

    @Test
    public void testSaveOrUpdate() {
        Exercise exercise = new Exercise();
        exercise.setExName("Dance");
        exercise.setExId(21L);
        exercise.setExCalories(21);
        when(exerciseRepository.saveAndFlush(exercise)).thenReturn(exercise);
        assertEquals(exercise, exerciseService.saveOrUpdate(exercise));
    }

    @Test
    public void testDeleteById() {
        Exercise exercise = new Exercise(21L, "Skipping", 2.11);

        exerciseService.deleteById(21L);
        verify(exerciseRepository).deleteById(21L);
    }
}

