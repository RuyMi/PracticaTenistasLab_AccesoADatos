package mappers

import entities.MaquinaPersonalizacionDao
import models.Maquina

fun MaquinaPersonalizacionDao.fromMaquinaPersonalizacionDaoToMaquinaPersonalizacion(): Maquina.MaquinaPersonalizacion {
    return  Maquina.MaquinaPersonalizacion(
        id=id,
        numSerie = numSerie,
        marca =marca,
        modelo =modelo,
        fechaAdquisicion =fechaAdquisicion,
        swingweight =swingweight,
        balance =balance,
        rigidez =rigidez
    )
}