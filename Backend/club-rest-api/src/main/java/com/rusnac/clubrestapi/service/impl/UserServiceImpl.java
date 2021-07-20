package com.rusnac.clubrestapi.service.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.rusnac.clubrestapi.model.User;
import com.rusnac.clubrestapi.repository.IUserRepository;
import com.rusnac.clubrestapi.service.IUserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService<User> {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public  User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public CompletableFuture<String> deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            userRepository.deleteById(id);
            jsonObject.put("message", "User deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(jsonObject.toString());
    }

}