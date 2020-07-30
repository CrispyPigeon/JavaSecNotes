package by.crispypigeon.secnotes.assistances.encryption;

import org.jetbrains.annotations.NotNull;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import by.crispypigeon.secnotes.di.DaggerUtils;

public class CryptographyAssistance {

    private static final String CryptographyMode = "AES";
    private static final String CryptographyFullMode = "AES/CBC/PKCS5Padding";

    private Cipher cipher;
    private SecretKeySpec secretKeySpec;

    @Inject
    public HashAssistance hashAssistance;

    @Inject
    public CryptographyAssistance(String hashedPassword) {
        try {
            initializeDI();

            initializeCipher();
            initializeSecretKeySpec(hashedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeDI() {
        DaggerUtils.activityComponent.inject(this);
    }

    private void initializeSecretKeySpec(@NotNull String hashedPassword) {
        byte[] decodedKey = hashAssistance.getByteArrayFromHexString(hashedPassword);
        secretKeySpec = new SecretKeySpec(decodedKey, CryptographyMode);
    }

    private void initializeCipher() throws Exception {
        cipher = Cipher.getInstance(CryptographyFullMode);
    }

    public byte[] getIV() {
        SecureRandom randomSecureRandom = new SecureRandom();
        byte[] iv = new byte[cipher.getBlockSize()];
        randomSecureRandom.nextBytes(iv);
        return iv;
    }

    public byte[] encrypt(byte[] text, byte[] IV)
            throws Exception {
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
        return cipher.doFinal(text);
    }

    public String decrypt(byte[] encryptedText, byte[] IV) {
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
            byte[] decryptedText = cipher.doFinal(encryptedText);
            return new String(decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
