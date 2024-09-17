package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "storage_location")
public class StorageLocationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Номер стеллажа не может быть пустым")
    @Pattern(regexp = "[A-Z]\\d{2}", message = "Номер стеллажа должен быть в формате A01")
    private String rack;

    @NotBlank(message = "Номер полки не может быть пустым")
    @Pattern(regexp = "\\d{2}", message = "Номер полки должен быть двузначным числом")
    private String shelf;

    @NotBlank(message = "Номер ячейки не может быть пустым")
    @Pattern(regexp = "\\d{3}", message = "Номер ячейки должен быть трехзначным числом")
    private String cell;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;
}
