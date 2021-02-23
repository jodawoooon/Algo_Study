package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class marble{
    int rr, rc, br, bc, cnt;

	public marble(int rr, int rc, int br, int bc, int cnt) {
		this.rr = rr;
		this.rc = rc;
		this.br = br;
		this.bc = bc;
		this.cnt = cnt;
	}

	public marble() {
		// TODO Auto-generated constructor stub
	}

	
    
}

public class b13460����Ż��2_8 {
    static char[][] arr;
    static boolean[][][][] select;
    static int redR, redC, blueR, blueC, holeR, holeC;
    static int[] dr = {-1,0, 1, 0}; //�� ������ �Ʒ� ����
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   	 

    	StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        select = new boolean[N][M][N][M];

        
        for (int i = 0; i < N; i++) {
        	String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j);
                if (arr[i][j] == 'R') {
                	redR = i;
                	redC = j;

                }
                if (arr[i][j] == 'B') {
                	blueR = i;
                	blueC = j;

                }if (arr[i][j]=='O') {
                	holeR = i;
                	holeC = j;
                }
            }
        }
        
       
        go(new marble(redR, redC, blueR, blueC, 0));
    }
    

    private static void go(marble m) {
        Queue<marble> q = new LinkedList<>();
        
        q.offer(m);

       
        while (!q.isEmpty()) {
            marble tmp = q.poll();

            int r_red = tmp.rr;
        	int c_red = tmp.rc;
        	int r_blue = tmp.br;
        	int c_blue = tmp.bc;
        	int cnt = tmp.cnt;
        	
        	select[r_red][c_red][r_blue][c_blue] = true;
        	
        	if(cnt>=10) {
            	System.out.println(-1);
            	return;
            }
        	
            
            
            
            for (int i = 0; i < 4; i++) {
            	
            	int rr = r_red+dr[i];
            	int rc = c_red+dc[i];
            	int br = r_blue+dr[i];
            	int bc = c_blue+dc[i];

            
                
              //�Ķ�����
                while(true) {
                	if(arr[br][bc]=='#') {
            			br-=dr[i];
            			bc-=dc[i];
            			break;
            		}
                	if(br==holeR&&bc==holeC) {
            			break;
            		}

            		br+=dr[i];
            		bc+=dc[i];
                }

                
                
                
                if(br==holeR&&bc==holeC) {
                	//�Ķ������� ���ۿ������� �ȵȴ�
                	continue;
                }
                
            	//��������
                while(true) {
                	
                	if(arr[rr][rc]=='#') {
            			rr-=dr[i];
            			rc-=dc[i];
            			break;
            		}
                	if(rr==holeR&&rc==holeC) {
            			break;
            		}

            		rr+=dr[i];
            		rc+=dc[i];
                }

                if(rr==holeR&&rc==holeC) {
                	//������������������
                	System.out.println(cnt+1);
                	return;
                }
                
                if(br==rr&&bc==rc) { //�ΰ��� ���� ��ġ�� �ִٸ�

                	if(i==0) { //����
                		if(r_blue>r_red) { //�����̱� ���� �Ķ������� �� �ڿ��־��ٸ�? 
                			br+=1;//�Ķ������� br���� �� �� ������.(��ĭ�� �ϳ��� �ü�����) �׷��Ƿ� 1�� �����༭ ��ĭ �ڷ� �̵���Ų��
                		}else { //���������� �ڿ��־��ٸ�
                			rr+=1;
                		}
                	}else if(i==1) { //���������� �̵��Ҷ�
                		if(c_blue>c_red) { //�Ķ������� c��ǥ�� �� �Ǵٸ� �Ķ������� �տ��ִ� 
                			rc-=1; //���������� ��ĭ�ڷ� �̵��Ѵ�
                		}else {
                			bc-=1;
                		}
                		
                	}else if(i==2) {//�Ʒ������� �̵��Ҷ�
                		if(r_blue>r_red) { //�Ķ������� r��ǥ�� �� �Ǵٸ�, �Ķ������� �� �տ��ִ�
                			rr-=1;
                		}else {
                			br-=1;
                		}
                	}else if(i==3) {//�������� �̵��Ҷ�
                		if(c_blue>c_red) { //�Ķ������� r��ǥ�� �� �Ǵٸ�, ���������� ���տ��ִ�
                			bc+=1;
                		}else {
                			rc+=1;
                		}
                	}
                }
                
                if(!select[rr][rc][br][bc]) { //���� üũ�غ��� ��������Ǽ��̴�.
                	q.offer(new marble(rr,rc,br,bc,cnt+1));//ť�� �ִ´�
                }
            }
        }System.out.println(-1);
        
    }


}