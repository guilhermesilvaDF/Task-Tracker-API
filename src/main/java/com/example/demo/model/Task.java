package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tasks")
@Data
public class Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O titulo eh obrigatorio")
    private String title;


    private String description;
    private boolean completed;
}