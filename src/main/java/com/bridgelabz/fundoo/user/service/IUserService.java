package com.bridgelabz.fundoo.user.service;

import java.io.UnsupportedEncodingException;
import java.io.*;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.response.ResponseToken;
import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.model.ForgotPassword;
@Service
public interface IUserService {
Response registeruser(UserDTO userDto, StringBuffer requestUrl);
ResponseToken loginUser(LoginDto loginDto);
Response validateEmail(String token) throws UserException, UnsupportedEncodingException;
Response forgotpassword(LoginDto loginDto);
Response resetpassword(String token,ForgotPassword forgotPassword) throws UserException, UnsupportedEncodingException;
Response changePassword(LoginDto loginDto);
Response uploadImage(String token,MultipartFile file) throws IllegalArgumentException, UnsupportedEncodingException;
Resource getImage(String token) throws IllegalArgumentException, UnsupportedEncodingException ;

}
