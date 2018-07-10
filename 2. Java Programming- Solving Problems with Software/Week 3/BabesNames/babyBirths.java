
/**
 * Write a description of babyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.*;

import java.io.File;

public class babyBirths {
    public void printNames(FileResource fr) {
        // FileResource fr = new FileResource();
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numborn = Integer.parseInt(record.get(2));
            if (numborn <= 100) {
                System.out.println(" Name " + record.get(0) + " Gender " + record.get(1) + " Num Born " + record.get(2));
            }
        }
    }

    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int girlsName = 0;
        int boysName = 0;
        int totalNumOfName = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numborn = Integer.parseInt(record.get(2));
            totalBirths += numborn;
            if (record.get(1).equals("F")) {
                totalGirls += numborn;
                girlsName += 1;

            } else {
                totalBoys += numborn;
                boysName += 1;

            }
            totalNumOfName = boysName + girlsName;

        }
        System.out.println("Total births = " + totalBirths + " total names " + totalNumOfName);
        System.out.println("Total Girls = " + totalGirls + " Number of girls name " + girlsName);
        System.out.println("Total Boys = " + totalBoys + " Number of boys name " + boysName);

    }
    public Integer getRank(Integer year, String name, String gender) {
        FileResource fr = new FileResource(String.format("data/us_babynames_by_year/yob%s.csv", year));
        int rank = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                rank += 1;
            }
            // int numborn = Integer.parseInt(record.get(2));

            if (record.get(1).equals(gender) && record.get(0).equals(name)) {
                // System.out.print(name);
                return rank;

            }

        }
        return -1;

    }

    public String getName(Integer year, Integer rank, String gender) {
        String name = null;
        int currRank = 0;
        FileResource fr = new FileResource(String.format("data/us_babynames_by_year/yob%s.csv", year));
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (gender.equals(record.get(1))) {
                currRank += 1;
            }
            if (gender.equals(record.get(1)) && rank == currRank) {
                return record.get(0);
            }

        }
        return "NO Name";
    }

    public String whatIsNameInYear(String name, Integer year, Integer newYear, String gender) {
        String newName = "";
        int currRank =0;
        currRank = getRank(year, name, gender);
        newName = getName(newYear, currRank, gender);
        System.out.println(name + " born in " + year + " would be  " + newName + " if she was born in " + newYear);
        return newName;
    }

    public Integer yearOfHighestRank(String name, String gender) {
        String fileName = "";
        int currRank = 0;
        int currYear = 0;
        int a = 0;
        int newRank = 0;
        String fileName1 = "";
        String fileName2 = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            fileName = f.getName();
            a = Integer.parseInt(fileName.substring(3, 7));
            currRank = getRank(a, name, gender);
            if ((newRank == 0 && currRank !=-1)) {
                newRank = currRank;
                fileName1 = f.getName();
                currYear = Integer.parseInt(fileName1.substring(3, 7));

            }
            else {

                if ((newRank > currRank) && (currRank != -1)) {
                    newRank = currRank;
                    fileName2 = f.getName();

                    currYear = Integer.parseInt(fileName2.substring(3, 7));
                }

            }

        }

        return currYear;
    }

    public double getAverageRank(String name, String gender) {
        double count = 0;
        double sum = 0;
        double avg = 0;
        int currRank = 0;
        int rank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            currRank = 0;
            rank = 0;
            FileResource fr = new FileResource(f);
            count += 1;
            for (CSVRecord record : fr.getCSVParser(false)) {
                if (gender.equals(record.get(1))) {
                    currRank += 1;
                }
                if (record.get(1).equals(gender) && record.get(0).equals(name)) {
                    rank = currRank;

                }

            }
            sum += rank;

        }
        return (avg = (sum / count));
    }

    public Integer getTotalBirthsRankedHigher(int year,String name,String gender){
        int sum= 0;
        FileResource fr = new FileResource(String.format("data/us_babynames_by_year/yob%s.csv", year));
        for (CSVRecord record : fr.getCSVParser(false)) {
            if(record.get(1).equals(gender) && (!record.get(0).equals(name)))
            {
                Integer num =Integer.parseInt(record.get(2));
                sum += num;

            }
            if (record.get(0).equals(name) && record.get(1).equals(gender)){
                return sum;
            }

        }

        return sum;
    }

    public void test() {
        FileResource fr = new FileResource();
        // FileResource fr = new FileResource("C:\\Users\\abhij\\Documents\\GItHub\\Java\\Coursera\\Baby Names miniProject\\us_babynames\\us_babynames_test\\example-small.csv");
        totalBirths(fr);
        printNames(fr);
        // getRank(2012 ,"Noah" ,"F");

    }

    public void ranktest() {
        int a = getRank(1971, "Frank", "M");
        System.out.println(" " + a);

    }

    public void testGetName() {
        String a = getName(1982, 450, "M");
        System.out.println(a);
    }


    public void testWhatIsNameInyear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");

    }

    public void testYearOfHighestRank() {
        int year = yearOfHighestRank("Genevieve", "F");
        System.out.println(year);
    }

    public void testGetAverageRank() {
        double avg = getAverageRank("Robert", "M");
        System.out.println(avg);

    }

    public void testGetTotalBirthsRankedHigher(){
        int rankedHigher =getTotalBirthsRankedHigher(1990,"Emily","F");
        System.out.println(rankedHigher);
    }
}