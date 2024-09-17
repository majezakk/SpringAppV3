package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Дата создания не может быть пустой")
    private LocalDateTime creationDate;

    @NotNull(message = "Статус заказа не может быть пустым")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @PositiveOrZero(message = "Общая сумма должна быть неотрицательной")
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductModel> products;

    // Enum для статуса заказа
    public enum OrderStatus {
        НОВЫЙ, ОБРАБОТКА, ОТПРАВЛЕН, ДОСТАВЛЕН, ОТМЕНЕН
    }
}