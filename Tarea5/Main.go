package main

import (
	"fmt"
	"sort"
	"strings"
)

// Estructura cliente
type infoCliente struct {
	nombre string
	correo string
	edad   int32
}

// Lista de personas
type Clientes []infoCliente

var listaClientes Clientes

func (c *Clientes) agregarCliente(nombre string, correo string, edad int32) {
	if c.buscarCliente(nombre) == nil { //no se encontro cliente con este nombre se agrega uno nuevo
		*c = append(*c, infoCliente{nombre, correo, edad})
	} else {
		fmt.Println("cant add existing client to the list")
	}
}

func (c *Clientes) buscarCliente(nom string) *infoCliente {
	for i, cliente := range *c {
		if cliente.nombre == nom {
			return &(*c)[i] // Devuelve un puntero al cliente encontrado
		}
	}
	return nil // Retorna nil si no se encuentra el cliente
}

func (c *Clientes) listaClientes_ApellidoEnCorreo() Clientes {
	// Función para verificar si el apellido está contenido en el correo electrónico
	contieneApellido := func(cliente infoCliente) bool {
		return strings.Contains(cliente.correo, extraerApellido(cliente.nombre))
	}

	// Filtrar la lista de clientes según el apellido en el correo
	clientesFiltrados := filter2(*c, contieneApellido)

	return clientesFiltrados
}

func extraerNombre(nombreCompleto string) string {
	// Dividir el nombre completo en palabras
	palabras := strings.Split(nombreCompleto, " ")

	// Verificar si hay al menos dos palabras
	if len(palabras) >= 2 {
		// La segunda palabra es el apellido, convertirla a minúsculas
		apellido := strings.ToLower(palabras[0])
		return apellido
	}

	// Si no hay al menos dos palabras, devolver una cadena vacía
	return nombreCompleto
}

func extraerApellido(nombreCompleto string) string {
	// Dividir el nombre completo en palabras
	palabras := strings.Split(nombreCompleto, " ")

	// Verificar si hay al menos dos palabras
	if len(palabras) >= 2 {
		// La segunda palabra es el apellido, convertirla a minúsculas
		apellido := strings.ToLower(palabras[1])
		return apellido
	}

	// Si no hay al menos dos palabras, devolver una cadena vacía
	return ""
}

func (c *Clientes) cantidadCorreosCostaRica() int32 {
	// Función para verificar si el correo electrónico pertenece a un dominio de Costa Rica
	esCR := func(cliente infoCliente) bool {
		return strings.HasSuffix(cliente.correo, ".cr")
	}

	// Filtrar la lista de clientes según el dominio del correo
	clientesCRCorreo := filter2(*c, esCR)

	// Convertir los clientes a []interface{}
	interfazClientes := make([]interface{}, len(clientesCRCorreo))
	for i, cliente := range clientesCRCorreo {
		interfazClientes[i] = cliente
	}

	// Contar la cantidad de clientes con correos de Costa Rica utilizando reduce
	cantidad := reduce(interfazClientes)

	return cantidad
}

