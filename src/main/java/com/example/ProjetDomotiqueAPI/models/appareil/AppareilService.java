package com.example.ProjetDomotiqueAPI.models.appareil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AppareilService {

    private final AppareilRepository appareilRepository;

    @Autowired
    public AppareilService(AppareilRepository appareilRepository) {
        this.appareilRepository = appareilRepository;
    }

    public List<Appareil> findAllDevices(){
        return appareilRepository.findAllDevices();
    }

    public Optional<Appareil> findDeviceByID(int AP_ID){
        return appareilRepository.findDeviceByID(AP_ID);
    }

    public List<Appareil> updateDeviceById(){
        return appareilRepository.findAllDevices();
    }


}
