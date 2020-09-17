package com.example.Services;

import com.example.Controllers.ctrVoluntario;
import org.springframework.web.bind.annotation.*;
import com.example.Models.Voluntario;

import java.util.List;

@CrossOrigin
@RestController
public class VoluntarioService {
    private final ctrVoluntario ctrVol;

    VoluntarioService(ctrVoluntario ctrVol){
        this.ctrVol = ctrVol;
    }

    @GetMapping("/voluntarios/all")
    public List<Voluntario> getVoluntario(){
        return ctrVol.getVoluntarios();
    }

    @GetMapping("/voluntarios/{rut}")
    public Voluntario getVoluntario(@PathVariable(value = "rut")String rut){
        return ctrVol.getVoluntario(rut);
    }

    @PostMapping("/voluntarios/add")
    @ResponseBody
    public String createVoluntario(@RequestBody Voluntario voluntario){
        return ctrVol.createVoluntario(voluntario);
    }

    @PutMapping("/voluntarios/update/{rut}")
    @ResponseBody
    public String updateVoluntario(@RequestBody Voluntario voluntario, @PathVariable(value = "rut") String rut){
        return ctrVol.updateVoluntario(voluntario, rut);
    }

    @DeleteMapping("/voluntarios/delete/{rut}")
    public String deleteVoluntario(@PathVariable(value = "rut") String rut)
    {
        return ctrVol.deleteVoluntario(rut);
    }
}
