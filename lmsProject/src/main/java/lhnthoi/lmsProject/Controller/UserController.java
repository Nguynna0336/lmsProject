package lhnthoi.lmsProject.Controller;

import lhnthoi.lmsProject.DTOs.UserDTO;
import lhnthoi.lmsProject.DTOs.UserLoginDTO;
import lhnthoi.lmsProject.Enums.FileUploadDestination;
import lhnthoi.lmsProject.Components.FileFunction;
import lhnthoi.lmsProject.Models.User;
import lhnthoi.lmsProject.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FileFunction fileFunction;
    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        try {
            if(result.hasErrors()) {
                List<String> errorsMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorsMessages);
            }
            if(!userDTO.getPassword().equals(userDTO.getRetypePassword())) {
                return ResponseEntity.badRequest().body("Retype password does not match");
            }
            User user = userService.createUser(userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login (@Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            String token = userService.login(userLoginDTO.getUserName(), userLoginDTO.getPassword());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("")
    public ResponseEntity<?> editProfile(@Valid @RequestBody UserDTO userDTO) {
        try {
            User editedUser = User.builder()
                    .userName(userDTO.getUserName())
                    .fullName(userDTO.getFullName())
                    .dateOfBirth(userDTO.getDateOfBirth())
                    .phoneNumber(userDTO.getPhoneNumber())
                    .password(userDTO.getPassword())
                    .email(userDTO.getEmail())
                    .build();
            return ResponseEntity.ok(userService.editProfile(editedUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadAvatar(@PathVariable("id") int userId, @RequestParam("files") MultipartFile file) {
        try {
            User existingUser = userService.getUserById(userId);
            List<String> allowedType = Arrays.asList("jpg", "png", "jpeg");
            List<MultipartFile> avatar = Collections.singletonList(file);
            String check = fileFunction.checkRequire(avatar,allowedType,10L,1);
            if(!check.equals("accepted")) {
                return ResponseEntity.badRequest().body(check);
            }
            String fileUrl = fileFunction.storeFile(file, String.valueOf(FileUploadDestination.AVATAR));
            if(!existingUser.getAvatarUrl().isEmpty()){
                fileFunction.deleteFile(existingUser.getAvatarUrl());
            }
            existingUser.setAvatarUrl(fileUrl);
            userService.editProfile(existingUser);
            return ResponseEntity.ok("Avatar has been saved");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
