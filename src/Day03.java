import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Vector;

public class Day03 {
    /**
     * Each line of the puzzle is a knapsack, this sack has 2 compartments (left and right)
     * Find the item that is in both the right and left side of the sack
     * and add its priority to the sum
     */
    public static void part1(){
        try{
            File file = new File("Puzzles/Day03.txt");
            Scanner sacksContents = new Scanner(file);

            int prioritiesSum = 0;
            while(sacksContents.hasNext()){
                //Split the sack into the 2 compartments
                String sack = sacksContents.nextLine();
                String rightCompartment = sack.substring(0, sack.length() / 2);
                String leftCompartment = sack.substring(sack.length() / 2);

                //add items from right compartment to set
                HashSet<Character> contents = new HashSet<>();
                for(int i = 0; i < rightCompartment.length(); i++)
                    contents.add(rightCompartment.charAt(i));

                //check if items are in the set
                for(int i = 0; i < leftCompartment.length(); i++){
                    char item = leftCompartment.charAt(i);
                    if(contents.contains(item)){
                        int priority;
                        if(Character.isUpperCase(item))
                            priority = item - 'A' + 1 + 26;
                        else
                            priority = item - 'a' + 1;

                        prioritiesSum += priority;
                        break;
                    }
                }
            }
            System.out.println("Part 1) Sum of priorities is: " + prioritiesSum);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Elves are split into teams of 3
     * find the item that all members of the group have in common
     * and add its priority to the sum
     */
    public static void part2(){
        try{
            File file = new File("Puzzles/Day03.txt");
            Scanner sacksContents = new Scanner(file);

            int prioritiesSum = 0;
            while(sacksContents.hasNext()){
                //Creates the team of elves and add the contents of their knapsacks to a unique hashset
                Vector<HashSet<Character>> elfTeam = new Vector<>();
                for(int i = 0; i < 3; i++){
                    String sack = sacksContents.nextLine();
                    elfTeam.add(new HashSet<>());

                    for(int j = 0; j < sack.length(); j++)
                        elfTeam.get(i).add(sack.charAt(j));
                }

                //Find the shared item in all the team's knapsacks
                char sharedItem = 0;
                for(char item : elfTeam.get(0)){
                    for(int j = 1; j < elfTeam.size(); j++){
                        if(!elfTeam.get(j).contains(item)) {
                            sharedItem = 0;
                            break;
                        }

                        sharedItem = item;
                    }
                    if(sharedItem != 0)
                        break;
                }

                //Add priority of item to the sum
                int priority;
                if(Character.isUpperCase(sharedItem))
                    priority = sharedItem - 'A' + 1 + 26;
                else
                    priority = sharedItem - 'a' + 1;

                prioritiesSum += priority;
            }
            System.out.println("Part 2) Sum of priorities is: " + prioritiesSum);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
