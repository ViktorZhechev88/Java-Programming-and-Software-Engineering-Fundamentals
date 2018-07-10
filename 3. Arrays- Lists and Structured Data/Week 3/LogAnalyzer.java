
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.text.Format;
import java.text.SimpleDateFormat;
public class LogAnalyzer{

    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        this.records = new ArrayList<LogEntry>();
    }

    private int maxNumIPVisitsInDay( HashMap<String,Integer> ipCountInDay ) {
        // Find the maximum number of visits from a single IP in the given map
        int currentMax = -1;
        for( String ipAddr : ipCountInDay.keySet() ) {
            int value = ipCountInDay.get( ipAddr );
            if( value > currentMax ) {
                currentMax = value;
            }
        }

        return currentMax;
    }

    private HashMap<String,Integer> ipVisitsPerDay( HashMap<String,ArrayList<String>> dayIPMap, String day ) {
        HashMap<String,Integer> ipCount = new HashMap<String,Integer>();
        for( String ipAddr : dayIPMap.get( day ) ) {
            if( ipCount.containsKey( ipAddr ) ) {
                ipCount.put( ipAddr, ipCount.get( ipAddr ) + 1 );
            }
            else {
                ipCount.put( ipAddr, 1 );
            }
        }
        return ipCount;
    }

    public void readFile(String filename) {
        // Read logs line by line from a file
        // For each line,
        // Parse it into a LogEntry
        // Put the LogEntry into records

        FileResource fr = new FileResource(filename);
        for(String line : fr.lines()){
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);            
        }
    }

    public int countUniqueIPs(){
        // Count the number of unique IPs in each record 
        // Return the count

        //uniquesIPs starts as an empty list
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        //for each element le in records
        for(LogEntry le : records){
            // ipAddr is le's ipIAddress
            String ipAddr = le.getIpAddress();
            //if ipAddr is not in uniquesIPs
            if(!uniqueIPs.contains(ipAddr)){
                //add ipAddr to uniquesIPs
                uniqueIPs.add(ipAddr);
            }
        }
        //return the size of uniqueIPs
        return uniqueIPs.size();
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public void printAllHigherThanNum(int num) {
        // Look at all the LogEntry record and print those with a status code greater than num
        System.out.println("All log entries with status higher than " + num + ":");
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        // someDay Format: MMM DD
        // Example: Dec 02
        // Look through records for the unique IP addresses that appear for the given day
        // Return an ArrayList containing the unique IPs for the given day      

        ArrayList<String> uniqueIPs = new ArrayList<String>();

        for(LogEntry le : records){
            String logDateString = le.getAccessTime().toString();
            if(logDateString.indexOf(someday) != -1){
                String ipAddr = le.getIpAddress();
                if(!uniqueIPs.contains(ipAddr)){
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        // This method returns the number of unique IP addresses in records that have a status code in the range from low to high, inclusive.
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            int statusCode = le.getStatusCode();
            if (statusCode >= low && statusCode <= high && !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public HashMap<String, Integer> countVisitsPerIP(){
        // Build a map that maps an IP to the number of times the IP has visited the site
        HashMap<String, Integer> counts = new HashMap<String,Integer>();

        for(LogEntry le:records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }
            else{
                counts.put(ip,counts.get(ip) + 1);
            }
        }
        return counts;
    }   

    public int mostNumberVisitsByIP(HashMap<String,Integer> ipCounts){
        // Returns the max number of visits from one IP in the given HashMap
        int maxNum = 0;
        for(String ip: ipCounts.keySet()){
            int currMax = ipCounts.get(ip);
            if( currMax > maxNum ) {
                maxNum = currMax;
            }
        }
        return maxNum;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> ipCounts){
        // Get an ArrayList containing all of the IPs that visited the site the max number of times
        int maxVisits = mostNumberVisitsByIP(ipCounts);
        ArrayList<String> maxVisitIPs = new ArrayList<String>();
        for(String ipAddr:ipCounts.keySet()){
            if(ipCounts.get(ipAddr) == maxVisits){
                maxVisitIPs.add(ipAddr);
            }
        }
        return maxVisitIPs;
    }

    public HashMap<String,ArrayList<String>> iPsForDays(){
        // Builds a HashMap that maps Days to the list of IPs that visited on that day
        HashMap<String,ArrayList<String>> result = new HashMap<String,ArrayList<String>>();
        for(LogEntry le : records){
            Date accessTime = le.getAccessTime();
            String ipAddr = le.getIpAddress();
            Format formatter = new SimpleDateFormat("MMM dd");
            String formattedDate = formatter.format(accessTime);
            ArrayList<String> ips = result.containsKey(formattedDate)?
                    result.get(formattedDate): new ArrayList<String>();
            ips.add(ipAddr);
            result.put(formattedDate,ips);   
        }
        return result;  
    }    

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayMap){
        // Return the day that had the most IP visits
        int maxNum = 0;
        String maxDay="";

        for(String dayStr: dayMap.keySet()){
            int numIPVisits = dayMap.get(dayStr).size();
            if(numIPVisits > maxNum){
                maxNum = numIPVisits;
                maxDay = dayStr;
            }
        }
        return maxDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay( HashMap<String,ArrayList<String>> dayIPMap, String day ) {
        // Return an ArrayList containing the IP addresses with the most accesses on the given day
        ArrayList<String> ipVisitsOnDay = dayIPMap.get( day );
        ArrayList<String> maxVisitIPs = new ArrayList<String>();

        HashMap<String,Integer> ipCountInDay = ipVisitsPerDay( dayIPMap, day );
        int maxNumVisitsInDay = maxNumIPVisitsInDay( ipCountInDay );

        for( String ipAddr: ipCountInDay.keySet() ) {
            int ipVisits = ipCountInDay.get( ipAddr );
            if( ipVisits == maxNumVisitsInDay ) {
                maxVisitIPs.add( ipAddr );
            }
        }
        return maxVisitIPs;
    } 
}
