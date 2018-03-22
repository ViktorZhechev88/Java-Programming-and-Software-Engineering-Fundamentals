
/**
 * Write a description of Part4 here.
 * 
 * @author (Viktor Zhechev) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {

    public void findingWebLinks( ){
        
        URLResource resource = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");

        for(String word : resource.words()){
            int startIndex = word.toLowerCase().indexOf("youtube.com");
             
            if(startIndex != -1){
                int rightQuoteIndex = word.lastIndexOf("\"",startIndex);
                int leftQuoteIndex = word.indexOf("\"", startIndex +1);
                System.out.println(word.substring(rightQuoteIndex+1, leftQuoteIndex));
            }
        }
    }  
}
