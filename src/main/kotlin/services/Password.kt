package services

import java.security.MessageDigest


/**
 * Password
 *
 */
class Password {
    /**
     * Sha256
     *
     * @param cadena
     * @return
     */
    fun SHA256(cadena: String): String {
        var md: MessageDigest? = null
        var hash: ByteArray? = null
        // Llamamos a la función de hash de java
        try {
            md = MessageDigest.getInstance("SHA-256")
            hash = md.digest(cadena.toByteArray(charset("UTF-8")))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return hash.toString()
    }


}