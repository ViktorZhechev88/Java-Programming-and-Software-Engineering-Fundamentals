
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {

    private HashMap<String,Integer> dnaMap;

    public CodonCount(){
        this.dnaMap = new HashMap<String, Integer>();
    }

    public void buildCodonMap (int start, String dna){

        this.dnaMap.clear();
        dna = dna.toUpperCase().trim();
        for(int i= start; i <dna.length() - 2; i+=3){
            String codon = dna.substring(i, i+3);
            if(!dnaMap.containsKey(codon)){
                dnaMap.put(codon,1);
            }
            else{
                dnaMap.put(codon,dnaMap.get(codon)+1);
            }
        }
    }

    public String getMostCommonCodon (){
        int maxCount =0;
        String mostCodon = "";

        for(String codon: dnaMap.keySet()){
            int current = dnaMap.get(codon);
            if(current > maxCount){
                maxCount = current;
                mostCodon = codon;
            }
        }
        return "Most common codon: " + mostCodon + " with count " + maxCount;
    }

    public void printCodonCounts (int start, int end){
        System.out.println( "Codons that occur between " + start + " and " + end + " times" );
        for(String codon: dnaMap.keySet()){
            int count = dnaMap.get(codon);
            if(count >= start && count <= end){
                System.out.println(codon + " with count " + count);
            }

        }
    }

    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().trim().toUpperCase();

        int start = 6;
        int end = 8;

        for(int i = 0; i<=2;i++){
            buildCodonMap(i,dna);
            System.out.println("Reading frame starting with " + i + " results in " + dnaMap.size() + " unique codons");
            System.out.println(getMostCommonCodon());
            printCodonCounts(start, end);
        }
    }
}
