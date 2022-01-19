# shopping-cart
Ejemplo arquitectura hexagonal. SpringBoot - NodeJS

Aplicaci�n de comercio electr�nico. La aplicaci�n proporciona dos casos de uso diferentes:

## CRUD de productos

Los productos tienen los siguientes datos:

* Marca
* Nombre
* Stock (cantidad de producto disponible)

API REST Product:

* GET /api/products - Muestra los productos
* POST /api/products - A�ade un producto
* PUT /api/products/:id/stock/:quantity � Modifica el stock de un producto
* GET /api/products/:id - Muestra un producto en espec�fico
* DELETE /api/products/:id - Borra un producto en espec�fico


##Gesti�n del carrito de la compra

* A�adir productos al carrito
* Eliminar productos del carrito
* Eliminar el carrito
* Finalizar el carrito. Cuando se finaliza el carrito, la aplicaci�n debe validar el carrito, porque puede haber productos que ya no est�n disponibles. Para ello, se debe implementar un servicio que realice esta validaci�n comprobando que hay stock disponible y actualizando el stock de los productos.

API REST shoppingcarts:

* POST /api/shoppingcarts - Crea un carrito de compra
* PATCH /api/shoppingcarts/:id - Modifica el carrito de compra para pasar el estado a completo (finalizar el carrito)
* GET /api/shoppingcarts/:id - Obtiene un carrito de compra espec�fico
* DELETE /api/shoppingcarts/:id - Borra un carrito de compra espec�fico
* POST /api/shoppingcarts/:cart_id/product/:prod_id/quantity/:prod_quantity - A�ade un producto al carrito de compra, en la cantidad indicada por :prod_quantity. Si ya existiera lo modifica con la nueva cantidad. Debe validar que hay suficiente stock.
* DELETE /api/shoppingcarts/:cart_id/product/:prod_id - Borra un producto espec�fico de un carrito de compra


JAVA

https://bootify.io/app/X11EHHCRQCVS