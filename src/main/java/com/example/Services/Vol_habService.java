package com.example.Services;

import com.example.Controllers.ctrVol_hab;
import com.example.Models.Vol_habilidad;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class Vol_habService {

    private final ctrVol_hab ctrVolHab;

    Vol_habService(ctrVol_hab ctrVolHab){
        this.ctrVolHab = ctrVolHab;
    }

    @GetMapping("/vol_hab/all")
    public List<Vol_habilidad> getVols_hab(){
        return ctrVolHab.getVols_habilidad();
    }

    @GetMapping("/vol_hab/{cod}")
    public Vol_habilidad getVoluntario(@PathVariable(value = "cod")int cod){
        return ctrVolHab.getVol_habilidad(cod);
    }

    @PostMapping("/vol_hab/add")
    @ResponseBody
    public String createVol_hab(@RequestBody Vol_habilidad volHab){
        return ctrVolHab.createVol_habilidad(volHab);
    }

    @PutMapping("/vol_hab/update/{cod}")
    @ResponseBody
    public String updateVol_hab(@RequestBody Vol_habilidad volHab, @PathVariable(value = "cod") int cod){
        return ctrVolHab.updateVol_habilidad(volHab, cod);
    }

    @DeleteMapping("/vol_hab/delete/{cod}")
    public String deleteVol_hab(@PathVariable(value = "cod") int cod)
    {
        return ctrVolHab.deleteVol_habilidad(cod);
    }
}
