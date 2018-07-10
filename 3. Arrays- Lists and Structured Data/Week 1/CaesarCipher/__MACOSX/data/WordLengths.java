
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.FileResource;

public class WordLengths {

    public void countWordLengths(FileResource resource, int [] counts){
        for(String word: resource.words()){
            int count = 0;
            for(int i = 0; i < word.length(); i++){
                char currentChar = word.charAt(i);
                if(Character.isLetter(currentChar) || currentChar=='-' || currentChar=='\''){
                    count++;
                }
            }

            if(count<30){
                counts[count]++;
            } else {
                counts[30]++;
            }
        }
    }

    public int indexOfMax(int[] values){
        int max = 0;
        int pos = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] > max){
                max = values[i];
                pos = i;
            }
        }
        return pos;
    }

    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int counts[] = new int[31];
        countWordLengths(fr, counts);

        for(int i = 1; i < counts.length; i++){
            if(counts[i]!=0)
                System.out.println(counts[i]+" words of length " + i);

        }
        System.out.println("Most common length: " + indexOfMax(counts));
    }
}

