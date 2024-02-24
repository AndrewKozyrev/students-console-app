package org.landsreyk.app.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Student {

    private Long id;

    private final String firstName;

    private final String lastName;

    private final Integer age;
}