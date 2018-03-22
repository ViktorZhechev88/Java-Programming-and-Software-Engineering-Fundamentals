
/**
 * Write a description of TEST here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TEST {
    
    public void findAbc(String input) {
        
        int index = input.indexOf("abc");
        while (true) {
        if ((index == -1) || index>=input.length()-3){
            break;
        }
            System.out.println("index+1: " + (index+1));
            System.out.println("index+4: " + (index+4));
            String found = input.substring(index+1, index+4);
            System.out.println("index before updating: " + index);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
            //Switching to the below line will make error
            //index = input.indexOf("abc",index+4);
            System.out.println("index after updating: " + index);
        }
        
}

   public void test() {
    String input = "abcd";
    //System.out.println("Length is "+input.length());
    findAbc(input);
    input ="abcdabc";
    findAbc(input);
}
}
