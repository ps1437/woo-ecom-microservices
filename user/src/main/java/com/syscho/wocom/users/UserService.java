package com.syscho.wocom.users;

import com.syscho.wocom.users.dto.UserDTO;
import com.syscho.wocom.users.repo.UserEntity;
import com.syscho.wocom.users.repo.UserRepository;
import com.syscho.wocom.users.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper; // Assume you have a mapper

    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return userMapper.toUserDTOList(users);
    }

    public UserDTO getUserById(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        return userMapper.toUserDTO(user);
    }

    public void createUser(UserDTO userDTO) {
        UserEntity newUser = userMapper.toUserEntity(userDTO);
        userRepository.save(newUser);
    }

    public void updateUser(Long userId, UserDTO userDTO) {
        UserEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // Update user fields based on userDTO
        existingUser.setUsername(userDTO.getUsername());

        // Additional updates as needed

        userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
