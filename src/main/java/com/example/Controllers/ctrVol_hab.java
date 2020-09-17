package com.example.Controllers;

import com.example.Models.Vol_habilidad;
import com.example.Models.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Repository
public class ctrVol_hab {

    @Autowired
    private Sql2o[] sql2o;

    public List<Vol_habilidad> getVols_habilidad(){
        try {
            ExecutorService executor = Executors.newFixedThreadPool(sql2o.length);
            List<Vol_habilidad>[] results = new ArrayList[sql2o.length];
            for (int i = 0; i < sql2o.length; i++) {
                final int db = i;
                results[i] = new ArrayList<Vol_habilidad>();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (Connection conn = sql2o[db].open()) {
                            results[db] = conn.createQuery("select * from vol_habilidad")
                                    .executeAndFetch(Vol_habilidad.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24 * 3600, TimeUnit.SECONDS);
            List<Vol_habilidad> merged = new ArrayList<Vol_habilidad>();
            for (int i = 0; i < sql2o.length; i++) {
                merged.addAll(results[i]);
            }
            return merged;
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    public String createVol_habilidad(Vol_habilidad vol_hab){
        int db = vol_hab.getCod() % sql2o.length; //Hash
        try(Connection conn = sql2o[db].open()){
            conn.createQuery("insert into vol_habilidad (cod, rut_vol, cod_hab) values (:cod, :rut_vol, :cod_hab)")
                    .addParameter("cod", vol_hab.getCod())
                    .addParameter("rut_vol", vol_hab.getRut_vol())
                    .addParameter("cod_hab", vol_hab.getCod_hab())
                    .executeUpdate();
            return "Voluntario_habilidad creado con exito y alojado en la base " + db + " (cod "+vol_hab.getCod()+" % "+sql2o.length+" = "+db+")";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Vol_habilidad getVol_habilidad(int cod){
        int db = cod % sql2o.length; //Hash
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("SELECT * FROM Vol_habilidad WHERE cod = :cod")
                    .addParameter("cod", cod)
                    .executeAndFetch(Vol_habilidad.class).get(0);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public String updateVol_habilidad(Vol_habilidad vol_hab, int cod){
        int db = cod % sql2o.length; //Hash
        try(Connection conn = sql2o[db].open()){
            conn.createQuery("UPDATE voluntario SET rut_vol = :rut_vol, cod_hab = :cod_hab WHERE cod = :cod")
                    .addParameter("rut_vol", vol_hab.getRut_vol())
                    .addParameter("cod_hab", vol_hab.getCod_hab())
                    .addParameter("cod", cod)
                    .executeUpdate();
            return "Vol_hab " + cod +" de la base " + db + " actualizado con exito (digito "+vol_hab.getCod()+" % "+sql2o.length+" = "+db+")";
        }
    }

    public String deleteVol_habilidad(int cod){
        int db = cod % sql2o.length; //Hash
        try(Connection conn = sql2o[db].open())
        {
            conn.createQuery("DELETE FROM vol_habilidad WHERE cod = :cod")
                    .addParameter("cod", cod)
                    .executeUpdate();

            return "Vol_habilidad " + cod + " de la base " + db + " eliminado con exito";
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
