import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Main {
    static String[][] maze;
   
    //driver method for the maze recursion
    public static void main(String[] args) throws Exception {
        File mazeFile = new File("maze2.dat");
        //BufferedReader shri= new BufferedReader(fr);
        //String[] tot= shri.readLine().split(" ");
        Scanner shri = new Scanner(mazeFile);
        String[] maz = shri.nextLine().split(" ");
        int r = Integer.parseInt(maz[0]);
        int c = Integer.parseInt(maz[1]);
        maze = new String[r][c];
        String[] temp = new String[r];
        int i = 0;
        while(shri.hasNextLine()) {
            temp[i] = shri.nextLine();
            i++;
        }
        for(int row = 0; row < temp.length; row++) {
            for(int col = 0; col < temp[1].length();col++) {
                maze[row][col] = Character.toString(temp[row].charAt(col));
            }
        }
        //printMaze(); 
        new Main().move(1,0);
    }
    private void moveUp(int r, int c, boolean b) {
        if(!b) {
            maze[r][c] = "+";
            maze[r-1][c] = "+";
            move(r-1,c);
        }
        else if (b) {
            maze[r][c] = ".";
            maze[r-1][c] = "+";
            move(r-1,c);
        }
    }
    private void moveDown(int r, int c, boolean b) {
        if(!b) {
            maze[r][c] = "+";
            maze[r+1][c] = "+";
            move(r+1,c);
        }
        else if (b) {
            maze[r][c] = ".";
            maze[r+1][c] = "+";
            move(r+1,c);
        }
    }
    private void moveRight(int r, int c, boolean b) {
        if(!b) {
            maze[r][c] = "+";
            maze[r][c+1] = "+";
            move(r,c+1);
        }
        else if (b) {
            maze[r][c] = ".";
            maze[r][c+1] = "+";
            move(r,c+1);
        }
    }
    private void moveLeft(int r, int c, boolean b) {
        if(!b) {
            maze[r][c] = "+";
            maze[r][c-1] = "+";
            move(r,c-1);
        }
        else if (b) {
            maze[r][c] = ".";
            maze[r][c-1] = "+";
            move(r,c-1);
        }
    }
    private void move(int r, int c) {
        if(checkUp(maze, r,c," ")) {
            moveUp(r,c,false);
        }
        else if(checkRight(maze,r,c," ")) {
            moveRight(r,c,false);
        }
        else if(checkLeft(maze,r,c," ")) {
            moveLeft(r,c,false);
        }
        else if(checkDown(maze,r,c," ")) {
            moveDown(r,c,false);
        }
        else
            lastPlace(r,c);
    }
   
    //Make sure to leave breadcrumbs(look at stanford ppt)
    private void lastPlace(int r, int c) {
        if(checkUp(maze,r,c,"+")) {
            moveUp(r,c,true);
        }
        if(checkDown(maze,r,c,"+")) {
            moveDown(r,c,true);
        }
        else if(checkRight(maze,r,c,"+")) {
            moveRight(r,c,true);
        }
        else if(checkLeft(maze,r,c,"+")) {
            moveLeft(r,c,true);
        }
        else
            System.out.print("Maze not solved");  
    }
   
    //checks sides
    private boolean checkUp(String[][] m, int r, int c, String move) {
        if(r == 0) {
            return false;
        }
        if(m[r-1][c].equals("-")) {
            print();
        }
        if(m[r-1][c].equals(move)) {
            return true;
        }
        return false;
    }
    private boolean checkDown(String[][] m, int r, int c, String move) {
        if(r == m.length-1) {
            return false;
        }
        if(m[r+1][c].equals("-")) {
            print();
        }
        if(m[r+1][c].equals(move)) {
            return true;
        }
        return false;
    }
    private boolean checkRight(String[][] m, int r, int c, String move) {
        if(c == m[r].length-1) {
            return false;
        }
        if(m[r][c+1].equals("-")) {
            print();
        }
        if(m[r][c+1].equals(move)) {
            return true;
        }
        return false;
    }
    private boolean checkLeft(String[][] m, int r, int c, String move) {
        if(c == 0) {
            return false;
        }
        if(m[r][c-1].equals("-")) {
            print();
        }
        if(m[r][c-1].equals(move)) {
            return true;
        }
        return false;
    }
   
//	public static boolean solve(int row, int col, char[][] maze ) { // maybe add another variable char[][]answer 
////if(maze[row][col]=='X') {
////	return false;
////}
////if(maze[row][col]=='+') {
////	return false;
////}
////if(maze[row][col]=='-') {
////	return true; 
////}
////maze[row][col]='+'; 
////
////if(solve(row-1,col,maze)) {
////	return true; 
////}
////if(solve(row,col+1, maze)) {
////	return true;
////}
////if(solve(row+1, col,maze)) {
////	return true;
////}
////if(solve(row,col-1,maze)) {
////	return true; 
////}
////maze[row][col]= '.';
////return false;
//
//
////
//
//
//if(maze[row][col]==' ') {
//	maze[row][col]= '+';
//	return true;
//}
//else if(maze[row][col]=='X') {
//	return false; 
//}
//
//
//if((col<maze[0].length)&& solve(row,col+1,maze)) {
//	return solve(row,col+1,maze); 
//}
//if(solve(row+1,col,maze)){
//	return true;
//}
//
//if(solve(row-1,col,maze)||solve(row+1,col,maze)||solve(row,col-1,maze)|| solve(row,col+1,maze)) {
//	maze[row][col]= '+';
//	return true; 
//}
//else {
//	maze[row][col]='.';
//}	
//if(maze[row][col]== '-') {
//	printMaze(maze);
//}
//return false; 
//}
   
    private static void print(){
        for (int r = 0; r < maze.length; r++) {
            for(int c = 0; c < maze[r].length;c++) {
                System.out.print(maze[r][c]);
            }
            System.out.println();
        }
        System.out.println("Maze solved!");
        System.exit(0); 
       
    }
}