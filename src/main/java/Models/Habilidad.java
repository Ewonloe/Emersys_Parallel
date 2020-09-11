package Models;

import java.math.BigDecimal;
import java.sql.Date;

public class Habilidad {

    private BigDecimal id;
    private String descrip;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
