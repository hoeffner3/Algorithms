package com.kushd.hackerrank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CircleMath {
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = br.readLine();
			int tc = Integer.parseInt(strLine.trim());
			for(int pp=0;pp<tc;pp++){
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.split(" ")[0]);
				int kk = Integer.parseInt(strLine.split(" ")[1]);
				
				long ans = process(nn,kk);
				System.out.println(ans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	private static long MOD = 1000000007;
	
	private static long addMod(long a, long b){
		return ((a+b)%MOD);
	}
	
	private static long multMod(long a, long b){
		return ((a*b)%MOD);
	}
	
	
	
	private static long subMod(long a, long b){
		long ans = a-b;
		while(ans<0){
			ans = ans + MOD;
		}
		return  ans;
	}
	
	private static Map<Integer,Long> dmap = new HashMap<Integer,Long>();
	private static NCR ncrr = new NCR();
	
	private static long dearrage(int n){
		if(n==0){
			return 1;
		}
		if(n==1){
			return 0;
		}
		if(dmap.get(n)!=null){
			return dmap.get(n);
		}
		
		long ans = multMod((n-1), addMod(dearrage(n-1), dearrage(n-2)));
		dmap.put(n, ans);
		return ans;
	}

	private static long process(int nn, int kk) {
		if(kk==1){
			return 0;
		}
		long ans;
		if((nn-kk) < kk){
			long t1 = ncrr.getncr(nn, kk);
			long t2 = modFactorial(kk-1, MOD);
			long t3 = dearrage(nn-kk);
			ans = multMod(multMod(t1,t2),t3);
		}else{
			long t1 = ncrr.getncr(nn, kk);
			long t2 = process(nn-kk, kk);
			long t3 = dearrage(nn-kk);
			ans = multMod(t1,subMod(t3, t2));
			int i=1;
			int temp = kk;
			while(temp <= (nn-kk) ){
				long t4 = ggg(nn-kk,kk,i);
				ans = ans + multMod(t1, t4)/(i+1);
				temp = temp + kk;
			}
		}
		
		return ans;
		
	}
	
	private static long ggg(int nn, int kk, int i){
		if(i==0){
			long t1 = dearrage(nn);
			long t2;
			if(nn<kk){
				t2=0;
			}else{
				t2 = process(nn, kk);
			}
			return subMod(t1, t2);
		}
		long t3 = ncrr.getncr(nn, kk);
		long t4 = ggg(nn-kk,kk,i-1);
		long ans = multMod(t3, t4)/(i);
		return ans;
	}

	private static long modFactorial(long N, long P)
	{
	    long val = 1;
	   while (N > 0)
	   {
	       for (long i=2, m=N%P; i<=m; i++)
	           val = (val * i) % P;

	       if ((N/=P)%2 > 0)
	           val = P - val;
	   }
	   return val;

	}

}



class NCR {
	
	private long P = 1000000007;
	
	public long getncr(int N, int R){
		long multN = findMultiplicity(N, P);
	       long multR = findMultiplicity(R, P);
	       long multNR = findMultiplicity(N-R, P);
	       long prod;

	       if (multN > multNR + multR)
	           prod = 0;
	       else {
	           long modFactN = modFactorial(N, P);
	           long modFactR = modFactorial(R, P);
	           long modFactNR = modFactorial(N-R, P);
	           //std::cout << "n= " << modFactN<<"   r= "<<modFactR<<"  n-r= "<<modFactNR<<"\n";
	           long invR = modInverse(modFactR, P);
	           long invNR = modInverse(modFactNR, P);
	           prod = (invR * invNR) % P;
	           prod = (modFactN * prod) % P;
	           //long denom = (invR * invNR) % P;
	           //std::cout << "invR= "<<invR<<"  Inv n-r= "<<invNR<<"\n";
	           //prod = (modFactN * denom) % P;
	       }
	       
	       return prod;
	}
	
	
	private long findMultiplicity(long num, long p)
	{
	   long multiplicity = 0;
	   while (num > 0) {
	       multiplicity += num / p;
	       num /= p;
	   }
	   return multiplicity;
	}

	long modFactorial(long N, long P)
	{
	    long val = 1;
	   while (N > 0)
	   {
	       for (long i=2, m=N%P; i<=m; i++)
	           val = (val * i) % P;

	       if ((N/=P)%2 > 0)
	           val = P - val;
	   }
	   return val;

	}

	long pow(long a, long b, long MOD)
	{
	   long x=1,y=a; 
	   while(b > 0)
	   {
	       if(b%2 == 1)
	       {
	           x=(x*y);
	           if(x>MOD) x%=MOD;
	       }
	       y = (y*y);
	       if(y>MOD) y%=MOD; 
	       b /= 2;
	   }
	   return x;
	}

	long modInverse(long n, long MOD)
	{
	   return pow(n,MOD-2,MOD);
	}
}
