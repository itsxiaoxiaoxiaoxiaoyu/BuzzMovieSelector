
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Encryptor class which helps encrypt user password.
 * @author huangdun
 */
public final class Encryptor {
    /**
     * private constructor.
     */
    private Encryptor() { };

    /**
     * encrypt method which use SHA-512 to hash the password.
     * @param password given password
     * @return encrypted password
     */
    public static String encrypt(final String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digesta = md
                    .digest(password.getBytes(Charset.forName("UTF-8")));
            final int hex = 16;
            return new BigInteger(1, digesta).toString(hex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encryptor.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return password;
    }

}
