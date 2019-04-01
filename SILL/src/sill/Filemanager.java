/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package sill;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *
 * @author Aaron Brody
 * @studentID 11574516
 * @Class ITC322
 * @Semester 201830
 */
public class Filemanager {
    
    
    private int lines;
    private String[] numbers; 
    String error;
   
    /**
     * Creates a Filemanager for the application, this performs the checking of the 
     * file in case there are any issues with the input
     * 
     * @param path the full path to the file.
     * @param numberoflines how many lines to read, this allows more than one file to be read
     * @throws FileNotFoundException  exception thrown if file does not exist
     */
    public Filemanager(String path, int numberoflines) throws FileNotFoundException {
        lines = numberoflines;
        numbers = new String[numberoflines];
        int Count = 0;
        
        
        try{
        File input = new File(path);

        Scanner in = new Scanner(input);

        while (in.hasNext() && Count <= lines)
            {
                
                String line = in.nextLine();
                if (line.matches("[-\\d]+")){
                numbers[Count] = line;
                Count++;
                }
                else {
                     numbers[Count] = "";
                     error = "line contained invalid charachters";
                     Count++;
                }
            }
        }
        catch (FileNotFoundException e){
            while (Count < lines)
            {
                error = "Filepath did not exist";
                numbers[Count] = "";
                Count++;
            }
        }
    }
    
    /**
     * getter function for the string of numbers.
     * @param linenumber
     * @return 
     */
    String getnthnumber(int linenumber){
        if (numbers.length == 0){
            return null;
        }
        return numbers[linenumber-1];
                
        
    }
    /**
     * clears the current filemanager values.
     */
    void clear(){
        lines = 0;
        numbers = null;
        
    }
    
    
    
        
}

