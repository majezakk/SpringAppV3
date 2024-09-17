package com.example.SpringAppV3.controller;

import com.example.SpringAppV3.model.OrderModel;
import com.example.SpringAppV3.model.ProductModel;
import com.example.SpringAppV3.service.OrderService;
import com.example.SpringAppV3.service.CustomerService;
import com.example.SpringAppV3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders/list";
    }

    @GetMapping("/create")
    public String createOrderForm(Model model) {
        model.addAttribute("order", new OrderModel());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("products", productService.getAllProducts());
        return "orders/form";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute OrderModel order, @RequestParam Map<String, String> productQuantities) {
        processOrderProducts(order, productQuantities);
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String editOrderForm(@PathVariable Long id, Model model) {
        OrderModel order = orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Неверный ID заказа:" + id));
        model.addAttribute("order", order);
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("products", productService.getAllProducts());

        // Подготовка данных о количестве продуктов
        Map<Long, Integer> productQuantities = new HashMap<>();
        for (ProductModel product : order.getProducts()) {
            productQuantities.put(product.getId(), 1); // Предполагаем, что количество всегда 1, если нет отдельного поля
        }
        model.addAttribute("productQuantities", productQuantities);

        return "orders/form";
    }

    @PostMapping("/edit/{id}")
    public String editOrder(@PathVariable Long id, @ModelAttribute OrderModel order,
                            @RequestParam Map<String, String> productQuantities) {
        OrderModel existingOrder = orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Неверный ID заказа:" + id));

        // Обновляем только изменяемые поля
        existingOrder.setCustomer(order.getCustomer());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setTotalAmount(order.getTotalAmount());

        processOrderProducts(existingOrder, productQuantities);
        orderService.saveOrder(existingOrder);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }

    private void processOrderProducts(OrderModel order, Map<String, String> productQuantities) {
        Set<ProductModel> selectedProducts = productQuantities.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("productQuantities[") && !entry.getValue().isEmpty())
                .map(entry -> {
                    Long productId = Long.parseLong(entry.getKey().replaceAll("\\D+", ""));
                    int quantity = Integer.parseInt(entry.getValue());
                    ProductModel product = productService.getProductById(productId)
                            .orElseThrow(() -> new IllegalArgumentException("Неверный ID продукта: " + productId));
                    // Здесь можно добавить логику для установки количества, если это необходимо
                    return product;
                })
                .collect(Collectors.toSet());
        order.setProducts(selectedProducts);
    }
}