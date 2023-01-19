package unlp.basededatos.tarjetas.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Monthly")
public class MonthlyPayments extends Purchase {

    @Column(name = "intereset")
    private float interest;

    @Column(name = "numberOfQuotas")
    private int numberOfQuotas;

    public MonthlyPayments(String paymentVoucher, String store, String cuitStore, float amount, float finalAmount, float interest, int numberOfQuotas) {
        super(paymentVoucher, store, cuitStore, amount, finalAmount);
        this.interest = interest;
        this.numberOfQuotas = numberOfQuotas;
    }

    public MonthlyPayments(){}

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public int getNumberOfQuotas() {
        return numberOfQuotas;
    }

    public void setNumberOfQuotas(int numberOfQuotas) {
        this.numberOfQuotas = numberOfQuotas;
    }
}