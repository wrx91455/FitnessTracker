package com.capgemini.wsb.fitnesstracker.repository;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        userRepository.save(user);
    }

    @Test
    void testFindById() {
        Optional<User> result = userRepository.findById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void testSave() {
        User newUser = new User();
        newUser.setId(2L);
        userRepository.save(newUser);

        Optional<User> result = userRepository.findById(2L);
        assertTrue(result.isPresent());
    }
}
