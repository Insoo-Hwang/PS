import java.util.*;
import java.io.*;

class Solution
{
    static int N;
    static int W;
    static int H;
    static int [][] A;
    static int min;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();
            A = new int [H][W];
            for(int i = 0; i < H; i++){
            	for(int j = 0; j < W; j++){
                	A[i][j] = sc.nextInt();
                }
            }
            min = W*H;
            DFS(0);
            System.out.println("#" + test_case + " " + min);
		}
	}
    
    static void DFS(int d){
    	if(d == N){
            int cnt = 0;
            for(int i = 0; i < H; i++){
            	for(int j = 0; j < W; j++){
                	if(A[i][j] != 0) cnt++;
                }
            }
        	min = Math.min(min, cnt);
            return;
        }
        else{
        	int cnt = 0;
            for(int i = 0; i < H; i++){
            	for(int j = 0; j < W; j++){
                	if(A[i][j] != 0) cnt++;
                }
            }
            if(cnt == 0){
            	min = 0;
                return;
            }
        }
        int [][] B = new int [H][W];
        B = backup(B, A);
        for(int i = 0; i < W; i++){
            int h = 0;
            while(h < H && A[h][i] == 0){
            	h++;
            }
            if(h == H) continue;
            if(A[h][i] == 1) A[h][i] = 0;
            else crush(h, i, A[h][i]);
            down();
            DFS(d+1);
            A = backup(A, B);
        }
    }
    
    static void crush(int n , int m, int num){
    	A[n][m] = 0;
        int [] dy = {-1, 1, 0, 0};
        int [] dx = {0, 0, -1, 1};
        for(int i = 0; i < 4; i++){
        	for(int j = 1; j < num; j++){
            	int y = n+dy[i]*j;
                int x = m+dx[i]*j;
                if(y > H-1 || y < 0 || x > W-1 || x < 0 || A[y][x] == 0) continue;
                if(A[y][x] == 1) A[y][x] = 0;
                else crush(y, x, A[y][x]);
            }
        }
    }
    
    static void down(){
    	for(int i = 0; i < W; i++){
            boolean check = false;
        	for(int j = H-1; j > -1; j--){
                if(check) continue;
            	if(A[j][i] != 0) continue;
                int h = j;
                while(h > 0 && A[h][i] == 0){
                	h--;
                }
                if(A[h][i] == 0){
                    check = true;
                }
                else{
                	A[j][i] = A[h][i];
                    A[h][i] = 0;
                }
            }
        }
    }
    
    static int [][] backup(int [][] a, int [][] b){
    	for(int i = 0; i < H; i++){
        	for(int j = 0; j < W; j++){
            	a[i][j] = b[i][j];
            }
        }
        return a;
    }
}