package com.example.demo.serviceimpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.controller.UserController;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.exceptions.InvalidCredentialsException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;



/**
 * @author Manasa
 *
 */
@Service
public class UserServiceImpl implements UserService {
	private static Log logger = LogFactory.getLog(UserController.class);
	
	@Autowired
	UserDao userDao;
	
	/**
	 *{@inheritDoc}
	 */
	@Override
	public void addUser(@RequestBody UserDto userDto) {
		logger.info("implementation of user registration");
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		userDao.save(user);
	}

	/**
	 * {@inheritDoc}
	 * @throws InvalidCredentialsException 
	 */
	@Override
	public boolean authenticateUser(String userName, String password) throws InvalidCredentialsException {
		logger.info("implementaion of authenticating the user");
		User user = userDao.findByUserNameAndPassword(userName,password);
		if (user !=null)
			return true;
		throw new InvalidCredentialsException("Invalid Credentials..!!Please Verify your Credentials and Try Again.");
        }

	}

	

