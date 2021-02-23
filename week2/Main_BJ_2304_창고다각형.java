package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2304_â��ٰ��� {
	//â�� �ٰ���
	//
	static int N,L,H, cnt;
	static int maxX, maxY, tmpY;
	static boolean[][] map;
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];

		
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			L = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			arr[n][0] = L; //�Է¹��� �� ��ǥ ���� �迭�� �־��
			arr[n][1] = H;
			
			if(H>maxY) {//���� Y���� �� ��ǥ���� maxX, maxY�� �����ؼ� ����Ѵ�.
				maxX = L;
				maxY = H;
			}
			
		}
		
		//x��ǥ ������� �����Ѵ�
		Arrays.sort(arr, (o1,o2) -> Integer.compare(o1[0], o2[0]));

		int endX = arr[N-1][0];
		int idx = 0;
		
		//maxY�� �������� ��, �ĸ� �����ߴ�.
		
		
		//maxY ������
		for (int x = arr[0][0]; x < maxX; x++) { //x=0���� maxX-1����
			if(x==arr[idx][0]) {
				if(arr[idx][1]>tmpY) { 
					tmpY = arr[idx][1];
				}
				idx++;
			}
			cnt += tmpY;
		}

		//maxY
		cnt += maxY;
		tmpY = 0;
		
		//maxY ����
		idx = N-1;
		for (int x = endX ; x>maxX ; x--) { //x=endX���� maxX+1����
			if(x==arr[idx][0]) { //�ݴ�� �ڿ������� Ȯ��
				if(arr[idx][1]>tmpY) {
					tmpY = arr[idx][1];
				}
				idx--;
			}
			cnt += tmpY;
			
		}	


		System.out.println(cnt);
	
	}
}
