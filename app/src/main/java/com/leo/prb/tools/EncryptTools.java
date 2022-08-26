package com.leo.prb.tools;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.security.auth.x500.X500Principal;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/16 10:35
 * Desc:
 */
@SuppressLint("ObsoleteSdkInt")
public class EncryptTools {

    private static EncryptTools instance;
    private KeyStore keyStore;
    private final Application application;

    public static EncryptTools getInstance(Application application) {
        if (instance == null) {
            synchronized (EncryptTools.class) {
                if (instance == null) {
                    instance = new EncryptTools(application);
                }
            }
        }
        return instance;
    }

    private EncryptTools(Application application) {
        this.application = application;
    }

    private void initKeyStore(String alias) {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            createNewKeys(alias);
        }
    }


    private void createNewKeys(String alias) {
        if (!TextUtils.isEmpty(alias)) {
            try {
                if (!keyStore.containsAlias(alias)) {
                    Calendar start = Calendar.getInstance();
                    Calendar end = Calendar.getInstance();
                    end.add(Calendar.YEAR, 1);
                    KeyPairGeneratorSpec spec = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        spec = new KeyPairGeneratorSpec.Builder(application)
                                .setAlias(alias)
                                .setSubject(new X500Principal("CN=Sample Name, O=Android Authority"))
                                .setSerialNumber(BigInteger.ONE)
                                .setStartDate(start.getTime())
                                .setEndDate(end.getTime())
                                .build();
                    }
                    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        generator.initialize(spec);
                    }
                    KeyPair keyPair = generator.generateKeyPair();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String encryptString(String needEncryptWord, String alias) {
        if (!TextUtils.isEmpty(needEncryptWord) && !TextUtils.isEmpty(alias)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                initKeyStore(alias);
            }
            String encryptStr = "";
            byte[] vals = null;
            try {
                KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, null);
                if (needEncryptWord.isEmpty()) {
                    return encryptStr;
                }

                Cipher inCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                inCipher.init(Cipher.ENCRYPT_MODE, privateKeyEntry.getCertificate().getPublicKey());
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, inCipher);
                cipherOutputStream.write(needEncryptWord.getBytes(StandardCharsets.UTF_8));
                cipherOutputStream.close();
                vals = outputStream.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return Base64.encodeToString(vals, Base64.DEFAULT);
        }
        return "";
    }

    public String decryptString(String needDecryptWord, String alias) {
        if (!TextUtils.isEmpty(needDecryptWord) && !TextUtils.isEmpty(alias)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                initKeyStore(alias);
            }
            String decryptStr = "";
            try {
                Cipher outCipher = getCipher(alias);
                CipherInputStream cipherInputStream = new CipherInputStream(new ByteArrayInputStream(Base64.decode(needDecryptWord, Base64.DEFAULT)), outCipher);
                ArrayList<Byte> values = new ArrayList<>();
                int nextByte;
                while ((nextByte = cipherInputStream.read()) != -1) {
                    values.add((byte) nextByte);
                }

                byte[] bytes = new byte[values.size()];
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = values.get(i);
                }

                decryptStr = new String(bytes, 0, bytes.length, StandardCharsets.UTF_8);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return decryptStr;
        }
        return "";
    }

    public PrivateKey getPrivateKey(String alias) {
        initKeyStore(alias);
        try {
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, null);
            return privateKeyEntry.getPrivateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cipher getCipher(String alias) {
        initKeyStore(alias);
        KeyStore.PrivateKeyEntry privateKeyEntry;
        try {
            privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, null);
            Cipher outCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            outCipher.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());
            return outCipher;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
