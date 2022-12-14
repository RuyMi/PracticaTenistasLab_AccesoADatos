package mappers

import entities.MaquinaPersonalizacionDao
import models.Maquina

/**
 * From maquina personalizacion dao to maquina personalizacion
 *
 * @return
 */
fun MaquinaPersonalizacionDao.fromMaquinaPersonalizacionDaoToMaquinaPersonalizacion(): Maquina.MaquinaPersonalizacion {
    return  Maquina.MaquinaPersonalizacion(
        id=id.value,
        numSerie = numSerie,
        marca =marca,
        modelo =modelo,
        fechaAdquisicion =fechaAdquisicion,
        swingweight =swingweight,
        balance =balance,
        rigidez =rigidez
    )
}