- Modelo Usuario (id, uuid, nombre, apellido, email, password[codificado], perfil[ADMINISTRADOR, ENCORDADOR, TENISTA]) (Para lo de los turnos, capar en el controlador solo administrador)
- Máquina(id, num_serie (uuid), marca, modelo, fecha_adquisición)
- Maquina encordadora (Manual o automatico ***, tensión maxima y minima de trabajo,)
- Maquina personalizacion (Swingweight (true, false), balance (equilibrio), rigidez (resilencia))
- Pedido (Solo lo puede hacer un tenista) (Cuando se haga un pedido se asigna a un encordador) (id, uuid, estado [RECIBIDO, EN_PROCESO, TERMINADO], maquina asociada que se necesita, fecha de entrada, fecha salida programada, fecha entrega (la misma que la salida programada y cuando se entregue se actualiza) (ejemplo de esto), precio (suma de todas las acciones)
- Tarea/Accion(id, uuid, raqueta/-s, precio, accion por raqueta (si se necesita)) Si es encordado necesitamos saber (encordado 1 o 2, si es un encordado es horizontal y vertical y en nudos 2 o 4 pero si son 2 encordados si o si seran 4 nudos) (El precio del encordado son 15€ + el precio la raqueta) Personalizacion (peso en gramos, balance y rigidez, precio 60)
- Encordado personalizacion y adquisicion heredan de Tarea
- Adquisicion es el precio del producto adquirido
- Producto(id, uuid, marca, modelo, precio, stock)

Restricciones:
1. El encordador no puede tener mas de 2 pedidos activos a la vez, es decir,
cuando vayamos a asignar un pedido a un encordador tenemos que comprobar que el encordador de todos los pedidos que tiene no tenga mas de 2 con el estado en "activo".
2. Del turno, puede ser 2, mañana o tarde.

