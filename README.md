# 📝 Todo API - Spring Boot
![CI](https://github.com/Lyndalino/todo-api-springboot/actions/workflows/ci.yml/badge.svg)
API REST de gestion de tâches construite avec Spring Boot.

## 🛠️ Stack technique

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.14-green)
![Maven](https://img.shields.io/badge/Maven-blue)
![H2](https://img.shields.io/badge/H2-Database-lightgrey)

## 🚀 Comment lancer le projet

```bash
git clone https://github.com/Lyndalino/todo-api-springboot.git
cd todo-api-springboot
./mvnw spring-boot:run
```

L'API sera disponible sur `http://localhost:8080`

## 📡 Les routes de l'API

| Méthode | Route | Description |
|---|---|---|
| GET | `/tasks` | Récupérer toutes les tâches |
| GET | `/tasks/{id}` | Récupérer une tâche par ID |
| POST | `/tasks` | Créer une nouvelle tâche |
| PUT | `/tasks/{id}` | Modifier une tâche |
| DELETE | `/tasks/{id}` | Supprimer une tâche |

## 📦 Exemple de requête

```json
{
    "titre": "Ma première tâche",
    "description": "Apprendre Spring Boot",
    "statut": "TODO"
}
```

## 👩‍💻 Auteur

**Lynda** — Étudiante M2 Génie Logiciel