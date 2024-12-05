package com.example.PureConnect.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SequenceGenerator(name = "item_seq", sequenceName = "custom_item_seq", allocationSize = 1)

public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private double price;
    @Column
    private String description;
    @Column
    private Boolean bio;
    @Column
    private String review;

    // Relación con la entidad Image a través de la tabla intermedia
    @ManyToMany
    @JoinTable(
            name = "item_images",  // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "item_id"),  // Columna que referencia el Item
            inverseJoinColumns = @JoinColumn(name = "image_id")  // Columna que referencia la Imagen
    )
    private Set<Image> images = new HashSet<>();
}
