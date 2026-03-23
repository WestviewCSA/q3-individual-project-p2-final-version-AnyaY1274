import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class Runner {

	private static String[][] map;
	private static int rows;
	private static int cols;
	private static int nums;
	
	private static int wolvX;
	private static int wolvY;
	private static int coinX;
	private static int coinY;
	
	
	public static void main(String[] args) { //main method needs to be exact
		
//		String option = args[0];
//		
//		switch(option) {
//			case "--Queue": Queue()
//		}
//		
		Queue("easyMap2");
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
			
			//System.out.println(Arrays.deepToString(map)); //printing map
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
			
			//System.out.println(Arrays.deepToString(map)); //printing map
			scanner.close();
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Queue(String fileName) {
		
		//enqueue wolverines position
			//find wolverines position
		//dequeue next location
		//enqueue all walkable tiles
		//check if the spaces have the coin. if not found repeat prev steps
			//find coins position
		//once the coin is found, guide the wolverine to it (replace chars w +)
		
		
		if(fileName.contains("C")) {
			try{
				readCoFile(fileName);
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
		else {
			try{
				readTextFile(fileName);
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
		
		
		
		Queue<int[]> queue = new LinkedList<>(); 
		
		//ArrayList<int[]> vis = new ArrayList<int[]>(); //holds all read coordinates
		boolean[][] vis = new boolean[rows][cols]; //true if point has been visited, false otherwise
		boolean coinFound = false;
		
		
		//store the previous position of wolverine
		int[][] parentRow = new int[rows][cols];
		int[][] parentCol = new int[rows][cols];

		//adding runtime
		long startTime = System.nanoTime();
		long total = 0;
		
		
		//find coordinate of w and $
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				if(map[r][c].equals("$")) {
					coinX = r;
					coinY = c;
				}
			}
		}
		
		for(int r = 0; r < nums; r++) {
			for(int c = 0; c < cols; c++) {
				if(map[r][c].equals("w") || map[r][c].equals("W")) {
					wolvX = r;
					wolvY = c;
					
				}
			}
		}
		
		//wolverine position is added to arr and then enqueued
		int[] wolvCo = {wolvX, wolvY}; 
		queue.add(wolvCo);
		vis[wolvX][wolvY] = true;
		
		int[] northSouth = {-1, 1, 0, 0};
		int[] eastWest = {0, 0, 1, -1};
		
		while(!queue.isEmpty()) {
			//dequeue the first location and save each coordinate, then compare to coin coords
			int[] current = queue.remove(); //save the first value added
			int rowVal = current[0]; //save the row number of the first coordinate
			int colVal = current[1]; //save the column number of the first coordinate
			
			if(rowVal == coinX && colVal == coinY) {
				coinFound = true;
				break;
			}
			
			//enqueue north south east west
			for(int i = 0; i < 4; i++) {
				int nextRow = rowVal + northSouth[i];
				int nextCol = colVal + eastWest[i];
				
				//only include points that are still on the map (not null)
				if(nextRow >= 0 && nextRow<rows && nextCol >= 0 && nextCol < cols) {
					if(vis[nextRow][nextCol] == false && !map[nextRow][nextCol].equals("@")) { //if coord has not been visited before and is walkable
						vis[nextRow][nextCol] = true;
						queue.add(new int[]{nextRow, nextCol}); //enqueue new points
						
						//need to store previous point
						parentRow[nextRow][nextCol] = rowVal;
						parentCol[nextRow][nextCol] = colVal;
					}
					/*
					if(map[nextRow][nextCol].equals("|")) {
						for(int i = 0; i < 
						queue.add(new int[]{});
					}
					*/
				}
			}
		}
	
		//once coin is found, need to retrace steps and rewrite path with +
		if(coinFound == true) {
			int ro = coinX;
			int co = coinY;
			
			while((ro!= wolvX || co!= wolvY)) { //excluding wolvs original position bc it is not overwritten
				//find coords of previous point
				int prevR = parentRow[ro][co]; //tracing backwards from coins position
				int prevC = parentCol[ro][co];
				
				if(map[ro][co].equals(".")) {
					map[ro][co] = "+";
				}
				
				ro = prevR;
				co = prevC;
			}
			
			long endTime = System.nanoTime();
			total = endTime - startTime;
			
			
			//System.out.println(Arrays.deepToString(map)); //print map
			
			//print maps in proper formatting:
			int nco = 0;
			
			if(fileName.contains("C")) {
				for(int r = 0; r < map.length; r++) {
					for(int c = 0; c < map[0].length; c++) {
						if(map[r][c].equals("+")) {
							if(nums>1) {
								nco = rows/nums;
							}
							System.out.println("+ " + r + " "+ c + " " + nco);
						}
					}
				}
			}
			else {
				for(int r = 0; r < map.length; r++) {
					for(int c = 0; c < map[0].length; c++) {
						System.out.print(map[r][c]);
					}
					System.out.println();
				}
			}
			
		}
		else {
			System.out.println("The Wolverine Store is closed."); //no coin can be found or coin is unreachable
		}
		
		double convert = (double) total/1000000000;
		String formatSeconds = String.format("%f", convert);
		System.out.println("Total RunTime: " + formatSeconds + " seconds");
	}
	
	
	
	public static void Stack(String fileName) {
		
		
		if(fileName.contains("C")) {
			try{
				readCoFile(fileName);
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
		else {
			try{
				readTextFile(fileName);
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
		
		
		
		Stack<int[]> stack  = new Stack<>(); //last in first out
		
		
		boolean[][] vis = new boolean[rows][cols]; //true if point has been visited, false otherwise
		boolean coinFound = false;
		

		//store the previous position of wolverine
		int[][] parentRow = new int[rows][cols];
		int[][] parentCol = new int[rows][cols];
		
		int[] northSouth = {-1, 1, 0, 0};
		int[] eastWest = {0, 0, 1, -1};

		
		//adding runtime
		long startTime = System.nanoTime();
		long total = 0;
		
		
		//find coordinate of w and $
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				if(map[r][c].equals("$")) {
					coinX = r;
					coinY = c;
				}
			}
		}
		for(int r = 0; r < nums; r++) {
			for(int c = 0; c < cols; c++) {
				if(map[r][c].equals("w") || map[r][c].equals("W")) {
					wolvX = r;
					wolvY = c;
					
				}
			}
		}

		
		stack.push(new int[] {wolvX, wolvY});
		vis[wolvX][wolvY] = true;
		
		while(!stack.isEmpty()) {
			int[] current = stack.pop(); //removes last added point
			int rowVal = current[0];
			int colVal = current[1];
			
			if(rowVal == coinX && colVal == coinY) { //must be && because we want to compare it to the exact location of the coin, not any points in the same row or column.
				coinFound = true;
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nextRow = rowVal + northSouth[i];
				int nextCol = colVal + eastWest[i];
				
				//remove nulls and unwalkable characters:
				if(nextRow >= 0 && nextRow<rows && nextCol >= 0 && nextCol<cols && vis[nextRow][nextCol] == false && !map[nextRow][nextCol].equals("@")) {
					vis[nextRow][nextCol] = true;
					stack.push(new int[] {nextRow, nextCol});
						
					//save past point coordinates to array so steps can be retraced
					parentRow[nextRow][nextCol] = rowVal;
					parentCol[nextRow][nextCol] = colVal;
				
				}
			}
		}
		
		if(coinFound == true) {
			int ro = coinX;
			int co = coinY;
			
			while((ro!= wolvX || co!= wolvY)) { 
				int prevR = parentRow[ro][co];
				int prevC = parentCol[ro][co];
				
				if(map[ro][co].equals(".")) {
					map[ro][co] = "+"; //replacing found path with +s
				}
				
				ro = prevR;
				co = prevC;
			}
			
			
			long endTime = System.nanoTime();
			total = endTime-startTime;
			
			//print maps in proper formatting specific to their type:
			
			int nco = 0; //used to properly label maps w multiple sections
			
			if(fileName.contains("C")) {
				for(int r = 0; r < map.length; r++) {
					for(int c = 0; c < map[0].length; c++) {
						if(map[r][c].equals("+")) {
							if(nums>1) {
								nco = rows/nums;
							}
							System.out.println("+ " + r + " "+ c + " " + nco);
						}
					}
				}
			}
			else {
				for(int r = 0; r < map.length; r++) {
					for(int c = 0; c < map[0].length; c++) {
						System.out.print(map[r][c]);
					}
					System.out.println();
				}
			}
			
		}
		else {
			System.out.println("The Wolverine Store is closed."); //no coin can be found or coin is unreachable
		}

		double convert = (double) total/1000000000;
		String formatSeconds = String.format("%f", convert);
		System.out.println("Total RunTime: " + formatSeconds + " seconds");
		
	}
	
		
	
	

	
	public static void Optimal(String fileName) {
		
		
		if(fileName.contains("C")) {
			try{
				readCoFile(fileName);
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
		else {
			try{
				readTextFile(fileName);
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
		
		
		
		Stack<int[]> stack  = new Stack<>(); //last in first out
		
		
		boolean[][] vis = new boolean[rows][cols]; //true if point has been visited, false otherwise
		boolean coinFound = false;
		

		//store the previous position of wolverine
		int[][] parentRow = new int[rows][cols];
		int[][] parentCol = new int[rows][cols];
		
		int[] northSouth = {-1, 1, 0, 0};
		int[] eastWest = {0, 0, 1, -1};

		
		//adding runtime
		long startTime = System.nanoTime();
		long total = 0;
		
		
		//find coordinate of w and $
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				if(map[r][c].equals("$")) {
					coinX = r;
					coinY = c;
				}
			}
		}
		for(int r = 0; r < nums; r++) {
			for(int c = 0; c < cols; c++) {
				if(map[r][c].equals("w") || map[r][c].equals("W")) {
					wolvX = r;
					wolvY = c;
					
				}
			}
		}

		
		stack.push(new int[] {wolvX, wolvY});
		vis[wolvX][wolvY] = true;
		
		while(!stack.isEmpty()) {
			int[] current = stack.pop(); //removes last added point
			int rowVal = current[0];
			int colVal = current[1];
			
			if(rowVal == coinX && colVal == coinY) { //must be && because we want to compare it to the exact location of the coin, not any points in the same row or column.
				coinFound = true;
				break;
			}
			
			//pushing north south east west coordinates in the opposite order
			//this will make them get popped in the north south east west order
			for(int i = 3; i >= 0; i--) {
				int nextRow = rowVal + northSouth[i];
				int nextCol = colVal + eastWest[i];
				
				//remove nulls and unwalkable characters:
				if(nextRow >= 0 && nextRow<rows && nextCol >= 0 && nextCol<cols && vis[nextRow][nextCol] == false && !map[nextRow][nextCol].equals("@")) {
					vis[nextRow][nextCol] = true;
					stack.push(new int[] {nextRow, nextCol});
						
					//save past point coordinates to array so steps can be retraced
					parentRow[nextRow][nextCol] = rowVal;
					parentCol[nextRow][nextCol] = colVal;
				
				}
			}
		}
		
		if(coinFound == true) {
			int ro = coinX;
			int co = coinY;
			
			while((ro!= wolvX || co!= wolvY)) { 
				int prevR = parentRow[ro][co];
				int prevC = parentCol[ro][co];
				
				if(map[ro][co].equals(".")) {
					map[ro][co] = "+"; //replacing found path with +s
				}
				
				ro = prevR;
				co = prevC;
			}
			
			
			long endTime = System.nanoTime();
			total = endTime-startTime;
			
			//print maps in proper formatting specific to their type:
			
			int nco = 0; //used to properly label maps w multiple sections
			
			if(fileName.contains("C")) {
				for(int r = 0; r < map.length; r++) {
					for(int c = 0; c < map[0].length; c++) {
						if(map[r][c].equals("+")) {
							if(nums>1) {
								nco = rows/nums;
							}
							System.out.println("+ " + r + " "+ c + " " + nco);
						}
					}
				}
			}
			else {
				for(int r = 0; r < map.length; r++) {
					for(int c = 0; c < map[0].length; c++) {
						System.out.print(map[r][c]);
					}
					System.out.println();
				}
			}
			
		}
		else {
			System.out.println("The Wolverine Store is closed."); //no coin can be found or coin is unreachable
		}

		double convert = (double) total/1000000000;
		String formatSeconds = String.format("%f", convert);
		System.out.println("Total RunTime: " + formatSeconds + " seconds");
	}
	
	
	
}

