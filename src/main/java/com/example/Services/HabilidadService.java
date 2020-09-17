package com.example.Services;

import com.example.Controllers.ctrHabilidad;
import com.example.Models.Habilidad;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class HabilidadService {

    private final ctrHabilidad ctrHab;

    HabilidadService(ctrHabilidad ctrHab){
        this.ctrHab = ctrHab;
    }

    @GetMapping("/habilidades/all")
    public List<Habilidad> getHabilidades(){
        return ctrHab.getHabilidades();
    }

    @GetMapping("/habilidades/{cod}")
    public Habilidad getHabilidad(@PathVariable(value = "cod")int cod){
        return ctrHab.getHabilidad(cod);
    }

    @PostMapping("/habilidades/add")
    @ResponseBody
    public String createHabilidad(@RequestBody Habilidad habilidad){
        return ctrHab.createHabilidad(habilidad);
    }

    @PutMapping("/habilidades/update/{cod}")
    @ResponseBody
    public String updateHabilidad(@RequestBody Habilidad habilidad, @PathVariable(value = "cod") int cod){
        return ctrHab.updateHabilidad(habilidad, cod);
    }

    @DeleteMapping("/habilidades/delete/{cod}")
    public String deleteHabilidad(@PathVariable(value = "cod") int cod)
    {
        return ctrHab.deleteHabilidad(cod);
    }

    @GetMapping("/hola")
    public String hola(){
        return "Hola";
    }
}
