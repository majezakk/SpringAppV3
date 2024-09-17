package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "suppliers")
public class SupplierModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название поставщика не может быть пустым")
    @Size(max = 25, message = "Название поставщика не может быть длиннее 25 символов")
    private String name;

    @NotBlank(message = "Имя контактного лица не может быть пустым")
    @Size(max = 25, message = "Имя контактного лица не может быть длиннее 25 символов")
    private String contactPerson;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;

    @NotBlank(message = "Телефон не может быть пустым")
    @Pattern(regexp = "\\+7\\d{10}", message = "Телефон должен быть в формате +7XXXXXXXXXX")
    private String phone;

    @NotBlank(message = "Адрес не может быть пустым")
    @Size(max = 100, message = "Адрес не может быть длиннее 100 символов")
    private String address;

    @ManyToMany(mappedBy = "suppliers")
    private Set<ProductModel> products;
}