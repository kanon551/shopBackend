package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.MessageResponse;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    private static final String AUTH_MECHANISM = "bearerAuth";

    @GetMapping("/user")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find User ", description = "Get User", tags = { "Users" })
    public List<User> fetchUserById(){
        return userService.getUser();
    }

    @GetMapping("/user/{email}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find user By Name", description = "Find Records of user By Name", tags = { "Users" })
    public MessageResponse fetchUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping("/register")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Register", description = "Register Users", tags = { "Users" })
    public MessageResponse register(@Valid @RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find user By email and Password", description = "Find Records of user By email and Password", tags = { "Users" })
    public MessageResponse fetchUserByEmailAndPass(@Valid @RequestBody User user){
        return userService.getUserByEmailAndPass(user);
    }

    @PutMapping("/user/{id}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Update User", description = "Update/Modify User Record", tags = { "Users" })
    public MessageResponse editUser(@RequestBody User user,@PathVariable String id){
        User existingUser = userRepo.findById(id).orElse(null);
        return userService.updateUser(existingUser,user);
    }

    @PutMapping("/userPassword/{id}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Update User Password", description = "Update/Modify User Password Record", tags = { "Users" })
    public MessageResponse editUserPassword(@RequestBody User user,@PathVariable String id){
        User existingUser = userRepo.findById(id).orElse(null);
        return userService.updateUserPassword(existingUser,user);
    }


    @DeleteMapping("/user/{id}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Delete User", description = "Delete User By ID", tags = { "Users" })
    public MessageResponse removeUser(@PathVariable String id ) {
        return userService.deleteUser(id);
    }




}
