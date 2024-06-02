package com.capgemini.wsb.fitnesstracker.service;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import com.capgemini.wsb.fitnesstracker.user.internal.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);
        assertEquals(user, result);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);
        assertEquals(user, result);
    }
}
