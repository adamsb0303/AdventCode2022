import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Day06 {
    /**
     * Go through line of chars and mark the end of the first section of 4 chars that are unique
     */
    public static void part1(){
        try{
            //load file
            File file = new File("Puzzles/Day06.txt");
            Scanner signals = new Scanner(file);

            //Load the chars into a hashset
            String dataSteam = signals.nextLine();
            for(int i = 3; i < dataSteam.length(); i++){
                HashSet<Character> codes = new HashSet<>();
                for(int j = 0; j < 4; j++)
                    codes.add(dataSteam.charAt(i - j));

                //if the set isn't the desired size, then move to the next section
                if(codes.size() == 4) {
                    System.out.println("Part 1) Number of characters that need to be processed: " + (i + 1));
                    break;
                }
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Go through line of chars and mark the end of the first section of 14 chars that are unique
     */
    public static void part2(){
        try{
            //load file
            File file = new File("Puzzles/Day06.txt");
            Scanner signals = new Scanner(file);

            //Load the chars into a hashset
            String dataSteam = signals.nextLine();
            for(int i = 13; i < dataSteam.length(); i++){
                HashSet<Character> codes = new HashSet<>();
                for(int j = 0; j < 14; j++)
                    codes.add(dataSteam.charAt(i - j));

                //if the set isn't the desired size, then move to the next section
                if(codes.size() == 14) {
                    System.out.println("Part 2) Number of characters that need to be processed: " + (i + 1));
                    break;
                }
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
