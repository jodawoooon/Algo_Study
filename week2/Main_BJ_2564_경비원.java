package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2564_���� {
	/*
	 * ����� ũ��� ������ ���� �� ��ġ �׸��� �������� ��ġ�� �־��� ��
	 *  �������� ��ġ�� �� ���� ������ �ִ� �Ÿ��� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
	 */
	
	static int[][] arr; //������ġ
	static int W,H,N, ans;
	static int tgtDir, tgtDis; //������ġ
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());//���� = W
		H = Integer.parseInt(st.nextToken());//���� = H
		N = Integer.parseInt(br.readLine()); //������ ����

		arr = new int[N][2];
		
		
		//���� ��ġ arr[][], ���� ��ġ tgtW, tgtH ����
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[n][0] = Integer.parseInt(st.nextToken());
			arr[n][1] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		tgtDir = Integer.parseInt(st.nextToken());
		tgtDis = Integer.parseInt(st.nextToken());
		
		
//		for (int[] a : arr) {
//			System.out.println(Arrays.toString(a));
//		}
//		System.out.println(tgtW+" "+tgtH);
		
		for (int n = 0; n < N; n++) {
			int min = Integer.MAX_VALUE;
			
			if(tgtDir+arr[n][0]==3) { 
				//tgtDir==1, ����Dir==2 �̰ų� tgtDir==2, ����Dir==1�� ���
				//�����̰� �� �Ǵ� �� ��ġ�϶� ������ ���ֺ��� �ִٸ�
				int ans1 = tgtDis+H+arr[n][1]; //�������� �����ϴ� ���
				int ans2 = (W-tgtDis)+H+(W-arr[n][1]);
				min = Math.min(ans1, ans2);
				
			}else if(tgtDir+arr[n][0]==7) {
				//tgtDir==3, ����Dir==4 �̰ų� tgtDir==4, ����Dir==3�� ���
				//�����̰� �� �Ǵ� �� ��ġ�϶� ������ ���ֺ��� �ִٸ�
				int ans1 = tgtDis+W+arr[n][1];
				int ans2 = (H-tgtDis)+W+(H-arr[n][1]);
				min = Math.min(ans1, ans2);
				
			}else if(tgtDir==arr[n][0]) {
				//�����̿� ������ ���� ���⿡ �ִ� ���
				min = Math.abs(tgtDis-arr[n][1]);
				
			}else {
				//�����̿� ������ ���� �پ��ִ� ���� ��ġ�� ���
				//arr[n][0]�� ����, arr[n][1]�� distance
				//�������� ��ġ �� ���
				switch (tgtDir) {
				case 1:
					if(arr[n][0]==3) {
						min = tgtDis+arr[n][1];
					}else if(arr[n][0]==4) {
						min= (W-tgtDis)+arr[n][1];
					}
					break;
				case 2:
					if(arr[n][0]==3) {
						min = tgtDis+H-arr[n][1];
					}else if(arr[n][0]==4) {
						min= (W-tgtDis)+(H-arr[n][1]);
					}
					break;
				case 3:
					if(arr[n][0]==1) {
						min = tgtDis+arr[n][1];
					}else if(arr[n][0]==2) {
						min = (H-tgtDis)+arr[n][1];
					}
					break;
				case 4:
					if(arr[n][0]==1) {
						min = tgtDis+(W-arr[n][1]);
					}else if(arr[n][0]==2) {
						min = (H-tgtDis)+(W-arr[n][1]);
					}
					break;
				}

			}
			ans += min;
		}
		
		System.out.println(ans);
	}
}
