package com.fiap.Plant4U.configs.security;

import com.fiap.Plant4U.models.UserModel;
import com.fiap.Plant4U.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDetailsImpl.build(userModel);
    }

    public UserDetails loadUserById(Long userId) throws AuthenticationCredentialsNotFoundException {
        UserModel userModel = userRepository.findById(userId)
                .orElseThrow(
                        () -> new AuthenticationCredentialsNotFoundException("User not found with userId: " + userId));
        return UserDetailsImpl.build(userModel);
    }
}
