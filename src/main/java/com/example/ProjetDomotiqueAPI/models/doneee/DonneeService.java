package com.example.ProjetDomotiqueAPI.models.doneee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonneeService {

    private final DonneeRepository donneeRepository;

    @Autowired
    public DonneeService(DonneeRepository donneeRepository) {
        this.donneeRepository = donneeRepository;
    }

    public List<Donnee> getDonneesAfter(String after, List<String> deviceIds, List<String> roomIds){
        return donneeRepository.getDonneesAfter(after, deviceIds, roomIds);
    }
}
