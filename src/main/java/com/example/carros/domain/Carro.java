package com.example.carros.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity(name = "carro")
@Data
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;

}
