package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя клиента не может быть пустым")
    @Size(max = 25, message = "Имя клиента не может быть длинее 25 символов")
    private String name;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;

    @NotBlank(message = "Телефон не может быть пустым")
    @Pattern(regexp = "\\+7\\d{10}", message = "Телефон должен быть в формате +7XXXXXXXXX")
    private String phone;

    @NotBlank(message = "Адрес доставки не может быть пустым")
    @Size(max = 100, message = "Адрес доставки не может быть длинее 100 символов")
    private String deliveryAddress;

    @OneToMany(mappedBy = "customer")
    private List<OrderModel> orders;

}
