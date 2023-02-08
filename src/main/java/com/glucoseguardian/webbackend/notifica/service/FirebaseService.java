package com.glucoseguardian.webbackend.notifica.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Servizio che si occupa di inviare notifiche tramite firebase.
 */
@Service
public class FirebaseService {
  @Autowired
  private FirebaseMessaging fcm;

  /**
   * Metodo che invia una notifica, con titolo = title, contenuto = message al topic destinatario.
   */
  @Async
  public void sendNotification(String title, String message, String destinatario) {
    Message msg = Message.builder()
        .setTopic(destinatario.replace("@", "%"))
        .putData("title", title)
        .putData("message", message)
        .build();
    try {
      fcm.sendAsync(msg);
    } catch (Exception ignored) {
      // Do nothing
    }
  }

  /**
   * Metodo che invia una notifica, con titolo = title, contenuto = message a
   * tutti i topic in destinatari.
   */
  @Async
  public void sendNotification(String title, String message, List<String> destinatari) {
    destinatari.forEach(destinatario -> sendNotification(title, message, destinatario));
  }
}
