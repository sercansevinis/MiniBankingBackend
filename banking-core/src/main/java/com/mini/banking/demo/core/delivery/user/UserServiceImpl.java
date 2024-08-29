package com.mini.banking.demo.core.delivery.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserBackEnd userBackEnd;

    private final UserDtoConverter userDtoConverter;

    public UserDto getById(int id) {
        Optional<User> userOptional = userBackEnd.getById(id);
        return getUserDto(userOptional);
    }

    @Override
    @Transactional
    public void create(UserDto dto) {
        User data = userDtoConverter.convertDtoToData(dto);

        userBackEnd.save(data);
    }

    @Override
    public void update(UserDto dto) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
    }

    private UserDto getUserDto(Optional<User> userOptional) {
        if (!userOptional.isPresent())
            return null;

        User user = userOptional.get();

        UserDto userDto = userDtoConverter.convertDataToDto(user);

        return userDto;
    }

    @Override
    public UserDto findByUsername(String username) {
        Optional<User> userOptional = userBackEnd.getByUsername(username);
        return getUserDto(userOptional);
    }

}
