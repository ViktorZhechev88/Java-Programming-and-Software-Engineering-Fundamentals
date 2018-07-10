import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++){
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);

            if(Character.isLowerCase(currChar)){
                idx = alphabet.toLowerCase().indexOf(currChar);
            }
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                if(Character.isLowerCase(currChar)){
                    newChar = shiftedAlphabet.toLowerCase().charAt(idx);
                }
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i,newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);

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

    public void eyeballDecrypt(String encrypted){
        CaesarCipher cipher = new CaesarCipher();
        for(int i = 0; i<26; i++){
            String s = cipher.encrypt(encrypted, i);
            System.out.println( i + "\t" + s);
        }        

    }

    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15));
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8,21));
    }

    public void testEncrypting() {
        System.out.println(encrypt("FIRST LEgiON aTTACK EAST FLaNK!", 23));
        System.out.println(encrypt("First Legion", 17));
        System.out.println();
        System.out.println(encryptTwoKeys("Just a test string with lots of eeeeeeeeeeeeeeeees", 23, 2));
    }
}

