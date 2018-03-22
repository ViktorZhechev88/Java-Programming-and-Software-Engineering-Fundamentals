
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part1 {

   public int findStopCodon(String dna, int startIndex, String stopCodon){
       // find stopCodon starting from (startIndex +3), currIndex;
       String dnaUpperCase = dna.toUpperCase();
       int currIndex = dnaUpperCase.indexOf(stopCodon, startIndex + 3);
       //as long as currIndex is not equal to -1;
       while(currIndex!=-1){
            int diff = currIndex - startIndex;
            //check if currIndex -startIndex is a multiple of 3;
            if(diff % 3 ==0){
            //if so, currIndex is answer, return it;    
            return currIndex;
            }
            //if not, update currIndex, looking for stopCodonstarting from currIndex + 1                        
            else{
            currIndex = dnaUpperCase.indexOf(stopCodon, currIndex + 1);
            }
       }
            // if we exit loop, we didn't find stopCodonso - return dnaStr.length();
            return dnaUpperCase.length();// for testing make return -1;          
    }
    
   public void testFindStopCodon (){
       String dna = "ATGGTGA";
       System.out.println(findStopCodon(dna, 0, "TGA"));
       
       dna = "ATGTTGTAATAATATA";
       System.out.println(findStopCodon(dna, 7, "TAA"));
       
       dna = "ATGTCGTGCTAATTAG";
       System.out.println(findStopCodon(dna, 5, "TAG"));
       
       dna = "ATGTCGTGCTAATAG";
       System.out.println(findStopCodon(dna, 0, "TAG"));
       
   }
   
   public String findGene(String dna, int where){
       String dnaUpperCase = dna.toUpperCase();
       //find firsт occurrence of "ATG", startIndex
       int startIndex = dnaUpperCase.indexOf("ATG", where);
       //if startIndex is -1, return ""
       if(startIndex == -1){
            return "";
       }   
       //use taaIndex to store findStopCodon(dna,startIndex, "TAA");
       int taaIndex = findStopCodon(dnaUpperCase,startIndex,"TAA");
       //use tagIndex to store findStopCodon(dna,startIndex, "TAG");
       int tagIndex = findStopCodon(dnaUpperCase,startIndex,"TAG");
       //use tgaIndex to store findStopCodon(dna,startIndex, "TGA");
       int tgaIndex = findStopCodon(dnaUpperCase,startIndex,"TGA");
       //store in minIndex the smallest of thse three values;
       int temp = Math.min(taaIndex,tagIndex);
       int minIndex = Math.min(temp, tgaIndex);
        // we can initialize minIndex on a sigle line
        //--> ind minIndex = Math.min(taaIndex,Math.min(tagIndex,tgaIndex));
         
       //if minIndex is dna.length()? nothing found, return "";
       if(minIndex == dnaUpperCase.length()){
          return "";
       }else{
       //otherwise answer is text from startIndex to minIndex +3;
       return dnaUpperCase.substring(startIndex,minIndex + 3);
       }
   }
    
   public void testFindGene(){
       String dna = "ATATGTTATGGTAA";            // VALID CASE
       String result = findGene(dna,0);
       System.out.println("dna = " + dna + "\n" 
                         + "gene = " + result);  
       
       dna = "ATCATCATGCGCGTGTAGTGAATGTAA";      // VALID CASE
       result = findGene(dna,0);
       System.out.println("dna = " + dna + "\n"
                         + "gene = " + result); 
      
       dna = "ATGGTGTGTATT";            //invalid case
       result = findGene(dna,0);
       System.out.println("dna = " + dna + "\n" 
                         + "gene = " + result);
       
       dna = "ATTCGCGTGTAATGA";         //invalid case
       result = findGene(dna,0);
       System.out.println("dna = " + dna + "\n" 
                         + "gene = " + result); 
       
       dna = "TAAATGTTAGTTAATTGAATGTAAATGGGGTGA";// VALID CASE
       result = findGene(dna,0);
       System.out.println("dna = " + dna + "\n" 
                         + "gene = " + result); 
                   System.out.println();
   }
   
   public StorageResource getAllGenes (String dna){
       String dnaUpperCase = dna.toUpperCase();
       //creat an empty StorageResource, call it geneList;
       StorageResource geneList = new StorageResource();
       //set startIndex to 0
       int startIndex = 0;
       
       //repeat the following steps
       while(true){
           //find the next gene after startIndex
           String currentGene = findGene(dnaUpperCase,startIndex);
           //if no gene was found, leave this loop
           if(currentGene.isEmpty()){
                break;
           }
           //add that gene to the geneList;
           geneList.add(currentGene);
           //set startIndex to just past the end of the gene
           startIndex = dnaUpperCase.indexOf(currentGene, startIndex)
                                    + currentGene.length();
       } 
        //your answer is geneList
       return geneList;
   }
   
   public double cgRatio(String dna){
       double count = 0.0;
       String dnaUpperCase = dna.toUpperCase();
       for (int i = 0; i < dna.length(); i++) {
           if(dnaUpperCase.charAt(i) =='C' || dnaUpperCase.charAt(i) == 'G'){
           count++;
           }
       }
       double ratio = count/dnaUpperCase.length();
       return ratio;
   }
   
   public void testOncgRatio(){
       String dna = "ATGCCATAG";
       System.out.println(cgRatio(dna));
   }
    
   public void countCTG (String dna){
       String dnaUpperCase = dna.toUpperCase();
       int count = 0; 
       int currIndex = 0;
              
       while (currIndex != -1) {
            currIndex = dnaUpperCase.indexOf("CTG", currIndex);
            if (currIndex == -1 ) {
                break;
            } else {
                currIndex += 1;
                count++;
            }
        }
       System.out.println("CTG appears in this DNA strand " + count + " times");    
   }
   
   public void testOnCountCTG(){
       FileResource file = new FileResource("GRch38dnapart.fa");
       String dna = file.asString().toUpperCase();
       countCTG(dna);
   }
   
   public void testOn(String dna){
       String dnaUpperCase = dna.toUpperCase();
       System.out.println("Testing getAllGenes on " + dnaUpperCase);
       StorageResource genes = getAllGenes(dnaUpperCase);
       
       for(String gene:genes.data()){
           System.out.println(gene);
       }
   }
    
   public void test(){
        //      ATGv  TAAv  ATG   v  v  TGA
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        //      ATGv  v  v     TAAv  v  v  ATGTAA
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
   }
    
   public void testStorageGenes() {
        // Read file (which is a long string of DNA)
        FileResource file = new FileResource("GRch38dnapart.fa");
        // Find and store all genes in file
        String sequence = file.asString().toUpperCase();
        StorageResource genes = getAllGenes(sequence);
        // Print the number of genes found
        System.out.println("Number of genes found: " + genes.size());
        printInfoForGenes(genes);
   } 
    
   public void printInfoForGenes(StorageResource sr){
       int count = 0;
       // Print all strings that are longer than 60 characters
       for(String word : sr.data()){
           if(word.length() > 60){
           //System.out.println("This word is longer than 60 characters: \n" + word); 
           count++;
           }
       }
       
       System.out.println("Printed " + count + " strings that are longer than 60 characters.");
       
       count = 0;
       // Print strings whose C-G ratio is higher than 0.35
       for(String str : sr.data()){
           double ratio = cgRatio(str);
           if(ratio >0.35){
           //System.out.println("This word C-G ratio is higher than 0.35: " + str);
           count++;
           }
       }
       
       System.out.println("Printed " + count + " strings whose C-G ratio is higher than 0.35.");       
       // Print the length of the longest gene in sr
       int maxLength = 0;
       String longestGene ="";
       for(String gene : sr.data()){
           int currLength = gene.length();
           
           if(currLength > maxLength){
           maxLength = currLength;
           longestGene = gene;
           }
       }
       System.out.println("The length of the longest gene is " 
                + maxLength + " and the gene is:\n"+ longestGene);
      
       
   }
   
   public String mystery(String dna) {
          int pos = dna.indexOf("T");
          int count = 0;
          int startPos = 0;
          String newDna = "";
          if (pos == -1) {
            return dna;
          }
          while (count < 3) {
            count += 1;
            newDna = newDna + dna.substring(startPos,pos);
            startPos = pos+1;
            pos = dna.indexOf("T", startPos);
            if (pos == -1) {
              break;
            }
          }
          newDna = newDna + dna.substring(startPos);
          return newDna;
   }
   
   public void  testMystery(){
       String dna = "AATBBBTCCCCTGGGTGG";
       System.out.println(dna);
       System.out.println(mystery(dna));
    } 

}
