package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getAllUsers() throws Exception {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setEmail("john.doe@example.com");

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setBirthDate(LocalDate.of(1990, 1, 1));
        userDto.setEmail("john.doe@example.com");

        List<User> users = Arrays.asList(user);
        List<UserDto> userDtos = Arrays.asList(userDto);

        when(userService.findAllUsers()).thenReturn(users);
        when(userMapper.toDto(user)).thenReturn(userDto);

        mockMvc.perform(get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    // Dodaj testy dla pozostałych metod...
}
