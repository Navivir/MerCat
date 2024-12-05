package com.example.PureConnect.service;

import com.example.PureConnect.dao.ImageRepository;
import com.example.PureConnect.dao.ItemRepository;
import com.example.PureConnect.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ItemService {


    private ItemRepository itemRepository;
    private ImageRepository imageRepository;


    public ItemService(ItemRepository itemRepository, ImageRepository imageRepository) {
        this.itemRepository = itemRepository;
        this.imageRepository = imageRepository;
    }

    public ResponseEntity<?> getItems() {
        List<Item> items = itemRepository.findAll();
        if (!items.isEmpty()){
            return ResponseEntity.ok(items);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> createItem(String name, double price, String description, Boolean bio,
                                        MultipartFile[] images, String season, String type) {
        // Crear el objeto base según el tipo
        Item item;
        switch (type.toLowerCase()) {
            case "vegetable":
                Vegetable vegetable = new Vegetable();
                vegetable.setSeason(season);
                item = vegetable;
                break;

            case "fruit":
                Fruit fruit = new Fruit();
                fruit.setSeason(season);
                item = fruit;
                break;

            case "tuber":
                Tuber tuber = new Tuber();
                tuber.setSeason(season);
                item = tuber;
                break;

            default:
                return ResponseEntity.badRequest().body("Invalid item type");
        }

        // Asignar propiedades comunes a todos los tipos de `Item`
        item.setName(name);
        item.setPrice(price);
        item.setDescription(description);
        item.setBio(bio);
        item.setType(type);

        // Procesar las imágenes y asignarlas al `Item`
        Set<Image> imageEntities = new HashSet<>();
        try {
            for (MultipartFile file : images) {
                Image image = new Image();
                image.setImageData(file.getBytes());
                imageRepository.save(image); // Guardar imagen en la base de datos
                imageEntities.add(image);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing images: " + e.getMessage());
        }

        item.setImages(imageEntities); // Asignar imágenes al `Item`

        // Guardar el `Item` en la base de datos
        try {
            itemRepository.save(item);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create item. Please try again later. Error: " + e.getMessage());
        }

        return ResponseEntity.ok("Item created successfully");
    }



}