func (clientes *Clientes) clientesSugerenciasCorreos() []infoCliente {
	// Función para verificar si el correo electrónico hace referencia al nombre de la persona
	correoContieneNombre := func(cliente infoCliente) bool {
		return !strings.Contains(cliente.correo, extraerNombre(cliente.nombre))
	}

	// Filtrar la lista de clientes según si el correo contiene el nombre
	clientesSinNombreEnCorreo := filter2(*clientes, correoContieneNombre)

	// Función para cambiar las direcciones de correo electrónico de los clientes que no hacen referencia al nombre
	cambiarCorreo := func(cliente infoCliente) infoCliente {
		if correoContieneNombre(cliente) {
			// Aquí puedes implementar tu lógica para cambiar el correo electrónico
			// Por ejemplo, podrías agregar el nombre al correo o cambiar el dominio, etc.
			// Por ahora, simplemente agregamos el nombre al inicio del correo seguido de un sufijo
			nuevoCorreo := extraerNombre(cliente.nombre)[0:3] + extraerApellido(cliente.nombre)[0:3] + "@" + obtenerDominio(cliente.correo)
			cliente.correo = nuevoCorreo
		}
		return cliente
	}

	// Crear una nueva lista de clientes con los correos electrónicos modificados
	clientesModificados := make(Clientes, len(clientesSinNombreEnCorreo))
	for i, cliente := range clientesSinNombreEnCorreo {
		clientesModificados[i] = cambiarCorreo(cliente)
	}

	return clientesModificados
}
func (c *Clientes) correosOrdenadosAlfabeticos() []string {
	// Crear una slice de strings para almacenar los correos electrónicos
	correos := make([]string, len(*c))

	// Extraer los correos electrónicos de los clientes
	for i, cliente := range *c {
		correos[i] = cliente.correo
	}

	// Ordenar los correos electrónicos alfabéticamente
	sort.Strings(correos)

	return correos
}
func obtenerDominio(correo string) string {
	// Busca la posición del "@" en el correo
	indiceArroba := strings.Index(correo, "@")

	// Si no se encuentra el "@" o está al final del correo, retorna una cadena vacía
	if indiceArroba == -1 || indiceArroba == len(correo)-1 {
		return ""
	}

	// Retorna el substring que está después del "@" en el correo
	return correo[indiceArroba+1:]
}

func map2[P1, P2 any](list []P1, f func(P1) P2) []P2 {
	mapped := make([]P2, len(list))

	for i, e := range list {
		mapped[i] = f(e)
	}
	return mapped
}

func filter2[P1 any](list []P1, f func(P1) bool) []P1 {
	filtered := make([]P1, 0)

	for _, element := range list {
		if f(element) {
			filtered = append(filtered, element)
		}
	}
	return filtered
}

func reduce(list []interface{}) int32 {
	return int32(len(list))
}

func main() {

	listaClientes.agregarCliente("Oscar Viquez", "oviquez@tec.ac.cr", 44)
	listaClientes.agregarCliente("Pedro Perez", "elsegundo@gmail.com", 30)
	listaClientes.agregarCliente("Maria Lopez", "mlopez@hotmail.com", 18)
	listaClientes.agregarCliente("Juan Rodriguez", "jrodriguez@gmail.com", 25)
	listaClientes.agregarCliente("Luisa Gonzalez", "muyseguro@tec.ac.cr", 67)
	listaClientes.agregarCliente("Marco Rojas", "loquesea@hotmail.com", 47)
	listaClientes.agregarCliente("Marta Saborio", "msaborio@gmail.com", 33)
	listaClientes.agregarCliente("Camila Segura", "csegura@ice.co.cr", 19)
	listaClientes.agregarCliente("Fernando Rojas", "frojas@estado.gov", 27)
	listaClientes.agregarCliente("Rosa Ramirez", "risuenna@gmail.com", 50)

	// Obtener la lista de clientes cuyos correos electrónicos contienen el apellido "Rodriguez"
	clientesConApellidoRodriguez := listaClientes.listaClientes_ApellidoEnCorreo()

	// Imprimir la lista de clientes filtrada
	fmt.Println("Clientes con apellido en el correo:")
	for _, cliente := range clientesConApellidoRodriguez {
		fmt.Println(cliente)
	}
	fmt.Println("Cantidad de clientes con correo de costa rica (.cr):")
	fmt.Println(listaClientes.cantidadCorreosCostaRica())
	clientesModificados := listaClientes.clientesSugerenciasCorreos()

	// Imprimir los clientes modificados
	fmt.Println("Clientes con sugerencias de correos:")
	for _, cliente := range clientesModificados {
		fmt.Println(cliente)
	}

	// Obtener una lista de correos electrónicos ordenados alfabéticamente
	correosOrdenados := listaClientes.correosOrdenadosAlfabeticos()
	// Imprimir los correos electrónicos ordenados
	fmt.Println("Correos electrónicos ordenados alfabéticamente:")
	fmt.Println(correosOrdenados)

}
