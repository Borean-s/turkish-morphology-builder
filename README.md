# MorphoTR — Turkish Suffix Stacker

Learn Turkish nouns suffix by suffix with live grammar rules.

An interactive web app for building and visualizing Turkish noun declensions.

**Live site:** https://your-render-url.onrender.com

## Features

- Interactive suffix builder with live-updating word display
- Grammar-aware UI, buttons disable automatically when a suffix is grammatically invalid
- Full morphological breakdown (`stem + suffix (label)`) for every generated word
- Multi-level undo, rebuilt from a persisted suffix history (not just in-memory state)
- History sidebar, reopen, continue, or delete past sessions
- Handles real Turkish grammar edge cases: consonant softening (k→ğ, p→b), and irregular pronouns (o/şu/bu, su/ne, ben/sen)
- Optional colored suffix highlighting and hover tooltips with grammar explanations
- Turkish special-character input panel for non-Turkish keyboards

## Tech Stack

**Backend**
- Java 21
- Spring Boot 4.1 (Spring Web, Spring Data JPA)
- Maven
- Hibernate / MySQL (hosted on Aiven)

**Frontend**
- Thymeleaf
- JavaScript
- CSS 

**Infrastructure**
- Docker 
- Deployed on Render


## Project Structure
turkish-morphology-builder/
├── console-app/ Original Java console version (grammar engine origin)
└── web-app/ Spring Boot web application

## Running Locally

Requires Java 21 and a MySQL-compatible database.

```bash
cd web-app
export DB_URL=jdbc:mysql://<host>:<port>/<database>
export DB_USERNAME=<username>
export DB_PASSWORD=<password>
mvn spring-boot:run
```

Then visit `http://localhost:8080`.
