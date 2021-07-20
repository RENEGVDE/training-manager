package com.rusnac.clubrestapi.controller.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusnac.clubrestapi.config.JwtTokenProvider;
import com.rusnac.clubrestapi.model.Role;
import com.rusnac.clubrestapi.model.User;
import com.rusnac.clubrestapi.service.IUserService;

import java.util.ArrayList;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class UserIntegrationTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private IUserService<User> iUserService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserControllerImpl userControllerImpl;

    @Test
    public void testAuthenticate() throws Exception {
        when(this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("jane.doe@example.org", "212121")))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        User user = new User();
        user.setPosition("Position");
        user.setEmail("jane.doe@example.org");
        user.setPassword("212121");
        user.setRole(new Role(1L, "admin"));
        user.setId(21L);
        user.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userControllerImpl)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

