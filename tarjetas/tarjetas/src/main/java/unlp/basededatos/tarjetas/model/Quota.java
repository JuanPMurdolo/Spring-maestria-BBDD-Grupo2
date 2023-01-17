package unlp.basededatos.tarjetas.model;

import javax.persistence.*;

@Entity
@Table(name = "Quota")
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_quota")
    private Long id;

    @Column(name ="number")
    private  int number;

    @Column(name ="price")
    private float price;

    @Column(name ="month")
    private String month;

    @Column(name ="year")
    private String year;

    public Quota(int number, float price, String month, String year) {
        this.number = number;
        this.price = price;
        this.month = month;
        this.year = year;
    }
    public Quota(){}

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
}