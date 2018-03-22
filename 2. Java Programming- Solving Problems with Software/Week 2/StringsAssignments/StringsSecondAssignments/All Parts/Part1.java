
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {

   public int findStopCodon(String dna, int startIndex, String stopCodon){
       // find stopCodon starting from (startIndex +3), currIndex;
       int currIndex = dna.indexOf(stopCodon, startIndex + 3);
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
            currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
       }
            // if we exit loop, we didn't find stopCodonso - return dnaStr.length();
            return dna.length();// for testing make return -1;          
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
   
   public String findGene(String dna){
       //find firsт occurrence of "ATG", startIndex
       int startIndex = dna.indexOf("ATG");
       //if startIndex is -1, return ""
       if(startIndex == -1){
            return "";
       }   
       //use taaIndex to store findStopCodon(dna,startIndex, "TAA");
       int taaIndex = findStopCodon(dna,startIndex,"TAA");
       //use tagIndex to store findStopCodon(dna,startIndex, "TAG");
       int tagIndex = findStopCodon(dna,startIndex,"TAG");
       //use tgaIndex to store findStopCodon(dna,startIndex, "TGA");
       int tgaIndex = findStopCodon(dna,startIndex,"TGA");
       //store in minIndex the smallest of thse three values;
       int temp = Math.min(taaIndex,tagIndex);
       int minIndex = Math.min(temp, tgaIndex);
        // we can initialize minIndex on a sigle line
        //--> ind minIndex = Math.min(taaIndex,Math.min(tagIndex,tgaIndex));
         
       //if minIndex is dna.length()? nothing found, return "";
       if(minIndex == dna.length()){
          return "";
       }else{
       //otherwise answer is text from startIndex to minIndex +3;
       return dna.substring(startIndex,minIndex + 3);
    }
   }
    
   public void testFindGene(){
       String dna = "ATATGTTATGGTAA";            // VALID CASE
       String result = findGene(dna);
       System.out.println("dna = " + dna + "\n" 
                         + "gene = " + result);  
       
       dna = "ATCATCATGCGCGTGTAGTGAATGTAA";      // VALID CASE
       result = findGene(dna);
       System.out.println("dna = " + dna + "\n"
                         + "gene = " + result); 
      
       dna = "ATGGTGTGTATT";            //invalid case
       result = findGene(dna);
       System.out.println("dna = " + dna + "\n" 
                         + "gene = " + result);
       
       dna = "ATTCGCGTGTAATGA";         //invalid case
       result = findGene(dna);
       System.out.println("dna = " + dna + "\n" 
                         + "gene = " + result); 
       
       dna = "TAAATGTTAGTTAATTGAATGTAAATGGGGTGA";// VALID CASE
       result = findGene(dna);
       System.out.println("dna = " + dna + "\n" 
                         + "gene = " + result); 
                   System.out.println();
   }
   
   public void printAllGenes (String dna){
       //set startIndex to 0
       int startIndex = 0;
       //repeat the following steps
       while(true){
           //find the next gene after startIndex
           String currentGene = findGene(dna.substring(startIndex));
           //if no gene was found, leave this loop
           if(currentGene.isEmpty()){
                break;
           }
           //print that gene out
           System.out.println(currentGene);
           //set startIndex to just past the end of the gene
           startIndex += currentGene.length();
        }
   }
   
   public void test() {
        printAllGenes("AATGGCTTGAAATGATTFTAGATGTCGTAAATGTGG"); //ATGGCTTGA, ATGATTTAG, ATGTCGTAA
   }
}
