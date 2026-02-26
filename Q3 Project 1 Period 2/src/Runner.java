import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Runner {

	private static String[][] map;
	private static int rows;
	private static int cols;
	private static int nums;
	
	
	public static void main(String[] name) { //main method needs to be exact
		//readCoFile("easyMap1C");
		
		try{
			readCoFile("easyMap1C");
		} catch (IllegalMapCharacterException e) {
			System.out.println(e.getMessage());
		}  catch (IncompleteMapException e) {
			System.out.println(e.getMessage());
		} catch (IncorrectMapFormatException e) {
			System.out.println(e.getMessage());
		} catch (IllegalCommandLineInputsException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void readTextFile(String fileName) throws IllegalMapCharacterException, IncompleteMapException, IncorrectMapFormatException, IllegalCommandLineInputsException {
		File file = new File(fileName);
		
		try { 
			//reading file
			Scanner scanner = new Scanner(file);
			
			
			//saving how many total rows, columns, and sections are in the map
			
			//if first line does not have only 3 integers, error.
			
			rows = Integer.parseInt(scanner.next()); //total amount of rows
			cols = Integer.parseInt(scanner.next()); //total amount of columns
			nums = Integer.parseInt(scanner.next()); //total amount of sections
			
			if(rows<=0 || cols <= 0 || nums<=0){
				throw new IncorrectMapFormatException("IncorrectMapFormatException - Incorrectly formatted maps");
			}
			
			
			map = new String[rows*nums][cols]; //creating 2d array with row/col/section numbers
			
			//add map values to 2d array map
			for(int r = 0; r < map.length; r++) {
				String oneRow = scanner.next(); //set entire line to one string
				//check for not enough characters in row + not enough rows
				if(oneRow.length() != cols) {
					throw new IncompleteMapException("IncompleteMapException - Incomplete map files such as not enough characters for a given row or too few rows");
					
				}
				for(int c = 0; c < map[0].length; c++) {
					//check for illegal characters
					if(!(oneRow.substring(c, c+1).equals("w")) && !(oneRow.substring(c, c+1).equals("@")) && !(oneRow.substring(c, c+1).equals(".")) && !(oneRow.substring(c, c+1).equals("$")) && !(oneRow.substring(c, c+1).equals("|"))) {
						throw new IllegalMapCharacterException("IllegalMapCharacterException - Illegal characters on map");
						
					}
					else {
						map[r][c] = oneRow.substring(c, c+1); //save each individual character to map
					}
				}
				
			}
			
			
			
			System.out.println(Arrays.deepToString(map)); //printing map
			scanner.close();
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public static void readCoFile(String fileName) throws IllegalMapCharacterException, IncompleteMapException, IncorrectMapFormatException, IllegalCommandLineInputsException {
		File file = new File(fileName);
		
		try { 
			//reading file
			Scanner scanner = new Scanner(file);

			//saving how many total rows, columns, and sections are in the map
			
			
			
			rows = Integer.parseInt(scanner.next());
			cols = Integer.parseInt(scanner.next());
			nums = Integer.parseInt(scanner.next());
			
			//if first line does not have only 3 integers, throw exception.
			if(rows <= 0 || cols <= 0 || nums <= 0){
				throw new IncorrectMapFormatException("IncorrectMapFormatException - Incorrectly formatted maps");
			}
			
			map = new String[rows*nums][cols]; //creating 2d array with row/col/section numbers
			
			while(scanner.hasNext()) {
				String val = scanner.next(); //individual character
				
				//row, col, section number for each individual character
				
				int row = Integer.parseInt(scanner.next());
				int col = Integer.parseInt(scanner.next());
				int num = Integer.parseInt(scanner.next());
				
				map[row + (rows*num)][col] = val; //row location is equal to the row read in file + total amount of rows preceding it
				
			}
			//setting every remaining empty location in array equal to "."
			for(int r = 0; r < map.length; r++) {
				for(int c = 0; c < map[0].length; c++) {
					if(map[r][c] == null) {
						map[r][c] = ".";
					}
				}
			}
			
			System.out.println(Arrays.deepToString(map)); //printing map
			scanner.close();
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
			
}

