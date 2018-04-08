package dcian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class OTPCracker {
	private final List<String> ciphertexts;
	private final List<String> dictionary;
	private List<int[]> padList;
	private final int wordLength = 9;
	
	public OTPCracker(List<String> ciphertexts, List<String> dictionary) {
		this.ciphertexts = ciphertexts;
		this.dictionary = dictionary;
		padList = new ArrayList<int[]>(ciphertexts.size()*dictionary.size());
	}
	
	public void possibleDecryptions() {
		findPadList();
		
		for(int[] p : padList) {
			StringBuilder pad = new StringBuilder();
			for(int i = 0; i < wordLength; i++) {
				pad.append(unicodeOffset((char) p[i]));
			}
			System.out.println("Key: " + pad.toString());
			for(String s : ciphertexts) {
				System.out.println(decrypt(s, p));
			}
			System.out.println("---");
		}
	}
	
	private String decrypt(String ciphertext, int[] otp) {
		StringBuilder plaintext = new StringBuilder();
		
		for(int i = 0; i < wordLength; i++) {
			plaintext.append(unicodeOffset(decryptChar((char)(reverseUnicodeOffset(ciphertext.charAt(i))), otp[i])));
		}
		
		return plaintext.toString();
	}
	
	private Character decryptChar(Character c, int otp) {
		return (char) Math.floorMod(c - otp, 27);
	}
	
	private void findPadList() {
		for(String c : ciphertexts) {
			for(String w : dictionary) {
				padList.add(findPad(c, w));
			}
		}
	}
	
	private int[] findPad(String ciphertext, String plaintext) {
		int[] onetimepad = new int[wordLength];
		
		for(int i = 0; i < wordLength; i++) {
			onetimepad[i] = findShift(ciphertext.charAt(i), plaintext.charAt(i));
		}
		
		return onetimepad;
	}
	
	private int findShift(Character cipher, Character plain) {
		int shift = Math.floorMod(reverseUnicodeOffset(cipher) - reverseUnicodeOffset(plain), 27);
		
		return shift;
	}
	
	private int reverseUnicodeOffset(Character c) {
		return c == '_' ? 0 : c - 'A' + 1;
	}
	
	private Character unicodeOffset(Character c) {
		return (char) (c == 0 ? 0x5F : c + 'A' - 1);
	}
}
