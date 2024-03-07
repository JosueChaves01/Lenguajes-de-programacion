(define (agregarProducto nombre cantidad precio lista)
  (cond ((null? lista)
         (list (list nombre cantidad precio)))
        ((equal? nombre (caar lista))
         (cons (list
                (caar lista)
                (+ (list-ref (car lista) 1) cantidad)
                precio)
               (cdr lista)))
        (else (cons (car lista) (agregarProducto nombre cantidad precio (cdr lista))))))


(define (venderProducto lista nombre cantidad)
  (cond ((null? lista)
         (display "No existe producto para vender\n")
         lista)
        ((equal? nombre (caar lista))
         (let ((nueva-cantidad (- (list-ref (car lista) 1) cantidad)))
           (if (>= nueva-cantidad 0)
               (cons (list
                      (caar lista)
                      nueva-cantidad
                      (caddr (car lista)))
                     (cdr lista))
               (begin
                 (display "No hay suficiente cantidad para vender\n")
                 lista))))
        (else
         (cons (car lista)
               (venderProducto (cdr lista) nombre cantidad)))))

(define (agregarProducto nombre cantidad precio lista)
  (cond ((null? lista)
         (list (list nombre cantidad precio)))
        ((equal? nombre (caar lista))
         (cons (list
                (caar lista)
                (+ (list-ref (car lista) 1) cantidad)
                precio)
               (cdr lista)))
        (else (cons (car lista) (agregarProducto nombre cantidad precio (cdr lista))))))

(define (agregar_a_factura factura producto cantidad inventario)
  (define producto_en_factura (filter (lambda (item) (equal? (car item) producto)) factura))
  (if (not (null? producto_en_factura))
      (let* ((producto_existente (car producto_en_factura))
             (cantidad_existente (cadr producto_existente))
             (precio_existente (* cantidad_existente (caddr producto_existente))))
        (let ((nueva_cantidad (+ cantidad_existente cantidad)))
          (if (>= (cadr (car (filter (lambda (item) (equal? (car item) producto)) inventario))) nueva_cantidad)
                (cons (list producto nueva_cantidad (caddr producto_existente))
                      (filter (lambda (item) (not (equal? (car item) producto))) factura))
              (begin
                (display "No hay suficiente cantidad disponible para agregar a la factura.\n")
                factura))))
      (let ((producto_en_inventario (filter (lambda (item) (equal? (car item) producto)) inventario)))
        (if (not (null? producto_en_inventario))
            (let* ((producto_existente (car producto_en_inventario))
                   (cantidad_disponible (cadr producto_existente))
                   (precio_producto (caddr producto_existente)))
              (if (>= cantidad_disponible cantidad)
                  (let ((nuevo_inventario (venderProducto inventario producto cantidad)))
                    (cons (list producto cantidad precio_producto) factura))
                  (begin
                    (display "No hay suficiente cantidad disponible para agregar a la factura.\n")
                    factura)))
            (begin
              (display "El producto no está disponible en el inventario.\n")
              factura)))))

(define (calcular_impuesto_total factura precio_minimo)
  (define (calcular_impuesto_producto producto)
    (let* ((nombre (car producto))
           (cantidad (cadr producto))
           (precio_unitario (caddr producto)))
      (if (> precio_unitario precio_minimo)
          (* cantidad precio_unitario 0.13)
          0)))
  (foldl + 0 (map calcular_impuesto_producto factura)))


(define (calcular_total factura)
  (define (calcular_precio_producto producto)
    (let* ((cantidad (cadr producto))
           (precio_unitario (caddr producto)))
      (* cantidad precio_unitario)))
  (foldl + 0 (map calcular_precio_producto factura)))

(define (existenciasMinimas lista cantidad)
  (filter (lambda (x) (<= (cadr x) cantidad))
          lista))

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~;

(define (string-split string delimiter)
  (regexp-split (regexp (format "~a" delimiter)) string))

(define (sub_cadenas palabra lista)
  (filter (lambda (x) (member palabra (string-split x " ")))
          lista))

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~;

(define listaProductos '(("Arroz" 10 1800) ("Pan" 5 1000) ("Frijoles" 10 1500) ("Huevos" 10 1450) ("Leche" 15 950) ("Sal" 20 1200)))

(define factura '(("Pan" 1 1000) ("Arroz" 5 1800) ("Frijoles" 3 1500)))
(define factura2 '(("Huevos" 1 1450) ("Leche" 5 950) ("Sal" 1 1200)))

(agregar_a_factura factura2 "Pan" 5 listaProductos)
(agregar_a_factura factura "Arroz" 5 listaProductos)
(agregar_a_factura (agregar_a_factura factura "Arroz" 5 listaProductos) "Pan" 5 listaProductos)

(calcular_impuesto_total factura 1500)
(calcular_impuesto_total factura 1000)
(calcular_impuesto_total factura 1000)

(calcular_total factura)
(calcular_total factura2)
(+ (calcular_total factura) (calcular_impuesto_total factura 1400))

(sub_cadenas "la" '("la casa" "el perro" "pintando la cerca"))

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~;


