package com.example.PureConnect.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob // Esto indica que el campo contiene datos grandes, como un archivo binario
    @Column(nullable = false, columnDefinition = "LONGBLOB")
    private byte[] imageData;  // Almacena la imagen en formato binario

    // Relación inversa con Item
    @ManyToMany(mappedBy = "images")
    @JsonIgnore
    private Set<Item> items = new HashSet<>();
}
