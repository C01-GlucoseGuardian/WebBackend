# Glucose Guardian WebBackend
[![Build Status](https://travis-ci.com/C01-GlucoseGuardian/WebBackend.svg?branch=main)](https://travis-ci.com/C01-GlucoseGuardian/WebBackend) [![Coverage Status](https://coveralls.io/repos/github/C01-GlucoseGuardian/WebBackend/badge.svg)](https://coveralls.io/github/C01-GlucoseGuardian/WebBackend) [![Docker](https://github.com/C01-GlucoseGuardian/WebBackend/actions/workflows/docker-image.yml/badge.svg)](https://github.com/C01-GlucoseGuardian/WebBackend/actions/workflows/docker-image.yml) [![checkstyle](https://github.com/C01-GlucoseGuardian/WebBackend/actions/workflows/checkstyle.yml/badge.svg)](https://github.com/C01-GlucoseGuardian/WebBackend/actions/workflows/checkstyle.yml)

## Introduzione
Questa repo contiene il codice sorgente del backend del progetto Glucose Guardian, basato sul framework Java Spring Boot e DBMS PostgreSQL.

## Installazione con Docker
### 1. Creare l'immagine docker dai sorgenti (Opzionale)
1. Installare Git e Docker
2. Aprire un terminale e clonare questo progetto:

   ````
   git clone https://github.com/c01-glucoseguardian/webbackend
   ````
3. Spostarsi nella cartella webbackend:
   ````
   cd webbackend
   ````
4. Effettuare tutte le modifiche necessarie al file di configurazione in [src/main/java/resources/application.properties](https://github.com/C01-GlucoseGuardian/WebBackend/blob/524a9ccb0d55aed8681325b0a2744497df12e11e/src/main/resources/application.properties) (Opzionale)
5. Compilare l'immagine Docker:
   ````
   docker build -t c01glucoseguardian/webbackend .
   ````
### 2. Lanciare l'immagine Docker
> **Warning**<br>
> Il server non si avvierà senza una connessione con il DBMS PostgreSQL. È quindi importante configurare correttamente tali parametri in production.properties

> **Note**<br>
> Per utilizzare un'istanza di PostgreSQL locale, potete utilizzare il dominio host.docker.internal o l'ip 172.17.0.1.<br>
> In tal caso verificare che la configurazione di PostgreSQL accetti connessioni dalla subnet 172.17.0.0/16 (la default per la rete bridge di Docker)


Un esempio di commando per eseguire l'immagine Docker è il seguente:
```
 docker run --name webbackend -p 8081:8080 -v ./production.properties:/config/production.properties:ro -d c01glucoseguardian/webbackend
```
Dove:
- ```--name``` indica il nome da dare al container.
- ```-p 8081:8080``` espone la porta 8080 del container sulla porta 8081 dell'host.
- ```-v ./production.properties:/config/production.properties:ro``` monta il file di configurazione locale production.properties al posto del file di [configurazione predefinito](https://github.com/C01-GlucoseGuardian/WebBackend/blob/524a9ccb0d55aed8681325b0a2744497df12e11e/src/main/resources/application.properties).
- ```-d``` utilizza la modalità detached ovvero il container verrà eseguito in background.
- ```c01glucoseguardian/webbackend``` specifica l'immagine da utilizzare. Nel caso in cui non sia disponibile localmente, verrà scaricata da [dockerhub](https://hub.docker.com/r/c01glucoseguardian/webbackend).

Per altre informazioni si rimanda alla [documentazione di Docker](https://docs.docker.com/engine/reference/commandline/run/).

## Installazione da sorgenti
1. Installare Git e la JDK 17 o superiore
2. Aprire un terminale e clonare questo progetto:

   ````
   git clone https://github.com/c01-glucoseguardian/webbackend
   ````
3. Spostarsi nella cartella webbackend:
   ````
   cd webbackend
   ````
4. Effettuare tutte le modifiche necessarie al file di configurazione in [src/main/java/resources/application.properties](https://github.com/C01-GlucoseGuardian/WebBackend/blob/524a9ccb0d55aed8681325b0a2744497df12e11e/src/main/resources/application.properties) (Opzionale)
5. Compilare il file .jar:
   ````
   ./mvnw clean package -DskipTests=true -Dmaven.javadoc.skip=true
   ````
6. Eseguire il file .jar:
   > **Warning**<br>
   > Il server non si avvierà senza una connessione con il DBMS PostgreSQL. È quindi importante configurare correttamente tali parametri in application.properties
   ````
   java -jar target/*.jar
   ````

## Team
Il progetto è stato realizzato dal **Team C01**<br>
### Team members
<table>
  <tbody>
    <tr>  
      <td align="center" valign="top">
        <img width="100" height="100" src="https://github.com/HandyMenny.png?s=150">
        <br>
        <a href="https://github.com/HandyMenny">Andrea Mennillo</a>
      </td>
      <td align="center" valign="top">
        <img width="100" height="100" src="https://github.com/IamMarco29.png?s=150">
        <br>
        <a href="https://github.com/IamMarco29">Marco Maria<br>Marchese</a>
      </td>
      <td align="center" valign="top">
        <img width="100" height="100" src="https://github.com/matteoaldi.png?s=150">
        <br>
        <a href="https://github.com/matteoaldi">Matteo Aldi</a>
      </td>
      <td align="center" valign="top">
        <img width="100" height="100" src="https://github.com/veetaw.png?s=150">
        <br>
        <a href="https://github.com/veetaw">Vito Piegari</a>
      </td>
      <td align="center" valign="top">
        <img width="100" height="100" src="https://github.com/domenicocifelli.png?s=150">
        <br>
        <a href="https://github.com/domenicocifelli">Domenico Cifelli</a>
      </td>
      </tr>
  </tbody>
</table>

### Project managers
<table>
  <tbody>
    <tr>  
      <td align="center" valign="top">
        <img width="100" height="100" src="https://github.com/Scient122.png?s=150">
        <br>
        <a href="https://github.com/Scient122">Ludovico Lerose</a>
      </td>
      <td align="center" valign="top">
        <img width="100" height="100" src="https://github.com/AmineSr97.png?s=150">
        <br>
        <a href="https://github.com/AmineSr97">Amine M. Serraj</a>
      </td>
      </tr>
  </tbody>
</table>

## Link utili
- [Frontend web](https://github.com/C01-GlucoseGuardian/web)
- [Mobile App](https://github.com/C01-GlucoseGuardian/mobileApp)
- [Test di Sistema](https://github.com/C01-GlucoseGuardian/fullproject)
- [Documentazione](https://docs.glucoseguardian.it)
- [Demo](https://glucoseguardian.it)
