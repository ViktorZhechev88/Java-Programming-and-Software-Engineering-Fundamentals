
/**
 * Write a description of CSV_Min_Avg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;
public class CSV_Min_Avg {

    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord minTempRecord = null;
        double minTemp = 0.0;
        for(CSVRecord record : parser){
            if(minTempRecord == null){
                minTempRecord = record;
                minTemp = Double.parseDouble(minTempRecord.get("TemperatureF"));
            } else {
                double temp = Double.parseDouble(record.get("TemperatureF"));
                if(temp < minTemp){
                    minTemp = temp;
                    minTempRecord = record;
                }
            }
        }
        return minTempRecord;
    }

    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        String fileName = "";
        double coldestTemp = 0.0;
        StorageResource temperatures = new StorageResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord record = coldestHourInFile(parser);
            double temp = Double.parseDouble(record.get("TemperatureF"));
            if((temp < coldestTemp || coldestTemp == 0.0)&& temp != -9999.0){
                temperatures.clear();
                coldestTemp = temp;
                fileName = f.getName();
                CSVParser p = fr.getCSVParser();
                for(CSVRecord r: p){
                    temperatures.add(r.get("DateUTC") + ": " + r.get("TemperatureF"));
                }
            }
        }
        System.out.println("Coldest day was in file "+fileName);
        System.out.println("Coldest temperature on that day was " + coldestTemp);
        System.out.println("All the Temperatures on the coldest day were: ");
        for(String s : temperatures.data()){
            System.out.println(s);
        }
        return fileName;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord minHumidityRecord = null;
        String minHumidityStr = "N/A";
        for(CSVRecord record : parser){
            if(minHumidityRecord == null){
                minHumidityRecord = record;
            }
            String humidityStr = record.get("Humidity");
            if(!humidityStr.equals("N/A")){
                if(minHumidityStr.equals("N/A")){
                    minHumidityStr = humidityStr;
                    minHumidityRecord = record;
                } else {
                    int minHumidity = Integer.parseInt(minHumidityStr);
                    int humidity = Integer.parseInt(humidityStr);
                    if(humidity < minHumidity){
                        minHumidityRecord = record;
                        minHumidityStr = humidityStr;
                    }
                }

            }
        }
        return minHumidityRecord;
    }

    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestRecord = null;
        int lowestHumidity = 100;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord record = lowestHumidityInFile(parser);
            int humidity = Integer.parseInt(record.get("Humidity"));
            if(humidity < lowestHumidity || lowestRecord == null){
                lowestHumidity = humidity;
                lowestRecord = record;
            }
        }

        return lowestRecord;
    }

    public double averageTemperatureInFile(CSVParser parser){
        int records = 0;
        double avgTemperature = 0.0;
        for(CSVRecord record : parser){
            avgTemperature += Double.parseDouble(record.get("TemperatureF"));
            records++;
        }
        return avgTemperature/records;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        int records = 0;
        double avgTemperature = 0.0;
        for(CSVRecord record : parser){
            if(Integer.parseInt(record.get("Humidity")) >= value){
                avgTemperature += Double.parseDouble(record.get("TemperatureF"));
                records++;
            }
        }
        if(records == 0){
            return 0.0;
        }
        return avgTemperature/records;
    }

    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        System.out.println("Coldest temperature: " +coldestHourInFile(parser).get("TemperatureF"));
    }

    public void testFileWithColdestTemperature(){
        fileWithColdestTemperature();
    }

    public void testLowestHumidityFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+ record.get("Humidity") + " at " +record.get("DateUTC"));
    }

    public void testlowestHumidityInManyFiles(){
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }

    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemperature = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(avgTemperature == 0.0){
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + avgTemperature);
        }

    }
}
