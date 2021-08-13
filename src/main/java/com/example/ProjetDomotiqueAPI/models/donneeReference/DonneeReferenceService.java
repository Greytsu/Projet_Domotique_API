package com.example.ProjetDomotiqueAPI.models.donneeReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonneeReferenceService {

    private DonneeReferenceRepository donneeReferenceRepository;

    @Autowired
    public DonneeReferenceService(DonneeReferenceRepository donneeReferenceRepository) {
        this.donneeReferenceRepository = donneeReferenceRepository;
    }

    public List<DonneeReference> findReferencesByRoomId(int PI_ID){
        return donneeReferenceRepository.findReferencesByRoomId(PI_ID);
    }

    public int insertReference(DonneeReference reference){
        return donneeReferenceRepository.insertReference(reference);
    }

    public int deleteReferences(int TD_ID, int PI_ID){
        return donneeReferenceRepository.deleteReferences(TD_ID, PI_ID);
    }
}
