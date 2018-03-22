
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

    public int howMany (String stringa,String stringb){
        int currIndex = 0;
        int occurrences = 0;
        
        while (true) {
            int index = stringb.indexOf(stringa, currIndex);
            if (index == -1 ) {
                break;
            } else {
                currIndex = index + stringa.length();
                occurrences++;
            }
        }
        return occurrences;
    }
    
     public void testHowMany() {
        int occurrences = howMany("ababa", "bababaabababaabababababa");
        System.out.println("occurrence = " + occurrences); 
        occurrences = howMany("howmany", "howmanykhowmanykkhowmangsdyhowmany");
        System.out.println("occurrence= " + occurrences); 
        occurrences = howMany("a", "baaanaaanaaa");
        System.out.println("occurrence = " + occurrences); 
        occurrences = howMany("aaaa", "aaabbbaaaaa");
        System.out.println("occurrence = " + occurrences);
        occurrences = howMany("code", "coodecodecodeedcod");
        System.out.println("occurrence = " + occurrences);
        occurrences = howMany("aaaa", "nopeaaa");
        System.out.println("occurrence = " + occurrences);
    }
}
