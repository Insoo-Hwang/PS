import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int L;
    static int C;
    static char [] A;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new char[C];
        visited = new boolean[C];
        String [] str = br.readLine().split(" ");
        for(int i = 0; i < C; i++){
            A[i] = str[i].charAt(0);
        }
        Arrays.sort(A); //알파벳을 사전 순으로 정렬

        DFS(0, 0, 0);
    }

    static void DFS(int d, int s, int c){ //깊이, 시작 숫자, 모음의 개수
        if(d == L){
            if(c >= 1 && L-c >= 2){ //모음 및 자음의 개수 확인
                for(int i = 0; i < C; i++){
                    if(visited[i]){
                        System.out.print(A[i]);
                    }
                }
                System.out.println();
            }
            return;
        }
        for(int i = s; i < C; i++){
            if(!visited[i]){
                visited[i] = true;
                if(A[i] == 'a' || A[i] == 'e' || A[i] == 'i' || A[i] == 'o' || A[i] == 'u') //모음의 개수 업데이트
                    c++;
                DFS(d+1, i, c);
                visited[i] = false;
                if(A[i] == 'a' || A[i] == 'e' || A[i] == 'i' || A[i] == 'o' || A[i] == 'u')
                    c--;
            }
        }
    }
}
