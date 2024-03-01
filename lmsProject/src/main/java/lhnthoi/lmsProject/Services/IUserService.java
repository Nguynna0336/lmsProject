package lhnthoi.lmsProject.Services;

import lhnthoi.lmsProject.DTOs.UserDTO;
import lhnthoi.lmsProject.Exceptions.DataNotFoundException;
import lhnthoi.lmsProject.Models.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;
    String login(String userName, String password) throws Exception;
    User editProfile(User user) throws DataNotFoundException;
    User getUserById(int userId) throws DataNotFoundException;
    void deleteUser(int userId) throws DataNotFoundException;
}
