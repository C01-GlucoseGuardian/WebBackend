package com.glucoseguardian.webbackend.application.service.auth;

import com.glucoseguardian.webbackend.storage.dao.AdminDao;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TutoreDao;
import com.glucoseguardian.webbackend.storage.dto.LoginOutputDto;
import com.glucoseguardian.webbackend.storage.entity.TipoUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceStub implements AuthServiceInterface {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AdminDao adminDao;

  @Autowired
  private DottoreDao dottoreDao;

  @Autowired
  private PazienteDao pazienteDao;

  @Autowired
  private TutoreDao tutoreDao;

  @Override
  public LoginOutputDto login(String email, String password, Integer otp) {
    return new LoginOutputDto("CCCCDDDEER", TipoUtente.ADMIN.ordinal(), null);
  }

}
