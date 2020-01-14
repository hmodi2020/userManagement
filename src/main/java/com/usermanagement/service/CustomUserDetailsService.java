package com.usermanagement.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<User> user = userRepository.findByEmail(username);
	
				user.orElseThrow(()->new UsernameNotFoundException("user not found with given id" + username));
		return user.map(CustomUserDetails::new).get();
	
	}
}
