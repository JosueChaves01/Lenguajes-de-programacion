package main

import (
	"errors"
	"fmt"
)

type producto struct {
	nombre   string
	cantidad int
	precio   int
}
type listaProductos []producto

var lProductos listaProductos

const existenciaMinima int = 10 //la existencia mínima es el número mínimo debajo de el cual se deben tomar eventuales desiciones

func (l *listaProductos) agregarProducto(nombre string, cantidad int, precio int) {
	prod, err := l.buscarProducto(nombre)
	if err == nil {
		// Si el producto ya existe, actualiza la cantidad y el precio si es necesario
		prod.cantidad += cantidad
		if prod.precio != precio {
			prod.precio = precio
		}
	} else {
		// Si el producto no existe, agrégalo a la lista
		*l = append(*l, producto{nombre: nombre, cantidad: cantidad, precio: precio})
	}
}

// Hacer una función adicional que reciba una cantidad potencialmente infinita de productos previamente creados y los agregue a la lista
func (l *listaProductos) agregarVariosProductos(productos ...producto) {
	for _, p := range productos {
		l.agregarProducto(p.nombre, p.cantidad, p.precio)
	}
}

/*
Devuelve null y no 0 ya que se utiliza una libreria para devolver el error del que trata.
null en caso de que el prducto fuera encontrado,
un errors en caso contrario.
*/
func (l *listaProductos) buscarProducto(nombre string) (*producto, error) {
	for i := 0; i < len(*l); i++ {
		if (*l)[i].nombre == nombre {
			return &(*l)[i], nil // Producto encontrado, devolver el puntero al producto y nil como error
		}
	}
	return nil, errors.New("producto no encontrado") // Producto no encontrado, devolver nil como producto y un error
}

//funcion segun las indicaciones.
/*
func (l *listaProductos) buscarProducto(nombre string) (*producto, int32) {
	for i := 0; i < len(*l); i++ {
		if (*l)[i].nombre == nombre {
			return &(*l)[i], 0 // Producto encontrado, devolver el puntero al producto y nil como error
		}
	}
	return nil, 1 // Producto no encontrado, devolver nil como producto y un error
}
*/

//modifique la función para que esta vez solo retorne el producto como tal y que retorne otro valor adicional "err" conteniendo un 0 si no hay error y números mayores si existe algún
//tipo de error como por ejemplo que el producto no exista. Una vez implementada la nueva función, cambie los usos de la anterior función en funciones posteriores, realizando los cambios
//que el uso de la nueva función ameriten

func (l *listaProductos) venderProducto(nombre string, cantidad int) error {
	prod, err := l.buscarProducto(nombre)
	if err != nil {
		return err // Producto no encontrado
	}

	if existenciaMinima > prod.cantidad-cantidad {
		return errors.New("no hay suficiente cantidad de producto para vender")
	}
	prod.cantidad -= cantidad

	if prod.cantidad == existenciaMinima {
		// Si la cantidad llega a la exitencia minima, eliminar el producto de la lista
		err := l.eliminarProducto(nombre)
		if err != nil {
			return err // Error al intentar eliminar el producto
		}
	}

	return nil // Venta exitosa
}

// modificar para que cuando no haya existencia de cantidad de productos, el producto se elimine de "la lista" y notificar dicha acción
// modifique posteriormente para que se ingrese de parámetro la cantidad de productos a vender
func (l *listaProductos) eliminarProducto(nombre string) error { // para efectos de esta funcion se toma la existencia minima como la innexistencia de productos
	// por lo que si se supera en una venta las exitencias minimas, esta no se efectua y se muestra un error.
	for i, p := range *l {
		if p.nombre == nombre {
			// Eliminar el producto de la lista
			*l = append((*l)[:i], (*l)[i+1:]...)
			return nil // Producto eliminado exitosamente
		}
	}
	return errors.New("producto no encontrado") // Producto no encontrado en la lista
}

//haga una función para a partir del nombre del producto, se pueda modificar el precio del mismo Pero utilizando la función buscarProducto MODIFICADA PREVIAMENTE

func llenarDatos() {
	lProductos.agregarProducto("arroz", 15, 2500)
	lProductos.agregarProducto("frijoles", 4, 2000)
	lProductos.agregarProducto("leche", 8, 1200)
	lProductos.agregarProducto("cafe", 12, 4500)
	lProductos.agregarProducto("frijoles", 8, 2800)

	nuevosProductos := []producto{
		{"azucar", 20, 1500},
		{"aceite", 10, 3000},
		{"sal", 30, 800},
	}
	lProductos.agregarVariosProductos(nuevosProductos...)
}

// retorna una lista de productos cuya cantidad esta por debajo de la exitencia minima
func (l *listaProductos) listarProductosMinimos() listaProductos {
	var productosMinimos listaProductos

	for _, p := range *l {
		if p.cantidad <= existenciaMinima {
			// actualiza la lista de productos eliminando tambien lo que se encuentren por debajo de las exitencias minimas
			// l.eliminarProducto(p.nombre)
			productosMinimos = append(productosMinimos, p)
		}
	}

	return productosMinimos
}

func main() {
	llenarDatos()
	fmt.Println(lProductos.listarProductosMinimos())
	fmt.Println(lProductos)
	//venda productos
	lProductos.venderProducto("azucar", 10)
	lProductos.venderProducto("arroz", 3)
	fmt.Println(lProductos.venderProducto("frijoles", 3))
	fmt.Println(lProductos)
}
