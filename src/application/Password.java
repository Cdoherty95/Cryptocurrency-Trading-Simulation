package application;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Code modeled off StackExchange
 */
public class Password {

  private static final Random RND = new SecureRandom();
  private static final int ITERATE = 10000;
  private static final int KLENGTH = 256;

  //Returns salt to be used to hash a password
  public static byte[] getNextSalt() {
    byte[] salt = new byte[16];
    RND.nextBytes(salt);
    return salt;
  }

  //Returns a salted and hashed password using the provided hash
  public static byte[] hash(char[] password, byte[] salt) {
    PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATE, KLENGTH);
    Arrays.fill(password, Character.MIN_VALUE);
    try {
      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      return skf.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
    } finally {
      spec.clearPassword();
    }
  }

  //Returns true if the given password and salt match the hashed value
  public static boolean isExpectedPassword(char[] password, byte[] salt, byte[] expectedHash) {
    byte[] pwdHash = hash(password, salt);
    Arrays.fill(password, Character.MIN_VALUE);
    if (pwdHash.length != expectedHash.length) return false;
    for (int i = 0; i < pwdHash.length; i++) {
      if (pwdHash[i] != expectedHash[i]) return false;
    }
    return true;
  }
}