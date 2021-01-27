package com.eService.customers;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EncPublicPrivateKey {

	protected static String DEFAULT_ENCRYPTION_ALGORITHM = "RSA";

	protected static int DEFAULT_ENCRYPTION_KEY_LENGTH = 1024;

	protected static String DEFAULT_TRANSFORMATION = "RSA/ECB/PKCS1Padding";

	protected String mEncyAlgo, mTansformation;

	protected int mEncryptionKeyLength;

	protected PublicKey mPublicKey;

	protected PrivateKey mPrivateKey;

	EncPublicPrivateKey() {

		mEncyAlgo = EncPublicPrivateKey.DEFAULT_ENCRYPTION_ALGORITHM;

		mEncryptionKeyLength = EncPublicPrivateKey.DEFAULT_ENCRYPTION_KEY_LENGTH;

		mTansformation = EncPublicPrivateKey.DEFAULT_TRANSFORMATION;

		mPublicKey = null;

		mPrivateKey = null;
	}

	public static BigInteger keyToNumber(byte[] byteArray) {
		return new BigInteger(1, byteArray);
	}

	public String getEncryptionAlgorithm() {
		return mEncyAlgo;
	}

	public int getEncryptionKeyLength() {
		return mEncryptionKeyLength;
	}

	public String getTransformation() {
		return mTansformation;
	}

	public PublicKey getPublicKey() {
		return mPublicKey;
	}

	public byte[] getPublicKeyAsByteArray() {
		return mPublicKey.getEncoded();
	}

	public String getEncodedPublicKey() {
		String encodedKey = Base64.getEncoder().encodeToString(mPublicKey.getEncoded());
		return encodedKey;
	}

	public PrivateKey getPrivateKey() {
		return mPrivateKey;
	}

	public byte[] getPrivateKeyAsByteArray() {
		return mPrivateKey.getEncoded();
	}

	public String getEncodedPrivateKey() {
		String encodedKey = Base64.getEncoder().encodeToString(mPrivateKey.getEncoded());
		return encodedKey;
	}

	public byte[] encryptText(String text) {
		byte[] encryptedText = null;
		try {

			KeyPairGenerator kpg = KeyPairGenerator.getInstance(mEncyAlgo);
			kpg.initialize(mEncryptionKeyLength);
			KeyPair keyPair = kpg.generateKeyPair();
			mPublicKey = keyPair.getPublic();
			mPrivateKey = keyPair.getPrivate();
			Cipher cipher = Cipher.getInstance(mEncyAlgo);
			cipher.init(Cipher.PUBLIC_KEY, mPublicKey);
			encryptedText = cipher.doFinal(text.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return encryptedText;
	}
	
	public byte[] decryptText(byte[] encryptedText) {

		byte[] decryptedText = null;

		try {

			Cipher cipher = Cipher.getInstance(mEncyAlgo);

			cipher.init(Cipher.PRIVATE_KEY, mPrivateKey);

			decryptedText = cipher.doFinal(encryptedText);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (NoSuchPaddingException e) {

			e.printStackTrace();

		} catch (InvalidKeyException e) {

			e.printStackTrace();

		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();

		} catch (BadPaddingException e) {

			e.printStackTrace();

		}

		return decryptedText;

	}

}
