package com.example.demo.controller;

import com.example.demo.model.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ParcelService;

import java.util.List;

@RestController
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @PostMapping("/parcel")
    public ResponseEntity<?> createParcel(@RequestBody List<Parcel> parcels) {
        List<Parcel> createdParcels = parcelService.createParcels(parcels);
        return new ResponseEntity<>(createdParcels, HttpStatus.CREATED);
    }

}
