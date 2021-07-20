package com.rusnac.clubrestapi.service.impl;

import com.rusnac.clubrestapi.model.Role;
import com.rusnac.clubrestapi.repository.IRoleRepository;
import com.rusnac.clubrestapi.service.IService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class RoleServiceImpl implements IService<Role> {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role saveOrUpdate(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public CompletableFuture<String> deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            roleRepository.deleteById(id);
            jsonObject.put("message", "Role deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(jsonObject.toString());
    }

}
