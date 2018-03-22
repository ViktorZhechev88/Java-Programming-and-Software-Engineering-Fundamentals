
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {

    public String findSimpleGene(String dna){
        String result = "";
        int startCodon = dna.indexOf("ATG");
        int stopCodon = dna.indexOf("TAA", startCodon +3);
        if(startCodon == -1 || stopCodon == -1){
        return "not found!!!";
        }
        result = dna.substring(startCodon,stopCodon+3);
        if(((stopCodon+3) - startCodon) % 3 ==0){
            return result;
        }else{
            return "not found";
        }
    }
    public void testSimpleGene (){
        String dna = "ATATSATTGATGGGATAAGGATGT";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        System.out.println();
        dna = "atgatttaa";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        System.out.println();
        dna = "TAGATATGATTAAAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        System.out.println();
        dna = "TAAGAGGTAAAAatgATATAaTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        System.out.println();
        dna = "ATGATGATGATGATGTAAGAGGGATTG";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        System.out.println();
        dna = "atgTATTAGATTGATTGAttA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        System.out.println();
        dna = "ATGGGGTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }
}
