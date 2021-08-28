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

    public List<Donnee> getDonneesAfter(String after, String before, String value_type, List<String> deviceIds, List<String> roomIds, String order){
        return donneeRepository.getDonneesAfter(after, before, value_type, deviceIds, roomIds, order);
    }

    public List<Donnee> getLastDatas(int PI_ID){
        return donneeRepository.getLastDatas(PI_ID);
    }
}
