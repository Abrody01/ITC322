/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import static socialnetwork.Graph.*;


/**
 *
 * @author Aaron Brody
 * @studentID 11574516
 * @Class ITC322
 * @Semester 201830
 */
public class SocialNetwork {

    Graph thisnetwork = Network("src/index.txt","src/friend.txt");
    
    
    /**
     * empty constructor
     */
    public SocialNetwork(){
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        SocialNetwork sn = new SocialNetwork();
        
        int ans = 0;
        int user1;
        int user2;
        while(true){
            printMenu();
            try {
            Scanner myscan = new Scanner(System.in);
            ans = myscan.nextInt();
            myscan.nextLine();
            }
            catch (Exception e){
              
            }
            if(1 <= ans && ans <=7)
            {
                switch(ans)
                {
                    case 1: Graph oldnetwork = sn.thisnetwork;
                        sn.rebuildNetwork();
                        if (oldnetwork.equals(sn.thisnetwork)){
                            System.out.println("New network could not be built, this is usually because the filename entered caused an error, the old network remains in tact");
                        }
                        break;
                    case 2: 
                        if (sn.thisnetwork.size() == 0){
                        System.out.println("network is not built, this is usually because the filename entered caused an error, the old network remains in tact");
                        break;
                        }
                        user1 = sn.getUser();
                            if (user1 == -1) 
                            {
                                System.out.println("Username entered was not found in the network, returning to main menu");
                                break;
                            }
                            sn.printFriendsoffriends(user1);
                            break;
                    case 3: 
                        if (sn.thisnetwork.size() == 0){
                        System.out.println("network is not built, this is usually because the filename entered caused an error, the old network remains in tact");
                        break;
                        }
                        user1 = sn.getUser();
                            if (user1 == -1)
                            {
                                System.out.println("Username entered was not found in the network, returning to main menu");
                                break;
                            }
                            sn.printfriends(user1);
                        break;
                    case 4: 
                        if (sn.thisnetwork.size() == 0){
                        System.out.println("network is not built, this is usually because the filename entered caused an error, the old network remains in tact");
                        break;
                        }
                        user1 = sn.getUser();
                        if (user1 == -1)
                        {
                            System.out.println("Username entered was not found in the network, returning to main menu");
                            break;
                        }
                            user2 = sn.getUser();
                        if (user2 == -1)
                        {
                            System.out.println("Username entered was not found in the network, returning to main menu");
                            break;
                        }
                        sn.printCommonlinks(user1, user2);
                        break;
                    case 5: 
                        if (sn.thisnetwork.size() == 0){
                        System.out.println("network is not built, this is usually because the filename entered caused an error, the old network remains in tact");
                        break;
                        }
                        user1 = sn.getUser();
                        if (user1 == -1)
                        {
                            System.out.println("Username entered was not found in the network, returning to main menu");
                            break;
                        }
                        sn.removeuser(user1);
                        break;
                    case 6:
                        if (sn.thisnetwork.size() == 0){
                        System.out.println("network is not built, this is usually because the filename entered caused an error, the old network remains in tact");
                        break;
                        }
                        sn.printPopularityReport();
                        break;
                    case 7:                        
                        System.exit(0);
                    default:
                        System.out.println("Menu Selection invalid, Returning to main menu");
                        break;
                     
                        


                }
            }
        }
    }
    /**
     * prints the graph of the network.
     * 
     * Currently not used by the program, could be used as part of an upgrade.
     */
    public void printGraph()
    {
        depthFirstPrint(thisnetwork, 0);
    }
    /**
     * Returns an Graph which is built from an index file and a friend list file.
     * 
     * @param index lists who is friends with who and should be stored line by line using the users number stored in the index file
     * @param Friendlist is a list of names and index numbers stored in a text file.
     * @return a Graph which is resulting linkages between the index and friend file.
     */
    public Graph Network(String index,String Friendlist)
    {
        Graph retgraph = new Graph(0);
        int count = 0;
        try{
            Scanner input = new Scanner(new File(index));
            int length = input.nextInt();
            retgraph = new Graph(length);
            while(input.hasNextLine() && count < length){
              retgraph.setLabel(input.nextInt(), (Object)input.next());
              count++;
            }
            input = new Scanner(new File(Friendlist));
            count = 0;
            length = input.nextInt();
            while(input.hasNextLine() && input.hasNextInt() && count < length){
                int F1 = input.nextInt();
                int F2 = input.nextInt();
                retgraph.addEdge(F1,F2);
                retgraph.addEdge(F2,F1);
            }
            System.out.println("Input files successfully read");
        }
        catch(Exception e){
            System.err.println("File Not Found, please enter new files to continue"); 
        }       
            return retgraph;      
    }
    /**
    * Returns the integer representation for a name in the network.
    * @param name should be the name of the user
    * @Return if name not found a -1 will be returned, otherwise the users array location will be returned
    **/
    public int getVertex(String name){
        int ret = -1;
     
        for(int i= 0; i < thisnetwork.size(); i++)
        {
            if(thisnetwork.getLabel(i).equals((Object)name)){
                ret = i;
            }
        }
        return ret;
    }
    
