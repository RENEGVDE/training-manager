package com.rusnac.clubrestapi.service.impl;

import com.rusnac.clubrestapi.model.Role;
import com.rusnac.clubrestapi.model.User;
import com.rusnac.clubrestapi.repository.IUserRepository;
import com.rusnac.clubrestapi.service.IService;
import com.rusnac.clubrestapi.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserUnitTest {

    @Autowired
    private IUserService service;

//    @Autowired
//    private IService<Role> roleService;

    @MockBean
    private IUserRepository repository;

    @Test
    public void findAllTest(){
        when(repository.findAll())
                .thenReturn(Stream.of(new User(21L, "Statham", "mail@test.com", "CB", new BCryptPasswordEncoder().encode("212121"), new Role(1L, "admin")), new User(22L, "Jason", "email@test.com", "ST", new BCryptPasswordEncoder().encode("212121"), new Role(1L, "admin")))
                    .collect(Collectors.toList()));
        assertEquals(2, service.findAll().size());
    }

    @Test
    public void findByIdTest(){
        Long id = 21L;
        String email = "testemail@email.com";

        when(repository.findById(id))
                .thenReturn(Optional.of(new User(21L, "Statham", "testemail@email.com", "CB", new BCryptPasswordEncoder().encode("212121"), new Role(1L, "admin"))));
        assertTrue(service.findById(id).isPresent());
    }

    @Test
    public void saveUserTest(){
        User user = new User(21L, "Statham", "testemail@email.com", "CB", new BCryptPasswordEncoder().encode("212121"), new Role(1L, "admin"));

        when(repository.saveAndFlush(user)).thenReturn(user);
        assertEquals(user, service.saveOrUpdate(user));
    }

    @Test
    public void deleteUserTest(){
        User user = new User(21L, "Statham", "testemail@email.com", "CB", new BCryptPasswordEncoder().encode("212121"), new Role(1L, "admin"));

        when(repository.saveAndFlush(user)).thenReturn(user);
        service.deleteById(user.getId());
        assertFalse(service.findById(user.getId()).isPresent());
    }


}
