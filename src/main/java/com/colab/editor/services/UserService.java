package com.colab.editor.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.colab.editor.content.User;
import com.colab.editor.repository.UserRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean saveUser(User usr) {
        try {
            usr.setPassword(encoder.encode(usr.getPassword()));
            userRepository.save(usr);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public boolean saveUserUnsafe(User user) {
        try {
            userRepository.save(user);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public boolean deleteUser(String uid) {
        try {
            userRepository.deleteById(uid);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    } 

    public boolean deleteUser(User usr) {
        try {
            userRepository.delete(usr);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByName(String name) {
        return userRepository.findByUsername(name);
    }




}
