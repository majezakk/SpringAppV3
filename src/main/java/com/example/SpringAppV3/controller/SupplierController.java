package com.example.SpringAppV3.controller;

import com.example.SpringAppV3.model.SupplierModel;
import com.example.SpringAppV3.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public String listSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "suppliers/list";
    }

    @GetMapping("/create")
    public String createSupplierForm(Model model) {
        model.addAttribute("supplier", new SupplierModel());
        return "suppliers/form";
    }

    @PostMapping("/create")
    public String createSupplier(@ModelAttribute SupplierModel supplier) {
        supplierService.saveSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String editSupplierForm(@PathVariable Long id, Model model) {
        model.addAttribute("supplier", supplierService.getSupplierById(id).orElseThrow(() -> new IllegalArgumentException("Invalid supplier Id:" + id)));
        return "suppliers/form";
    }

    @PostMapping("/edit/{id}")
    public String editSupplier(@PathVariable Long id, @ModelAttribute SupplierModel supplier) {
        supplier.setId(id);
        supplierService.saveSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return "redirect:/suppliers";
    }
}