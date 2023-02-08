package com.glucoseguardian.webbackend.notifica.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Servizio che si occupa dell'invio di email tramite jakarta/javax mail.
 *
 */
@Service
public class MailService {

  @Value("${spring.mail.from}")
  private String from;


  @Autowired
  private JavaMailSender javaMailSender;

  /**
   * Metodo che invia una mail, con oggetto = title, contenuto = message alla mail destinatario.
   */
  @Async
  public void sendNotification(String title, String message, String destinatario) {
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setFrom(from);
    mail.setSubject(title);
    mail.setText(message);
    mail.setTo(destinatario);
    try {
      javaMailSender.send(mail);
    } catch (Exception ignored) {
      // Do nothing
    }
  }

  /**
   * Metodo che invia una mail, con oggetto = title, contenuto = message ad ogni mail in
   * destinatario.
   */
  @Async
  public void sendNotification(String title, String message, List<String> destinatari) {
    destinatari.forEach(email -> sendNotification(title, message, email));
  }
}
