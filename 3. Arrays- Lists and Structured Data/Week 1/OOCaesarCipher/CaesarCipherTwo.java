
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {

    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo(int key1, int key2) {

        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet1 = this.alphabet.substring(key1) + this.alphabet.substring(0, key1);
        this.shiftedAlphabet2 = this.alphabet.substring(key2) + this.alphabet.substring(0, key2);
        this.mainKey1 = key1;
        this.mainKey2 = key2;
    }

    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);

        for(int i = 0; i < encrypted.length();i++){
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);

            if(Character.isLowerCase(currChar)){
                idx = alphabet.toLowerCase().indexOf(currChar);
            }

            if(idx != -1){
                char newChar =' ';
                if( i % 2 == 0){
                    newChar = shiftedAlphabet1.charAt(idx); 
                }else{
                    newChar = shiftedAlphabet2.charAt(idx);
                }

                if(Character.isLowerCase(currChar)){
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i,newChar);
            }

        }
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(input);
    }

}
