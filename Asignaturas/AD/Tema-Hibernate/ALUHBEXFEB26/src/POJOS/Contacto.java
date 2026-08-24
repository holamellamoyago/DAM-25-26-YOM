package POJOS;

public class Contacto {
    private String email;
    private String telefonofijo, telefonomovil;

    public Contacto() {
    }

    public Contacto(String email, String tlfnFijo, String tlfnMovil) {
        this.email = email;
        this.telefonofijo = tlfnFijo;
        this.telefonomovil = tlfnMovil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonofijo() {
        return telefonofijo;
    }

    public void setTelefonofijo(String telefonofijo) {
        this.telefonofijo = telefonofijo;
    }

    public String getTelefonomovil() {
        return telefonomovil;
    }

    public void setTelefonomovil(String telefonomovil) {
        this.telefonomovil = telefonomovil;
    }

    @Override
    public String toString() {
        return "Contacto [email=" + email + ", telefonofijo=" + telefonofijo + ", telefonomovil=" + telefonomovil + "]";
    }

    



}
