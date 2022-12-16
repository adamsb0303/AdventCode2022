import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Day11 {
    /**
     * Read data from file
     * @return String of every line from file
     */
    private static Vector<Monkey> readFile(){
        try{
            Scanner scanner = new Scanner(new File("Puzzles/Day11.txt"));
            Vector<Monkey> data = new Vector<>();
            while(scanner.hasNext()){
                Monkey monkey = new Monkey();

                scanner.nextLine();

                String[] startingItems = scanner.nextLine().substring(18).split(", ");
                for (String startingItem : startingItems)
                    monkey.items.add((long) Integer.parseInt(startingItem));

                monkey.operation = scanner.nextLine().substring(23);

                monkey.test = Integer.parseInt(scanner.nextLine().substring(21));

                monkey.partners[0] = Integer.parseInt(scanner.nextLine().substring(29));
                monkey.partners[1] = Integer.parseInt(scanner.nextLine().substring(30));

                if(scanner.hasNext())
                    scanner.nextLine();

                data.add(monkey);
            }
            return data;
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Each monkey is tossing items to each other. Which monkey they toss to is determined by
     * each monkey's own test value that the item's worry score is checked to see if it's a multiple.
     *
     * The worry score is divided by 3 before it is checked.
     */
    public static void part1(){
        //load data from file
        Vector<Monkey> monkeyGroup = readFile();
        if(monkeyGroup == null)
            return;

        //simulate 20 rounds
        for(int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeyGroup) {
                while (monkey.items.size() != 0) {
                    //Perform the operation unique to the given monkey on the item
                    long item = monkey.items.get(0);
                    char operation = monkey.operation.charAt(0);
                    long modifier = (monkey.operation.substring(2).equals("old")) ? item : Integer.parseInt(monkey.operation.substring(2));
                    switch (operation) {
                        case '+' -> item += modifier;
                        case '*' -> item *= modifier;
                    }

                    item /= 3;

                    //Determine if item is thrown to monkey 1 or 2
                    if (item % monkey.test == 0)
                        monkeyGroup.get(monkey.partners[0]).items.add(item);
                    else
                        monkeyGroup.get(monkey.partners[1]).items.add(item);

                    monkey.items.remove(0);
                    monkey.itemsInspected++;
                }
            }
        }

        //Find the 2 monkeys that inspected the most items
        int max = 0;
        int second = 0;
        for(Monkey monkey : monkeyGroup){
            if(monkey.itemsInspected > max) {
                second = max;
                max = monkey.itemsInspected;
            }else if(monkey.itemsInspected > second)
                second = monkey.itemsInspected;
        }
        System.out.println("Part 1) Monkey business level: " + (max * second));
    }

    /**
     * Each monkey is tossing items to each other. Which monkey they toss to is determined by
     * each monkey's own test value that the item's worry score is checked to see if it's a multiple.
     *
     * The worry score is divided by each monkey's test value multiplied together before it is checked.
     */
    public static void part2(){
        //load data from file
        Vector<Monkey> monkeyGroup = readFile();
        if(monkeyGroup == null)
            return;

        //Calculate the ceiling that the worry level can be at, but is still divisible by all test values
        int masterMod = 1;
        for (Monkey value : monkeyGroup)
            masterMod *= value.test;

        //simulate 10000 rounds
        for(int i = 0; i < 10000; i++) {
            for (Monkey monkey : monkeyGroup) {
                while (monkey.items.size() != 0) {
                    //Perform the operation unique to the given monkey on the item
                    long item = monkey.items.get(0);
                    char operation = monkey.operation.charAt(0);
                    long modifier = (monkey.operation.substring(2).equals("old")) ? item : Integer.parseInt(monkey.operation.substring(2));
                    switch (operation) {
                        case '+' -> item += modifier;
                        case '*' -> item *= modifier;
                    }

                    item %= masterMod;

                    //Determine if item is thrown to monkey 1 or 2
                    if (item % monkey.test == 0)
                        monkeyGroup.get(monkey.partners[0]).items.add(item);
                    else
                        monkeyGroup.get(monkey.partners[1]).items.add(item);
                    monkey.items.remove(0);
                    monkey.itemsInspected++;
                }
            }
        }

        //Find the 2 monkeys that inspected the most items
        int max = 0;
        int second = 0;
        for(Monkey monkey : monkeyGroup){
            if(monkey.itemsInspected > max) {
                second = max;
                max = monkey.itemsInspected;
            }else if(monkey.itemsInspected > second)
                second = monkey.itemsInspected;
        }
        System.out.println("Part 2) Monkey business level: " + (long) max * second);
    }
}

/**
 * Object to store information about each monkey
 * items - monkey's currently held items
 * operation - the operation (* or + x) that is performed on the item
 * test - the number that is checked when determining which monkey to throw to
 * partners - monkey 1 or 2, the monkeys that result in the test
 * itemsInspected - the items over the course of a simulation that the monkey has thrown
 */
class Monkey{
    Vector<Long> items = new Vector<>();
    String operation;
    int test;
    int[] partners = new int[2];
    int itemsInspected;
}
