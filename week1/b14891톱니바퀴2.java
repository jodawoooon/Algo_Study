package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class b14891��Ϲ���2 {
/*	8���� ��ϸ� ���� ��Ϲ��� 4��
	��ϴ� N�� �Ǵ� S�� �� �ϳ��� ����
	��Ϲ����� ���� ���ʺ��� 1,2,3,4��
	
	�� �� ��Ϲ����� �� K�� ȸ��(��ĭ) ��Ų��
	ȸ���� �ð���� or �ݽð����.
	
	��Ϲ����� ȸ����Ű���� ������ �����ؾ��Ѵ�
	���� �´��� �ؿ� ���� ����
	
	A�� ȸ���� ��, B�� ���� �´��� ����� ���� �ٸ���
	B�� A�� ȸ���� ����� �ݴ�������� ȸ���ϰ� �ȴ�
	
	*���� ��Ϲ��� ���¸� ������
	*
	*/
	static int K, ans;
	//static boolean flagL, flagR;
	static char[][] arr = new char[5][8];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 1; i <= 4; i++) {
			arr[i] = br.readLine().toCharArray(); //12�ù������ �ð�������
		}
		
		K= Integer.parseInt(br.readLine()); //ȸ�� Ƚ��
		
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int num = Integer.parseInt(st.nextToken());//a = ȸ����Ų ��Ϲ��� ��ȣ,
			int d = Integer.parseInt(st.nextToken());// b=���� : 1�̸� �ð�, -1�̸� �ݴ�
			
			
			solve(true, true, num, d); //ù ��Ϲ����� ������ ���� �� ȸ���Ѵ�. 

		}

		//���� ���
		for (int i = 1; i <= 4; i++) {
			//�� ��Ϲ����� 12�� ������ S���̸� ������� 1,2,4,8��
			//System.out.println(Arrays.toString(arr[i]));
            if (arr[i][0]-'0'==1) {
            	
                ans += Math.pow(2, i-1); //2�� i-1���������� ����
            }
        }
		
		System.out.println(ans);
		
	}
	
	private static void solve(boolean rotateLeft,boolean rotateRight, int num, int d ) {
		if(num<1||num>4) return;
		//a-1�� ��Ϲ��� ȸ�� (����)
		if(num!=1&&arr[num][6]!=arr[num-1][2]) {
			//a�� 1���� �ƴϰ�
			//���� ��Ϲ����� �´��� �κ��� ���� �ٸ� ���̶��
			//���� ��Ϲ�����  �ݴ����(-b) �������� ȸ��
			
			//ȸ���������� ���� ��Ϲ����� ȸ���ϸ� �ȵȴ�.
			//flag�� �Ѱ��༭ flag�� true���� ȸ���Ѵ�.
			
			if(rotateLeft) { //�������� ȸ�������Ƿ�
				solve(true, false, num-1, -d); //���ʸ� true
			}
			
		}
		
		//a+1�� ��Ϲ��� ȸ�� (������)
		if(num!=4&&arr[num][2]!=arr[num+1][6]) {

			//a�� 4���� �ƴϰ�, ������ ��Ϲ����� �´��� �κ��� ���� �ٸ� ���̶��
			//������ ��Ϲ����� -b �������� ȸ��
			
			if(rotateRight) {
				solve(false, true, num+1, -d); //�����ʸ� true
			}

		}
		//System.out.println(Arrays.toString(arr[num]));
		
		
		//a�� ��Ϲ��� ȸ��
		if(d==1) { //�ð�����̸�
			char tmp = arr[num][7]; //������ �� ����ϱ�
			for (int i = 7 ; i > 0; i--) {
				arr[num][i] = arr[num][i-1];
			}
			arr[num][0] = tmp;
			
			
		}else if(d==-1) { //�ݽð����
			char tmp = arr[num][0];
			for (int i = 0; i < 7; i++) {
				arr[num][i] = arr[num][i+1];
			}
			arr[num][7] = tmp;
			
		}
		//System.out.println(Arrays.toString(arr[num]));
	}
}
	

