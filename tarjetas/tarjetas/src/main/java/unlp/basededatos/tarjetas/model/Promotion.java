package unlp.basededatos.tarjetas.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Promotion")
public abstract class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_promotion")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "promotionTitle")
    private String promotionTitle;

    @Column(name = "nameStore")
    private String nameStore;

    @Column(name = "cuitStore")
    private String cuitStore;

    @Column(name = "validityStartDate")
    private Date validityStartDate;

    @Column(name = "validityEndDate")
    private Date validityEndDate;

    @Column(name = "comments")
    private String comments;

    //Una promocion puede pertenecer a muchos bancos
    //Y los bancos pueden tener 0 o muchas promociones
    @ManyToMany()
    @JoinTable(
            name = "Bank_Promotions",
            joinColumns = @JoinColumn(name = "bankID"),
            inverseJoinColumns = @JoinColumn(name = "promotionID")
    )
    private List<Bank> banks;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<Purchase> purchase;


    public Promotion(String code, String promotionTitle, String nameStore, String cuitStore, Date validityStartDate, Date validityEndDate, String comments) {
        this.code = code;
        this.promotionTitle = promotionTitle;
        this.nameStore = nameStore;
        this.cuitStore = cuitStore;
        this.validityStartDate = validityStartDate;
        this.validityEndDate = validityEndDate;
        this.comments = comments;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPromotionTitle() {
        return promotionTitle;
    }

    public void setPromotionTitle(String promotionTitle) {
        this.promotionTitle = promotionTitle;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getCuitStore() {
        return cuitStore;
    }

    public void setCuitStore(String cuitStore) {
        this.cuitStore = cuitStore;
    }

    public Date getValidityStartDate() {
        return validityStartDate;
    }

    public void setValidityStartDate(Date validityStartDate) {
        this.validityStartDate = validityStartDate;
    }

    public Date getValidityEndDate() {
        return validityEndDate;
    }

    public void setValidityEndDate(Date validityEndDate) {
        this.validityEndDate = validityEndDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}