package com.genesis.validator;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
public class PersonIdValidator {

    private final Set<String> validPersonIds = new HashSet<>();

    @PostConstruct
    public void loadValidIds() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("dataPersonId.txt")))) {

            String line;
            while ((line = reader.readLine()) != null) {
                validPersonIds.add(line.trim());
            }

        } catch (Exception e) {
            throw new RuntimeException("Could not load valid person IDs from file", e);
        }
    }

    public boolean isValid(String personId) {
        return validPersonIds.contains(personId);
    }
}
