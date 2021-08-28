package com.example.ProjetDomotiqueAPI.models.doneee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "ProjetDomotique/api/v1/donnee")
public class DonneeController {

    private final DonneeService donneeService;

    @Autowired
    public DonneeController(DonneeService donneeService) {
        this.donneeService = donneeService;
    }

    //GET---------------------------------------------------------------------------------------------------------------
    @GetMapping
    public List<Donnee> getDonnees(@RequestParam(defaultValue = "0", name = "after") String after,
                                   @RequestParam(defaultValue = "0", name = "before") String before,
                                   @RequestParam(defaultValue = "", name = "value_type") String value_type,
                                   @RequestParam(defaultValue = "", name = "devices") List<String> deviceIds,
                                   @RequestParam(defaultValue = "", name = "rooms") List<String> roomIds,
                                   @RequestParam(defaultValue = "", name = "order") String order){
        
        if(!after.equals("0") && !before.equals("0"))
            return null;

        return donneeService.getDonneesAfter(after, before, value_type, deviceIds, roomIds, order);
    }

    @GetMapping(path = "last_datas")
    public List<Donnee> getDonnesRecentes(@RequestParam (defaultValue = "0", name = "room_id") int PI_ID){
        return donneeService.getLastDatas(PI_ID);
    }

    @GetMapping(path = "sample")
    public List<Donnee> getDonnesSample(){
        return List.of();
    }



}
