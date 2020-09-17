package com.example.Models;

public class Vol_habilidad {

    private int cod; //Columna a la que se le aplica hash
    private String rut_vol;
    private int cod_hab;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getRut_vol() {
        return rut_vol;
    }

    public void setRut_vol(String rut_vol) {
        this.rut_vol = rut_vol;
    }

    public int getCod_hab() {
        return cod_hab;
    }

    public void setCod_hab(int cod_hab) {
        this.cod_hab = cod_hab;
    }
}
