package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.MessageResponse;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public List<User> getUser(){
        return userRepo.findAll();
    }

    public MessageResponse getUserByEmail(String email){
        User user = userRepo.findByEmail(email);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setUser(user);
        if (user == null){
            messageResponse.setId(email);
            messageResponse.setMessage("User Doesnt exist with mail: "+email);

            return messageResponse;
        }
        else {

            messageResponse.setId(email);
            messageResponse.setMessage("User Found with User Name: "+email);
            return messageResponse;
        }
    }

    public MessageResponse registerUser(User user){

        User findUserByemail = userRepo.findByEmail(user.getEmail());
        MessageResponse messageResponse = new MessageResponse();
        if(findUserByemail != null) {
            messageResponse.setId(findUserByemail.getEmail());
            messageResponse.setMessage("User Already Exists with Mail :"+findUserByemail.getEmail());

            return messageResponse;
        }
        else {
            String Pass = user.getPassword();
            user.setPassword(encoder.encode(Pass));

            Date currentDate = new Date();
            user.setCurrentDate(currentDate.toString());
            user.setUpdatedDate(currentDate.toString());

            Boolean adminValue = user.isAdmin();
            if(adminValue == null){
                user.setAdmin(false);
            }
            else if(adminValue == true){
                user.setAdmin(true);
            }
            else {
                user.setAdmin(false);
            }
            userRepo.save(user);



            messageResponse.setUser(user);
            messageResponse.setId(user.get_id());
            messageResponse.setMessage("User Successfully Registered");

            return messageResponse;
        }

    }

    public MessageResponse getUserByEmailAndPass(User user){
        User userMail = userRepo.findByEmail(user.getEmail());
        MessageResponse messageResponse = new MessageResponse();

        if(userMail == null) {
            messageResponse.setId(null);
            messageResponse.setMessage("Couldnt Find User with Mail :"+user.getEmail());
            return messageResponse;
        }

        String Pass = user.getPassword();
        String currentEmpPass = encoder.encode(Pass);
        String oldEmpPass =  userMail.getPassword();

        if(encoder.matches(user.getPassword(), userMail.getPassword())){

            String token = jwtUtil.generateToken(user.getEmail());
            messageResponse.setUser(userMail);
            messageResponse.setId(user.getEmail());
            messageResponse.setToken(token);
            messageResponse.setMessage("User Credentials Matched with Mail "+user.getEmail()+" and Password ");

            return messageResponse;
        }
        else {

            messageResponse.setId(user.getEmail());
            messageResponse.setMessage("User Credentials Didnt Match");
            messageResponse.setToken("Invalid User Credentials");
            return messageResponse;
        }
    }

    public MessageResponse updateUser(User existingUser,User user){
        Date currentDate = new Date();

        existingUser.setEmail(user.getEmail());
        Boolean adminValue = user.isAdmin();
        if(adminValue == null){
            user.setAdmin(false);
        }
        else if(adminValue == true){
            user.setAdmin(true);
        }
        else {
            user.setAdmin(false);
        }
        System.out.println(adminValue);
        System.out.println(user);
        existingUser.setAdmin(adminValue);
        existingUser.setUpdatedDate(currentDate.toString());

        userRepo.save(existingUser);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setUser(existingUser);
        messageResponse.setId(existingUser.get_id());
        messageResponse.setMessage("User Successfully Updated");

        return messageResponse;

    }

    public MessageResponse updateUserPassword(User existingUser,User user){
        Date currentDate = new Date();
        existingUser.setUpdatedDate(currentDate.toString());

        existingUser.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(existingUser);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setUser(existingUser);
        messageResponse.setId(existingUser.get_id());
        messageResponse.setMessage("User Password Successfully Updated");

        return messageResponse;

    }


    public MessageResponse deleteUser(String id) {
        userRepo.deleteById(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setId(id);
        messageResponse.setMessage("User Successfully Deleted");

        return messageResponse;


    }


}
