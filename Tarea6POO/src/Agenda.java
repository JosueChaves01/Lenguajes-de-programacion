import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Agenda extends ElementoFactoryImpl{
    private List<Elemento> elementos = new ArrayList<>();
    private static Agenda instance;

    private Scanner scanner = new Scanner(System.in);

    // Constructor privado para evitar la instanciación desde fuera
    private Agenda() {}

    // Método estático para obtener la única instancia de la clase
    public static Agenda getInstance() {
        if (instance == null) {
            instance = new Agenda();
        }
        return instance;
    }

    // Métodos para retornar una persona, null si no se encuentra
    public  Persona buscarPersona(String cedula){
        for(Elemento elemento : elementos){
            if(elemento instanceof Contacto){
                if(((Contacto) elemento).getPersona().cedula.equals(cedula)){
                    return ((Contacto) elemento).getPersona();
                };
            }
        }
        return null;
    }

    // Métodos para crear contactos solicitando los datos por consola
    public void crearContactoSencillo() {
        System.out.println("Ingrese el nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la edad:");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Ingrese la cédula:");
        String cedula = scanner.nextLine();
        System.out.println("Ingrese el teléfono:");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese el correo:");
        String correo = scanner.nextLine();

        if (buscarPersona(cedula) == null) {
            elementos.add(this.crearContacto(new Persona(nombre, edad, cedula), telefono, correo));
        }
    }

    public void crearContactoEmpresarial() {
        System.out.println("Ingrese el nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la edad:");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Ingrese la cédula:");
        String cedula = scanner.nextLine();
        System.out.println("Ingrese el teléfono:");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese el correo:");
        String correo = scanner.nextLine();
        System.out.println("Ingrese la empresa:");
        String empresa = scanner.nextLine();
        System.out.println("Ingrese el puesto:");
        String puesto = scanner.nextLine();

        if (buscarPersona(cedula) == null) {
            elementos.add(this.crearContactoEmp(new Persona(nombre, edad, cedula), telefono, correo, empresa, puesto));
        }
    }

    public void crearContactoFamiliar() {
        System.out.println("Ingrese el nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la edad:");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Ingrese la cédula:");
        String cedula = scanner.nextLine();
        System.out.println("Ingrese el teléfono:");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese el correo:");
        String correo = scanner.nextLine();
        System.out.println("Ingrese el parentezco:");
        String parentezco = scanner.nextLine();
        System.out.println("Ingrese la ubicación:");
        String ubicacion = scanner.nextLine();

        if (buscarPersona(cedula) == null) {
            elementos.add(this.crearContactoFam(new Persona(nombre, edad, cedula), telefono, correo, parentezco, ubicacion));
        }
    }

    // Métodos para crear eventos solicitando los datos por consola
    public void crearEventoSencillo() {
        System.out.println("Ingrese el nombre del evento:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el lugar del evento:");
        String lugar = scanner.nextLine();
        System.out.println("Ingrese la fecha del evento (formato: dd/mm/yyyy):");
        String fecha = scanner.nextLine();

        elementos.add(this.crearEvento(nombre, lugar, fecha));
    }

    public void crearEventoFamiliar() {
        System.out.println("Ingrese el nombre del evento:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el lugar del evento:");
        String lugar = scanner.nextLine();
        System.out.println("Ingrese la fecha del evento (formato: dd/mm/yyyy):");
        String fecha = scanner.nextLine();
        System.out.println("¿Requiere regalo? (true/false):");
        boolean regalo = scanner.nextBoolean();
        scanner.nextLine(); // Consumir el salto de línea

        elementos.add(this.crearEventoFam(nombre, lugar, fecha, regalo));
    }

    public void crearEventoEmpresarial() {
        System.out.println("Ingrese el nombre del evento:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el lugar del evento:");
        String lugar = scanner.nextLine();
        System.out.println("Ingrese la fecha del evento (formato: dd/mm/yyyy):");
        String fecha = scanner.nextLine();
        System.out.println("Ingrese la sede del evento:");
        String sede = scanner.nextLine();

        elementos.add(this.crearEventoEmp(nombre, lugar, fecha, sede));
    }

    // Método para modificar eventos solicitando los datos por consola
    public void modificarEvento() {
        System.out.println("Ingrese el nombre del evento a modificar:");
        String nombre = scanner.nextLine();

        Evento evento = null;
        for (Elemento elemento : elementos) {
            if (elemento instanceof Evento e && e.getNombre().equals(nombre)) {
                evento = e;
                break;
            }
        }

        if (evento == null) {
            System.out.println("Evento no encontrado.");
            return;
        }

        System.out.println("Ingrese el nuevo nombre (o presione Enter para dejar sin cambios):");
        String nuevoNombre = scanner.nextLine();
        System.out.println("Ingrese la nueva ubicación (o presione Enter para dejar sin cambios):");
        String ubicacion = scanner.nextLine();
        System.out.println("Ingrese la nueva fecha (formato: dd/mm/yyyy, o presione Enter para dejar sin cambios):");
        String nuevaFecha = scanner.nextLine();

        if (!nuevoNombre.isEmpty()) {
            evento.setNombre(nuevoNombre);
        }
        if (!ubicacion.isEmpty()) {
            evento.setUbicacion(ubicacion);
        }
        if (!nuevaFecha.isEmpty()) {
            evento.setFecha(nuevaFecha);
        }

        if (evento instanceof EventoFamiliar eventoFamiliar) {
            System.out.println("¿Requiere regalo? (true/false, o presione Enter para dejar sin cambios):");
            String regaloInput = scanner.nextLine();
            if (!regaloInput.isEmpty()) {
                eventoFamiliar.setRegalo(Boolean.parseBoolean(regaloInput));
            }
        } else if (evento instanceof EventoEmpresarial eventoEmpresarial) {
            System.out.println("Ingrese la nueva sede (o presione Enter para dejar sin cambios):");
            String nuevaSede = scanner.nextLine();
            if (!nuevaSede.isEmpty()) {
                eventoEmpresarial.setSede(nuevaSede);
            }
        }
    }

    // Método para modificar contactos solicitando los datos por consola
    public void modificarContacto() {
        System.out.println("Ingrese la cédula del contacto a modificar:");
        String cedula = scanner.nextLine();

        Contacto contacto = null;
        for (Elemento elemento : elementos) {
            if (elemento instanceof Contacto c && c.getPersona().getCedula().equals(cedula)) {
                contacto = c;
                break;
            }
        }

        if (contacto == null) {
            System.out.println("Contacto no encontrado.");
            return;
        }

        System.out.println("Ingrese el nuevo teléfono (o presione Enter para dejar sin cambios):");
        String nuevoTelefono = scanner.nextLine();
        System.out.println("Ingrese el nuevo correo (o presione Enter para dejar sin cambios):");
        String nuevoCorreo = scanner.nextLine();

        if (!nuevoTelefono.isEmpty()) {
            contacto.setTelefono(nuevoTelefono);
        }
        if (!nuevoCorreo.isEmpty()) {
            contacto.setCorreo(nuevoCorreo);
        }

        if (contacto instanceof ContactoEmpresarial contactoEmpresarial) {
            System.out.println("Ingrese la nueva empresa (o presione Enter para dejar sin cambios):");
            String nuevaEmpresa = scanner.nextLine();
            System.out.println("Ingrese el nuevo puesto (o presione Enter para dejar sin cambios):");
            String nuevoPuesto = scanner.nextLine();

            if (!nuevaEmpresa.isEmpty()) {
                contactoEmpresarial.setEmpresa(nuevaEmpresa);
            }
            if (!nuevoPuesto.isEmpty()) {
                contactoEmpresarial.setPuesto(nuevoPuesto);
            }
        } else if (contacto instanceof ContactoFamiliar contactoFamiliar) {
            System.out.println("Ingrese el nuevo parentezco (o presione Enter para dejar sin cambios):");
            String nuevoParentezo = scanner.nextLine();
            System.out.println("Ingrese la nueva ubicación (o presione Enter para dejar sin cambios):");
            String nuevaUbicacion = scanner.nextLine();

            if (!nuevoParentezo.isEmpty()) {
                contactoFamiliar.setParentesco(nuevoParentezo);
            }
            if (!nuevaUbicacion.isEmpty()) {
                contactoFamiliar.setUbicacion(nuevaUbicacion);
            }
        }
    }

    public void eliminarContacto(String cedula) {
        Iterator<Elemento> iterator = elementos.iterator();
        while (iterator.hasNext()) {
            Elemento elemento = iterator.next();
            if (elemento instanceof Contacto contacto) {
                if (contacto.getPersona().getCedula().equals(cedula)) {
                    iterator.remove(); // Eliminamos el contacto de la lista
                    return; // Terminamos la búsqueda después de eliminar el contacto
                }
            }
        }
    }

    public List<Elemento> filtrarElementos(Class<? extends Elemento> tipoElemento) {
        return elementos.stream()
                .filter(tipoElemento::isInstance)
                .collect(Collectors.toList());
    }

    private List<Elemento> filtrarElementosPorClase(String tipoElemento) {
        switch (tipoElemento) {
            case "Contacto":
                return filtrarElementos(Contacto.class);
            case "ContactoEmpresarial":
                return filtrarElementos(ContactoEmpresarial.class);
            case "ContactoFamiliar":
                return filtrarElementos(ContactoFamiliar.class);
            case "Evento":
                return filtrarElementos(Evento.class);
            case "EventoFamiliar":
                return filtrarElementos(EventoFamiliar.class);
            case "EventoEmpresarial":
                return filtrarElementos(EventoEmpresarial.class);
            default:
                System.out.println("Tipo de elemento no válido. Retornando lista vacía.");
                return new ArrayList<>();
        }
    }
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear contacto sencillo");
            System.out.println("2. Crear contacto empresarial");
            System.out.println("3. Crear contacto familiar");
            System.out.println("4. Crear evento sencillo");
            System.out.println("5. Crear evento familiar");
            System.out.println("6. Crear evento empresarial");
            System.out.println("7. Modificar evento");
            System.out.println("8. Modificar contacto");
            System.out.println("9. Eliminar contacto");
            System.out.println("10. Mostrar lista elementos filtrada segun clase (Elemento, Evento,EventoFamiliar,..., Contacto,ContactoEmpresarial,...)");
            System.out.println("11. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearContactoSencillo();
                    break;
                case 2:
                    crearContactoEmpresarial();
                    break;
                case 3:
                    crearContactoFamiliar();
                    break;
                case 4:
                    crearEventoSencillo();
                    break;
                case 5:
                    crearEventoFamiliar();
                    break;
                case 6:
                    crearEventoEmpresarial();
                    break;
                case 7:
                    modificarEvento();
                    break;
                case 8:
                    modificarContacto();
                    break;
                case 9:
                    System.out.println("Ingrese la cédula del contacto a eliminar:");
                    String cedula = scanner.nextLine();
                    eliminarContacto(cedula);
                    break;
                case 10:
                    System.out.println("Ingrese el tipo de elemento para filtrar (Contacto, ContactoEmpresarial, ContactoFamiliar, Evento, EventoFamiliar, EventoEmpresarial):");
                    String tipoElemento = scanner.nextLine();
                    List<Elemento> elementosFiltrados = filtrarElementosPorClase(tipoElemento);
                    System.out.println("Elementos filtrados:");
                    for (Elemento elemento : elementosFiltrados) {
                        System.out.println(elemento);
                    }
                    break;
                case 11:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    public void cargarDatos(){
        this.elementos.add(this.crearContacto(new Persona("Josue",22,"12345678"),"88888888","jos.chaves@estudiantec.cr"));
        this.elementos.add(this.crearContacto(new Persona("Carlos",24,"12345688"),"88888888","carlos@correo.com"));
        this.elementos.add(this.crearContacto(new Persona("Luis",32,"12345888"),"88888888","luis@correo.com"));
        this.elementos.add(this.crearContacto(new Persona("David",19,"12348888"),"88888888","david@correo.com"));
        this.elementos.add(this.crearContacto(new Persona("Roman",26,"12312313"),"88888888","roman@correo.com"));

        this.elementos.add(this.crearContactoFam(new Persona("Jose",50,"41241241"),"88888888","jose@correo.com","Padre","San Carlos"));
        this.elementos.add(this.crearContactoFam(new Persona("Robert",18,"12412412"),"88888888","robert@correo.com","Hermano","San Carlos"));
        this.elementos.add(this.crearContactoFam(new Persona("Oscar",48,"51241243"),"88888888","oscar@correo.com","Tio","San Ramon"));
        this.elementos.add(this.crearContactoFam(new Persona("Efren",24,"12312233"),"88888888","efren@correo.com","Primo","Zarcero"));
        this.elementos.add(this.crearContactoFam(new Persona("Steven",20,"13512341"),"88888888","steven@correo.com","Hermano","San Carlos"));

        this.elementos.add(this.crearContactoEmp(new Persona( "Esteban",50,"7354673"),"88888888","esteban@correo.com","Intel","Gerente"));
        this.elementos.add(this.crearContactoEmp(new Persona("Jeremy",18,"62543624"),"88888888","jeremy@correo.com","Ancora","Programador"));
        this.elementos.add(this.crearContactoEmp(new Persona("Brayan",48,"64524315"),"88888888","brayan@correo.com","CocaCola","Recursos Humanos"));
        this.elementos.add(this.crearContactoEmp(new Persona("Elon",24,"53215235"),"88888888","elon@correo.com","First Factory","CEO"));
        this.elementos.add(this.crearContactoEmp(new Persona("Mark",20,"35153215"),"88888888","mark@correo.com","PnG","Representante"));

        this.elementos.add(crearEvento("Reunion hijos", "Tucano", "5/30/2024"));
        this.elementos.add(crearEvento("Fiesta", "KFC", "5/24/2024"));
        this.elementos.add(crearEvento("Visita", "Casa Padres", "6/10/2024"));
        this.elementos.add(crearEvento("Dia de la independecia", "", "7/15/2024"));
        this.elementos.add(crearEvento("Cita Medica", "Hospital", "7/10/2024"));

        this.elementos.add(crearEventoFam("cumpleanios", "Casa Laura", "5/15/2024",true));
        this.elementos.add(crearEventoFam("Visita", "Casa papa", "6/14/2024",false));
        this.elementos.add(crearEventoFam("Fiesta de Navidad", "Casa Padres", "12/24/2024",true));
        this.elementos.add(crearEventoFam("Cena", "Casa suegra", "6/27/2024",false));
        this.elementos.add(crearEventoFam("Reunion Familiar", "Casa", "8/10/2024",false));

        this.elementos.add(crearEventoEmp("Entrega Final", "San Jose", "5/25/2024","Empresa"));
        this.elementos.add(crearEventoEmp("Reunion Clientes", "San Jose", "6/12/2024","Centro de reuniones"));
        this.elementos.add(crearEventoEmp("Fiesta Jefe", "San Jose", "5/24/2024","Casa jefes"));
        this.elementos.add(crearEventoEmp("Cena", "San Jose", "6/17/2024","Empresa"));
        this.elementos.add(crearEventoEmp("Celebracion anual", "San Jose", "5/15/2024","Comedor"));

    }

    @Override
    public String toString() {
        return
                "elementos=" + elementos;
    }
}
