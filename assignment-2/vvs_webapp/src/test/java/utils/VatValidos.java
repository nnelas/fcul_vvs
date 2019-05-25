package utils;

/**
 * Classe para encontrar os primeiros 1000000 vat validos 
 * @author Simao Ferreira
 *
 */
public class VatValidos {

	public static void main(String[] args) {
		for (int i = 100000000; i < 101000000; i++) {
			if (isValidVAT(i)) {
				System.out.println(i);
			}
		}
	}

	/**
	 * Checks if a VAT number is valid.
	 * 
	 * @param vat The number to be checked.
	 * @return Whether the VAT number is valid.
	 */
	private static boolean isValidVAT(int vat) {
		// If the number of digits is not 9, error!
		if (vat < 100000000 || vat > 999999999)
			return false;

		// If the first number is not 1, 2, 5, 6, 8, 9, error!
		int firstDigit = vat / 100000000;
		if (firstDigit != 1 && firstDigit != 2 && firstDigit != 5 && firstDigit != 6 && firstDigit != 8
				&& firstDigit != 9)
			return false;

		// Checks the congruence modules 11.
		int sum = 0;
		int checkDigit = vat % 10;
		vat /= 10;

		for (int i = 2; i < 10 && vat != 0; i++) {
			sum += vat % 10 * i;
			vat /= 10;
		}

		int checkDigitCalc = 11 - sum % 11;
		if (checkDigitCalc == 10)
			checkDigitCalc = 0;
		return checkDigit == checkDigitCalc;
	}
}
