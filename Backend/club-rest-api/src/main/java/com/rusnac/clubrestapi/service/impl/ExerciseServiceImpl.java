package com.rusnac.clubrestapi.service.impl;

import com.rusnac.clubrestapi.model.Exercise;
import com.rusnac.clubrestapi.repository.IExerciseRepository;
import com.rusnac.clubrestapi.service.IExerciseService;
import com.rusnac.clubrestapi.service.IService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ExerciseServiceImpl implements IExerciseService<Exercise> {

    @Autowired
    private IExerciseRepository exerciseRepository;

    @Override
    public Collection<Exercise> findAll(){
        return exerciseRepository.findAll();
    }

    @Override
    public Collection<Exercise> findExName(){
        return exerciseRepository.findExNames();
    }

    @Override
    public Optional<Exercise> findById(Long id){
        return exerciseRepository.findById(id);
    }

    @Override
    public Exercise saveOrUpdate(Exercise exercise){
        return exerciseRepository.saveAndFlush(exercise);
    }

    @Override
    public CompletableFuture<String> deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            exerciseRepository.deleteById(id);
            jsonObject.put("message", "Calories deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(jsonObject.toString());
    }
}
