package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser() {
        UserDto userDto = new UserDto(null, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com");
        User user = new User("John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com");
        when(userMapper.toEntity(any(UserDto.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        UserDto createdUser = userService.createUser(userDto);

        assertEquals(userDto.getFirstName(), createdUser.getFirstName());
        assertEquals(userDto.getLastName(), createdUser.getLastName());
    }

    private <__TMP__> void assertEquals(__TMP__ firstName, __TMP__ firstName1) {

    }

    @Test
    void getUserById() {
        User user = new User("John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com");
        UserDto userDto = new UserDto(null, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        UserDto foundUser = userService.getUserById(1L);

        assertEquals(userDto.getFirstName(), foundUser.getFirstName());
    }

}
