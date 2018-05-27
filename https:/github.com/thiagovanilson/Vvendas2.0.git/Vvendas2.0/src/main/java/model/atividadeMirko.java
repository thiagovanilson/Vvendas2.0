package model;

public class atividadeMirko {

	public static void main(String[] args) {
		System.out.println(fibo(5) );
		System.out.println(fibo(20) );
		System.out.println(fibo(100) );
		System.out.println(fibo(1000) );
		System.out.println(fibo(10000) );
		
	}
	

	static int fibo (int v) {
		if(v == 0 || v == 1)
			return v;
		else
			return fibo (v -1) + fibo(v -2);
	}
}
