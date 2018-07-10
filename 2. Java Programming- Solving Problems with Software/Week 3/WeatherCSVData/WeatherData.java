
/**
 * Write a description of WeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WeatherData {

    public CSVRecord getColdestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar){
        if(coldestSoFar==null){
            coldestSoFar=currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if(currentTemp<coldestTemp && currentTemp>-999){
                coldestSoFar=currentRow;
            }
        }
        return coldestSoFar;
    }

    public CSVRecord coldestHourInFile (CSVParser parser){
        CSVRecord coldestSoFar=null;
        for (CSVRecord currentRow: parser){
           coldestSoFar=getColdestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }

    public void testColdestInDay(){
        FileResource fr = new FileResource("nc_weather/2015/weather-2015-02-02.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was "+ coldest.get("TemperatureF") + " at "+ coldest.get("TimeEST") );
    }

    public CSVRecord coldestInManyDays(){
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }

    //Method that prints all temperatures on the day
   public void allTemperaturesInDay (CSVParser parser){
       for (CSVRecord currentRow: parser){
           double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
           System.out.println(currentRow.get("DateUTC") + currentTemp);
       }
   }

   public String fileWithColdestTemperature(){
       DirectoryResource dr = new DirectoryResource();
       String fileName = null;
       CSVRecord coldestSoFar = null;
       for (File f: dr.selectedFiles()){
           FileResource fr = new FileResource(f);
           CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
           coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
           Path p = Paths.get(String.valueOf(f));
           fileName = p.toString();
       }
       return fileName;
   }

   public void testFileWithColdestTemperature(){
       // Find a file with the coldest temperature
       String coldestTempFileName = fileWithColdestTemperature();
       // Print a name of the file with the coldest temperature
       System.out.println("Coldest day was in file "+ coldestTempFileName);
       //Find and print the coldest temperature in this file (day)
       FileResource file = new FileResource(coldestTempFileName);
       CSVRecord coldest = coldestHourInFile(file.getCSVParser());
       System.out.println("Coldest temperature on that day was "+ coldest.get("TemperatureF"));
       //Print all temperatures on that day
       System.out.println("All the temperatures on the coldest day were: " );
       allTemperaturesInDay(file.getCSVParser());
   }
   
   public double averageTemperatureInFile(CSVParser parser){
        int count = 0;
        double sum = 0.0;
        double temp = 0.0;
        double average = 0.0;
        for(CSVRecord record : parser){
            temp = Double.parseDouble(record.get("TemperatureF"));
            sum = sum + temp;
            count++;
        }
        
        average = sum /count;
        return average;
    }
    
    public void test(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average Temperature in file is " + average);
    }

}
