package unlp.basededatos.tarjetas.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("Financing")
public class Financing extends Promotion{

    @Column(name = "numberOfQuotas")
    private int numberOfQuotas;

    @Column(name = "interes")
    private float interes;

    public Financing(String code, String promotionTitle, String nameStore, String cuitStore, Date validityStartDate, Date validityEndDate, String comments, int numberOfQuotas, float interes) {
        super(code, promotionTitle, nameStore, cuitStore, validityStartDate, validityEndDate, comments);
        this.numberOfQuotas = numberOfQuotas;
        this.interes = interes;
    }

    public Financing(){}
    public int getNumberOfQuotas() {
        return numberOfQuotas;
    }

    public void setNumberOfQuotas(int numberOfQuotas) {
        this.numberOfQuotas = numberOfQuotas;
    }

    public float getInteres() {
        return interes;
    }

    public void setInteres(float interes) {
        this.interes = interes;
    }
}