package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_BJ_17070_파이프옮기기1_2 {

	/*
	 * 집 N*N
	 * r은 행의번호 c는 열의번호 (둘다 1부터 시작)
	 * 각 칸은 빈칸이거나 벽이다
	 * 파이프는 연속된 두개의 칸을 차지함
	 * 3가지 방향.
	 * 좌,우  / 상,하  / 좌상,우하
	 * 
	 * 파이프는 빈칸만 차지 
	 * 방향은 우, 우하, 하  방향
	 * 45도씩만 회전 가능
	 * 
	 * 가로 => 가로 or 대각선 
	 * 세로 => 세로 or 대각선
	 * 대각선 => 가로 or 세로 or 대각선
	 * 
	 * 가로 이동 시 => 우 검사
	 * 세로 이동 시 => 하 검사
	 * 대각선 이동시 => 우,하, 우하 검사
	 * 
	 * 
	 * 파이프의 위치를 체크하기. boolean?
	 * 
	 * (1,1)-(1,2)의 파이프의 한쪽끝은 (N,N)으로 이동시키는 방법의 개수. => BFS
	 */
	
	static int N, arr[][], cnt;
	static int dr[] = {0,1,1};//우 하 우하
	static int dc[] = {1,0,1};
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //집의 크기
		
		arr = new int[N+1][N+1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(1,2,0);
		System.out.println(cnt);
	}


	private static void solve(int r, int c, int dir) {
		if(r==N&&c==N) {
			cnt++;
			return;
		}
		
		for (int d = 0; d < 3; d++) {
			if(dir==0&&d==1) continue;
			if(dir==1&&d==0) continue;
			
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(nr<=N&&nc<=N) {
				if(d==0||d==1) {
					if(arr[nr][nc]==0) {
						solve(nr,nc,d);
					}
				}else if(d==2) {
					if(arr[nr][nc]==0&&arr[nr-1][nc]==0&&arr[nr][nc-1]==0) {
						solve(nr,nc,d);
					}
				}
			}
		}
		
		
		
	}
	
	
	
}

