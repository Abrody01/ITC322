/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sill;

/**
 *
 * @author Aaron Brody
 * @studentID 11574516
 * @Class ITC322
 * @Semester 201830
 */



public class Intlinkedlist {
    boolean isnegative = false;
    link node;
    int length = 0;
    
    /**
     * constructor for linkedlist when you have the first digit and 
     * its corresponding negative or positive value.
     * 
     * @param neg boolean, used for when the number is negative or positive True is negative
     * @param startdigit  int used for the first link value in the chain.
     */
    public Intlinkedlist(boolean neg, int startdigit){
        isnegative = neg;
        node = new link(startdigit);
        length = 1;
        
    }
    /**
     * empty constructor, creates a blank Intlinkedlist
     */
    public Intlinkedlist(){
        isnegative = false;
        node = null;
        length = 0;
        
    }
    /**
     * Adds a link to the linked list at the end of the list. (most right number)
     * @param number Int used as the number value of the new link.
     */
    public void addlink(int number){
        link end;
        if(this.length <= 0)
        {
        this.node = new link(number);
        length++;
        }
        else {
            end = lastlink();
            end.addlink(number);
            length++;
        }
        
    
    }
    /**
     * gets the nth link in an intlinked list.
     * @param number the nth number to retrieve from the linked list.
     * @return a link which contains a number and relevant node information from the list.
     */
    public link getlink(int number){
    link retval = this.node;
    for(int i=1;i<number;i++)
    {
        retval = retval.node;
        
    }
    return retval;
    }
    /**
     * gets the last link in this link.
     * @return the last link in the node.
     */
    public link lastlink(){
        
        link last = this.node;
        
        while(last.hasnext()){
        
        last = last.node;
    
        }
    
    return last;
    }
    
    
    /**
     * Turns the intlinked list into a string for printing.
     * because the inlinkedlist by default stores the digits in the little endian
     * format it converts the output to big endian
     * @return Sting - of the Int linked list
     */
    @Override
    public String toString(){
        String Retval = "";
        
        link digits = node;  
        if (node == null) return "";
        while(digits.hasnext()){
        Retval = String.valueOf(digits.number) + Retval;
        digits = digits.node;
        
        }
        Retval = String.valueOf(digits.number) + Retval;
        if (isnegative) Retval = '-' + Retval ;
        
        return Retval;
    }
    /**
     * Adds a link to the front of the linked list
     * @param num the integer to be stored at the front of the list
     */
    public void addtofront(int num){
        node = new link(num,node);
        length = length + 1;
    }
    
    /**
     * constructor for an int linked list from a string.
     * @param number String value of the number you want to turn into a linked list.
     */
    public Intlinkedlist(String number){
        int index = 0;
        char Mychar[] = number.toCharArray();
        if(Mychar[index]=='-')
        {
            isnegative = true;
            index++;    
        }
        if(String.valueOf(Mychar[index]).matches("[0-9]")){
        addlink(Integer.parseInt(String.valueOf(Mychar[index])));
        }
        else {
            System.out.println("invalid charachter sequence, exiting building intlinked list");
        }
        index++;
        for(int i =index; i<number.length() ;i++)
        {
            
           if(String.valueOf(Mychar[i]).matches("[0-9]"))
           {
           addtofront(Integer.parseInt(String.valueOf(Mychar[i])));
           }
           else 
           {
            System.out.println("invalid charachter sequence, exiting building intlinked list");
           }
        }
        
        
        
    }
    
    
    
    
}
