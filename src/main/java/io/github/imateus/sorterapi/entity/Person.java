package io.github.imateus.sorterapi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Person {
    @org.springframework.data.annotation.Transient
    public static final String SEQUENCE_NAME = "person_sequence";

    @Id
    private Long id;
    private String name;
    private String email;
    private Boolean active;
    private byte[] picture;
}
