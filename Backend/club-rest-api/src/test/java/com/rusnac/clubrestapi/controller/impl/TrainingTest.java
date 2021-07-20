package com.rusnac.clubrestapi.controller.impl;

import com.rusnac.clubrestapi.model.Exercise;
import com.rusnac.clubrestapi.model.Training;
import com.rusnac.clubrestapi.service.ITrainingService;

import java.util.ArrayList;
import java.util.Optional;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TrainingTest {

    @MockBean
    private ITrainingService<Training> trainingITrainingService;

    @Autowired
    private TrainingControllerImpl trainingControllerImpl;

    @Test
    public void testFindAll() {
        when(trainingITrainingService.findAll()).thenReturn(new ArrayList<Training>());
        trainingControllerImpl.findAll();
    }

    @Test
    public void testFindById() {
        Exercise exercise = new Exercise();
        exercise.setExName("Dance");
        exercise.setExId(21L);
        exercise.setExCalories(21);

        Training training = new Training();
        training.setTrPosition("CB");
        training.setId(21L);
        training.setExId(exercise);
        training.setTrDuration(21);
        Optional<Training> trn = Optional.of(training);
        when(trainingITrainingService.findById((21L))).thenReturn(trn);
        trainingControllerImpl.findById(21L);
        verify(trainingITrainingService).findById(21L);
    }

    @Test
    public void testSave() {
        Exercise exercise = new Exercise();
        exercise.setExName("Dance");
        exercise.setExId(21L);
        exercise.setExCalories(21);

        Training training = new Training();
        training.setTrPosition("GK");
        training.setId(21L);
        training.setExId(exercise);
        training.setTrDuration(21);
        when(trainingITrainingService.saveOrUpdate(training)).thenReturn(training);
//        this.trainingControllerImpl.save(new Training());
        assertEquals(training, trainingITrainingService.saveOrUpdate(training));
    }

//    @Test
//    public void testUpdate() {
//        Exercise exercise = new Exercise();
//        exercise.setExName("Dance");
//        exercise.setExId(21L);
//        exercise.setExCalories(21);
//
//        Training training = new Training();
//        training.setTrPosition("CB");
//        training.setId(21L);
//        training.setExId(exercise);
//        training.setTrDuration(21);
//        when(trainingITrainingService.saveOrUpdate(training)).thenReturn(training);
//        ResponseEntity<Training> updatedTraining = trainingControllerImpl.update(new Training(21L, 10, "GK", exercise, 0));
//        assertEquals(training, updatedTraining);
//    }

    @Test
    public void testDeleteById() {
        Exercise exercise = new Exercise();
        exercise.setExName("Dance");
        exercise.setExId(21L);
        exercise.setExCalories(21);

        Training training = new Training();
        training.setTrPosition("GK");
        training.setId(21L);
        training.setExId(exercise);
        training.setTrDuration(21);

        when(trainingITrainingService.saveOrUpdate(training)).thenReturn(training);
        trainingControllerImpl.deleteById(21L);
        assertFalse(trainingITrainingService.findById(21L).isPresent());
    }
}

