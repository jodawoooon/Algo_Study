package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class b17142������3_2 {
	
	/*
	 * ���̷��� - Ȱ������, ��Ȱ������
	 * Ȱ�� ���� ���̷����� �����¿� ������ ��� ��ĭ���� "���ÿ�"����. 1�ʰɸ�
	 * �¿��̴� �������� ���̷��� M���� Ȱ�����·� �����ϰ��� �Ѵ�.
	 * 
	 * �����Ҵ� N*N ���簢��
	 * ��ĭ, ��, ���̷����� �̷����
	 * 0 : ��ĭ / 1 : �� / 2 : ���̷���
	 * Ȱ�� ���̷����� ��Ȱ�� ���̷����� �ִ� ĭ���� ���� ��Ȱ�� ���̷����� Ȱ������ ����
	 * 
	 * !! ��� = ��� ��ĭ�� ���̷����� �۶߸��� "�ּҽð�" min
	 * �ּҽð��� �� N*N�迭�� �ִ� ���� �� max �����̴�.
	 * ���̷����� ��� ��Ʈ�� �� ���ٸ� -> -1 ���
	 * 
	 * M = ���� �� �ִ� ���̷����� ����.
	 * 
	 * �� M���� Ȱ�� ���̷��� "����"�� ����� ���̷����� ��Ʈ�� �� "max"�� ���� �� �� �� " min�� ���Ѵ�.
	 * 
	 * 
	 */
	
	static List<int[]> virus = new ArrayList<>();

	static boolean flag;
	static boolean[] select;
	static int N,M,ans, max, count, virusNum;
	static int[] dr = {-1,1,0,0};  //�����¿�
	static int[] dc = {0,0,-1,1};
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;


		arr = new int[N][N];
		
		//�������� ���� ����
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==2) virus.add(new int[]{i,j});

			}
		}

		virusNum= virus.size();
		select = new boolean[virusNum];
		
		//M���� ���̷����� ���Ƽ� 0�� ��� ���̷����� �۶߸���. �� �ּ� �ð��� ����ؾ��Ѵ�.
		//=> �������� ������ 2 �߿��� M���� ���� => ����
		
		comb(0, 0);
		
		//���̷����� ��� ��Ʈ�� �� ���ٸ� ��� break���� �ɷ��� ans�� ������ �� ���� => ans == Integer.Max 
		if(ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
		
	}
	private static void comb(int cnt, int start) { //����
		//M���� ���̷����� ������
		if(cnt==M) {
			
			int[][] map = new int[N][N];
			List<int[]> tgtVirus = new ArrayList<>();
			flag = false; max = 0;
		//	System.out.println(Arrays.toString(select));			
			
			//���̷����� �۶߸���.
		
			
			//�⺻ map ����
			for (int i = 0; i < N; i++) { //�⺻ map �迭�� ��� -1�� �����Ѵ�
				for (int j = 0; j < N; j++) {
					map[i][j] = -1;
				}
			}
			
			//���õ� Ȱ��ȭ ���̷��� �ʿ� ǥ��
			for (int i = 0; i < virusNum; i++) { //���õ� ���̷����� Ȱ�����̷����̹Ƿ� 0���� ǥ��
				if(select[i]) {
					tgtVirus.add(virus.get(i));
					map[virus.get(i)[0]][virus.get(i)[1]] = 0; 
				}	
			}
			
//			System.out.println("======================");		
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(arr[i][j]+" ");
//				}System.out.println();
//			}
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j]+" ");
//				}System.out.println();
//			}
//			

			
			
			//������ �ð� üũ
			for (int i = 0; i < tgtVirus.size(); i++) { //���õ� tgtVirus���� �����¿�� �̵��ϸ鼭 ��ĭ�� ã�� ���̷����� ��Ʈ����

				int r = tgtVirus.get(i)[0];
				int c = tgtVirus.get(i)[1];
				
				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];
					
					if(nr>=0&&nr<N&&nc>=0&&nc<N&& arr[nr][nc]!=1 && map[nr][nc]==-1) {
						//�ε��� ���� ���̰�, ���� �ƴϰ�, �ʱ� Ȱ��ȭ ���̷��� �ƴϸ�
						map[nr][nc] = map[r][c]+1; //�湮 �Ҷ����� 1�� ����
						if(arr[nr][nc]==2) {
							tgtVirus.add(new int[]{nr,nc}); //Ȱ�����̷����� ��Ȱ�����̷����� �ִ� ������ ���� ��Ȱ�����̷����� Ȱ������ ���Ѵ�.
						}
						
					}
				}
			}

			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j]+" ");
				}System.out.println();
			}
			System.out.println("======================");	
//			
//			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(arr[i][j]==0) {//���� ���� �ƴѾֵ� �߿��� => ����ϸ� �ȵ�. ���� ��ĭ�� �ֵ��߿����� ���ؾ���
						if(map[i][j]!=-1) {//-1�� �ƴ϶�� ���̷����� �������̴�.
							max = Math.max(max, map[i][j]);
							
						}else if(map[i][j]==-1){

							//-1�� �����ִٸ� => �� ������ ���ߴ�. -1 ���
							//���⼭ ans=-1�� ó���ع����ϱ� �ٸ� ���̽������� �����ϴ��� -1�� �׳� ����� �ǹ�����.
							
							flag = true;

							break;
						}
						
					}
				}

			}
			//System.out.println(time);
			if(!flag) ans = Math.min(ans, max);


		}
		
		
			for (int i = start; i < virusNum; i++) {
				select[i] = true;
				comb(cnt+1, i+1);
				select[i] = false;
			}

	}

}
