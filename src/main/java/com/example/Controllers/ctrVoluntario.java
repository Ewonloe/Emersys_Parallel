package com.example.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.example.Models.Voluntario;

@Repository
public class ctrVoluntario {

    @Autowired
    private Sql2o[] sql2o;

    public List<Voluntario> getVoluntarios(){
        try {
            ExecutorService executor = Executors.newFixedThreadPool(sql2o.length);
            List<Voluntario>[] results = new ArrayList[sql2o.length];
            for (int i = 0; i < sql2o.length; i++) {
                final int db = i;
                results[i] = new ArrayList<Voluntario>();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (Connection conn = sql2o[db].open()) {
                            results[db] = conn.createQuery("select * from Voluntario")
                                    .executeAndFetch(Voluntario.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24 * 3600, TimeUnit.SECONDS);
            List<Voluntario> merged = new ArrayList<Voluntario>();
            for (int i = 0; i < sql2o.length; i++) {
                merged.addAll(results[i]);
            }
            return merged;
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    public String createVoluntario(Voluntario vol){
        int db = vol.getDigito() % sql2o.length; //Hash
        try(Connection conn = sql2o[db].open()){
            conn.createQuery("insert into Voluntario (rut, nombre, fnacimiento) values (:rut, :nombre, :fnacimiento)")
                    .addParameter("rut", vol.getRut())
                    .addParameter("nombre", vol.getNombre())
                    .addParameter("fnacimiento", vol.getFnacimiento())
                    .executeUpdate();
            return "Voluntario creado con exito y alojado en la base " + db + " (digito "+vol.getDigito()+" % "+sql2o.length+" = "+db+")";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Voluntario getVoluntario(String rut){
        Voluntario vol = new Voluntario();
        int db = vol.getRutStrDigito(rut) % sql2o.length; //Hash
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("SELECT * FROM Voluntario WHERE rut = :volRut")
                    .addParameter("volRut", rut)
                    .executeAndFetch(Voluntario.class).get(0);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public String updateVoluntario(Voluntario vol, String rut){
        int db = vol.getRutStrDigito(rut) % sql2o.length; //Hash
        try(Connection conn = sql2o[db].open()){
            conn.createQuery("UPDATE voluntario SET nombre = :volName, fnacimiento = :fnacimiento WHERE rut = :volRut")
                    .addParameter("volName", vol.getNombre())
                    .addParameter("fnacimiento", vol.getFnacimiento())
                    .addParameter("volRut", rut)
                    .executeUpdate();
            return "Voluntario " + rut +" de la base " + db + " actualizado con exito (digito "+vol.getRutStrDigito(rut)+" % "+sql2o.length+" = "+db+")";
        }
    }

    public String deleteVoluntario(String rut){
        Voluntario vol = new Voluntario();
        int db = vol.getRutStrDigito(rut) % sql2o.length; //Hash
        try(Connection conn = sql2o[db].open())
        {
            conn.createQuery("DELETE FROM voluntario WHERE rut = :volRut")
                    .addParameter("volRut", rut)
                    .executeUpdate();

            return "Voluntario " + rut + " de la base " + db + " eliminado con exito";
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
