package unlp.basededatos.tarjetas.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Document
public class Quota {
	
    @MongoId
    private Long id;

    private  int number;

    private float price;

    private String month;

    private String year;

//	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
//    @JoinColumn( name = "montlypayment_id" )
//	private MonthlyPayments montlypayment;

    public Quota(int number, float price, String month, String year) {
        this.number = number;
        this.price = price;
        this.month = month;
        this.year = year;
    }
    public Quota(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

//    public MonthlyPayments getMontlypayment() {
//        return montlypayment;
//    }
//
//    public void setMontlypayment(MonthlyPayments montlypayment) {
//        this.montlypayment = montlypayment;
//    }

}