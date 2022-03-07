package com.santu.predict.service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.santu.predict.dao.UserDao;
import com.santu.predict.model.User;
import com.santu.predict.model.UserDto;
import com.santu.predict.model.UserOtp;

import lombok.extern.slf4j.Slf4j;

@Service(value = "userService")
@Slf4j
public class AccessServiceImpl implements UserDetailsService, AccessService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Lazy
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),user.isVerified(),true,true,true,
				getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		});

		return authorities;
	}

	@Override
	public User save(UserDto userDto) {

		User user = userDto.getUserFromDto();
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		User userOnDb = userDao.findByEmail(userDto.getEmail());
		if (ObjectUtils.isEmpty(userOnDb)) {
			sendOtp(user);
			user.setId(sequenceGeneratorService.generateSequence(user.SEQUENCE_NAME));
		} else {
			user.setId(userOnDb.getId());
			sendOtp(user);
		}

		return userDao.save(user);
	}

	@Override
	public User verifyOtp(UserOtp userOtp) {
		User newUser = userDao.findByEmail(userOtp.getEmail());
		if (!ObjectUtils.isEmpty(newUser)) {
			if (userOtp.getOtp() == newUser.getOtp()
					&& newUser.getGenerated() >= (System.currentTimeMillis() - 60 * 60 * 1000)) {
				newUser.setVerified(true);
			} else {
				newUser.setVerified(false);
			}
		}
		return userDao.save(newUser);
	}

	private void sendOtp(User user) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		user.setOtp(otp);
		user.setGenerated(System.currentTimeMillis());
		sendEmail(user.getEmail(), otp);
	}

	private void sendEmail(String email, int otp) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(email);

			msg.setSubject("Testing from Spring Boot");
			msg.setText("Hello World \n Spring Boot Email \n your otp is:- " + otp);

			javaMailSender.send(msg);
		} catch (Exception ex) {
			log.info("faild to send email :-" + email);
		}

	}

}
