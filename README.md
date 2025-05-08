# Genesis Resources – Registrační systém uživatelů

Projekt je jednoduchý registrační systém vytvořený ve Spring Bootu pro správu uživatelů.


---

##  Technologie

- Java 22
- Spring Boot
- Spring Data JPA
- H2 in-memory databáze
- Postman (testovací kolekce)
- Lombok

---

## Funkcionalita

| Metoda | URL                            | Popis                    |
|--------|--------------------------------|---------------------------|
| POST   | `/api/v1/users`                | Vytvoření uživatele      |
| GET    | `/api/v1/users`                | Získání všech uživatelů  |
| GET    | `/api/v1/users/{id}`           | Získání uživatele podle ID |
| PUT    | `/api/v1/users/{id}`           | Úprava uživatele         |
| DELETE | `/api/v1/users/{id}`           | Smazání uživatele        |

---

## 🧪 Testování

1. Spusť aplikaci (`GenesisResourcesApplication`).
2. Otevři [Postman](https://www.postman.com/).
3. Naimportuj soubor:  
   `postman/genesis-resources-postman-collection.json`
4. Spusť kolekci „Genesis Resources Full Test“ a ověř správné odpovědi.

---

## Poznámky

- Každý `personId` musí být předem definován v `dataPersonId.txt`.
- Každý uživatel má automaticky generovaný UUID.
- `personId` a `uuid` jsou unikátní.

---



