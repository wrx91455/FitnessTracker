package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);

    List<UserDto> findAllUsers();
}
