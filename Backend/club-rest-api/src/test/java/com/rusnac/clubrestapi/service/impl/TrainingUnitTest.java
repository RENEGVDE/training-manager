package com.rusnac.clubrestapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rusnac.clubrestapi.model.Exercise;
import com.rusnac.clubrestapi.model.Training;
import com.rusnac.clubrestapi.repository.IExerciseRepository;
import com.rusnac.clubrestapi.repository.ITrainingRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rusnac.clubrestapi.service.ITrainingService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
public class TrainingUnitTest {

    @MockBean
    private ITrainingRepository trainingRepository;

    @Autowired
    private ITrainingService trainingService;


    @Test
    public void testFindAll() {
        Exercise exercise = new Exercise(21L, "Skipping", 2.11);
        Training training1 = new Training(21L, 10, "GK", exercise, 0);
        Training training2 = new Training(22L, 11, "CB", exercise, 0);

        when(trainingRepository.findAll())
                .thenReturn(List.of(training1, training2));
        assertEquals(2, trainingService.findAll().size());
    }

    @Test
    public void testFindById() {
        Exercise exercise = new Exercise();
        exercise.setExName("Dance");
        exercise.setExId(21L);
        exercise.setExCalories(21);

        Training training = new Training();
        training.setTrPosition("CB");
        training.setExId(exercise);
        training.setId(21L);
        training.setTrDuration(21);

        when(trainingRepository.findById(21L)).thenReturn(Optional.of(training));
        assertTrue(trainingService.findById(21L).isPresent());
    }

    @Test
    public void testSaveOrUpdate() {
        Exercise exercise = new Exercise(21L, "Skipping", 2.11);
        Training training = new Training(21L, 10, "GK", exercise, 0);

        when(trainingRepository.saveAndFlush(training)).thenReturn(training);
        assertEquals(training, trainingService.saveOrUpdate(training));
    }

    @Test
    public void testGetAllCalories() {
        Exercise exercise = new Exercise(21L, "Skipping", 2.11);
        Training training = new Training(21L, 10, "GK", exercise, 0);

        double dCalories = training.getTrDuration() * exercise.getExCalories();

        when(trainingRepository.getAllCalories()).thenReturn(Math.round(dCalories * 100.0)/ 100.0);
        assertEquals(training.getTotCalories(), trainingRepository.getAllCalories());

//        when(trainingRepository.getAllCalories()).thenReturn(10.0);
//        assertEquals(10.0, trainingService.getAllCalories());
//        verify(trainingRepository).getAllCalories();
    }

//    @Test
//    public void testDeleteById() {
//        Exercise exercise = new Exercise(21L, "Skipping", 2.11);
//        Training training = new Training(21L, 10, "GK", exercise, 0);
//
//
//        trainingService.deleteById(21L);
//        verify(trainingRepository).deleteById(21L);
////        assertTrue(trainingService.findAll().isEmpty());
//    }
}

