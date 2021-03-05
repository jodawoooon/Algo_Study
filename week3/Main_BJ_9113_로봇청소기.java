package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_9113_로봇청소기 {
	
	/*
	 * N*M 크기의 직사각형
	 * 각각의 칸은 벽 또는 빈칸
	 * 청소기는 동,서,남.북중 하나를 바라본다
	 * 지도의 각 칸은 (r,c). r은 북쪽으로부터, c는 서쪽으로부터
	 * 
	 * 로봇청소기는 
	 * 1. 현재위치청소
	 * 2. 현재위치에서 현재방향 기준으로 왼쪽부터 차례대로 탐색
	 * 	a. 왼쪽 방향에 아직 청소 안한곳있으면 "그 방향으로 회전"한 후 한칸 전진하고 1번부터 진행
	 * 	b. 왼쪽방향에 청소할 곳 없으면 그 방향으로 회전하고 2번으로 돌아간다.
	 * 	c. 네 방향 모두 청소되었거나 벽이면 바라보는 방향 유지하고 한칸 후진하고 2번
	 * 	d. 네 방향 모두 청소 되어있거나 벽이거나 뒤쪽으로 후진 불가하면 작동 멈춤
	 * 로봇 청소기가 청소하는 칸의 개수를 출력한다.
	 */
	
	//왼쪽 이동
	static int[] dr = {0,-1,0,1}; //0:북->서, 1:북, 2:동, 3:남
	static int[] dc = {-1,0,1,0};
	static int[] leftDir = {3,0,1,2}; //왼쪽방향
	
	//후진
	static int[] b_dr = {1,0,-1,0}; //0:밑 1:왼 2:위 3:오 
	static int[] b_dc = {0,-1,0,1};
	
	
	static int robotDir; //로봇의 현재 방향을 저장할 변수
	static int N,M, arr[][], ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());

		arr = new int[N][M]; //직사각형 배열 선언
		
		st = new StringTokenizer(br.readLine()," "); //로봇청소기의 위치 저장
		int robotR = Integer.parseInt(st.nextToken());
		int robotC = Integer.parseInt(st.nextToken());		
		robotDir = Integer.parseInt(st.nextToken());
	
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		/*for (int[] ia : arr) {
		System.out.println(Arrays.toString(ia));
		}*/
		


		clean(robotR, robotC, robotDir, 0);
		
		System.out.println(ans);
		
	}
	private static void clean(int r, int c, int dir, int cnt) {

		if(arr[r][c]==0) { //현재위치청소
			ans++;
			arr[r][c]=2;
		}
		
		//현재위치에서 현재방향 기준으로 왼쪽부터 차례대로 탐색
		//2여도 갈수는 있다. 1이면 갈수없지만 2면 갈수는있는데 청소를못함
		
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		int nd = leftDir[dir];	//dir별 왼쪽 방향 저장해둔 배열 leftDir
		if(nr>=0&&nc>=0&&nr<N&&nc<M) {
			if(arr[nr][nc]==0) {
				//왼쪽 방향에 아직 청소 안한곳있으면 "그 방향으로 회전"한 후 한칸 전진
				//다시 1번부터 (4방향 탐색)
				clean(nr,nc,nd, 0);
			}else {
				if(cnt<4) {
					//왼쪽방향에 청소할 곳 없으면 그 방향으로 회전하고 2번으로 돌아간다.
					clean(r,c,nd, cnt+1);
					//방향만 바꾸고 다시 4방향 탐색
				}else if(cnt==4) {
					//4방탐색 다 끝나도 청소할 곳이 없다면
					nr = r+b_dr[dir];
					nc = c+b_dc[dir];
					if(nr>=0&&nc>=0&&nr<N&&nc<M) {
						//바라보는 방향 유지하고 한칸 후진하고 2번
						if(arr[nr][nc]!=1) {
							clean(nr,nc,dir,0);//후진하고 다시4방탐색시작
						}else if(arr[nr][nc]==1){
							// 뒤쪽으로 후진 불가하면 작동 멈춤
							 return;
						}
					}
				}
				
			}
				
		}

	}

}
