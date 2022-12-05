import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Day05 {
    /**
     * Move boxes in stacks according to given list of instructions
     */
    public static void part1(){
        try{
            //load file
            File file = new File("Puzzles/Day05.txt");
            Scanner boxStacks = new Scanner(file);

            //loads the stacks into a vector of stacks of chars
            Vector<Stack<Character>> stacks = new Stack<>();
            String line = boxStacks.nextLine();
            while(line.charAt(0) == '['){
                //Make sure that there are the proper number of stacks before adding to the vector
                for(int i = stacks.size(); i <= line.length() / 4; i++)
                    stacks.add(new Stack<>());

                for(int i = 0; i <= line.length() / 4; i++)
                    if(line.charAt(1 + 4 * i) != ' ')
                        stacks.get(i).add(0, line.charAt(1 + 4 * i));

                line = boxStacks.nextLine();
            }

            //Throw away line that labels the stacks
            boxStacks.nextLine();

            //get where box is going to and from and pop it from one to the other
            while(boxStacks.hasNext()){
                String[] instructions = boxStacks.nextLine().split(" ");
                int numberOfBoxes = Integer.parseInt(instructions[1]);
                int boxesFrom = Integer.parseInt(instructions[3]);
                int boxesTo = Integer.parseInt(instructions[5]);

                for(int i = 0; i < numberOfBoxes; i++)
                    stacks.get(boxesTo - 1).add(stacks.get(boxesFrom - 1).pop());
            }

            System.out.println("Part 1) Top box of each stack : ");
            stacks.forEach(e -> System.out.print(e.peek()));
            System.out.println();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Same as previous, but crane can move multiple at a time
     */
    public static void part2(){
        try{
            //loda the file
            File file = new File("Puzzles/Day05.txt");
            Scanner boxStacks = new Scanner(file);

            //loads the stacks into a vector of stacks of chars
            Vector<Stack<Character>> stacks = new Stack<>();
            String line = boxStacks.nextLine();
            while(line.charAt(0) == '['){
                //Make sure that there are the proper number of stacks before adding to the vector
                for(int i = stacks.size(); i <= line.length() / 4; i++)
                    stacks.add(new Stack<>());

                for(int i = 0; i <= line.length() / 4; i++)
                    if(line.charAt(1 + 4 * i) != ' ')
                        stacks.get(i).add(0, line.charAt(1 + 4 * i));

                line = boxStacks.nextLine();
            }

            //Throw away line that labels the stacks
            boxStacks.nextLine();

            //get where box is going to and from
            //take the group of boxes that need to be moved in the first stack (from size - # moved to top of stack)
            //add them to the new stack and remove from old from the bottom of section
            while(boxStacks.hasNext()){
                String[] instructions = boxStacks.nextLine().split(" ");
                int numberOfBoxes = Integer.parseInt(instructions[1]);
                int boxesFrom = Integer.parseInt(instructions[3]);
                int boxesTo = Integer.parseInt(instructions[5]);

                for(int i = 0; i < numberOfBoxes; i++) {
                    Stack<Character> stack1 = stacks.get(boxesFrom - 1);
                    Stack<Character> stack2 = stacks.get(boxesTo - 1);

                    stack2.add(stack1.get(stack1.size() - numberOfBoxes + i));
                    stack1.remove(stack1.size() - numberOfBoxes + i);
                }
            }

            System.out.println("Part 2) Top box of each stack : ");
            stacks.forEach(e -> System.out.print(e.peek()));
            System.out.println();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
