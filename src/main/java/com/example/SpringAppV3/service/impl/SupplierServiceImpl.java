package com.example.SpringAppV3.service.impl;

import com.example.SpringAppV3.model.SupplierModel;
import com.example.SpringAppV3.repository.SupplierRepository;
import com.example.SpringAppV3.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierModel> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<SupplierModel> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public SupplierModel saveSupplier(SupplierModel supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
