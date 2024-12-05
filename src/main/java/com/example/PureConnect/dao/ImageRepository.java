package com.example.PureConnect.dao;

import com.example.PureConnect.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    // Puedes agregar métodos personalizados si es necesario
}