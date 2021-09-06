package com.example.ProjetDomotiqueAPI.models.donneeReference;

import com.example.ProjetDomotiqueAPI.MQTT.SimpleMqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "ProjetDomotique/api/v1/donneeReference")
public class DonneeReferenceController {

    private final DonneeReferenceService donneeReferenceService;

    @Autowired
    public DonneeReferenceController(DonneeReferenceService donneeReferenceService) {
        this.donneeReferenceService = donneeReferenceService;
    }

    //GET---------------------------------------------------------------------------------------------------------------

    @GetMapping(path = "{PI_ID}")
    public List<DonneeReference> getRoomReferences(@PathVariable("PI_ID") int PI_ID){
        return donneeReferenceService.findReferencesByRoomId(PI_ID);
    }

    @GetMapping(path = "sample")
    public static List<DonneeReference> getReferencesSample(){
        return List.of(
                new DonneeReference(1, 23.5f, 1, 1),
                new DonneeReference(2, 75, 1, 2),
                new DonneeReference(3, 24.5f, 2, 1),
                new DonneeReference(4, 76, 2, 2),
                new DonneeReference(5, 25.5f, 3, 1),
                new DonneeReference(6, 77, 3, 2)
        );
    }

    //POST--------------------------------------------------------------------------------------------------------------
    @PostMapping
    public boolean newReference(@RequestBody DonneeReference donneeReference){
        boolean success = false;

        if(donneeReference.isValid()){
            deleteReference(donneeReference.getTD_ID(), donneeReference.getPI_ID());
            success = donneeReferenceService.insertReference(donneeReference) > 0;
        }

        if(success){
            SimpleMqttClient mqttClient = new SimpleMqttClient();
            mqttClient.connectBroker();
            mqttClient.publishMqttMessage("DomotiqueMaison/config/input", "config", 0);
            mqttClient.shutdownClient();
        }

        return success;
    }

    @DeleteMapping
    public boolean deleteReference(@RequestParam(defaultValue = "0", name = "type") int TD_ID,
                                   @RequestParam(defaultValue = "0", name = "room") int PI_ID){
        if(TD_ID > 0 && PI_ID > 0)
            return donneeReferenceService.deleteReferences(TD_ID, PI_ID) > 0;

        return false;
    }


}
