package com.capgemini.wsb.fitnesstracker.mapper;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    private final UserMapper userMapper = new UserMapperImpl();

    @Test
    void testToDto() {
        User user = new User();
        user.setId(1L);
        UserDto dto = userMapper.toDto(user);

        assertEquals(user.getId(), dto.getId());
    }

    @Test
    void testToEntity() {
        UserDto dto = new UserDto();
        dto.setId(1L);
        User user = userMapper.toEntity(dto);

        assertEquals(dto.getId(), user.getId());
    }
}
