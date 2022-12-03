import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day02 {
    /**
     * Converts inputs into ints 0-2
     * always add player score + 1
     * if they are equal, add 3
     * if the elf and player scores are as follows add 6
     * elf      player
     *  0         1
     *  1         2
     *  2         0
     */
    public static void part1(){
        try {
            //Read file
            File games = new File("Puzzles/Day02.txt");
            Scanner scanner = new Scanner(games);

            //Iterate through file and add the score of each out to total
            int score = 0;
            while(scanner.hasNext()){
                String gameString = scanner.nextLine();
                int elf = gameString.charAt(0) - 'A';
                int player = gameString.charAt(2) - 'X';
                score += player + 1;

                if(elf == player) {
                    score += 3;
                    continue;
                }

                int win = (player == ((elf + 1) % 3)) ? 6 : 0;
                score += win;
            }

            System.out.println("Part 1) Score is: " + score);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Converts inputs into ints 0-2
     * if second column is:
     * 0 - loss
     * 1 - draw
     * 2 - win
     * Calculate score for each round and add to sum
     */
    public static void part2(){
        try {
            //Read from file
            File games = new File("Puzzles/Day02.txt");
            Scanner scanner = new Scanner(games);

            //calculates score for each round and adds to total
            int score = 0;
            while(scanner.hasNext()){
                String gameString = scanner.nextLine();
                int elf = gameString.charAt(0) - 'A';
                int player = gameString.charAt(2) - 'X';
                switch (player) {
                    case 0 -> score += (elf + 2) % 3 + 1;
                    case 1 -> score += elf + 1 + 3;
                    case 2 -> score += (elf + 1) % 3 + 1 + 6;
                }
            }

            System.out.println("Part 2) Score is: " + score);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}