package com.example.PureConnect.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@DiscriminatorValue("fruit")
public class Fruit extends Item {
    @Column
    private String season;

}
