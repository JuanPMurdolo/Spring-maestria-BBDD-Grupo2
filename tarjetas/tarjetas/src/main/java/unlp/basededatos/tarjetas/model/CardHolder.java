package unlp.basededatos.tarjetas.model;

import java.util.Date;

public class CardHolder {

    private String completeName;

    private String dni;

    private String cuil;

    private String address;

    private String telephone;

    private Date entry;

    public CardHolder(String completeName, String dni, String cuil, String address, String telephone, Date entry) {
        this.completeName = completeName;
        this.dni = dni;
        this.cuil = cuil;
        this.address = address;
        this.telephone = telephone;
        this.entry = entry;
    }

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
}