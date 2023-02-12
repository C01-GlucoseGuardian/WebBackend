package com.glucoseguardian.webbackend.unittests.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.glucoseguardian.webbackend.storage.dao.AdminDao;
import com.glucoseguardian.webbackend.storage.dao.DottoreDao;
import com.glucoseguardian.webbackend.storage.dao.PazienteDao;
import com.glucoseguardian.webbackend.storage.dao.TutoreDao;
import com.glucoseguardian.webbackend.storage.dao.UtenteDao;
import com.glucoseguardian.webbackend.storage.entity.Admin;
import com.glucoseguardian.webbackend.storage.entity.Dottore;
import com.glucoseguardian.webbackend.storage.entity.Notifica;
import com.glucoseguardian.webbackend.storage.entity.Paziente;
import com.glucoseguardian.webbackend.storage.entity.TipoUtente;
import com.glucoseguardian.webbackend.storage.entity.Tutore;
import com.glucoseguardian.webbackend.storage.entity.Utente;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;

@ExtendWith(MockitoExtension.class)
public class UtenteDaoTest {

  @Mock
  DottoreDao dottoreDao;
  @Mock
  PazienteDao pazienteDao;
  @Mock
  TutoreDao tutoreDao;
  @Mock
  AdminDao adminDao;
  @InjectMocks
  UtenteDao utenteDao;

  /**
   * ExistsByEmail: no user with given email exist
   */
  @Test
  public void existsByEmail1() {
    boolean result = utenteDao.existsByEmail("");
    assertFalse(result);
  }

  /**
   * ExistsByEmail: Admin with given email exist
   */
  @Test
  public void existsByEmail2() {
    when(adminDao.existsByEmail("admin@glucoseguardian.it")).thenReturn(true);

    boolean result = utenteDao.existsByEmail("admin@glucoseguardian.it");
    assertTrue(result);
  }

  /**
   * ExistsByEmail: Dottore with given email exist
   */
  @Test
  public void existsByEmail3() {
    when(dottoreDao.existsByEmail("dottore@glucoseguardian.it")).thenReturn(true);

    boolean result = utenteDao.existsByEmail("dottore@glucoseguardian.it");
    assertTrue(result);
  }


  /**
   * ExistsByEmail: Paziente with given email exist
   */
  @Test
  public void existsByEmail4() {
    when(pazienteDao.existsByEmail("paziente@glucoseguardian.it")).thenReturn(true);

    boolean result = utenteDao.existsByEmail("paziente@glucoseguardian.it");
    assertTrue(result);
  }

  /**
   * ExistsByEmail: Tutore with given email exist
   */
  @Test
  public void existsByEmail5() {
    when(tutoreDao.existsByEmail("tutore@glucoseguardian.it")).thenReturn(true);

    boolean result = utenteDao.existsByEmail("tutore@glucoseguardian.it");
    assertTrue(result);
  }


  /**
   * FindByEmail: no user with given email exist
   */
  @Test
  public void findByEmail1() {
    assertEquals(Optional.empty(), utenteDao.findByEmail(""));
  }

  /**
   * FindByEmail: Admin with given email exist
   */
  @Test
  public void findByEmail2() {
    Admin admin = new Admin();
    when(adminDao.findByEmail("admin@glucoseguardian.it")).thenReturn(Optional.of(admin));

    assertEquals(utenteDao.findByEmail("admin@glucoseguardian.it"), Optional.of(admin));
  }

  /**
   * FindByEmail: Dottore with given email exist
   */
  @Test
  public void findByEmail3() {
    Dottore dottore = new Dottore();

    when(dottoreDao.findByEmail("dottore@glucoseguardian.it")).thenReturn(Optional.of(dottore));

    assertEquals(utenteDao.findByEmail("dottore@glucoseguardian.it"), Optional.of(dottore));
  }


  /**
   * FindByEmail: Paziente with given email exist
   */
  @Test
  public void findByEmail4() {
    Paziente paziente = new Paziente();

    when(pazienteDao.findByEmail("paziente@glucoseguardian.it")).thenReturn(Optional.of(paziente));

    assertEquals(utenteDao.findByEmail("paziente@glucoseguardian.it"), Optional.of(paziente));
  }

  /**
   * FindByEmail: Tutore with given email exist
   */
  @Test
  public void findByEmail5() {
    Tutore tutore = new Tutore();

    when(tutoreDao.findByEmail("tutore@glucoseguardian.it")).thenReturn(Optional.of(tutore));

    assertEquals(utenteDao.findByEmail("tutore@glucoseguardian.it"), Optional.of(tutore));
  }

  /**
   * save: unknown utente
   */
  @Test
  public void testSave1() {
    Utente utente = new Utente() {
      public String getCodiceFiscale() {
        return null;
      }

      public void setCodiceFiscale(String codiceFiscale) {
      }

      public String getNome() {
        return null;
      }

      public void setNome(String nome) {
      }

      public String getCognome() {
        return null;
      }

      public void setCognome(String cognome) {
      }

      public Date getDataNascita() {
        return null;
      }

      public void setDataNascita(Date dataNascita) {
      }

      public String getIndirizzo() {
        return null;
      }

      public void setIndirizzo(String indirizzo) {
      }

      public String getTelefono() {
        return null;
      }

      public void setTelefono(String telefono) {
      }

      public String getEmail() {
        return null;
      }

      public void setEmail(String email) {
      }

      public String getPassword() {
        return null;
      }

      public void setPassword(String password) {
      }

      public char getSesso() {
        return 0;
      }

      public void setSesso(char sesso) {
      }

      public String getTotpKey() {
        return null;
      }

      public void setTotpKey(String totpKey) {
      }

      public List<Notifica> getNotifiche() {
        return null;
      }

      public void setNotifiche(List<Notifica> notifiche) {

      }

      public TipoUtente getTipoUtente() {
        return null;
      }

      public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
      }

      public String getUsername() {
        return null;
      }

      public boolean isAccountNonExpired() {
        return false;
      }

      public boolean isAccountNonLocked() {
        return false;
      }

      public boolean isCredentialsNonExpired() {
        return false;
      }

      public boolean isEnabled() {
        return false;
      }
    };
    assertThrows(RuntimeException.class, () -> utenteDao.save(utente));
  }

  /**
   * save: utente admin
   */
  @Test
  public void testSave2() {
    Admin utente = new Admin();
    when(adminDao.save(utente)).thenReturn(utente);
    assertEquals(utente, utenteDao.save(utente));
  }

  /**
   * save: utente dottore
   */
  @Test
  public void testSave3() {
    Dottore utente = new Dottore();
    when(dottoreDao.save(utente)).thenReturn(utente);
    assertEquals(utente, utenteDao.save(utente));
  }

  /**
   * save: utente paziente
   */
  @Test
  public void testSave4() {
    Paziente utente = new Paziente();
    when(pazienteDao.save(utente)).thenReturn(utente);
    assertEquals(utente, utenteDao.save(utente));
  }

  /**
   * save: utente tutore
   */
  @Test
  public void testSave5() {
    Tutore utente = new Tutore();
    when(tutoreDao.save(utente)).thenReturn(utente);
    assertEquals(utente, utenteDao.save(utente));
  }
}
