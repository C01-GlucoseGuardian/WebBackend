package com.glucoseguardian.webbackend.application.service;

import com.glucoseguardian.webbackend.storage.dto.LoginOutputDto;

public interface AuthServiceInterface {

  LoginOutputDto login(String email, String password, Integer otp);
}
