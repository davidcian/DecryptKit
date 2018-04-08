package dcian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> cipher = Arrays.asList("CRYFOY_EP", "N_AKOZBSA");
		List<String> dictionary = Arrays.asList("A_C_MILAN", "A_S_ROMA_", "ATLETICO_", "BARCELONA", "BESIKTAS_", "DEPORTIVO", "F_C_BASEL", "F_C_PORTO",
				"JUVENTUS_", "LIVERPOOL", "MARSEILLE", "NEWCASTLE", "PARIS_S_G", "SAMPDORIA", "ST_GALLEN", "TOTTENHAM");
		
		OTPCracker cracker = new OTPCracker(cipher, dictionary);
		
		cracker.possibleDecryptions();
	}

}
