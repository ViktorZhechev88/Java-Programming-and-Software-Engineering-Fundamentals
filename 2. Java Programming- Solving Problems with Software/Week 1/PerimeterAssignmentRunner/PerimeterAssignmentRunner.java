import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public int getNumPoints (Shape s) {
       int numPoints = 0;
       
       for(Point currPt: s.getPoints()){
           numPoints+=1;
       }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double length = getPerimeter(s);
        double numSides = getNumPoints(s);
        double avgLength = length / numSides;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        Point lastPoint = s.getLastPoint();
        double largestSide = 0.0;
        
        for(Point currPt: s.getPoints()){
            double currDist = lastPoint.distance(currPt);
            if(currDist>largestSide){
                 largestSide =currDist;
            }
            lastPoint = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        Point lastPoint = s.getLastPoint();
        double lastPointX = lastPoint.getX();
        double largestX = lastPointX;
        
        for(Point p : s.getPoints()){
            double newX = p.getX();
            if(newX > largestX){
                largestX = newX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        
        for(File f : dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double perim = getPerimeter(shape);
            if(perim >= largestPerim){
                largestPerim = perim;
            }
        }
        return largestPerim;
    }
  
    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0;
        String largest_filename = "";
        for(File f : dr.selectedFiles() ){
            FileResource fr = new FileResource(f);
            Shape shape = new Shape(fr);
            if(getPerimeter(shape) >= largestPerim){
                largestPerim = getPerimeter(shape);
                largest_filename = f.toString();
            }
        }
        return largest_filename;
    }

    public void testPerimeterMultipleFiles() {
        double largest = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter is: " + largest);
    }

    public void testFileWithLargestPerimeter() {
        String file = getFileWithLargestPerimeter();
        System.out.println("Largest perimeter file is: " + file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }
    
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0; 
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim += currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
                
        System.out.println("perimeter = " + getPerimeter(s));
        System.out.println("Number of Points: " + getNumPoints(s));
        System.out.println("Average Length: " + getAverageLength(s));
        System.out.println("Largest Side: " + getLargestSide(s));
        System.out.println("Largest X is: " + getLargestX(s));
        testPerimeterMultipleFiles();
        testFileWithLargestPerimeter();    
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
      
    }
}
