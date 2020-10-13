package cn.edu.bupt.sdmda.main;

import cn.edu.bupt.sdmda.ds.linearlist.SeqStack;

public class NumBaseConvertor {
	
	public final static char[] digits=
		{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	
	private static int num(char digit) {
		for(int i = 0 ; i < digits.length ; i ++) {
			if(digits[i] == digit) return i;
		}
		throw new IllegalArgumentException();
	}
	
	public static int toDec(String number, int base){
		int pow = 1, res = 0;
		for(int i = number.length() - 1 ; i >= 0 ; i--) {
			res += num(number.charAt(i)) * pow;
			pow *= base;
		}
		return res;
	}
	
	public static String Convert(int number, int base){
		if(base>digits.length)
			throw new RuntimeException(String.format(
					"base should not larger than {0}, but got {1}",
					digits.length, base));
		SeqStack<Integer> stk = new SeqStack<Integer>();
		while(number > 0) {
			stk.push(number % base);
			number /= base;
		}
		String res = "";
		while(!stk.isEmpty()) {
			res += digits[stk.pop()];
		}
		return res;
	}
	
	
	
	public static String Convert(String number, int srcBase, int desBase) {
		return Convert( toDec(number, srcBase),desBase);
	}
}
