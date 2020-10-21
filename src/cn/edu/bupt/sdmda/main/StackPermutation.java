package cn.edu.bupt.sdmda.main;

import cn.edu.bupt.sdmda.ds.linearlist.LinkedStack;

public class StackPermutation {
	public static boolean judge(String input, String output) {
		if(input.length() != output.length()) return false;
		LinkedStack<Character> stk = new LinkedStack<>();
		int len = input.length(), cur = 0;
		for(int i = 0; i < len; i++) {
			char c = output.charAt(i);
			while(stk.isEmpty() || stk.getTop() != c) {
				if(cur < len)
					stk.push(input.charAt(cur++));
				else return false;
			}
			stk.pop();
		}
		return true;
	}
}
