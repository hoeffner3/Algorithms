package com.kushd.leetcode.oj;

import java.util.LinkedList;
import java.util.Queue;

public class BFSMarker {
	
	public static void main(String[] args) {
		//char[][] board = new char[][] {"XOOXXXOXXOOOOOOOOOOO".toCharArray(),"XOOXXOOXOOOXOXOXOOXO".toCharArray(),"OOOXXXXOXOXXOOOOXOXO".toCharArray(),"OOOXXOOXOOOXXXOOXOOX".toCharArray(),"OOOOOOOXXXOOOOOOOOOO".toCharArray(),"XOOOOXOXOXXOOOOOOXOX".toCharArray(),"OOOXOOOXOXOXOXOXOXOX".toCharArray(),"OOOXOXOOXXOXOXXOXXXO".toCharArray(),"OOOOXOOXXOOOOXOOOXOX".toCharArray(),"OOXOOXOOOOOXOOXOOOXO".toCharArray(),"XOOXOOOOOOOXOOXOXOXO".toCharArray(),"OXOOOXOXOXXOXXXOXXOO".toCharArray(),"XXOXOOOOXOOOOOOXOOOX".toCharArray(),"OXOOXXXOOOXXXXXOXOOO".toCharArray(),"OOXXXOOOXXOOOXOXOOOO".toCharArray(),"XOOXOXOOOOXOOOXOXOXX".toCharArray(),"XOXOOOOOOXOOOXOXOOOO".toCharArray(),"OXXOOOXXXOXOXOXXXXOO".toCharArray(),"OXOOOOXXOOXOXOOXOOXX".toCharArray(),"OOOOOOXXXXOXOOOXXOOO".toCharArray()};
		char[][] board = new char[][] {"O".toCharArray()};
		solve(board);
		for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                System.out.println(board[i][j]);
            }
        }
	}
	
	public static void solve(char[][] board) {
        int rowSize = board.length;
        if(rowSize==0){
            return;
        }
        int colSize = board[0].length;
        char marker = 'K';
        for(int i=0;i<colSize;i++){
            if(board[0][i] == 'O'){
                markForBFS(board,marker,0,i);
            }
            if(board[rowSize-1][i] == 'O'){
                markForBFS(board,marker,rowSize-1,i);
            }
        }
        for(int i=0;i<rowSize;i++){
            if(board[i][0] == 'O'){
                markForBFS(board,marker,i,0);
            }
            if(board[i][colSize-1] == 'O'){
                markForBFS(board,marker,i,colSize-1);
            }
        }
        
        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++){
                if(board[i][j] == marker){
                    board[i][j] = 'O';
                }else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
        return;
    }
    
    private static void markForBFS(char[][] board, char marker, int row, int col){
        int rowSize = board.length;
        int colSize = board[0].length;
        Queue<String> queue = new LinkedList<String>();
        queue.add(row+"-"+col);
        board[row][col] = marker;
        while(!queue.isEmpty()){
            String point = queue.remove();
            int r = Integer.parseInt(point.split("-")[0]);
            int c = Integer.parseInt(point.split("-")[1]);
            if(r-1 >=0 && board[r-1][c] == 'O'){
                queue.add(r-1+"-"+c);
                board[r-1][c] = marker;
            }
            if(r+1 < rowSize&& board[r+1][c] == 'O'){
                queue.add(r+1+"-"+c);
                board[r+1][c] = marker;
            }
            if(c-1 >=0 && board[r][c-1] == 'O'){
                queue.add(r+"-"+(c-1));
                board[r][c-1] = marker;
            }
            if(c+1 <colSize && board[r][c+1] == 'O'){
                queue.add(r+"-"+(c+1));
                board[r][c+1] = marker;
            }
        }
    }

}
