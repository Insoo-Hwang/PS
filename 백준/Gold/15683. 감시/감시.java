import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static int [][] B;
    static int [][] cctv;
    static int tempCctv;
    static int wall;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        B = new int[N][M];
        cctv = new int[8][3]; //CCTV의 최대 개수는 8개이고 2차원에서는 0 : N좌표, 1 : M좌표, 2 : CCTV의 종류
        tempCctv = 0; //CCTV의 개수
        wall = 0; //벽의 개수
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int temp = Integer.parseInt(st.nextToken());
                A[i][j] = temp;
                if(temp > 0 && temp < 6){
                    cctv[tempCctv][0] = i;
                    cctv[tempCctv][1] = j;
                    cctv[tempCctv][2] = temp; //CCTV 정보를 CCTV 배열에 저장
                    tempCctv++;
                }
                else if(temp == 6){
                    wall++;
                }
            }
        }

        DFS(0);
        System.out.println(min);
    }

    static void DFS(int d){ //d로 몇 번째 CCTV까지 확인했는지 확인
        if(d == tempCctv){ //모두 확인이 끝났으면 사각지대 개수 체크
            int cnt = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(B[i][j] == 0){
                        cnt++;
                    }
                }
            }
            min = Math.min(min, cnt-wall);
            return;
        }

        int [][] backUp = new int[N][M]; //CCTV의 여러 방향 확인을 위해 백트래킹 사용(그 전 상황 저장용 백업 배열) 
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                backUp[i][j] = B[i][j];
            }
        }
        if(cctv[d][2] == 1){ //1번 CCTV
            UP(d);
            DFS(d+1);
            backTracking(backUp);
            DOWN(d);
            DFS(d+1);
            backTracking(backUp);
            LEFT(d);
            DFS(d+1);
            backTracking(backUp);
            RIGHT(d);
            DFS(d+1);
            backTracking(backUp);
        }
        else if(cctv[d][2] == 2){ //2번 CCTV
            UP(d);
            DOWN(d);
            DFS(d+1);
            backTracking(backUp);
            LEFT(d);
            RIGHT(d);
            DFS(d+1);
            backTracking(backUp);
        }
        else if(cctv[d][2] == 3){ //3번 CCTV
            UP(d);
            LEFT(d);
            DFS(d+1);
            backTracking(backUp);
            UP(d);
            RIGHT(d);
            DFS(d+1);
            backTracking(backUp);
            DOWN(d);
            LEFT(d);
            DFS(d+1);
            backTracking(backUp);
            DOWN(d);
            RIGHT(d);
            DFS(d+1);
            backTracking(backUp);
        }
        else if(cctv[d][2] == 4){ //4번 CCTV
            UP(d);
            LEFT(d);
            RIGHT(d);
            DFS(d+1);
            backTracking(backUp);
            DOWN(d);
            LEFT(d);
            RIGHT(d);
            DFS(d+1);
            backTracking(backUp);
            LEFT(d);
            UP(d);
            DOWN(d);
            DFS(d+1);
            backTracking(backUp);
            RIGHT(d);
            UP(d);
            DOWN(d);
            DFS(d+1);
            backTracking(backUp);
        }
        else{
            UP(d);
            DOWN(d);
            LEFT(d);
            RIGHT(d);
            DFS(d+1);
            backTracking(backUp);
        }
    }

    static void UP(int d){
        for(int i = cctv[d][0]; i > -1; i--){
            if(A[i][cctv[d][1]] == 6) break;
            B[i][cctv[d][1]] = 7;
        }
    }

    static void DOWN(int d){
        for(int i = cctv[d][0]; i < N; i++){
            if(A[i][cctv[d][1]] == 6) break;
            B[i][cctv[d][1]] = 7;
        }
    }

    static void LEFT(int d){
        for(int i = cctv[d][1]; i > -1; i--){
            if(A[cctv[d][0]][i] == 6) break;
            B[cctv[d][0]][i] = 7;
        }
    }

    static void RIGHT(int d){
        for(int i = cctv[d][1]; i < M; i++){
            if(A[cctv[d][0]][i] == 6) break;
            B[cctv[d][0]][i] = 7;
        }
    }
    static void backTracking(int[][] backUp){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                B[i][j] = backUp[i][j];
            }
        }
    }
}
