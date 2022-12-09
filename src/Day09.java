import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Vector;

public class Day09 {
    /**
     * Read data from file
     * @return String of every line from file
     */
    private static Vector<String> readFile(){
        try{
            Scanner scanner = new Scanner(new File("Puzzles/Day09.txt"));
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
     * Simulate a rope with 2 knots and count how many positions the tail of the rope traveled through
     */
    public static void part1(){
        //Read from file
        Vector<String> instructionsFile = readFile();
        if(instructionsFile == null)
            return;

        //Keep track of visited locations without duplicates
        HashSet<String> tailVisited = new HashSet<>();

        int[][] rope = new int[2][2];
        for (String fileInstructions : instructionsFile) {
            //record initial location
            tailVisited.add(rope[1][0] + ", " + rope[1][1]);

            //parse instructions
            String[] instructions = fileInstructions.split(" ");
            char direction = instructions[0].charAt(0);
            int distance = Integer.parseInt(instructions[1]);

            //move head of rope
            switch (direction) {
                case 'R' -> moveRope(rope, distance, 0, tailVisited);
                case 'L' -> moveRope(rope, -distance, 0, tailVisited);
                case 'U' -> moveRope(rope, distance, 1, tailVisited);
                case 'D' -> moveRope(rope, -distance, 1, tailVisited);
            }
        }
        System.out.println("Part 1) Visited locations: " + tailVisited.size());
    }

    /**
     * Simulate a rope with 10 knots and count how many positions the tail of the rope traveled through
     */
    public static void part2(){
        //Read from file
        Vector<String> instructionsFile = readFile();
        if(instructionsFile == null)
            return;

        //Keep track of visited locations without duplicates
        HashSet<String> tailVisited = new HashSet<>();

        int[][] rope = new int[10][2];
        for (String fileInstructions : instructionsFile) {
            //record initial location
            tailVisited.add(rope[9][0] + ", " + rope[9][1]);

            //parse instructions
            String[] instructions = fileInstructions.split(" ");
            char direction = instructions[0].charAt(0);
            int distance = Integer.parseInt(instructions[1]);

            //move head of rope
            switch (direction) {
                case 'R' -> moveRope(rope, distance, 0, tailVisited);
                case 'L' -> moveRope(rope, -distance, 0, tailVisited);
                case 'U' -> moveRope(rope, distance, 1, tailVisited);
                case 'D' -> moveRope(rope, -distance, 1, tailVisited);
            }
        }
        System.out.println("Part 2) Visited locations: " + tailVisited.size());
    }

    /**
     * See if points 1 and 2 are within 1 block of each other
     * @param point1 first point
     * @param point2 second point
     * @return if they are near each other
     */
    private static boolean checkForHead(int[] point1, int[] point2){
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(point1[0] == point2[0] + i && point1[1] == point2[1] + j)
                    return true;
            }
        }
        return false;
    }

    /**
     * Moves the head of the rope in the specified direction and distance, then updates
     * the rest of the knots in the rope and records the position of the tail
     * @param rope array of knots
     * @param distance how far to move the head
     * @param direction which way to move the head
     * @param tailVisited list of unique coordinates that the tail has been
     */
    private static void moveRope(int[][] rope, int distance, int direction, HashSet<String> tailVisited){
        for(int i = 0; i < Math.abs(distance); i++){
            for(int j = 0; j < rope.length - 1; j++) {
                int[] point1 = rope[j];
                int[] point2 = rope[j + 1];

                //only move point if it's the head the rope
                if(j == 0)
                    point1[direction] += distance / Math.abs(distance);

                //make sure the head is within 1 square of tail
                if (checkForHead(point1, point2))
                    continue;

                //if they aren't within 1 column, move the 2nd point 1 toward the 1st
                if (point1[1] != point2[1])
                    point2[1] += (point1[1] - point2[1]) / Math.abs(point1[1] - point2[1]);

                //they aren't on top of each other, move the 2nd point 1 toward the 1st
                if (point1[0] != point2[0])
                    point2[0] += (point1[0] - point2[0]) / Math.abs(point1[0] - point2[0]);

                if(j == rope.length - 2)
                    tailVisited.add(point2[0] + ", " + point2[1]);
            }
        }
    }
}
