package com.kushd.hackerrank;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LinearRecurrence {
	
	private static MatrixEXP mexp;

	public static void main(String[] args) {
		try {
			mexp = new MatrixEXP();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine.trim());
			for(int pp=0;pp<tc;pp++){
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.split(" ")[0]);
				int mm = Integer.parseInt(strLine.split(" ")[1]);
				int[] numbers = new int[nn];
				strLine = br.readLine();
				String[] values = strLine.split(" ");
				int cnt=0;	
				for(String value : values){
					numbers[cnt] = Integer.parseInt(value);
					cnt++;
				}
				process(numbers,nn,mm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	private static void process(int[] numbers, int nn, int mm) {
		int[][] A = new int[nn][nn];
		for(int bb=0;bb<nn;bb++){
			for(int vv=0;vv<nn;vv++){
				if(bb==nn-1){
					if(vv==0||vv==1||vv==nn-1){
						A[bb][vv] = 1;
					}else{
						A[bb][vv] = 0;
					}
				}else{
					if(vv == bb+1){
						A[bb][vv] = 1;
					}else{
						A[bb][vv] = 0;
					}
				}
			}
		}
		int[][] Am = mexp.powN(A, mm);
		int index = mm%nn;
		index = (nn-index)%nn;
		for(int i=0;i<nn;i++){
			int[][] dummy = new int[nn][1];
			int j=i;
			int k=0;
			while(k < nn){
				dummy[k][0] = numbers[j];
				j++; k++;
				if(j==nn){
					j=0;
				}
			}
			int[][] ans = mexp.multiply(Am, dummy);
			int oo = index-i; if(oo<0){oo = oo+nn;}
			k=0;
			while(k < nn){
				System.out.print(ans[oo][0]);
				System.out.print(" ");
				k++; oo++;
				if(oo==nn){
					oo=0;
				}
			}
			System.out.print("\n");
		}
	}
}


class MatrixEXP {
	
	
	public int[][] powN(int[][] A, int m){
		if(m==1){
			return A;
		}
		
		int[][] C = powN(A, m/2);
		if(m%2==0){
			return multiply(C, C);
		}else{
			int[][] D = multiply(C, C);
			return multiply(D, A);
		}
	}
	
	
	public int[][] multiply(int[][] A, int[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
        int[][] C = new int[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] = addMod(C[i][j], multMod(A[i][k],B[k][j]));
        return C;
    }
	
	
	private static long MOD = 1000000007;
	
	private static int addMod(long a, long b){
		return (int) ((a+b)%MOD);
	}
	
	private static int multMod(long a, long b){
		return (int) ((a*b)%MOD);
	}
	
	
	
	private static long subMod(long a, long b){
		long ans = a-b;
		while(ans<0){
			ans = ans + MOD;
		}
		return  ans;
	}

	
}
