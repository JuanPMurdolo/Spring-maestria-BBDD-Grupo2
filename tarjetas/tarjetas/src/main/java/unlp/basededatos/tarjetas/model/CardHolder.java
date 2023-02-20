package unlp.basededatos.tarjetas.model;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CardHolder")
public class CardHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_cardholder")
    private Long id;


    @Column(name = "completeName")
    private String completeName;

    @Column(name = "dni")
    private String dni;

    @Column(name = "cuil")
    private String cuil;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "entry")
    private Date entry;

    //La relacion parece ser muchos a muchos
    @ManyToMany(mappedBy = "cardHolders")
    private List<Bank> banks;
//    
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Card> cards;

    public CardHolder(String completeName, String dni, String cuil, String address, String telephone, Date entry) {
        this.completeName = completeName;
        this.dni = dni;
        this.cuil = cuil;
        this.address = address;
        this.telephone = telephone;
        this.entry = entry;
    }

    public CardHolder(){}

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getEntry() {
        return entry;
    }

    public void setEntry(Date entry) {
        this.entry = entry;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

//	public List<Card> getCards() {
//		return cards;
//	}
//
//	public void setCards(List<Card> cards) {
//		this.cards = cards;
//	}
//    
    
}