
/**
 * Find out how many times each word occurs, and
 * in particular the most frequently occurring word.
 * 
 * @author Duke Software Team
 * @version 1.0
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        this.myWords = new ArrayList<String>();
        this.myFreqs = new ArrayList<Integer>();
    }

    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();

        for(String word : resource.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1){
                myWords.add(word);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }

    public int findIndexOfMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int i=0; i < myFreqs.size(); i++){
            if (myFreqs.get(i) > max){
                max = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void tester(){
        findUnique();
        System.out.println("Number of unique words: "+myWords.size());
        System.out.println("------------------------------------------");
        int index = findIndexOfMax();
        //for (int i = 0; i < myFreqs.size(); i++) {
        //   System.out.println(myFreqs.get(i) + " -> " + myWords.get(i));
        //}
        System.out.println("The word that occurs most often and its count are: "+myWords.get(index)+" -> "+myFreqs.get(index));
    }
}
