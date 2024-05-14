class ContactoSencillo extends Contacto {
    public ContactoSencillo(Persona persona, String telefono, String correo) {
        super(persona,telefono,correo);
    }

    public String getTipo() {
        return "Contacto Sencillo";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}