package com.example.ProjetDomotiqueAPI.models.appareil;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AppareilService {

    public List<Appareil> findAllDevices(){
        return List.of();
    }

    public Optional<Appareil> findDeviceByID(int AP_ID){
        return Optional.empty();
    }

}
