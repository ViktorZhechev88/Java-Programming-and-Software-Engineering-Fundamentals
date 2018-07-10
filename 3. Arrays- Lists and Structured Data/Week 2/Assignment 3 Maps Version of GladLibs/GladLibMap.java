
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> mymap;
    private HashMap<String,Integer> map_used;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";

    public GladLibMap(){
        mymap = new HashMap<>();
        map_used = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();

    }

    public GladLibMap(String source){
        mymap = new HashMap<>();
        map_used = new HashMap<>();
        initializeFromSource(source);
        myRandom = new Random();

    }

    private void initializeFromSource(String source) {

        String[] labels = {"adjective","animal","color","country","fruit","name","noun","timeframe","verb"};
        for (String temp:labels){
            ArrayList<String> list = readIt(source+"/"+temp+".txt");
            mymap.put(temp,list);
        }
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix+sub+suffix;
    }

    private String getSubstitute(String label) {
        if (mymap.containsKey(label)){
            if (!map_used.containsKey((label))) {
                map_used.put(label, mymap.get(label).size());
            }
            return  randomFrom(mymap.get(label));
        }
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private int totalWordsInMap(){
        int total = 0;
        for (String temp:mymap.keySet()){
            ArrayList<String> al = mymap.get(temp);
            total = total+al.size();
        }
        return total;
    }

    private int totalWordsConsidered(){
        int sum =0;
        for (int temp:map_used.values()){
            sum += temp;
        }
        return sum;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate.txt");
        printOut(story, 100);
        System.out.println("\n\nTotal words replaced: " + map_used.size());
		System.out.println("Total words in map: " + totalWordsInMap());
		System.out.println("Total number of words that were considered: " + totalWordsConsidered());
    }

}