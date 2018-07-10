
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CharactersInPlay {

    private ArrayList<String> namesOfCharacters;
    private ArrayList<Integer> countOfCharacters;

    public CharactersInPlay(){
        this.namesOfCharacters = new ArrayList<String>();
        this.countOfCharacters = new ArrayList<Integer>();
    }

    private void update(String person){
        person = person.toLowerCase().trim();
        int index = namesOfCharacters.indexOf(person);

        if(index == -1){
            namesOfCharacters.add(person);
            countOfCharacters.add(1);
        }
        else{
            int count = countOfCharacters.get(index);
            countOfCharacters.set(index, count+1);
        }
    }

    private void findAllCharacters(){
        namesOfCharacters.clear();
        countOfCharacters.clear();
        FileResource fr = new FileResource();

        for(String line: fr.lines()){
            line = line.toLowerCase();
            int index = line.indexOf('.');
            if(index != -1){
                String subString = line.substring(0,index);
                update(subString);
            }
        }
    }

    private void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < namesOfCharacters.size(); i++) {
            if (countOfCharacters.get(i)>=num1 && countOfCharacters.get(i) <= num2) 
                System.out.println("Person: " + namesOfCharacters.get(i) + ": "+ countOfCharacters.get(i)+ "\t");
        }
    }

    public void tester(){
        findAllCharacters();
        int indexOfCharacter = 0;

        for(int i = 0; i < namesOfCharacters.size(); i ++){
            if(countOfCharacters.get(i) > countOfCharacters.get(indexOfCharacter)){
                indexOfCharacter = i;
            }
        }
        System.out.println("The speaking parts of main character is: \n" +
            namesOfCharacters.get(indexOfCharacter) + " " + 
            countOfCharacters.get(indexOfCharacter));

        charactersWithNumParts(10,15);
    }
}
