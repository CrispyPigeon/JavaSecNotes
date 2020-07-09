package by.crispypigeon.secnotes.assistances.encryption;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;

public class HashAssistance {
    private String _hashAlgorithm;

    @Inject
    public HashAssistance(String algorithm) {
        _hashAlgorithm = algorithm;
    }

    @NotNull
    public String getHashedPass(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(_hashAlgorithm);
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return getHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ""; //TODO to handle the error
    }

    @NotNull
    public String getHexString(byte[] data) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < data.length; i++)
            hexString.append(Integer.toHexString(0xFF & data[i]));
        return hexString.toString();
    }
}
