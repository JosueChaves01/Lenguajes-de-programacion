public class ElementoFactoryImpl implements ElementoFactory{
    @Override
    public Elemento crearContacto(Persona per, String telefono,String correo) {
        return new ContactoSencillo(per, telefono, correo);
    }

    @Override
    public Elemento crearContactoEmp(Persona per, String t, String c, String e, String p) {
        return new ContactoEmpresarial(per,t,c,e,p);
    }

    @Override
    public Elemento crearContactoFam(Persona per, String t, String c, String p, String u) {
        return new ContactoFamiliar(per,t,c,p,u);
    }
    @Override
    public Elemento crearEvento(String n, String u, String f) {
        // Por simplicidad, se pueden proporcionar valores predeterminados o solicitar al usuario la entrada de datos.
        return new EventoSencillo(n,u,f);
    }

    @Override
    public Elemento crearEventoFam(String n, String u, String f, boolean regalo) {
        return new EventoFamiliar(n,u,f,regalo);
    }

    @Override
    public Elemento crearEventoEmp(String n, String u, String f, String s) {
        // Por simplicidad, se pueden proporcionar valores predeterminados o solicitar al usuario la entrada de datos.
        return new EventoEmpresarial(n,u,f,s);
    }
}
