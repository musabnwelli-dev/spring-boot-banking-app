# Spring Boot Banking App

Eine RESTful Banking-Anwendung, die mit Spring Boot entwickelt wurde.  
Die Anwendung bietet grundlegende Bankfunktionen sowie Benutzerregistrierung, Authentifizierung und rollenbasierte Autorisierung.

## Funktionen

- Bankkonten erstellen und verwalten
- Geld einzahlen
- Geld abheben
- Geld zwischen Konten überweisen
- Transaktionshistorie anzeigen
- Kontostand anzeigen
- Benutzer registrieren und anmelden
- Passwörter mit BCrypt verschlüsseln
- Datenbankbasierte Authentifizierung mit Spring Security
- Rollenbasierte Autorisierung mit `ROLE_USER` und `ROLE_ADMIN`
- Globale Exception-Behandlung
  

## Verwendete Technologien

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL
- Maven
- Hibernate
- Lombok
- Postman (API-Testing)

## Projektstruktur

```text
src/main/java/net/javaguides/banking
├── config
├── controller
├── dto
├── entity
├── exception
├── map
├── repository
├── security
└── service
    └── impl
```

## API-Endpunkte

### Authentifizierung

| Methode | Endpunkt | Beschreibung |
|---------|----------|--------------|
| POST | `/api/auth/register` | Neuen Benutzer registrieren |
| POST | `/api/auth/login` | Benutzer anmelden |

### Bankkonten

| Methode | Endpunkt | Beschreibung |
|---------|----------|--------------|
| POST | `/api/accounts` | Neues Konto erstellen |
| GET | `/api/accounts/{id}` | Konto anhand der ID abrufen |
| GET | `/api/accounts` | Alle Konten abrufen |
| PUT | `/api/accounts/{id}/deposit` | Geld einzahlen |
| PUT | `/api/accounts/{id}/withdraw` | Geld abheben |
| DELETE | `/api/accounts/{id}` | Konto löschen |
| POST | `/api/accounts/transfer` | Geld zwischen Konten überweisen |
| GET | `/api/accounts/{id}/transactions` | Transaktionshistorie abrufen |
| GET | `/api/accounts/{id}/balance` | Kontostand abrufen |

## Sicherheit

Die Anwendung verwendet Spring Security für Authentifizierung und Autorisierung.

Folgende Rollen werden unterstützt:

- `ROLE_USER`
- `ROLE_ADMIN`

Passwörter werden mit BCrypt gehasht und nicht im Klartext gespeichert.

## Datenbank-Konfiguration

Die Anwendung verwendet eine MySQL-Datenbank.

Benutzername und Passwort werden über Umgebungsvariablen bereitgestellt:

```properties
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Vor dem Start müssen folgende Umgebungsvariablen gesetzt werden:

```text
DB_USERNAME
DB_PASSWORD
```

## Anwendung starten

1. Repository klonen.
2. MySQL-Datenbank `banking_app` erstellen.
3. Die Umgebungsvariablen `DB_USERNAME` und `DB_PASSWORD` konfigurieren.
4. Die Spring-Boot-Anwendung starten.
5. Die REST API beispielsweise mit Postman testen.

## Lerninhalte

In diesem Projekt wurden unter anderem folgende Themen praktisch umgesetzt:

- Entwicklung von REST APIs mit Spring Boot
- Controller-Service-Repository-Architektur
- Spring Data JPA und MySQL
- DTOs und Entity-Mapping
- Einzahlen, Abheben und Überweisen von Geld
- Speicherung und Abfrage von Transaktionen
- Benutzerregistrierung und Authentifizierung
- Spring Security
- Rollenbasierte Autorisierung
- Passwort-Hashing mit BCrypt
- Globale Exception-Behandlung

## Autor

**Musab Nwelli**
