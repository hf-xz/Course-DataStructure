package cn.edu.bupt.sdmda.main;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;
import cn.edu.bupt.sdmda.ds.linearlist.*;

public class Josephus {
    public static void main(String[] args) {
        prt("约瑟夫环问题求解：请给定总人数n和第k人出局的k（用空格隔开）");
        Scanner in = new Scanner(System.in);
        int n , k;
        n = in.nextInt();
        k = in.nextInt();
        officalSolve(n,k);
        prt("==============");
        mySolve(n,k);
    }

    private static void officalSolve(int n, int k) {
    	prt("官方LinkedList的求解");
        LinkedList<Integer> ll = new LinkedList<Integer>();
        for(int i = 0 ; i < n ; i ++) {
            ll.add(i);
        }
        prt("初始状态："); prt(ll);
        prt("开始出局：");
        Iterator<Integer> cur = ll.iterator();
        while(ll.size() > 1) {
        	for(int i = 0 ; i < k ; i ++) {
        		if(cur.hasNext()) { cur.next(); }
        		else { cur = ll.iterator(); cur.next(); }
        	}
        	cur.remove();
        	prt(ll);
        }
        prt("solved!");
    }

    private static void mySolve(int n, int k) {
    	prt("MyLinkedList的求解");
    	MyLinkedList<Integer> ll = new MyLinkedList<Integer>();
    	for(int i = 0 ; i < n ; i ++) {
    		ll.insert(i, i);
    	}
    	prt("初始状态："); prt(ll);
        prt("开始出局：");
        int cur = 0;
//        ll.deleteAt(cur);
//        prt("test " + ll);
        while(ll.getSize() > 1) {
        	cur = ( cur + k - 1 ) % ll.getSize();
        	ll.deleteAt(cur);
        	prt(ll);
        }
        prt("solved!");
    }

    private static void prt(Object item) {
        System.out.println(item);
    }
}
