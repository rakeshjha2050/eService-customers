package com.eService.customers;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class RemoteAccountRepository implements AccountRepository {
	@Autowired
	protected RestTemplate restTemplate;
	
	protected String serviceUrl;
	
	public RemoteAccountRepository(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}
	
	public List<Account> getAllAccounts() {
		Account[] accounts = restTemplate.getForObject("http://custribbon/accounts", Account[].class);
		return Arrays.asList(accounts);
	}
	
	public Account getAccount(String number) {
		String cardNum = "411015110053";
		final int STARTLENGTH = 0;   //first digit of card you don't want to mask
	    final int ENDLENGTH = 4;    //last digit of card you don't want to mask
	    int maskedLength = cardNum.length() - (STARTLENGTH + ENDLENGTH);
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < maskedLength; i++) {
	        sb.append("*");
	    }
	    String maskedCard = cardNum.substring(0, STARTLENGTH) + sb + cardNum.substring(cardNum.length() - ENDLENGTH, cardNum.length());
	    System.out.println("MaskedCard: "+maskedCard);
		encryptDecrypt("411015110053");
		System.out.print("**********RAMA CHANDRA JI MAHARAJ**********");
		return restTemplate.getForObject("http://custribbon/accounts/{id}",
				Account.class, number);
	}
	
	public String getCard(String number) {
		byte[] encryptedText = new EncPublicPrivateKey().encryptText(number);
		return restTemplate.getForObject("http://custribbon/card/45560012322",
				String.class, encryptedText);
	}
	
	public static void encryptDecrypt(String plainText)
	{
	 EncPublicPrivateKey edpkpk = new EncPublicPrivateKey();
	//byte[] secretKeyByteArray = sed.getSecretKeyAsByteArray();

	//System.out.println("secret key: '" + EncryptDecryptPublicKeyPrivateKey.keyToNumber(secretKeyByteArray).toString() + "'" );

	System.out.println("plainText: '" + plainText + "'");
	System.out.println("plainText size: '" + plainText.length() + "'");
	System.out.println("encryption key length: '" + edpkpk.getEncryptionKeyLength() + "'");

	System.out.println("encryption algorithm: '" + edpkpk.getEncryptionAlgorithm() + "'");

	System.out.println("encryption transform: '" + edpkpk.getTransformation() + "'");

	byte[] encryptedText = edpkpk.encryptText(plainText);

	System.out.println("encrypted text: '" + EncPublicPrivateKey.keyToNumber(encryptedText).toString() + "'" );

	System.out.println("encrypted text length: '" + EncPublicPrivateKey.keyToNumber(encryptedText).toString().length() + "'" );

	System.out.println("public key: '" + EncPublicPrivateKey.keyToNumber(edpkpk.getPublicKeyAsByteArray()).toString() + "'" );

	System.out.println("public key length: '" + EncPublicPrivateKey.keyToNumber(edpkpk.getPublicKeyAsByteArray()).toString().length() + "'" );

	System.out.println("private key: '" + EncPublicPrivateKey.keyToNumber(edpkpk.getPrivateKeyAsByteArray()).toString() + "'" );

	System.out.println("private key length: '" + EncPublicPrivateKey.keyToNumber(edpkpk.getPrivateKeyAsByteArray()).toString().length() + "'" );
	
	}

}
