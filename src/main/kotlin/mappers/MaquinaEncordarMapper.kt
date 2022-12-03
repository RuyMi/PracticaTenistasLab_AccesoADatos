package mappers

import entities.MaquinaEncordarDao
import entities.MaquinaPersonalizacionDao
import models.Maquina

fun MaquinaEncordarDao.fromMaquinaEncordadoraDaoToMaquinaEncordar(): Maquina.MaquinaEncordadora {
    return Maquina.MaquinaEncordadora(
        id = id.value,
        numSerie = numSerie,
        marca = marca,
        modelo = modelo,
        fechaAdquisicion = fechaAdquisicion,
        automatico = automatico,
        tensionMaxima = tensionMaxima,
        tensionMinima = tensionMinima
    )
}