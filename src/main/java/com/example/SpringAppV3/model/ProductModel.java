package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название товара не может быть пустым")
    @Size(max = 25, message = "Название товара не может быть длиннее 25 символов")
    private String name;

    @Size(max = 200, message = "Описание товара не может быть длиннее 200 символов")
    private String description;

    @NotBlank(message = "Штрих-код не может быть пустым")
    @Pattern(regexp = "\\d{13}", message = "Штрих-код должен состоять из 13 цифр")
    private String barcode;

    @Positive(message = "Вес должен быть положительным числом")
    private Double weight;

    @Positive(message = "Объем должен быть положительным числом")
    private Double volume;

    @Positive(message = "Цена должна быть положительным числом")
    private Double price;

    @PositiveOrZero(message = "Количество на складе не может быть отрицательным")
    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    @OneToOne(mappedBy = "product")
    private StorageLocationModel storageLocation;

    @ManyToMany
    @JoinTable(
            name = "product_suppliers",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    private Set<SupplierModel> suppliers;
}