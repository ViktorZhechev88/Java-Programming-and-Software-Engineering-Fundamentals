
/**
 * Write a description of CountryExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class CountryExport {

    public String countryInfo(CSVParser parser, String country){
        
        for(CSVRecord record : parser){
            if(record.get("Country").contains(country)){
            String result = record.get("Country") + ": " 
                           + record.get("Exports") + ": "
                           + record.get("Value (dollars)") + ".";
            return result;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2){
        
        System.out.printf("Counries exporting two products (%s,%s) are: \n",exportItem1,exportItem2);
        
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem1) &&
                record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country") + ".");
            }
        }
    }
    
    public void numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem)){
                count++;
            }
        }
        System.out.printf("Number of countries that export %s is %d.\n", exportItem, count);
    }
    
    public void bigExporters (CSVParser parser, String amount){
        
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)").trim();
            if(value.length() > amount.length()){
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }

     public void tester(){
        FileResource file = new FileResource();
        CSVParser parser = file.getCSVParser();
        System.out.println("1.Testing countryInfo:");
        System.out.println(countryInfo(parser, "Nauru"));
        System.out.println();
        System.out.println("2.Testing listExportersTwoProducts:");
        parser = file.getCSVParser();
        listExportersTwoProducts(parser, "fish", "nuts");
        System.out.println();
        System.out.println("3.Testing numberOfExporters:");
        parser = file.getCSVParser();
        numberOfExporters(parser, "gold");
        System.out.println();
        System.out.println("4.Testing bigExporters:");
        parser = file.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
        System.out.println();
    }
}
