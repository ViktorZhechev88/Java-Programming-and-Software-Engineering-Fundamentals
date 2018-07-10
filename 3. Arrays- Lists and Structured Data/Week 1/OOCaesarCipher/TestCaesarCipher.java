
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipher {
    
    private int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k<message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
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
    
    public String breakCaesarCipher (String input){
        int[] letterFreqs = countLetters(input);
        int maxindex = maxIndex(letterFreqs);
        int dkey = maxindex - 4;
        if (maxindex < 4) {
            dkey = 26 - (4-maxindex);
        }
        
        CaesarCipher cc = new CaesarCipher(dkey);
        System.out.println("Encrypted message: " + input);
        System.out.println("The message is encrypted with key " + dkey);
        System.out.print("Decrypted message: ");
        return cc.decrypt(input);
    }
    
    public void simpleTests(){
    //FileResource fr = new FileResource();
    //String message = fr.asString();
    String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
    CaesarCipher cc = new CaesarCipher(15);
    String encryptedMessage = cc.encrypt(message);
    System.out.println("Encrypted message :" +encryptedMessage);
    String decryptedMessage = cc.decrypt(encryptedMessage);
    System.out.println("Decrypted message :" + decryptedMessage);
    
    System.out.println(breakCaesarCipher("XJWW USCW AF LZW UGFXWJWFUW JGGE!"));
    
    }
}
