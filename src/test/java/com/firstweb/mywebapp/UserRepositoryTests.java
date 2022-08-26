package com.firstweb.mywebapp;

import com.firstweb.mywebapp.user.User;
import com.firstweb.mywebapp.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setEmail("ngocyen@bc.xyz");
        user.setPassword("12345");
        user.setFirstName("Yen");
        user.setLastName("Luong");

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }
    @Test
    public void testListAll(){
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users){
            System.out.println(user);
        }
    }
    @Test
    public void testFindById(){
        Optional<User> optionalUser = userRepository.findById(1);

        Assertions.assertThat(optionalUser).isNotNull();
        System.out.println(optionalUser);
    }
    @Test
    public void testGet(){
        Optional<User> optionalUser = userRepository.findById(2);

        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }
    @Test
    public void testUpdated(){
        Optional<User> optionalUser = userRepository.findById(4);

        User user = optionalUser.get();
        user.setFirstName("Yến");
        userRepository.save(user);

        User updateUser = userRepository.findById(4).get();
        Assertions.assertThat(updateUser.getFirstName()).isEqualTo("Yến");
    }
    @Test
    public void testDelete(){
        userRepository.deleteById(3);

        Optional<User> optionalUser = userRepository.findById(3);
        Assertions.assertThat(optionalUser).isNotPresent();
    }

}
