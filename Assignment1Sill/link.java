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
public class link {
    int number;
    link node;
    boolean next = false;
    
    /**
     * Constructor for a link where you do not have a next link defined
     * @param num number to assign to current link
     */
    public link(int num){
          number = num;
          next = false;
    }
       
    /**
     * Constructor to add a link to be the next link in this chain
     * @param num number to be assigned as the int in this link 
     */
    public void addlink(int num){
        node = new link(num);
        next = true;
        }
    
    /**
     * getter function to see if this link has a next value
     * @return 
     */
    public boolean hasnext(){
        return next;
    }
    /**
     * getter function for the next node if there is no next value it will return null.
     * @return link value of next link
     */
    public link getnext(){
        if (hasnext()){
            return node; 
        }
        else return null;
        
    }
    /**
     * Constructor to add a link to be the next link in this chain
     * @param num number to be assigned as the int in this link 
     * @param mynode is the node to add this link before.
     */
   public link(int num, link mynode){
          number = num;
          node = mynode;
          next = true;
    }
}

