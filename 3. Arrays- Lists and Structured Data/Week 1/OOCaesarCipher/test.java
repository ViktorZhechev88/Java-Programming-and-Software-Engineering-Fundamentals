
/**
 * Write a description of test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class test {
    private String word; 
    private String phrase; 
    public test(int number, String w) { 	
        word = w; 	
        phrase = mystery(number, w);          
    }   

    private String mystery(int num, String s) {  	
        String answer = "";  	
        for (int k=0; k<num; k++) {     	
            answer = answer + s;  	
        }  	
        return answer; 
    } 

    public String toString() { 	
        return phrase + " is " + word + " repeated";
    }

    public void print() {  	
        test item = new test(3, "blue");     	
        System.out.println(item);     
        System.out.println(item.mystery(5, "ho"));
    }      
}