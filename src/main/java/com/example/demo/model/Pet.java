package com.example.demo.model;

import com.example.demo.enums.Gender;
import com.example.demo.enums.Species;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pets")
@Getter
@Setter
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Species species;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String race;

    @Column(nullable = false)
    private Double size;

    @Column(nullable = false)
    private Double weight;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    @JsonBackReference
    private User user;

    @OneToMany(
            mappedBy = "pet"
    )
    @JsonManagedReference
    private List<Appointment> appointments;

    public Pet() {}

    public Pet(String name, Species species, Gender gender, Integer age, String race, Double size) {
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.age = age;
        this.race = race;
        this.size = size;
    }
}
