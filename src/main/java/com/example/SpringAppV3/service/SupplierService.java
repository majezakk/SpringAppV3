package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.SupplierModel;
import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<SupplierModel> getAllSuppliers();
    Optional<SupplierModel> getSupplierById(Long id);
    SupplierModel saveSupplier(SupplierModel supplier);
    void deleteSupplier(Long id);
}