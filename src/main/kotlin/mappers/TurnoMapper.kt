package mappers

import entities.TareaDao
import entities.TurnoDao
import models.Tarea
import models.Turno

fun TurnoDao.fromTurnoDaoToTurno(): Turno {
    return Turno(
        id = id.value,
        uuidTurno = uuidTurno,
        fechaInicio = fechaInicio,
        fechaFin = fechaFin
    )
}