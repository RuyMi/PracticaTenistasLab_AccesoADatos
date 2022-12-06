package mappers

import entities.TurnoDao
import models.Turno

/**
 * From turno dao to turno
 *
 * @return
 */
fun TurnoDao.fromTurnoDaoToTurno(): Turno {
    return Turno(
        id = id.value,
        uuidTurno = uuidTurno,
        fechaInicio = fechaInicio,
        fechaFin = fechaFin
    )
}