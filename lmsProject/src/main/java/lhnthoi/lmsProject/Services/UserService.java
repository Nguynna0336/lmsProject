package lhnthoi.lmsProject.Services;

import lhnthoi.lmsProject.DTOs.UserDTO;
import lhnthoi.lmsProject.Enums.UserRole;
import lhnthoi.lmsProject.Exceptions.DataNotFoundException;
import lhnthoi.lmsProject.Models.User;
import lhnthoi.lmsProject.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        if(userRepository.existsByUserName(userDTO.getUserName())) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        User newUser = User.builder()
                .userName(userDTO.getUserName())
                .fullName(userDTO.getFullName())
                .dateOfBirth(userDTO.getDateOfBirth())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .avatarUrl("")
                .userRole(UserRole.Student)
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public String login(String userName, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if(optionalUser.isEmpty()) {
            throw new DataNotFoundException("Invalid username or password");
        }
        User existingUser = optionalUser.get();
        if(!password.equals(existingUser.getPassword())) {
            throw new Exception("Wrong username or password");
//            throw new BadCredentialsException("Wrong username or password");
        }
        return "cai nay la token";
    }

    @Override
    public User editProfile(User user) throws DataNotFoundException {
        User existingUser = userRepository.findByUserName(user.getUserName())
                .orElseThrow(()-> new DataNotFoundException("Cannot find user with username: " + user.getUserName()));
        existingUser.setFullName(user.getFullName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setEmail(user.getEmail());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    @Override
    public User getUserById(int userId) throws DataNotFoundException {
        return userRepository.findByUserId(userId)
                .orElseThrow(()-> new DataNotFoundException("Cannot find user with id: " + userId));
    }

}
