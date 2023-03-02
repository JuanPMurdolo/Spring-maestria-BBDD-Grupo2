package unlp.basededatos.tarjetas.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document
public class Financing extends Promotion{

    private int numberOfQuotas;
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