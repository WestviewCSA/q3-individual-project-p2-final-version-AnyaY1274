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
		readQueueFile("hardMap1C");
	}
	
	
	public static void readTextFile(String fileName) {
		File file = new File(fileName);
		
		try { 
			//reading file
			Scanner scanner = new Scanner(file);
			
			//saving how many total rows, columns, and sections are in the map
			rows = Integer.parseInt(scanner.next()); //total amount of rows
			cols = Integer.parseInt(scanner.next()); //total amount of columns
			nums = Integer.parseInt(scanner.next()); //total amount of sections
			
			map = new String[rows*nums][cols]; //creating 2d array with row/col/section numbers
			
			//add map values to 2d array map
			for(int r = 0; r < map.length; r++) {
				String oneRow = scanner.next(); //set entire line to one string
				for(int c = 0; c < map[0].length; c++) {
					map[r][c] = oneRow.substring(c, c+1); //save each individual character to map
				}
			}
			
			
			System.out.println(Arrays.deepToString(map)); //printing map
			scanner.close();
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public static void readQueueFile(String fileName) {
		File file = new File(fileName);
		
		try { 
			//reading file
			Scanner scanner = new Scanner(file);

			//saving how many total rows, columns, and sections are in the map
			rows = Integer.parseInt(scanner.next());
			cols = Integer.parseInt(scanner.next());
			nums = Integer.parseInt(scanner.next());
			
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