    /**
    * Removes a user from the network.
    * @param friend integer representation for a user on the network
    * 
    */
    public void removeuser(int friend){
        System.out.println("are you sure you want to remove " + thisnetwork.getLabel(friend) + " from the social network? They will lose all their friends if you do" );
        
        int ans = 2;
        System.out.println("1: Yes");
        System.out.println("2: No");
        while(ans >= 1 && ans <= 2){
            ans = 0;
            try {
            Scanner myscan = new Scanner(System.in);
            ans = myscan.nextInt();
            myscan.nextLine();
            }
            catch (Exception e){
               
            }
            if (ans <= 1 && ans >= 2)
            {
                System.out.println("please ener a valid number");
            }
            else {break;}
        }
        if(1 <= ans && ans <=2)
        {
           switch(ans)
            {
                case 1: 
                for(int i= 0; i < thisnetwork.size(); i++)
                {
                    thisnetwork.removeEdge(friend, i);
                    thisnetwork.removeEdge(i, friend);
                    thisnetwork.setLabel(friend, "");   
                }
                System.out.println("user removed, they no longer have any friends, are you proud of yourself?");
                    break;
                case 2: 
                    System.out.println("User not removed");
                    break;

                default:
                    System.out.println("Something went wrong, user not removed");
            }
        }
    }
    /**
     * Returns a HashSet Representation for the network
     * @param person is the integer representation of the user you wish to get friends for.
     * @return a HashSet containing integers for all friends of the person user
     */
    public HashSet getFriends(int person){
        
        HashSet friendslist = new HashSet(thisnetwork.size());
        int[] friendsint = thisnetwork.neighbors(person);
        for(int i = 0; i < friendsint.length; i++)
        {
            friendslist.add(friendsint[i]);
        }
        return friendslist;
    }
    /**
     * Returns a HashSet Representation for the network
     * @param person is the integer representation of the user you wish to get friends and their friends for.
     * @return a HashSet containing integers for all friends of the person user and their friends
     */
    public HashSet getFriendsofFriends(int person){
        HashSet friendslist = new HashSet(thisnetwork.size());
        int[] friendsint = thisnetwork.neighbors(person);
        HashSet friendsofflist = new HashSet(thisnetwork.size());        
        for(int i = 0; i < friendsint.length; i++)
        {
            
            friendslist.add(friendsint[i]);
            friendsofflist = getFriends(friendsint[i]);
            Iterator thisiterator = friendsofflist.iterator();
            for (int j = 0; j < friendsofflist.size(); j++)
            {
                friendslist.add(thisiterator.next());
            }
            
        }
        
        return friendslist;
    }
    /**
     * Prints a list of friends for a single user
     * @param person is the integer representation of the user you wish to print friends for.
     */
    public void printfriends(int person){

        HashSet friends = getFriends(person);
        
        for(int i = 0; i < friends.size(); i++)
        {
            if(person != (int)friends.toArray()[i]){
                System.out.println(thisnetwork.getLabel((int)friends.toArray()[i]));
            }
        }
        
    }
    /**
     * Prints a list of friends for a single user
     * @param person is the integer representation of the user you wish to print friends for.
     */
    public void printFriendsoffriends(int person){
        HashSet friends = getFriendsofFriends(person);
        
        for(int i = 0; i < friends.size(); i++)
        {
            if(person != (int)friends.toArray()[i]){
            System.out.println(thisnetwork.getLabel((int)friends.toArray()[i]));
            }
        }
        
    }
    /**
     * Prints a list of common friends for 2  users
     * @param person1 is the first integer representation of the user you wish to print friends for.
     * @param person2 is the second integer representation of the user you wish to print friends for.
     */
    public void printCommonlinks(int person1, int person2){
        HashSet friends1 = getFriends(person1);
        HashSet friends2 = getFriends(person2);
        
        for(int i = 0; i < friends1.size(); i++){
            if(friends2.contains(friends1.toArray()[i])){
                System.out.println(thisnetwork.getLabel((int)friends1.toArray()[i]));   
            }
        }
        
    }
    /**
     * Prints a popularity report sorted by friend count then username.
     */
    public void printPopularityReport(){
        int[][] Popularity = new int[thisnetwork.size()][2];
        for(int i = 0; i < thisnetwork.size(); i++){
            Popularity[i][0] = getFriends(i).size();
            Popularity[i][1] = i;
                    
        }
        
        java.util.Arrays.sort(Popularity, new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) {
            return Double.compare(a[0], b[0]);
            }
        });
        System.out.println("Popularity list");
        System.out.println("Friends\tName");
        int skipcount = 1;
        boolean skip;
        
        List<String> Names = new ArrayList();   
        for(int i = Popularity.length-1; i>=0 ; i--){            
            
            Names.add((String)thisnetwork.getLabel(Popularity[i][1]));
            skipcount = 1;
            if(!(i-skipcount == -1)){
                            
                while(Popularity[i][0] == Popularity[i-skipcount][0]){
                    Names.add((String)thisnetwork.getLabel(Popularity[i-skipcount][1]));
                    skipcount++;
                        if (i-skipcount < 0){
                            break;
                        }
                    }   
                    Collections.sort(Names, String.CASE_INSENSITIVE_ORDER); 

                    ListIterator myiterator = Names.listIterator();
                    while(Names.isEmpty() == false)
                    {

                    System.out.println(Popularity[i][0]+ "\t" + myiterator.next());
                    myiterator.remove();
                    }
                    i = i - skipcount + 1; 
            }
        }
            
    }
    
     /**
     * prints the menu for display 
     * If the menu needs to be updated do so here.
     */
    public static void printMenu(){
        System.out.println("Please make a selection:");
        System.out.println("1. build new social network");
        System.out.println("2. Get Friends and friends of friends for a user");
        System.out.println("3. Get Friends for a user");
        System.out.println("4. Get Friends in Common for two users");
        System.out.println("5. Delete a member from the social network");
        System.out.println("6. Print Popularity list");
        System.out.println("7. Exit");         
    }
    /**
     * Gets input for someone to be asked for a user.
     * @Return returns the integer representation of that user or a -1 if the user is not valid
     */
    public int getUser(){
        
        int retval = -1;
        String username;
        System.out.println("please enter a name");
        try{ 
        Scanner myscan = new Scanner(System.in);
        username = myscan.nextLine();
        retval = getVertex(username);
        } catch (Exception e)
        {
            username = "invalidusername";
                    
        }
        if (retval < 0){
            System.out.println("user " + username + " not in network");
        }
        return retval;
    }
    /**
     * Gets input for someone to build a new social network, in the process it will destroy the old one.
     * 
     */
    public void rebuildNetwork()
    {
        Scanner myscan = new Scanner(System.in);
        System.out.println("please enter the index file to build the network");
        String newindex = myscan.nextLine();
        System.out.println("please enter the friends file to build the network");
        String newfriends = myscan.nextLine();
        thisnetwork = Network(newindex, newfriends);
    }
    
    
}