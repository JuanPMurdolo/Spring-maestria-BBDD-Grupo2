package unlp.basededatos.tarjetas.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class MonthlyPayments extends Purchase {
    private float interest;
    private int numberOfQuotas;

  //bi-directional many-to-one association to Quota
//  	@OneToMany(mappedBy="montlypayment")
//  	private List<Quota> quotas;
  	
    @JsonIgnore
    private List<Quota> quota;
  	
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

	public List<Quota> getQuotas() {
		return quota;
	}

	public void setQuotas(List<Quota> quotas) {
		this.quota = quotas;
	}
	
}