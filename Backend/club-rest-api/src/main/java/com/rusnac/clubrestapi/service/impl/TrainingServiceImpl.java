package com.rusnac.clubrestapi.service.impl;

import com.rusnac.clubrestapi.model.Training;
import com.rusnac.clubrestapi.repository.ITrainingRepository;
import com.rusnac.clubrestapi.service.IService;
import com.rusnac.clubrestapi.service.ITrainingService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TrainingServiceImpl implements ITrainingService<Training> {

    @Autowired
    private ITrainingRepository trainingRepository;

    //@Async
    @Override
    public Collection<Training> findAll() {
        return trainingRepository.findAll();
    }

    //@Async
    @Override
    public Optional<Training> findById(Long id) {
        return trainingRepository.findById(id);
    }

    //@Async
    @Override
    public Training saveOrUpdate(Training training) {
        return trainingRepository.saveAndFlush(training);
    }

    @Override
    public double getAllCalories(){
        return trainingRepository.getAllCalories();
    }

    @Async
    @Override
    public CompletableFuture<String> deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try{
            trainingRepository.deleteById(id);
            jsonObject.put("message", "Exercise deleted successfully");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(jsonObject.toString());
    }
}
