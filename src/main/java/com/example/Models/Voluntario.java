package com.example.Models;

import java.sql.Date;

public class Voluntario {

    private String rut; //Columna a la que se le aplica hash
    private String nombre;
    private Date fnacimiento;

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public int getDigito(){
        String tmp = this.rut.toLowerCase();
        String[] arr = tmp.split("-");
        if (arr[1].equals("k")){
            return 10;
        }
        return Integer.parseInt(arr[1]);
    }

    public int getRutStrDigito(String rut){
        String tmp = rut.toLowerCase();
        String[] arr = tmp.split("-");
        if (arr[1].equals("k")){
            return 10;
        }
        return Integer.parseInt(arr[1]);
    }
}
