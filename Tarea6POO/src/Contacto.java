abstract class Contacto extends Elemento{
    private Persona persona;
    private String telefono;
    private String correo;

    public Contacto(Persona persona, String telefono, String correo) {
        this.persona = persona;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public abstract String getTipo();

    public String getInfo() {
        return "Persona: " + persona + ", Teléfono: " + telefono + ", Correo: " + correo;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                persona +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                "} " + super.toString();
    }
}
