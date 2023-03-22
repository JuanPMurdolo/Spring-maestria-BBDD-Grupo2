package unlp.basededatos.tarjetas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;


@Document
public class Payment {

    @MongoId
    private String id;

    private String code;

    private String month;

    private String year;

    private Date firstExpiration;

    private Date secondExpiration;
    private float total;
    private float purchase;
    private float totalPrice;

    //falta la relacion e/Quota y Payment
    private List<Quota> quota;

    //Falta la relacion /CashPayment y Payment
	private List<CashPayment> cashpayment;
	
	public Payment(String code, String month, String year, Date firstExpiration, Date secondExpiration, float purchase, float totalPrice) {
        this.code = code;
        this.month = month;
        this.year = year;
        this.firstExpiration = firstExpiration;
        this.secondExpiration = secondExpiration;
        this.purchase = purchase;
        this.totalPrice = totalPrice;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CashPayment> getCashpayments() {
		return cashpayment;
	}

	public void setCashpayments(List<CashPayment> cashpayments) {
		this.cashpayment = cashpayments;
	}

    public Payment(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getFirstExpiration() {
        return firstExpiration;
    }

    public void setFirstExpiration(Date firstExpiration) {
        this.firstExpiration = firstExpiration;
    }

    public Date getSecondExpiration() {
        return secondExpiration;
    }

    public void setSecondExpiration(Date secondExpiration) {
        this.secondExpiration = secondExpiration;
    }

    public float getPurchase() {
        return purchase;
    }

    public void setPurchase(float purchase) {
        this.purchase = purchase;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Quota> getQuotas() {
        return quota;
    }

    public void setQuotas(List<Quota> quotas) {
        this.quota = quotas;
    }

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
}