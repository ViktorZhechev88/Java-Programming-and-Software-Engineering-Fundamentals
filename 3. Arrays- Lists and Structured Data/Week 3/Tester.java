
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester{
    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer(String fileName) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(fileName);
        la.printAll();        
    }

    public void testCountUniqueIP( String fileName ) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(fileName);
        System.out.println( "Number of Unique IPs in " + fileName +": " + la.countUniqueIPs() );
    }

    public void testUniqueIP(String fileName){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(fileName);
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }

    public void testPrintAllHigherThanNum( String fileName, int num ) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(fileName);
        System.out.println( "Testing Log Entries in " + fileName + " with Status Code greater than " + num );
        la.printAllHigherThanNum( num );
    }

    public void testUniqueIPVisitsOnDay( String fileName, String someDay ) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( fileName );
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay( someDay );
        System.out.println( "Unique IP Addresses for " + someDay + " in file " + fileName + ":\n===============================================" );
        if( uniqueIPs.size() < 50 ) {
            for( String IP : uniqueIPs ) {
                System.out.println( IP );
            }
        }
        System.out.println( "There are " + uniqueIPs.size() + " unique IP addresses for " + someDay );
    }

    public void testCountUniqueIPsInRange( String fileName, int low, int high ) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( fileName );
        System.out.println( "The number of unique IPs with status codes between " + low + " and " +  high + ", inclusive, in " + fileName + " is " + la.countUniqueIPsInRange( low, high ) + "." );
    }

    public void testCountVisitsPerIP(String fileName){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( fileName );
        HashMap<String,Integer> ipCounts = la.countVisitsPerIP();
        System.out.println( "Counting Visits per IP for file " + fileName + ":\n=====================================================" );
        for( String ipAddr : ipCounts.keySet() ) {
            System.out.println( ipAddr + ": " + ipCounts.get( ipAddr ) );
        }
    }

    public void testMostNumberVisitsByIP( String fileName ) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( fileName );
        HashMap<String,Integer> ipCounts = la.countVisitsPerIP();	
        System.out.println( "Most Number of Visits from a Single IP in " + fileName + " is " + la.mostNumberVisitsByIP( ipCounts ) );
    }

    public void testIPsMostVisits( String fileName ) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( fileName );
        HashMap<String,Integer> ipCounts = la.countVisitsPerIP();
        ArrayList<String> maxVisitIPs = la.iPsMostVisits( ipCounts );
        int mostNumberVisits = la.mostNumberVisitsByIP( ipCounts );
        System.out.println( "IPs that visited the site the most number of times (" + mostNumberVisits +" times) in " + fileName + ":\n==================================================================" );
        for( String ipAddr : maxVisitIPs ) {
            System.out.println( ipAddr );
        }
    }

    public void testIPsForDays( String fileName ) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( fileName );
        HashMap<String,ArrayList<String>> dayIPMap = la.iPsForDays();
        System.out.println( "IPs that visited site per in " + fileName + ":\n======================================================" );
        for( String day : dayIPMap.keySet() ) {
            System.out.print( day + ": " );
            for( String ipAddr : dayIPMap.get( day ) ) {
                System.out.print( ipAddr + "  " );
            }
            System.out.println();
        }
    }

    public  void testDayWithMostIPVisits( String fileName ) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( fileName );
        HashMap<String,ArrayList<String>> dayIPMap = la.iPsForDays();
        String maxDay = la.dayWithMostIPVisits( dayIPMap );
        System.out.println( "The day with the most IP visits in " + fileName + " is " + maxDay + "." );
    }

    public void testIPsWithMostVisitsOnDay( String fileName, String day ) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( fileName );
        HashMap<String,ArrayList<String>> dayIPMap = la.iPsForDays();
        ArrayList<String> maxVisitIPs = la.iPsWithMostVisitsOnDay( dayIPMap, day );
        System.out.println( "IPs with the most visits on " + day + " in file " + fileName +":\n===================================================================" );
        for( String ipAddr : maxVisitIPs ) {
            System.out.println( ipAddr );
        }
    }
}
