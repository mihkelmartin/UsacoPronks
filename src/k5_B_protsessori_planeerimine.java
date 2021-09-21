import java.io.BufferedReader;
import java.io.InputStreamReader;

public class k5_B_protsessori_planeerimine {
    public static void main(String[] args )throws Exception {
        InputStreamReader ina = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ina);

        String sisend[] = in.readLine().split(" ");
        int m = Integer.parseInt(sisend[0]);
        int n = Integer.parseInt(sisend[1]);
        int t = Integer.parseInt(sisend[2]);

        int[] toid_sekundil = new int[t+1]; // Algväärtus 0
        // Pane alates 1 väärtuseks -1 - tähendab, et sinna ei jõuta korda
        for (int i = 1; i < t + 1; i++) {
            toid_sekundil[i] = -1;
        }

        for (int i = 1; i <= t; i++) {
            if(m <= i && toid_sekundil[i-m] >= 0){
                toid_sekundil[i] = Math.max(toid_sekundil[i-m] + 1, toid_sekundil[i]);
            }
            if(n <= i && toid_sekundil[i-n] >= 0){
                toid_sekundil[i] = Math.max(toid_sekundil[i-n] + 1, toid_sekundil[i]);
            }
        }

        for (int i = t; i >= 0 ; i--) {
            if(toid_sekundil[i] != -1){
                System.out.println(toid_sekundil[i]);
                break;
            }
        }
    }
}

/*
            #include<iostream>
#include<algorithm>
#include<cstring>
using namespace std;
int n,m,t,a[20005];
int main(){
	cin>>n>>m>>t;
	memset(a,-1,sizeof(a));
	a[0]=0;
	for(int i=0; i<t; i++) if(a[i]>-1){
		if(a[i+n]<=a[i]) a[i+n]=a[i]+1;
		if(a[i+m]<=a[i]) a[i+m]=a[i]+1;
	}
	int k=t;
	while(a[k]==-1) k--;
	cout<<a[k]<<"\n";
}

             */
