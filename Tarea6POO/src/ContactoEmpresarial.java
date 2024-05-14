public class ContactoEmpresarial extends Contacto {
    private String empresa;
    private String puesto;

    public ContactoEmpresarial(Persona persona, String telefono, String correo, String empresa, String puesto) {
        super(persona,telefono,correo);
        this.empresa = empresa;
        this.puesto = puesto;
    }

    public String getTipo() {
        return "Contacto Empresarial";
    }

    public String getInfo() {
        return super.getInfo() + ", Empresa: " + empresa + ", Puesto: " + puesto;
    }

    @Override
    public String toString() {
        return "ContactoEmpresarial{" +
                "empresa='" + empresa + '\'' +
                ", puesto='" + puesto + '\'' +
                "} " + super.toString();
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}