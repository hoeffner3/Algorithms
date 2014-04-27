package com.kushd.leetcode.oj;
public class SudokuSolver {
	
	public static void main(String[] args) {
		char[][] board = new char[][]{
				"..9748...".toCharArray(),
				"7........".toCharArray(),
				".2.1.9...".toCharArray(),
				"..7...24.".toCharArray(),
				".64.1.59.".toCharArray(),
				".98...3..".toCharArray(),
				"...8.3.2.".toCharArray(),
				"........6".toCharArray(),
				"...2759..".toCharArray()
		};
		
		solveSudoku(board);
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				System.out.println(board[i][j]);
			}
		}
	}
	
	public static void solveSudoku(char[][] board) {
        char[][] initialBoard = new char[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                initialBoard[i][j] = board[i][j];
            }
        }
        solve(board, initialBoard, 0, 0);
        
    }
    
    private static boolean solve(char[][] board, char[][] initialBoard, int row, int col){
        if(row == 9 ){
            return true;
        }
        if(board[row][col] != '.'){
            if(col == 8){
                return solve(board,initialBoard,row+1,0);
            }else{
                return solve(board,initialBoard,row,col+1);
            }
        }
        for(int i=1;i<=9;i++){
            if(isValid(i,board,row,col)){
                board[row][col] = getCharValue(i);
                boolean result; 
                if(col==8){
                    result = solve(board,initialBoard,row+1,0);
                }else{
                    result = solve(board,initialBoard,row,col+1);
                }
                if(result){
                    return true;
                }
                board[row][col] = '.';
            }
        }
        board[row][col] = '.';
        return false;
    }
    
    private static boolean isValid(int value, char[][] board,int row,int col){
        return validateRow(value,board,row) && validateCol(value,board,col) && validateBox(value,board,row,col);
    }
    
    private static char getCharValue(int value){
    	if(value==1){
    		return '1';
    	}
    	if(value==2){
    		return '2';
    	}
    	if(value==3){
    		return '3';
    	}
    	if(value==4){
    		return '4';
    	}
    	if(value==5){
    		return '5';
    	}
    	if(value==6){
    		return '6';
    	}
    	if(value==7){
    		return '7';
    	}
    	if(value==8){
    		return '8';
    	}
    	if(value==9){
    		return '9';
    	}
    	return '#';
    }
    
    private static boolean validateRow(int value,char[][] board, int row){
        for(int i=0;i<9;i++){
            if(board[row][i] == getCharValue(value) ){
                return false;
            }
        }
        return true;
    }
    
    private static boolean validateCol(int value,char[][] board, int col){
        for(int i=0;i<9;i++){
            if(board[i][col] == getCharValue(value) ){
                return false;
            }
        }
        return true;
    }
    
    private static boolean validateBox(int value, char[][] board, int row, int col){
        int i=0;
        int j=0;
        if(row > 2 && row < 6){
            i = 3;
        }else if(row > 5){
            i = 6;
        }
        if(col > 2 && col < 6){
            j = 3;
        }else if(col > 5){
            j = 6;
        }
        int iEnd = i+3;
        int jEnd = j+3;
        for(int p=i;p<iEnd;p++){
            for(int q=j;q<jEnd;q++){
                if(board[p][q] == getCharValue(value) ){
                    return false;
                }
            }
        }
        return true;
    }

}
