package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b20055�����̾Ʈ {
/*	
 * ���̰� N�� �����̾� ��Ʈ�� ���̰� 2N�� ��Ʈ�� ���ΰ� �����ִ�.  1���� 2N���� ĭ
 * �� ĭ�� �̵�. 1���� �ö󰡴� ��ġ, N����  �������� ��ġ
 * �κ����ϳ��� �ø���
 * �ö󰡴� ��ġ������ �ö󰡰�, �������� ��ġ������ ��������
 * �κ��� � ĭ�� �ö󰡰ų� �̵��ϸ� �� ĭ�� �������� ����. �������� 0�̸� �ö� �� ����
 * �κ��� ������ �̵��� �� �ִ�.
 * 
 * �κ��� �ű�� ����
 * 1. ��Ʈ�� ��ĭ ȸ���Ѵ�
 * 2. ���� ���� ��Ʈ�� �ö� �κ����� ��Ʈ�� ȸ���ϴ� �������� ��ĭ �̵��� �� �ִٸ� �̵�. 
 * �̵��� �� ���ٸ� ������ �ִ´�. �κ��� �̵��ϱ� ���ؼ��� �̵��Ϸ��� ĭ�� �κ��� ������ ĭ�� �������� 1�̻� �����־���Ѵ�
 * 3. �ö󰡴� ��ġ(1��)�� �κ��� ���ٸ� �κ��� �ϳ� �ø���
 * 4. �������� 0�� ĭ�� ������ k�� �̻��̸� ����. �ƴϸ� �ٽ� 1������ ���ư�
 * 
 * ����Ǿ����� ���° �ܰ迴���� ������.
*/
	static int N, K, ans, zeroCnt;
	static int[] arr;
	static boolean[] robot;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[2*N+1];
		robot = new boolean[N+1];
		StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

		for (int i = 1; i <= 2*N; i++) {
			arr[i] = Integer.parseInt(st2.nextToken());
		}


		while(true) {
	
			//1. ��Ʈ�� �� ĭ ȸ���Ѵ�.
			int tmp = arr[2*N]; //arr[1]�� ó���ϱ� ���� tmp��. 
			for (int i = 2*N; i > 1; i--) { //�ڿ������� ó��. i=1���� �����ϸ� ���� �������
				arr[i] = arr[i-1]; //��ĭ�� �ű��.
				if(i<=N) {
					robot[i] = robot[i-1]; //�κ��� ��ĭ �ű��.
				}
			}		
			//��ĭ�� �ű� �� 1�� �ڸ��� �������� �κ� ������ ó���Ѵ�.
			arr[1] = tmp; //2N��° ĭ�� 1������ �ִ´�.
			robot[1] = false; //�Ű������Ƿ� false
			
			if(robot[N]) robot[N] = false; //N�� ��ġ�� �κ��� �ִٸ� �����;� �Ѵ�

				
			
			//2. ���� ���� �ö� �κ����� �̵�
			for (int i = N; i > 1; i--) {
				if(!robot[i] && arr[i]>=1 && robot[i-1]) { //�̵��Ϸ��� ĭ�� �κ��� ������ ĭ�� �������� 1�̻� ����������
					robot[i] = true;
					robot[i-1] = false; //�κ� ��ĭ �̵�
					arr[i]--; //������ ����
					if(arr[i]==0) zeroCnt++; //�������� 0���� üũ
				}
			}
			
			if(robot[N]) robot[N] = false; //N�� ��ġ�� �κ��� �ִٸ� �����;� �Ѵ�
			
			
			
			
			//3.�ö󰡴� ��ġ�� �κ��� ���ٸ� �ø���
			if(!robot[1] && arr[1]>=1) {
				robot[1] = true; //�κ� �ø���
				arr[1] --; //������ ����
				if(arr[1]==0) zeroCnt++;
			}
			
			ans++;

			
//			for (int i = 1; i < 2*N; i++) {
//				System.out.print(arr[i]+" ");
//			}System.out.println();
//			
			//4. �������� 0��ĭ�� ������ K���̻��̶�� ����
			if(zeroCnt>=K) break; 

		}
		System.out.println(ans);
		
		
	}
}
