package com.glucoseguardian.webbackend.storage.entity;

import java.sql.Date;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Questa interfaccia include alcuni metodi comuni alle entità {@link Admin}, {@link Dottore},
 * {@link Paziente}, {@link Tutore}.
 */
public interface Utente extends UserDetails {

  String getCodiceFiscale();

  void setCodiceFiscale(String codiceFiscale);

  String getNome();

  void setNome(String nome);

  String getCognome();

  void setCognome(String cognome);

  Date getDataNascita();

  void setDataNascita(Date dataNascita);

  String getIndirizzo();

  void setIndirizzo(String indirizzo);

  String getTelefono();

  void setTelefono(String telefono);

  String getEmail();

  void setEmail(String email);

  String getPassword();

  void setPassword(String password);

  char getSesso();

  void setSesso(char sesso);

  String getTotpKey();

  void setTotpKey(String totpKey);

  List<Notifica> getNotifiche();

  void setNotifiche(List<Notifica> notifiche);

  TipoUtente getTipoUtente();
}
