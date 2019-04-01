/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sill;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron Brody
 * @studentID 11574516
 * @Class ITC322
 * @Semester 201830
 */
public class SILL {

    /**
     * @param args the command line arguments
     * if args contains a string, it will use that string as the filepath to the input for the program.
     */
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner myscan = new Scanner(System.in);
        int input;
        String path = "";
        Intlinkedlist list1 = new Intlinkedlist();
        Intlinkedlist list2 = new Intlinkedlist();
        Intlinkedlist list3 = new Intlinkedlist();
        Filemanager fm;
        
        
        
        
        

        if(args.length == 0){
            System.out.println("Program Started without input file, Loading menu");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(SILL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            path = args[0];
            System.out.println("Path read was:"+path);
                    fm = new Filemanager(path, 2);
                    list1 = new Intlinkedlist(fm.getnthnumber(1));
                    list2 = new Intlinkedlist(fm.getnthnumber(2));
                    list3 = linkaddition(list1, list2);
        }
        
        while(true){
            input = 10102015;
            Printmenu();
                    
            input = myscan.nextInt();
            switch (input){
                
                case 1: 
                    System.out.println("Please enter the path for the file:");
                    path = myscan.next();
                    System.out.println("Path read was: "+path);
                    fm = new Filemanager(path, 2);
                    if (!(fm.getnthnumber(1).equals("") || fm.getnthnumber(2).equals(""))){
                    list1 = new Intlinkedlist(fm.getnthnumber(1));
                    list2 = new Intlinkedlist(fm.getnthnumber(2));
                    list3 = linkaddition(list1, list2);
                    System.out.println("the First number is: ");
                        System.out.println(list1.toString());
                        System.out.println("it has a length of " + list1.length + " Digits");
                        System.out.println("the Second  number is: ");
                        System.out.println(list2.toString());
                        System.out.println("it has a length of " +list1.length+ " Digits");
                    }
                    else{
                    System.out.println("The file contained an error: " + fm.error);
                    }
                    break;
                case 2:
                    if (list1.length != 0 || list2.length != 0){
                        System.out.println("the First number is:");
                        System.out.println(list1.toString());
                        System.out.println("it has a length of " + list1.length+ " Digits");
                        System.out.println("the Second number is:");
                        System.out.println(list2.toString());
                        System.out.println("it has a length of " + list2.length + " Digits");}
                    else {
                        System.out.println("No numbers loaded, please re-load numbers");
                    }
                    break;
                case 3:
                     if (list1.length != 0 || list2.length != 0){
                    System.out.println("the Result number is:");
                    System.out.println(list3.toString());
                    System.out.println("it has a length of " + list3.length + " Digits");}
                    else {
                     System.out.println("No numbers loaded, please re-load numbers");
                    }                    
                    break;
                case 0: 
                    System.out.println("Thank you for using this SILL System");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter an appropriate selection");

                    
            }
        }
        
        
    }
    /**
     * prints the menu for display 
     * If the menu needs to be updated do so here.
     */
    
    public static void Printmenu(){
        
        System.out.println("Please select an option to perform:");
        System.out.println("    1.  Enter text file to import numbers.");
        System.out.println("    2.  Display the stored integers");       
        System.out.println("    3.  Display the result of the addition of the integers");
        System.out.println("    0.  Exit");
        
    }
    /**
     * Returns an Intlinkedlist which is the sum of two Intlinkedlist numbers.
     * if one is a negative and the other is not, it will minus the negative from the positive and give the result.
     * @param link1 First link in the sum
     * @param link2 Second link in the sum
     * @return an Intlinkedlist which is the result of the sum.
     */
    public static Intlinkedlist linkaddition(Intlinkedlist link1, Intlinkedlist link2){
        boolean performsubtraction = false;
        Intlinkedlist retval = new Intlinkedlist();
        int carry = 0, sum = 0;
        link number1 = link1.node;
        link number2 = link2.node;
        boolean borrow = false;
        if(link1.isnegative){
            if (link2.isnegative){
                retval.isnegative = true;
            } else {
                performsubtraction = true;
                link temp = number1;
                number1 = number2;
                number2 = temp;
                if (link1.length > link2.length)
                    retval.isnegative = true;
                if (link1.length == link2.length)
                { 
                    link l1 = link1.node;
                    link l2 = link2.node;
                    while (l1.next && !retval.isnegative){
                        
                        if (l1.number > l2.number)
                        {
                            retval.isnegative = true;
                        }
                        else if(l1.number < l2.number)
                        {
                            retval.isnegative = false;
                        }
                        
                        l2=l2.node;
                        l1=l1.node;
                    }
                
                }
            }
        } else {
            if (link2.isnegative){
                performsubtraction = true;
                if (link2.length > link1.length)
                    retval.isnegative = true;
                if (link1.length == link2.length)
                {
                    link l1 = link1.node;
                    link l2 = link2.node;
                    while (l2.next){
                        
                        if (l2.number >= l1.number)
                        {
                            retval.isnegative = true;
                        }
                        else retval.isnegative = false;
                        l2=l2.node;
                        l1=l1.node;
                                       
                    }
                }
            } else {
                
            }
        }
        
        if (performsubtraction){
                       
            while (number1.hasnext() || number2.hasnext())
            {
                int num1 = number1.number;
                int num2 = number2.number;
                if (borrow)
                {
                    num1--;
                    borrow = false;
                }
                                    
                if (Math.signum(num1-num2) == -1)
                sum = 10 + num1-num2;
                else
                sum = 10 - (num1-num2);
                borrow = true;
                
                
                retval.addlink(sum);
                
                if (number1.hasnext()) 
                {    
                    number1 = number1.node;
                }
                else
                {
                    number1 = new link(0);
                }
                
                if (number2.hasnext())
                {
                    number2 = number2.node;
                }
                else
                {
                    number2 = new link(0);
                    
                }
                   
                
               

            }
            sum =  number1.number - number2.number;
            
            if(Math.signum(sum) == -1) {
                retval.isnegative = true;         
                sum = sum * -1;
                }
            if (borrow)
                {
                        
                    sum--;
                    borrow = false;
                }
                retval.addlink(sum);
            
                
        }
        else
        {
            while (number1.hasnext() || number2.hasnext())
            {
                
                sum = carry + number1.number + number2.number;
                if (number1.next == true) 
                {    
                    number1 = number1.node;
                }
                else
                {
                    number1 = new link(0);
                }
                
                if (number2.next == true) 
                {
                    number2 = number2.node;
                }
                else
                {
                    number2 = new link(0);
                }
                   
                carry = (sum >= 10) ? 1 : 0;
                
                sum = sum % 10;
                retval.addlink(sum);
               }
               sum = carry + number1.number + number2.number;
               carry = (sum >= 10) ? 1 : 0;
               
                sum = sum % 10;
                retval.addlink(sum);
                if (carry == 1) {
                    retval.addlink(carry);
                }
            
        }
          
        return retval;

    }
    
}
