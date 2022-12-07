import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day07 {
    /**
     * Given a command line output, create a file system
     * and find the sum of all directories that have a size of less than 100,000
     */
    public static void part1(){
        try{
            //load file
            File file = new File("Puzzles/Day07.txt");
            Scanner files = new Scanner(file);

            //Create tree of directories and add size of files
            SystemDirectory currentDirectory = new SystemDirectory("/");
            while(files.hasNext()){
                String line = files.nextLine();
                String[] command = line.split(" ");
                switch (command[0]) {
                    case "$" -> {
                        if (!command[1].equals("cd"))
                            break;

                        if(command[2].equals("..") && currentDirectory.prev != null)
                            currentDirectory = currentDirectory.prev;
                        else if(currentDirectory.children.get(command[2]) != null)
                            currentDirectory = currentDirectory.children.get(command[2]);
                    }
                    case "dir" -> {
                        SystemDirectory newDir = new SystemDirectory(command[1]);
                        newDir.prev = currentDirectory;
                        currentDirectory.addChildren(newDir);
                    }
                    default -> currentDirectory.addSize(Integer.parseInt(command[0]));
                }
            }

            //move pointer to root
            while(currentDirectory.prev != null)
                currentDirectory = currentDirectory.prev;

            //Depth-First search to put all directories in a vector
            Vector<SystemDirectory> directories = new Vector<>();
            Stack<SystemDirectory> dfs = new Stack<>();
            dfs.add(currentDirectory);
            while(dfs.size() != 0){
                SystemDirectory currentNode = dfs.pop();
                currentNode.children.forEach((e,f) -> {
                    dfs.add(f);
                    directories.add(f);
                });
            }

            //Search through vector to find all directories with size <= 100,000
            int sumSize = 0;
            for(SystemDirectory dir : directories){
                if(dir.size <= 100000)
                    sumSize += dir.size;
            }

            System.out.println("Part 1) Sum of directory sizes less than 100,000: " + sumSize);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Given a command line output, create a file system
     * and file the best directory to delete to free up enough space
     * on a 7,000,000 size disk to install 3,000,000 size update
     */
    public static void part2(){
        try{
            //load file
            File file = new File("Puzzles/Day07.txt");
            Scanner files = new Scanner(file);

            //Create tree of directories and add size of files
            SystemDirectory currentDirectory = new SystemDirectory("/");
            while(files.hasNext()){
                String line = files.nextLine();
                String[] command = line.split(" ");
                switch (command[0]) {
                    case "$" -> {
                        if (!command[1].equals("cd"))
                            break;

                        if(command[2].equals("..") && currentDirectory.prev != null)
                            currentDirectory = currentDirectory.prev;
                        else if(currentDirectory.children.get(command[2]) != null)
                            currentDirectory = currentDirectory.children.get(command[2]);
                    }
                    case "dir" -> {
                        SystemDirectory newDir = new SystemDirectory(command[1]);
                        newDir.prev = currentDirectory;
                        currentDirectory.addChildren(newDir);
                    }
                    default -> currentDirectory.addSize(Integer.parseInt(command[0]));
                }
            }

            //move pointer to root
            while(currentDirectory.prev != null)
                currentDirectory = currentDirectory.prev;

            //Depth-First search to put all directories in a vector
            Vector<SystemDirectory> directories = new Vector<>();
            Stack<SystemDirectory> dfs = new Stack<>();
            dfs.add(currentDirectory);
            while(dfs.size() != 0){
                SystemDirectory currentNode = dfs.pop();
                currentNode.children.forEach((e,f) -> {
                    dfs.add(f);
                    directories.add(f);
                });
            }

            //Calculate the desired size, and find a dir with size
            //closest without going over 3,000,000 - available space
            SystemDirectory delete = currentDirectory;
            int diskSize = 70000000;
            int availableSize = diskSize - currentDirectory.size;
            for(SystemDirectory dir : directories){
                if(dir.size < delete.size && dir.size >= 30000000 - availableSize)
                    delete = dir;
            }

            System.out.println("Part 2) directory to delete: " + delete.name + " size: " + delete.size);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}

/**
 * Class to hold information of a directory
 * name - dir name
 * size - size of all nested files within dir
 * prev - parent directory
 * children - all dirs that are nested
 */
class SystemDirectory {
    String name;
    int size;
    SystemDirectory prev = null;
    HashMap<String, SystemDirectory> children = new HashMap<>();

    SystemDirectory(String name){
        this.name = name;
    }

    public void addSize(int size){
        if(prev != null)
            prev.addSize(size);
        this.size += size;
    }

    public void addChildren(SystemDirectory child){
        children.put(child.name, child);
    }
}