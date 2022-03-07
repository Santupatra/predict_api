package com.santu.predict.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.santu.predict.dao.UserDao;
import com.santu.predict.model.User;
import com.santu.predict.model.UserDto;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {


    @Autowired
    private UserDao userDao;
    
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

//    @Autowired
//    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });
        
       // authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().toString()));    //"ROLE_"+user.getRole()));
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User save(UserDto user) {

        User nUser = user.getUserFromDto();
        //nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        nUser.setId(sequenceGeneratorService.generateSequence(nUser.SEQUENCE_NAME)); 
        return userDao.save(nUser);
    }
}
