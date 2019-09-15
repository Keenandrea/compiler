import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.Console;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;

public class Main
{
    public static void main(String... args) throws IOException
    {
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
        	System.out.print("Reading " + TEMP_FILE + "...");
        	data.readFile(TEMP_FILE, loggingData);
            System.out.print("Instantiating tree...");

            System.out.print("Building tree from logged data...");
            tree.treeHelper(loggingData);
            System.out.print("Tree successfully built. Observe menu selection below to continue.\n\n");

/*---------> at this point we break from main and begin using functions
			 in Tree.java to build our tree. assume tree is build befor
			 e reaching the lines below this line in Main.java       */

/*---------> at this line we assume tre
			 e has been built and we ar
			 e ready for traversals:

				bst.printInorder();
				bst.printPreorder();
				bst.printPostorder();

			Footnote: consider clearing
			the screen and pulling a us
			er menu to screen.

				switch 1, 2, 3
				1: print inorder traver
				   sal to screen
				2:  ''  ''   ''  ''  '' 
				3:  ''  ''   ''  ''  ''
				Q: to quit -> Yes (y/N) 
				Confirm the user that t
				he traversals have alre
				ady been saved to their 
				files, however, if user
				wishes to see the resul
				ting content in those f
				iles without opening th
				em, user can select one
				of the options listed*/
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
            data.readFile(fileAppendExtension, loggingData);
            System.out.print("Instantiating tree...");

            System.out.print("Building tree from logged elements...");
            tree.treeHelper(loggingData);
            System.out.print("Tree successfully built. Observe menu selection below to continue.\n\n");

            tree.printInorder();


        }      
    }

    final static String TEMP_FILE = "temp.txt";
    final static String EXTENSION = ".fs19";
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

    void readFile(String fileName, List<String> logs) throws IOException
    {
        Path path = Paths.get(fileName);
        try(Scanner scanner = new Scanner(path, ENCODING.name()))
        {   
            String log;
            Integer lineNumber = 1;
            scanner.useDelimiter(" ");
            boolean hasDuplicate = false;
            while(scanner.hasNext())
            {
                log = scanner.next();
                logs.add(log);
                System.out.print("Logging element " + log + " from " +
                                 " file at line no." + lineNumber + "...");
                lineNumber = lineNumber + 1;
            }
            scanner.close();
        }
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

// import java.util.Random;

// public class Main
// {
// 	public static void main(String... args)
// 	{
// 		Tree<Integer> bst = new Tree<Integer>();
// 		for (int i = 0; i < 5; i++)
// 		{
// 			// int r = (int)(Math.random() * 100) + 1;
// 			int r = 10;
// 			System.out.println("Inserting " + r + "...");
// 			bst.buildTree(r);
// 		}
// 		bst.printInorder();
// 		bst.printPreorder();
// 		bst.printPostorder();
// 	}
// }