import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Day04 {
    /**
     * Find the number of elf teams that have one elf's range contained within the other
     * Values are ranges from x-y
     */
    public static void part1(){
        try{
            File file = new File("Puzzles/Day04.txt");
            Scanner elfTeams = new Scanner(file);

            int overlappingTeams = 0;
            while(elfTeams.hasNext()){
                //Convert strings in ranges to ints and save as vectors
                String[] teamLine = elfTeams.nextLine().split(",");
                Vector<Vector<Integer>> teams = new Vector<>();
                for(int i = 0; i < 2; i++){
                    Vector<Integer> temp = new Vector<>();
                    String[] elf = teamLine[i].split("-");
                    for(int j = 0; j < 2; j++){
                        temp.add(Integer.parseInt(elf[j]));
                    }
                    teams.add(temp);
                }

                //check to see if the elf with the smaller section is contained within the other
                for(int i = 0; i < teams.size(); i += 2){
                    Vector<Integer> elf1 = teams.get(i);
                    Vector<Integer> elf2 = teams.get(i + 1);

                    int elf1Width = elf1.get(1) - elf1.get(0);
                    int elf2Width = elf2.get(1) - elf2.get(0);

                    if(elf1Width >= elf2Width){
                        if(elf1.get(0) <= elf2.get(0) && elf1.get(1) >= elf2.get(1))
                            overlappingTeams += 1;
                    }
                    else {
                        if (elf2.get(0) <= elf1.get(0) && elf2.get(1) >= elf1.get(1))
                            overlappingTeams += 1;
                    }
                }
            }

            System.out.println("Part 1) Number of duplicate work: " + overlappingTeams);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Find the number of elf teams that overlap
     * Values are ranges from x-y
     */
    public static void part2(){
        try{
            File file = new File("Puzzles/Day04.txt");
            Scanner elfTeams = new Scanner(file);

            int overlappingTeams = 0;
            while(elfTeams.hasNext()){
                //Convert strings in ranges to ints and save as vectors
                String[] teamLine = elfTeams.nextLine().split(",");
                Vector<Vector<Integer>> teams = new Vector<>();
                for(int i = 0; i < 2; i++){
                    Vector<Integer> temp = new Vector<>();
                    String[] elf = teamLine[i].split("-");
                    for(int j = 0; j < 2; j++){
                        temp.add(Integer.parseInt(elf[j]));
                    }
                    teams.add(temp);
                }

                //check to see if the min of either elf is contained within the other
                for(int i = 0; i < teams.size(); i += 2){
                    Vector<Integer> elf1 = teams.get(i);
                    Vector<Integer> elf2 = teams.get(i + 1);

                    if(elf1.get(0) >= elf2.get(0) && elf1.get(0) <= elf2.get(1))
                        overlappingTeams += 1;
                    else if(elf2.get(0) >= elf1.get(0) && elf2.get(0) <= elf1.get(1))
                        overlappingTeams += 1;
                }
            }

            System.out.println("Part 2) Number of overlapping teams: " + overlappingTeams);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
