/**
 * Takes a string and compresses it as the following examples show:
 * 
 * aaaaccc 			-> 		a4c4
 * aakaa 			-> 		a2k2
 * aaaaaaaaaaab 	->		a11b1
 * 
 * aa 				-> 		aa (no benefit to compressing, so nothing done)
 * 
 * The solution is my own, but the problem is from Cracking the Coding Interview.
 * 
 * @author Jonathan Sterling
 *
 */
public class StringCompressionAlgorithm{
	public char[] compress(String inputString){
		char[] input = inputString.toCharArray();
		char[] compressed = new char[input.length];
		int posInCompressedArray = 0;
		for (int i = 0; i < input.length; i++){
			if(posInCompressedArray > compressed.length - 2){
				return input;  
			}
			compressed[posInCompressedArray] = input[i];
			posInCompressedArray++;
			int numOfChar = 1;
			while(i+1 < input.length && input[i] == input[i+1]){
				numOfChar++;
				i++;
			}
			int numOfCharsToAdd = ("" + numOfChar).length();
			if(posInCompressedArray + numOfCharsToAdd > compressed.length - 1){
				return input;    
			}
			if(numOfCharsToAdd == 1){
				compressed[posInCompressedArray] = (char)(numOfChar + 48);
				posInCompressedArray++;
			} else {
				String numOfCharAsString = "" + numOfChar;
				for (char c : numOfCharAsString.toCharArray()){
					compressed[posInCompressedArray] = c;
					posInCompressedArray++;
				}
			}

		}
		return compressed;
	}
}
