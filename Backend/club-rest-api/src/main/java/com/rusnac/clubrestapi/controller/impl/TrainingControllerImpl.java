package com.rusnac.clubrestapi.controller.impl;

import com.rusnac.clubrestapi.controller.ITrainingController;
import com.rusnac.clubrestapi.model.Training;
import com.rusnac.clubrestapi.service.IService;
import com.rusnac.clubrestapi.service.ITrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/exercise")
@CrossOrigin(origins="http://localhost:3000")
public class TrainingControllerImpl implements ITrainingController<Training> {

    @Autowired
    private ITrainingService<Training> trainingIService;

    @Override
    public ResponseEntity<Collection<Training>> findAll() {
        return new ResponseEntity<>(trainingIService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Training> findById(Long id) {
        return new ResponseEntity<>(trainingIService.findById(id).get(), HttpStatus.OK);
    }

    @Override
    public  ResponseEntity<String> getAllCalories(){
        return new ResponseEntity(trainingIService.getAllCalories(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Training> save(Training training) {
        return new ResponseEntity<>(trainingIService.saveOrUpdate(training), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Training> update(Training training) {
        return new ResponseEntity<>(trainingIService.saveOrUpdate(training), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        return new ResponseEntity(trainingIService.deleteById(id), HttpStatus.OK);
    }

//    @GetMapping("/exnames")
//    public  ResponseEntity<Set<String>> findAllExNames() {
//        return new ResponseEntity<>(new TreeSet<>(Arrays.asList("Skipping", "Dumbbell squat", "Lunges", "Push-ups", "Lateral raise", "Burpees", "Bicep curl", "Plank")), HttpStatus.OK);
//    }
}
