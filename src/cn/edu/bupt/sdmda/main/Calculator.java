package cn.edu.bupt.sdmda.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Calculator {

    // some test case 
    // (2-3*4)-(4*(3*3-1)+3)
    // (2-3*4)-(4*(3*3+(-1))+3)
    // -123/(4-1)+3*(4-86)+98*2
	String exp;
	static ArrayList<Character> ops = new ArrayList<>();

	static HashMap<Character, Integer> prior = new HashMap<>();
	static{
		ops.add('+');
		ops.add('-');
		ops.add('*');
		ops.add('/');
		ops.add('(');
		ops.add(')');

		prior.put('+', 1);
		prior.put('-', 1);
		prior.put('*', 2);
		prior.put('/', 2);
		prior.put('(', 0);
	}

	Stack<Character> opsStack = new Stack<>();
	Stack<Integer> numsStack = new Stack<>();

	StringBuilder numBuilder;

	public Calculator(String str) {
		exp = str;
		numBuilder = new StringBuilder();
	}


	public int calc(){
		for(int i = 0; i < exp.length(); i ++) {
			if(getFlag(i) == 0) {
				numBuilder.append(exp.charAt(i));
			}
			else {
				if(numBuilder.length() > 0) {
					numsStack.push(Integer.valueOf(numBuilder.toString()));
					numBuilder.delete(0, numBuilder.length());
				}
				compareAndCalc(i);
			}
//			prt("cur: " + exp.charAt(i));
//			prt("nums: " + numsStack);
//			prt("ops: " + opsStack);
//			prt("");
		}
		if(numBuilder.length() != 0) {
			numsStack.push(Integer.valueOf(numBuilder.toString()));
		}
		while(!opsStack.empty())
			popAndCalcAndPush();
		return numsStack.pop();
	}


	// return 0 for number
	// return 1 for operator
	// note the '-' which can be both number or operator
	private int getFlag(int i){
		char c = exp.charAt(i);
		if(c == '-') {
			if(i == 0 || exp.charAt(i - 1) == '(') {
				return 0;
			}
			else return 1;
		}
		else if(ops.indexOf(c) != -1) {
			return 1;
		}
		else if (c <= '9' && c >= '0') return 0;
		throw new IllegalArgumentException(String.format("%c is Illegal", c));
	}


	// pop two numbers from stack, one operator from stack
	// calculate result and push in stack
	private void popAndCalcAndPush(){
		int a, b, res;
		char op;
		b = numsStack.pop(); a = numsStack.pop();
		op = opsStack.pop();
		switch(op) {
			case '+':
				res = a + b; break;
			case '-':
				res = a - b; break;
			case '*':
				res = a * b; break;
			case '/':
				res = a / b; break;
			default:
				throw new IllegalArgumentException();
		}
		numsStack.push(res);
	}


	// compare current operator and top operator in stack
	// if prior[cur]>prior[stack[top]], just push cur to stack
	// else popAndCalculate, then push cur to stack
	// note '(' and ')'
	// '(' always be pushed into stack
	// ')' always popAndCalculate until ')'
	private void compareAndCalc(int i){
		char c = exp.charAt(i);
		if(c == '(') {
			opsStack.push(c);
		}
		else if(c == ')') {
			while(opsStack.peek() != '(')
				popAndCalcAndPush();
			opsStack.pop();
		}
		else if(opsStack.empty() || prior.get(c) > prior.get(opsStack.peek())) {
			opsStack.push(c);
		}
		else {
			do{
				popAndCalcAndPush();
			}while(!opsStack.empty() && prior.get(c) <= prior.get(opsStack.peek()));
			opsStack.push(c);
		}
	}
	
//	private void prt(Object obj) {
//		System.out.println(obj);
//	}
}
