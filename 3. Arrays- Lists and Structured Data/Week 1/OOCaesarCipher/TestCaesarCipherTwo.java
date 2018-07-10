
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
public class TestCaesarCipherTwo {

    private String halfOfString(String message, int start){
        String answer = "";   
        for (int k = start; k< message.length() ; k+= 2) {
            answer += message.charAt(k);        
        }
        return answer;
    }

    public int[] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k<message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alphabet.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] values){
        int max=0;
        for(int i=0; i< values.length;i++){
            if(values[i] > values[max]){
                max = i;
            }  
        }    
        return max;
    }

    public int getKey(String input){
        int[] letterFreqs = countLetters(input);
        int maxindex = maxIndex(letterFreqs);
        int dkey = maxindex - 4;
        if (maxindex < 4) {
            dkey = 26 - (4-maxindex);
        }
        return dkey;
    }

    public String breakCaesarCipher (String input){
        String firstHalf = halfOfString(input, 0);
        String secondHalf = halfOfString(input, 1);
        int dkey1 = getKey(firstHalf);
        int dkey2 = getKey(secondHalf);

        CaesarCipherTwo cc = new CaesarCipherTwo(dkey1,dkey2);
        System.out.println("Encrypted message: " + input);
        System.out.println("The message was encrypted with keys " + dkey1 + ' ' + dkey2);
        System.out.print("Decrypted message: ");
        return cc.decrypt(input);
    }

    public void simpleTests() {
        FileResource fr = new FileResource();
       String message = fr.asString();
       //String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
       CaesarCipherTwo cc = new CaesarCipherTwo(21,8);
        
       String encryptedMessage = cc.encrypt(message);
       System.out.println("Encrypted message :" +encryptedMessage);
       String decryptedMessage = cc.decrypt(message);
       System.out.println("Decrypted message :" + decryptedMessage);
       System.out.println(breakCaesarCipher(message));
    }
}
