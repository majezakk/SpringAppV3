package com.example.SpringAppV3.repository;

import com.example.SpringAppV3.model.StorageLocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageLocationRepository extends JpaRepository<StorageLocationModel, Long> {

}
