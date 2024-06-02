package com.capgemini.wsb.fitnesstracker.controller;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        User user = new User();
        when(userService.getUserById(anyLong())).thenReturn(user);

        String viewName = userController.getUserById(1L, mock(Model.class));
        assertEquals("user/view", viewName);
    }

    @Test
    void testSaveUser() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.saveUser(any(User.class))).thenReturn(new User());

        String viewName = userController.saveUser(new User(), bindingResult, mock(Model.class));
        assertEquals("redirect:/users", viewName);
    }
}
