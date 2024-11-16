# Bookride

Ce projet est une API RESTful pour la gestion des chauffeurs, des véhicules et des réservations. Il permet d'effectuer des opérations CRUD (Créer, Lire, Mettre à jour, Supprimer) sur les entités, tout en offrant des analyses statistiques telles que le taux d'occupation et la répartition des statuts des chauffeurs.

## Table des Matières

- [Technologies Utilisées](#technologies-utilisées)
- [Structure du Projet](#structure-du-projet)
- [Installation](#installation)
- [Endpoints de l'API](#endpoints-de-lapi)
- [Exemples de Requêtes avec Postman](#exemples-de-requêtes-avec-postman)
- [Contributions](#contributions)
- [Auteur](#auteur)

### Technologies Utilisées

- Java 8+
- Spring Boot 3.x (Spring MVC, Spring Data JPA, Spring Validation)
- Hibernate pour l'ORM
- Base de données PostgreSQL
- Lombok pour la réduction du code boilerplate
- Postman pour le test des endpoints
- Maven pour la gestion des dépendances

### Structure du Projet

Le projet suit une architecture en couches (Controller, Service, DAO, Repository) pour une séparation claire des responsabilités.
```
📂 src
└── 📂 main
    ├── 📂 java
    │   └── 📂 com.bookride
    │       ├── 📂 controller           # Contrôleurs REST
    │       │   ├── DriverController.java
    │       │   ├── VehicleController.java
    │       │   └── ReservationController.java
    │       │
    │       ├── 📂 service              # Services contenant la logique métier
    │       │   ├── DriverService.java
    │       │   ├── VehicleService.java
    │       │   └── ReservationService.java
    │       │
    │       ├── 📂 dao                  # Accès aux données, requêtes spécifiques
    │       │   ├── DriverDao.java
    │       │   ├── VehicleDao.java
    │       │   └── ReservationDao.java
    │       │
    │       ├── 📂 repository           # Repositories JPA
    │       │   ├── DriverRepository.java
    │       │   ├── VehicleRepository.java
    │       │   └── ReservationRepository.java
    │       │
    │       ├── 📂 model                # Entités JPA représentant les tables de la base de données
    │       │   ├── Driver.java
    │       │   ├── Vehicle.java
    │       │   └── Reservation.java
    │       │
    │       └── 📂 dto                  # Objets de transfert de données (DTO)
    │           ├── DriverDto.java
    │           ├── VehicleDto.java
    │           └── ReservationDto.java
    │
    └── 📂 resources                    # Fichiers de configuration
        └── application.properties      # Configuration de la base de données
```

### Installation

Clonez le repository :

```bash
git clone https://github.com/Yassinean/bookride.git
```
Ou 
```bash
git clone https://github.com/Yorften/bookride.git
```
```bash
cd bookride
```
##### Configurez la base de données PostgreSQL : Assurez-vous que PostgreSQL est installé et créez une base de données :

```sql

CREATE DATABASE bookride;
Modifiez les informations de connexion dans application.properties :

```
spring.datasource.url=jdbc:postgresql://localhost:5432/bookride
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
Compilez et exécutez l'application :

```bash
mvn clean install
```
```bash
mvn spring-boot:run
```
##### La documentation de l'API (Swagger) est disponible sur :

```bash
http://localhost:8080/swagger-ui/index.html#/
```
- Endpoints de l'API
- Drivers
GET /api/Drivers : Obtenir la liste des Drivers (paginée)
GET /api/Drivers/{id} : Obtenir un chauffeur par ID
POST /api/Drivers : Créer un nouveau chauffeur
PUT /api/Drivers/{id} : Mettre à jour un chauffeur
DELETE /api/Drivers/{id} : Supprimer un chauffeur
Analytics
GET /api/drivers/analytics : Obtenir des statistiques sur le taux d'occupation, l'analyse des disponibilités et la répartition des statuts des drivers
Exemples de Requêtes avec Postman
Créer un Chauffeur

POST http://localhost:8080/api/drivers
Body (JSON) :
```json

{
    "firstName": "John",
    "lastName": "Doe",
    "status": "AVAILABLE",
    "availabilityStart": "01-01-2024 08:00:00",
    "availabilityEnd": "01-01-2024 18:00:00"
}
```
Obtenir le Taux d'Occupation

GET http://localhost:8080/api/chauffeurs/analytics

### Contributions
Les contributions sont les bienvenues ! Veuillez suivre les étapes ci-dessous pour proposer des modifications :

- Forkez le projet
- Créez une branche pour votre fonctionnalité ``git checkout -b nouvelle-fonctionnalite``
- Commitez vos modifications ``git commit -am 'Ajouter une nouvelle fonctionnalité'``
- Poussez sur la branche ```git push origin nouvelle-fonctionnalite```
- Créez une Pull Request
### Auteur

Yassine Hanach
- GitHub: [https://github.com/Yassinean](https://github.com/Yassinean)
Abderrahman Badi
- GitHub: [https://github.com/Yorften](https://github.com/Yorften)
