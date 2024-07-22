package org.example.etudiant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
}
