
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String result = "";
        
        boolean isLower = dna.toLowerCase().equals(dna);
        
        if(isLower){
            dna = dna.toLowerCase();
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }   
        
        int startIndex = dna.indexOf(startCodon);
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        
        if(startIndex == -1 || stopIndex == -1){
            return "not found!!!";
        }
        
        String subResult = dna.substring(startIndex,stopIndex+3);
        
        if(subResult.length() % 3 == 0){
            if(isLower) {
             result = subResult.toLowerCase();
            }else if(!isLower){
             result = subResult;
            }
        }else{
             return "not found!!!";
        }    
        return result;
    }
    
    public void testSimpleGene (){
        
        String dna = "ATATSATTGATGGGATAAGGATGT";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene is " + gene);
        System.out.println();
        
        dna = "atggggtaa";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene is " + gene);
        System.out.println();
        
        dna = "TAGATATGATTAAAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene is " + gene);
        System.out.println();
        
        dna = "TAAGAGGTAAAAATGATATAATAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene is " + gene);
        System.out.println();
        
        dna = "ATGATGATGATGATGTAAGAGGGATTG";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene is " + gene);
        System.out.println();
        
        dna = "TATTAGATTGATTGAT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,"atg","taa");
        System.out.println("Gene is " + gene);
        System.out.println();
        
        dna = "ATGGGGTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene is " + gene);
        
    }
    
}
