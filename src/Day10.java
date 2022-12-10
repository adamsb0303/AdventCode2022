import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Day10 {
    /**
     * Read data from file
     * @return String of every line from file
     */
    private static Vector<String> readFile(){
        try{
            Scanner scanner = new Scanner(new File("Puzzles/Day10.txt"));
            Vector<String> data = new Vector<>();
            while(scanner.hasNext()){
                data.add(scanner.nextLine());
            }
            return data;
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Increment and decrement registerX according to a list of commands
     */
    public static void part1(){
        //Load from file
        Vector<String> clockCommands = readFile();
        if(clockCommands == null)
            return;

        //Initialize clock timer, registerX, and sum for answer
        int registerX = 1;
        int runTime = 0;
        int sum = 0;

        for (String clockCommand : clockCommands) {
            String[] command = clockCommand.split(" ");

            //Add 1 to runtime for instruction
            runTime++;
            if (runTime == 20 || (runTime - 20) % 40 == 0)
                sum += registerX * runTime;

            //if the instruction is addx, wait another clock cycle and add the value to registerX
            if (command.length == 2) {
                runTime++;
                if (runTime == 20 || (runTime - 20) % 40 == 0)
                    sum += registerX * runTime;

                int cycles = Integer.parseInt(command[1]);
                registerX += cycles;
            }
        }
        System.out.println("Part 1) The sum of the signal strengths are: " + sum);
    }

    /**
     * Check if runTime value falls within 1 of registerX value
     * If it is print #, if not print .
     */
    private static int incrementClock(int runTime, int registerX){
        if(Math.abs(runTime - registerX) == 1 || Math.abs(runTime - registerX) == 0)
            System.out.print("#");
        else
            System.out.print(".");
        runTime++;

        //Carriage return after 40 cycles
        if (runTime % 40 == 0) {
            System.out.println();
            runTime %= 40;
        }
        return runTime;
    }

    public static void part2(){
        //Read from file
        Vector<String> clockCommands = readFile();
        if(clockCommands == null)
            return;

        //Initialize clock timer, registerX
        int registerX = 1;
        int runTime = 0;

        //Execute commands and print the resulting screen to console
        System.out.println("Part 2)");
        for (String clockCommand : clockCommands) {
            String[] command = clockCommand.split(" ");

            //Add 1 to runtime for instruction
            runTime = incrementClock(runTime, registerX);

            //if the instruction is addx, wait another clock cycle and add the value to registerX
            if (command.length == 2) {
                runTime = incrementClock(runTime, registerX);

                int cycles = Integer.parseInt(command[1]);
                registerX += cycles;
            }
        }
    }
}
