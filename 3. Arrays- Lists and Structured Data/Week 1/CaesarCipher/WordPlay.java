
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);
        if(ch =='a' ||ch =='e'||ch=='i'||ch=='o'||ch=='u')
            return true;
        else
            return false;
    }

    public void testIsVowel(){
        System.out.println(isVowel('e'));
        System.out.println(isVowel('E'));
        System.out.println(isVowel('f'));
        System.out.println(isVowel('F'));
    }

    public String replaceVowels(String phrase, char ch){
        
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int i = 0; i < newPhrase.length(); i++) {
            char currentChar = newPhrase.charAt(i);
            int currCharIndex = phrase.indexOf(currentChar, i);

            if(isVowel(currentChar)) {    
                newPhrase.setCharAt(currCharIndex, ch);
            }
        }
        return newPhrase.toString();
    }

    public void testReplaceVowels() {
        System.out.println(replaceVowels("HeelloOOOOOoooo WoOorld", '*'));       
    }

    public String emphasize (String phrase, char ch){
        
        StringBuilder newPhrase = new StringBuilder(phrase);

        for(int i = 0; i< phrase.length();i++){
            char currentChar = newPhrase.charAt(i);
            int currCharIndex = phrase.indexOf(currentChar,i);

            if(Character.toLowerCase(currentChar) == ch
                    ||Character.toUpperCase(currentChar)==ch){
                if(i%2==0){
                    newPhrase.setCharAt(currCharIndex,'*');
                }else{
                    newPhrase.setCharAt(currCharIndex,'+');
                }
            }
        } 
        return newPhrase.toString();
    }

    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaactga",'a'));
        System.out.println(emphasize("MaryYYyyY Bella Abracadabra",'y'));
    }
}
