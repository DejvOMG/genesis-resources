package com.genesis.validator;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class PersonIdValidator {

    private final Set<String> validIds = new HashSet<>();

    public PersonIdValidator() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                getClass().getClassLoader().getResourceAsStream("dataPersonId.txt")
                        ), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                validIds.add(line.trim());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load person IDs", e);
        }
    }

    /**
     * Validuje, že personId je v seznamu (dataPersonId.txt).
     * @throws IllegalArgumentException pokud není.
     */
    public void validate(String personId) {
        if (personId == null || !validIds.contains(personId)) {
            throw new IllegalArgumentException("Invalid personId: " + personId);
        }
    }
}
