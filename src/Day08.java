import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Day08 {
    /**
     * Read input from file
     * @return 2d vector array of integers from file
     */
    private static Vector<Vector<Integer>> readFile(){
        try{
            Scanner scanner = new Scanner(new File("Puzzles/Day08.txt"));
            Vector<Vector<Integer>> data = new Vector<>();
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                Vector<Integer> treeLine = new Vector<>();
                for(char height : line.toCharArray())
                    treeLine.add(height - '0');
                data.add(treeLine);
            }
            return data;
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * From the outside of the forest looking in, find how many trees are visible
     * a tree is visible if it is taller than any trees before it
     */
    public static void part1(){
        Vector<Vector<Integer>> forest = readFile();
        if(forest == null)
            return;

        //Create visibility array and make the outermost ring visible
        int[][] visible = new int[forest.size()][forest.get(0).size()];
        for(int i = 0; i < visible.length; i++)
            for (int j = 0; j < visible[i].length; j++)
                if((i == 0 || i == visible.length - 1) || (j == 0 || j == visible[i].length - 1))
                    visible[i][j] = 1;

        //Check every row and column and mark all the trees that are visible from that direction

        //Check from left
        for(int i = 1; i < forest.size() - 1; i++){
            int tallest = forest.get(i).get(0);
            for(int j = 1; j < forest.get(i).size() - 1; j++){
                if(forest.get(i).get(j) > tallest){
                    visible[i][j] = 1;
                    tallest = forest.get(i).get(j);
                }else if(visible[i][j] != 1)
                    visible[i][j] = 0;
            }
        }

        //Check from top
        for(int i = 1; i < forest.size() - 1; i++){
            int tallest = forest.get(0).get(i);
            for(int j = 1; j < forest.get(i).size() - 1; j++){
                if(forest.get(j).get(i) > tallest){
                    visible[j][i] = 1;
                    tallest = forest.get(j).get(i);
                }else if(visible[j][i] != 1)
                    visible[j][i] = 0;
            }
        }

        //check from right
        for(int i = forest.size() - 2; i > 0; i--){
            int tallest = forest.get(i).get(forest.size() - 1);
            for(int j = forest.get(i).size() - 2; j > 0; j--){
                if(forest.get(i).get(j) > tallest){
                    visible[i][j] = 1;
                    tallest = forest.get(i).get(j);
                }else if(visible[i][j] != 1)
                    visible[i][j] = 0;
            }
        }

        //Check from bottom
        for(int i = forest.size() - 2; i > 0; i--){
            int tallest = forest.get(forest.size() - 1).get(i);
            for(int j = forest.get(i).size() - 2; j > 0; j--){
                if(forest.get(j).get(i) > tallest){
                    visible[j][i] = 1;
                    tallest = forest.get(j).get(i);
                }else if(visible[j][i] != 1)
                    visible[j][i] = 0;
            }
        }

        //count the number of 1s in visibility array
        int visibleTrees = 0;
        for(int i = 0; i < forest.size(); i++)
            for(int j = 0; j < forest.get(i).size(); j++)
                if(visible[i][j] == 1)
                    visibleTrees++;
        System.out.println("Part 1) The number of visible trees is: " + visibleTrees);
    }

    /**
     * From every tree, see how far you can see from that tree and calculate a scenic score
     * The scenic score is calculated by multiplying the distance you can see in any direction
     */
    public static void part2(){
        Vector<Vector<Integer>> forest = readFile();
        if(forest == null)
            return;

        //loop through every tree excluding the outermost ring
        int bestScore = 0;
        for(int i = 1; i < forest.size() - 1; i++){
            for(int j = 1; j < forest.get(i).size() - 1; j++){
                int currentHeight = forest.get(i).get(j);
                int currentScore = 1;

                //check up
                for(int up = i - 1; up >= 0; up--){
                    if(forest.get(up).get(j) >= currentHeight || up == 0){
                        currentScore *= i - up;
                        break;
                    }
                }

                //check down
                for(int down = i + 1; down < forest.size(); down++){
                    if(forest.get(down).get(j) >= currentHeight || down == forest.size() - 1){
                        currentScore *= down - i;
                        break;
                    }
                }

                //check left
                for(int left = j - 1; left >= 0; left--){
                    if(forest.get(i).get(left) >= currentHeight || left == 0){
                        currentScore *= j - left;
                        break;
                    }
                }

                //check right
                for(int right = j + 1; right < forest.get(i).size(); right++){
                    if(forest.get(i).get(right) >= currentHeight || right == forest.get(i).size() - 1){
                        currentScore *= right - j;
                        break;
                    }
                }

                //if the score is better than previous best, replace it
                bestScore = Math.max(bestScore, currentScore);
            }
        }

        System.out.println("Part 2) Best scenic score is " + bestScore);
    }
}
