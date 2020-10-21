package cn.edu.bupt.sdmda.main;

import cn.edu.bupt.sdmda.ds.linearlist.LinkedStack;

public class BracketsMatch {
	public static boolean match(String input) {
		return match(input, "()[]{}");
	}
	
	public static boolean match(String input, String brackets) {
		LinkedStack<Character> stk = new LinkedStack<>();
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			int id = brackets.indexOf(c);
			if(id != -1) {
				if(id % 2 == 0) stk.push(c);
				else {
					if(brackets.indexOf(stk.pop())+1 != id)
						return false;
				}
			}
		}
		if(!stk.isEmpty()) return false;
		else return true;
	}
}
