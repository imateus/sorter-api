package io.github.imateus.sorterapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column
    private String email;
    @Column(nullable = false)
    private Boolean active;
    @Column
    @Lob
    private byte[] picture;
}
