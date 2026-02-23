import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Runner {

	private static String[][] map;
	private static int rows;
	private static int cols;
	private static int num;
	
	
	public static void main(String[] name) { //main method needs to be exact
		readTextFile("hardMap2");
	}
	
	
	public static void readTextFile(String fileName) {
		File file = new File(fileName);
		
		try { 
			//reading file
			Scanner scanner = new Scanner(file);
			
			rows = Integer.parseInt(scanner.next());
			cols = Integer.parseInt(scanner.next());
			num = Integer.parseInt(scanner.next());
			
			map = new String[rows*num][cols];
			
		
			for(int r = 0; r < map.length; r++) {
				String oneRow = scanner.next();
				for(int c = 0; c < map[0].length; c++) {
					map[r][c] = oneRow.substring(c, c+1);
				}
			}
			
			
			System.out.println(Arrays.deepToString(map));
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
			
			rows = Integer.parseInt(scanner.next());
			cols = Integer.parseInt(scanner.next());
			num = Integer.parseInt(scanner.next());
			
			map = new String[rows*num][cols];
			
		
			
			
			
			System.out.println(Arrays.deepToString(map));
			scanner.close();
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
			
}

