package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название категории не может быть пустым")
    @Size(max = 15, message = "Название категории не может быть длиннее 15 символов")
    private String name;

    @Size(max = 200, message = "Описание категории не может быть длиннее 200 символов")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<ProductModel> products;
}