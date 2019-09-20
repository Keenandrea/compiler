import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.Console;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String... args) throws IOException
    {
        long startTime = System.nanoTime();
        Main data = new Main();

        if(args.length > 2)
        {
            System.err.println("Error: Too many arguments. Found " + args.length +"." +
                               "\nExpected one of three formats:\n\n\t$java <program" +
                               "Name>\n\t$java <programName> <fileName>\n\t$java <pr" +
                               "ogramName> < <fileName>\n\nPlease re-run correctly.");
            System.exit(0);
        }

        if(args.length == 0)
        {
            Tree<String> tree = new Tree<String>();

			Console console = System.console();
			if(console == null)
			{
				System.err.println("Error: Console is not supported.");
				System.exit(1);
			}

            System.out.print("Log content for " + TEMP_FILE + " (Press ENTER to commit): ");
			List<String> consoleData = Arrays.asList(console.readLine().split("\\s+"));
			List<String> loggingData = new ArrayList<String>();

			System.out.print("Reading from console...");
			System.out.print("Splitting into list...");
			System.out.print("Writing to " + TEMP_FILE + "...");
        	data.writeFile(TEMP_FILE, consoleData);

            Stream.of(consoleData.toString()).forEach(System.out::println);

        	System.out.print("Reading " + TEMP_FILE + "...");
        	data.readFileFromConsole(TEMP_FILE, loggingData);
            System.out.print("Instantiating tree...");

            System.out.print("Building tree from logged elements...");
            tree.treeHelper(loggingData);
            System.out.print("Tree successfully built.\n\n");

            System.out.println("Working on tree traversals...");
            data.printTraversalFile(tree, TEMP_FILE);


            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("\n\nSuccess: Binary Search Tree made from file " + 
                               TEMP_FILE + " completed in " + 
                               timeElapsed / 1000000 + " milliseconds. Exiting program...\n");

        }

        if(args.length == 1)
        {
            Tree<String> tree = new Tree<String>();
            
            String fileName;
            String fileAppendExtension = "";

            fileName = args[0];
            fileAppendExtension = fileName + EXTENSION;
            File file = new File(fileAppendExtension);
            List<String> loggingData = new ArrayList<String>();

			System.out.print("Validating " + fileAppendExtension + "...");
            data.validateFile(file);
            System.out.print("Reading " + fileAppendExtension + "...");
            data.readFileFromFile(fileAppendExtension, loggingData);
            System.out.print("Instantiating tree...");

            System.out.print("Building tree from logged elements...");
            tree.treeHelper(loggingData);
            System.out.print("Tree successfully built.\n\n");

            System.out.print("Working on tree traversals...");
            data.printTraversalFile(tree, fileAppendExtension);

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("\n\nSuccess: Binary Search Tree made from file " + 
                               args[0] + " completed in " + 
                               timeElapsed / 1000000 + " milliseconds. Exiting program...\n");
        }      
    }

    final static String TEMP_FILE = "temp.txt";
    final static String EXTENSION = ".fs19";
    final static String PREORDER_EXTENSION = ".preorder";
    final static String POSTORDER_EXTENSION = ".postorder";
    final static String INORDER_EXTENSION = ".inorder";
    final static Charset ENCODING = StandardCharsets.UTF_8;

    void writeFile(String fileName, List<String> words) throws IOException
    {
        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path, ENCODING))
        {
            for(String word : words)
            {
                writer.write(word);
                writer.newLine();
            }

            System.out.print(fileName + " data has been written. ");
            writer.close();
        }
    }

    void readFileFromConsole(String fileName, List<String> logs) throws IOException
    {
        Path path = Paths.get(fileName);
        try(Scanner scanner = new Scanner(path, ENCODING.name()))
        {   
            String logStr;
            String logChar;

            while(scanner.hasNextLine())
            {
                logStr = scanner.nextLine();

                // logChar = logStr.substring(0,1);
                logs.add(logStr);
            }
            scanner.close();
        }
    }

    void readFileFromFile(String fileName, List<String> logs) throws IOException
    {
        Path path = Paths.get(fileName);
        try(Scanner scanner = new Scanner(path, ENCODING.name()))
        {   
            String logStr;
            String logChar;

            scanner.useDelimiter(" ");
            while(scanner.hasNext())
            {
                logStr = scanner.next();
                // logChar = logStr.substring(0,1);
                logs.add(logStr);
            }
            scanner.close();
        }
    }

    void printTraversalFile(Tree<String> tree, String fileName) throws IOException
    {
        String filePreOrderExtension = "";
        String filePostOrderExtension = "";
        String fileInOrderExtension = "";

        String outFile = fileName.substring(0, fileName.lastIndexOf('.'));
        filePreOrderExtension = outFile + PREORDER_EXTENSION;
        filePostOrderExtension = outFile + POSTORDER_EXTENSION;
        fileInOrderExtension = outFile + INORDER_EXTENSION;

        tree.printHelperPreorder(filePreOrderExtension);
        // tree.printHelperPostorder(filePostOrderExtension);
        // tree.printHelperInorder(fileInOrderExtension);
    }

    static public void validateFile(File file) throws FileNotFoundException, IOException
    {
        if (file == null)
        {
            throw new IllegalArgumentException("File should not be null.");
        }
        if (!file.exists())
        {
            throw new FileNotFoundException ("File does not exist: " + file);
        }
        if (!file.isFile())
        {
            throw new IllegalArgumentException("Should not be a directory: " + file);
        }
        if (!file.canWrite())
        {
            throw new IllegalArgumentException("File cannot be written: " + file);
        }

        System.out.print(file + " validated. ");
    }
}