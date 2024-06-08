package com.example.demo.service;

import com.example.demo.model.Parcel;
import com.example.demo.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelService {

    private ParcelRepository parcelRepository;

    @Autowired
    public ParcelService(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    public List<Parcel> createParcels(List<Parcel> parcels){
        return parcelRepository.saveAll(parcels);
    }

    public Parcel getParcelById(Long id) {
        return parcelRepository.findById(id).orElse(null);
    }

}

