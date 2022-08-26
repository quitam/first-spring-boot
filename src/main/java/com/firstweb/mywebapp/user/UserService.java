package com.firstweb.mywebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUser(){
        return (List<User>) userRepository.findAll();

    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User getUser(Integer id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find user by ID "+id);
    }

    public void deleteUser(Integer id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0){
            throw new UserNotFoundException("Could not find user by ID "+id);
        }
        userRepository.deleteById(id);
    }
}
