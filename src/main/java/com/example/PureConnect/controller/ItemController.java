package com.example.PureConnect.controller;

import com.example.PureConnect.model.Fruit;
import com.example.PureConnect.model.Image;
import com.example.PureConnect.model.Item;
import com.example.PureConnect.model.Tuber;
import com.example.PureConnect.model.Vegetable;
import com.example.PureConnect.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;

    public ItemController (ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping("/item")
    public ResponseEntity<?> getItems(){
        return itemService.getItems();
    }
    @PostMapping("/create-item")
    public ResponseEntity<?> createItem(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "price", required = true) double price,
            @RequestParam(value = "description", required = true) String description,
            @RequestParam(value = "bio", required = true) Boolean bio,
            @RequestParam(value = "images", required = true) MultipartFile[] images,
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "season", required = false) String season
    ) {

        return itemService.createItem(name,price,description,bio,images,season,type);
    }


    

}
