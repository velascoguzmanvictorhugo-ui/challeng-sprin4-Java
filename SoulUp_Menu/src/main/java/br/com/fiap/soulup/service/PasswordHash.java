package br.com.fiap.soulup.service;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordHash {
    private static final int ROUNDS = 600000;
    private static final SecureRandom RANDOM = new SecureRandom();
    private PasswordHash() {}

    public static String create(String password) {
        if (password == null || password.length() < 8 || password.length() > 72) {
            throw new IllegalArgumentException("A senha deve ter de 8 a 72 caracteres.");
        }
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return ROUNDS + "$" + Base64.getEncoder().encodeToString(salt) + "$" + Base64.getEncoder().encodeToString(derive(password, salt, ROUNDS));
    }

    public static boolean matches(String password, String stored) {
        if (password == null || password.length() > 72 || stored == null) return false;
        try {
            String[] parts = stored.split("\\$");
            if (parts.length != 3 || Integer.parseInt(parts[0]) != ROUNDS) return false;
            byte[] salt = Base64.getDecoder().decode(parts[1]);
            byte[] expected = Base64.getDecoder().decode(parts[2]);
            return salt.length == 16 && expected.length == 32 && MessageDigest.isEqual(expected, derive(password, salt, ROUNDS));
        } catch (IllegalArgumentException e) { return false; }
    }

    private static byte[] derive(String password, byte[] salt, int rounds) {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, rounds, 256);
        try { return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded(); }
        catch (java.security.GeneralSecurityException e) { throw new IllegalStateException("Falha ao processar a senha.", e); }
        finally { spec.clearPassword(); }
    }
}
