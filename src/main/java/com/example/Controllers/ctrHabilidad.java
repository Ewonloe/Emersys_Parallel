package com.example.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import com.example.Models.Habilidad;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Repository
public class ctrHabilidad {

    @Autowired
    private Sql2o[] sql2o;

    public List<Habilidad> getHabilidades(){
        try {
            ExecutorService executor = Executors.newFixedThreadPool(sql2o.length);
            List<Habilidad>[] results = new ArrayList[sql2o.length];
            for (int i = 0; i < sql2o.length; i++) {
                final int db = i;
                results[i] = new ArrayList<Habilidad>();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (Connection conn = sql2o[db].open()) {
                            results[db] = conn.createQuery("select * from Habilidad")
                                    .executeAndFetch(Habilidad.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24 * 3600, TimeUnit.SECONDS);
            List<Habilidad> merged = new ArrayList<Habilidad>();
            for (int i = 0; i < sql2o.length; i++) {
                merged.addAll(results[i]);
            }
            return merged;
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    public String createHabilidad(Habilidad hab){
        //int db = hab.getCod() % sql2o.length;
        for(int db = 0; db < sql2o.length; db++){
            try(Connection conn = sql2o[db].open()) {
                conn.createQuery("insert into habilidad (cod, descrip) values (:cod, :descrip)")
                        .addParameter("cod", hab.getCod())
                        .addParameter("descrip", hab.getDescrip())
                        .executeUpdate();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }
        return "Habilidad creada y replicada en todas las particiones";
    }

    public Habilidad getHabilidad(int cod){
        int db = cod % sql2o.length;
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("SELECT * FROM Habilidad WHERE cod = :habCod")
                    .addParameter("habCod", cod)
                    .executeAndFetch(Habilidad.class).get(0);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public String updateHabilidad(Habilidad hab, int cod){
        int db = cod % sql2o.length;
        try(Connection conn = sql2o[db].open()){
            conn.createQuery("UPDATE habilidad SET descrip = :habDesc WHERE cod = :habCod")
                    .addParameter("habDesc", hab.getDescrip())
                    .addParameter("habCod", cod)
                    .executeUpdate();
            return "Habilidad " + cod +" de la base " + db + " actualizada con exito (cod "+cod+" % "+sql2o.length+" = "+db+")";
        }
    }

    public String deleteHabilidad(int cod){
        int db = cod % sql2o.length;
        try(Connection conn = sql2o[db].open())
        {
            conn.createQuery("DELETE FROM habilidad WHERE cod = :habCod")
                    .addParameter("habCod", cod)
                    .executeUpdate();

            return "Habilidad " + cod + " de la base " + db + " eliminada con exito";
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
