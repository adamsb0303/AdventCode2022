import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Day1 {
    /**
     *Iterate through the file and if the sum of the section is greater than max, replace max
     */
    public static void part1(){
        try{
            //Read the file
            File elfCalories = new File("Puzzles/Day1.txt");
            Scanner reader = new Scanner(elfCalories);

            int max = 0;
            int sum = 0;
            while(reader.hasNext()){
                String line = reader.nextLine();
                if(line.equals("")) {
                    if(max < sum)
                        max = sum;
                    sum = 0;
                    continue;
                }
                sum += Integer.parseInt(line);
            }
            //print the largest sum
            System.out.println("Part 1) Max calories: " + max);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Iterates through the data and if any of the values in the max vector are
     * lower than the sum, it places the value in between the values
     * that are greater and smaller and makes sure to keep the array size 3
     */
    public static void part2(){
        try{
            //Read the file
            File elfCalories = new File("Puzzles/Day1.txt");
            Scanner reader = new Scanner(elfCalories);

            //create vector
            Vector<Integer> top3 = new Vector<>();
            top3.add(0);
            top3.add(0);
            top3.add(0);

            int sum = 0;
            while(reader.hasNext()){
                String line = reader.nextLine();
                if(line.equals("")) {
                    int index = 3;
                    for(int i = 2; i >= 0; i--)
                        if(top3.get(i) < sum)
                            index = i;

                    if(index < 3) {
                        top3.add(index, sum);
                        top3.remove(3);
                    }
                    sum = 0;
                    continue;
                }
                sum += Integer.parseInt(line);
            }

            //calculate sum of top 3 and print
            int max = 0;
            for (Integer integer : top3)
                max += integer;

            System.out.println("Part 2) Max 3 total calories: " + max);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
