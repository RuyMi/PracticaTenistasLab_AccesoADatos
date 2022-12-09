package services

import com.google.common.hash.Hashing
import java.nio.charset.StandardCharsets


/**
 * Password
 *
 */
class Password {

    fun encriptar(originalString: String): String {
        return Hashing.sha256()
            .hashString(originalString, StandardCharsets.UTF_8)
            .toString()
    }
}