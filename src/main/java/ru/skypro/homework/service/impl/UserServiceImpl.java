package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto updateUser(UserDto userDto, String username) {
        User user = userRepository.findUserByEmail(username).orElseThrow(RuntimeException::new); // обработать исключение!
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        User response = userRepository.save(user);

        return userMapper.userToUserDto(response);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findUserByEmail(username).orElseThrow(RuntimeException::new); // обработать исключение!

    }

    @Override
    public UserDto getUserDtoByUsername(String username) {
        User response = getUser(username);
        return userMapper.INSTANCE.userToUserDto(response);
    }

    @Override
    public void checkIfUserHasPermissionToAlter(Authentication authentication, String username) {
        boolean matchUser = authentication.getName().equals(username);
        boolean userIsAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().contains(Role.ADMIN.name()));

        if (!(userIsAdmin || matchUser)) {
            throw new RuntimeException(); // обработать исключение!
        }
    }

}
