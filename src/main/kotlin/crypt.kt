import java.lang.Exception
import java.security.spec.KeySpec
import java.util.*
import java.util.logging.Logger.global
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object crypt {


    private const val salt = "hola"
    fun encrypt(strToEncrypt: String, key: String): String? {
        try {
            val iv = byteArrayOf(43, 74, 41, 7, 35, 40, 47, 24, 30, 32, 49, 45, 3, 77, 6, 16)
            val ivspec = IvParameterSpec(iv)
            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
            val spec: KeySpec = PBEKeySpec(key.toCharArray(), salt.toByteArray(), 65536, 256)
            val tmp = factory.generateSecret(spec)
            val secretKey = SecretKeySpec(tmp.encoded, "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec)
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.toByteArray(charset("UTF-8"))))
        } catch (e: Exception) {
            println("Error while encrypting: $e")
        }
        return null
    }

    fun decrypt(strToDecrypt: String?,key: String): String? {

        try {
            val iv = byteArrayOf(43, 74, 41, 7, 35, 40, 47, 24, 30, 32, 49, 45, 3, 77, 6, 16)
            val ivspec = IvParameterSpec(iv)
            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
            val spec: KeySpec = PBEKeySpec(key.toCharArray(), salt.toByteArray(), 65536, 256)
            val tmp = factory.generateSecret(spec)
            val secretKey = SecretKeySpec(tmp.encoded, "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec)
            return String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)))
        } catch (e: Exception) {
            println("Error while decrypting: $e")
        }
        return null
    }
    fun checkPassword(key: String): Boolean {
        try {
            val iv = byteArrayOf(43, 74, 41, 7, 35, 40, 47, 24, 30, 32, 49, 45, 3, 77, 6, 16)
            val ivspec = IvParameterSpec(iv)
            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
            val spec: KeySpec = PBEKeySpec(key.toCharArray(), salt.toByteArray(), 65536, 256)
            val tmp = factory.generateSecret(spec)
            val secretKey = SecretKeySpec(tmp.encoded, "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec)
            return true
        } catch (e: Exception) {}
        return false
    }
}