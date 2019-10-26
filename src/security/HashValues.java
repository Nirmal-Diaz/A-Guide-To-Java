package security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class HashValues {
    //NOTE: Java "hash values" are known as "message digests"
    static byte[] generateHash(byte[] message, String algorithm) {
        MessageDigest messageDigest;
        byte[] digest = null;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(message);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException nsae) {
            System.out.println("NoSuchAlgorithmException occurred");
        }
        return digest;
    }

    static void convertHashToHex(byte[] digest) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            hexString.append(String.format("%02X", digest[i]));
        }
        System.out.println("Hex format: " + hexString);
    }

    //NOTE: "DigestInputStream" is best suited for generating hash values for files
    static byte[] demonstrateDigestInputStream(File file, MessageDigest messageDigest) {
        //NOTE: A "DigestInputStream" has a ByteInputStream and a MessageDigest
        try (DigestInputStream digestInputStream = new DigestInputStream(new FileInputStream(file), messageDigest) ) {
            while (digestInputStream.read() != -1) {
                //NOTE: When we call read() on the "DigestInputStream", it automatically reads the ByteInputStream and update() the MessageDigest
                //NOTE: This is an empty loop. All the work is done inside the expression area
            }
            return digestInputStream.getMessageDigest();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found: " + file.getAbsolutePath());
        } catch (IOException ioe) {
            System.out.println("IOException occurred");
        }
    }
}