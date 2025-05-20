# Genesis Resources – Registrační systém uživatelů

Tento projekt je jednoduchý RESTful systém pro správu uživatelů, postavený na frameworku **Spring Boot**. Umožňuje vytvářet, číst, upravovat a mazat uživatele, přičemž každý záznam obsahuje unikátní `personId` a automaticky generovaný `UUID`.

## Použité technologie

- **Java 22**
- **Spring Boot**
- **Spring Data JPA**
- **Lombok**
- **H2 in-memory databáze**
- **Postman** – kolekce pro testování API

---

## Funkcionalita (REST API)

| Metoda | Endpoint                  | Popis                    |
|--------|---------------------------|---------------------------|
| POST   | `/api/v1/users`           | Vytvoření nového uživatele |
| GET    | `/api/v1/users`           | Získání všech uživatelů (parametr `detail`) |
| GET    | `/api/v1/users/{id}`      | Získání uživatele podle ID (`detail=true/false`) |
| PUT    | `/api/v1/users/{id}`      | Úprava existujícího uživatele |
| DELETE | `/api/v1/users/{id}`      | Smazání uživatele podle ID |

Parametr `detail=true`/`false` určuje, zda se vrátí detailní nebo základní informace o uživateli.

---

## Testování (Postman)

1. Spusť aplikaci přes `GenesisResourcesApplication`.
2. Otevři **Postman**.
3. Importuj soubor:
4. Spusť kolekci:  

Ověř:
- že `GET` s `detail=true` obsahuje `uuid` a `personId`
- že `GET` s `detail=false` vrací pouze základní údaje

---

## Databáze & validace

- Databáze běží v paměti (H2), inicializuje se automaticky pomocí `schema.sql`.
- Předvyplněná data: **Alice** a **Bob**.
- Přípustné hodnoty `personId` se čtou ze souboru:
- `personId` i `uuid` musí být jedinečné.

---

H2 konzole je dostupná na: http://localhost:8080/h2-console

JDBC URL: `jdbc:h2:mem:genesisdb`  
Uživatel: `sa` | Heslo: *(prázdné)*

---





