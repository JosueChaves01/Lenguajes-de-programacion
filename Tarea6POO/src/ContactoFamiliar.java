public class ContactoFamiliar extends Contacto {
    private String parentesco;
    private String ubicacion;

    public ContactoFamiliar(Persona persona, String telefono, String correo, String parentesco, String ubicacion) {
        super(persona,telefono,correo);
        this.parentesco = parentesco;
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return "Contacto Familiar";
    }
    public String getInfo() {
        return super.getInfo() + ", Parentesco: " + parentesco + ", Ubicación: " + ubicacion;
    }

    @Override
    public String toString() {
        return "ContactoFamiliar{" +
                "parentesco='" + parentesco + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                "} " + super.toString();
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
