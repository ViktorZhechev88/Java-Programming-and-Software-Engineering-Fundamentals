
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {

    public boolean twoOccurrences (String stringa, String stringb){

        int found = stringb.indexOf(stringa);
        int count = 0;
    
        while( found != -1){
        found = stringb.indexOf(stringa, found+1);
        count++;
        }
        System.out.printf("Found \"%s\" %d times.%n", stringa, count);
        if(count>=2){
            return true;
        }else{
            return false;
        }    
    }
    
    public String lastPart(String stringa, String stringb){
        int stringbIndex = stringb.indexOf(stringa);
        String secondPart = stringb.substring(stringbIndex + stringa.length());
        
        if(stringbIndex == -1){
            return stringb;
        
        }else{
            return secondPart;
        }
    }
  
    public void testing(){
        String a = "porto";
        String b = "fruits: portobana, banana,lemon, bananaportobanana, orangebananaabananaasbanana.";
        
        System.out.println("The answer is " + twoOccurrences(a, b));
        a = "orange";
        System.out.println("The answer is " + twoOccurrences(a, b));
        a = "toba";
        System.out.println("The answer is " + twoOccurrences(a, b));
        
        String stringa = "an";
        String stringb = "banana";
        System.out.println(lastPart(stringa,stringb));
        
        stringa = "zoo";
        stringb = "forest";
        System.out.println(lastPart(stringa,stringb));
        
        stringa = "ass";
        stringb = "assistant";
        System.out.println(lastPart(stringa,stringb));
        
        stringa = "program";
        stringb = "programmer";
        System.out.println(lastPart(stringa,stringb));
                
        stringa = "empty";
        stringb = "full";
        System.out.println(lastPart(stringa,stringb));
    } 
}
