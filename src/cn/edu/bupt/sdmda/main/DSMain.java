package cn.edu.bupt.sdmda.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import cn.edu.bupt.sdmda.SortAlgorithm;
import cn.edu.bupt.sdmda.ds.hash.MyHash;
import cn.edu.bupt.sdmda.ds.linearlist.LinearList;
import cn.edu.bupt.sdmda.ds.linearlist.LinkedQueue;
import cn.edu.bupt.sdmda.ds.linearlist.LinkedStack;
import cn.edu.bupt.sdmda.ds.linearlist.MyLinkedList;
import cn.edu.bupt.sdmda.ds.linearlist.SeqList;
import cn.edu.bupt.sdmda.ds.linearlist.SeqQueue;
import cn.edu.bupt.sdmda.ds.linearlist.SeqStack;
import cn.edu.bupt.sdmda.ds.tree.BiTree;
import cn.edu.bupt.sdmda.ds.tree.BiTreeNode;

public class DSMain {
	public static void main(String[] args) {
		boolean res = false;
		if (args.length >= 2) {
			switch (args[0]) {
			case "linearlist":
				testLinearListMain(args);
				break;
			case "stack":
				testStackMain(args);
				break;
			case "queue":
				testQueueMain(args);
				break;
			case "calculator":
				calculator(args[1]);
				break;
			case "convert":
				convert(args[1], Integer.parseInt(args[2]), 
					Integer.parseInt(args[3]));
				break;
			case "bracketsmatch":
				if(args.length==2) {
					res = BracketsMatch.match(args[1]);
				}
				if(args.length==3) {
					res = BracketsMatch.match(args[1], args[2]);
				}
				System.out.println("Brackets match result: " + res);
				break;
			case "tree":
				testTreeMain(args);
				break;
			case "huffman":
				testHuffmanMain(args);
				break;
			case "graph":
				testGraphMain(args);
				break;
			case "hash":
				testHashMain(args);
				break;
			case "printer":
				testPrinter(args);
				break;
			case "stackpermutation":
				res = StackPermutation.judge(args[1], args[2]);
				System.out.println("Stack permutation is legal:" + res);
				break;
			case "heap":
				testHeapMain(args);
				break;
			case "sort":
				testSortMain(args);
				break;
			}				
		}

	}
	
	public static void testLinearListMain(String[] args) {
		SeqList<Integer> seql = new SeqList<Integer>(args.length - 1, 0);
		for (int i = 0; i < args.length - 1; ++i) {
			seql.set(i, Integer.parseInt(args[i + 1]));
		}
		testLinearList(seql);

		System.out.println("=======");

		MyLinkedList<Integer> lnkl = new MyLinkedList<Integer>(args.length - 1, 0);
		for (int i = 0; i < args.length - 1; ++i) {
			lnkl.set(i, Integer.parseInt(args[i + 1]));
		}
		testLinearList(lnkl);
	}

	private static void testLinearList(LinearList<Integer> ll) {
		Integer ele1 = ll.get(0) + ll.get(1);
		Integer ele2 = ll.get(0) - ll.get(1);
		Integer ele3 = ll.get(0) * ll.get(1);

		// print size and all
		printInfoOfLinearList(ll);

		// insert in head
		// insert in mid
		// insert in tail
		System.out.println("inserting 3 elements");
		ll.insert(0, ele1);
		ll.insert(ll.getSize() / 2, ele2);
		ll.insert(ll.getSize(), ele3);

		// print size and all
		printInfoOfLinearList(ll);

		// delete in head
		// delete in mid
		// delete in tail
		System.out.println("deleting 3 elements");

		System.out.print(ll.deleteAt(0) + "\t");
		System.out.print(ll.deleteAt(ll.getSize() / 2) + "\t");
		System.out.println(ll.deleteAt(ll.getSize() - 1));

		// print size and all
		printInfoOfLinearList(ll);

		// find element like the tail
		// delete element like the tail
		System.out.println("finding last element");
		Integer found = ll.find(ll.get(ll.getSize() - 1));
		System.out.println(found);
		ll.delete(ll.get(found));

		// print size and all
		printInfoOfLinearList(ll);

		// clear
		System.out.println("clearing");
		ll.clear();
		// print size and all
		printInfoOfLinearList(ll);
	}

	private static void printInfoOfLinearList(LinearList<Integer> ll) {
		System.out.println("List Summay:");
		System.out.println(ll.getSize());
		for (int i = 0; i < ll.getSize(); ++i) {
			System.out.print(ll.get(i) + "\t");
		}
		System.out.println("");
	}

	public static void testStackMain(String[] args) {

		SeqStack<String> s = new SeqStack<String>();
		System.out.println("Push and pop half of input");
		// push
		int i = 1;
		for (; i < args.length / 2; ++i) {
			s.push(args[i]); System.out.println(s);
		}
		// pop
		while (!s.isEmpty()) {
			System.out.println(s.pop()); System.out.println(s);
		}
		System.out.println("===half===");
		for (; i < args.length; ++i) {
			s.push(args[i]); System.out.println(s);
		}
		while (!s.isEmpty()) {
			System.out.println(s.pop()); System.out.println(s);
		}

		LinkedStack<String> ls = new LinkedStack<>();
		System.out.println("↑SeqStack-------↓LinkedStack");
		System.out.println("Push and pop half of input");
		// push
		i = 1;
		for (; i < args.length / 2; ++i) {
			ls.push(args[i]); System.out.println(ls);
		}
		// pop
		while (!ls.isEmpty()) {
			System.out.println(ls.pop()); System.out.println(ls);
		}
		System.out.println("===half===");
		for (; i < args.length; ++i) {
			ls.push(args[i]); System.out.println(ls);
		}
		while (!ls.isEmpty()) {
			System.out.println(ls.pop()); System.out.println(ls);
		}

	}

	public static void testQueueMain(String[] args) {
		SeqQueue<String> sq = new SeqQueue<String>();
		System.out.println("Offer and poll half of input");
		// offer
		int i = 1;
		for (; i < args.length / 2; ++i) {
			sq.offer(args[i]);
		}
		// poll
		while (!sq.isEmpty()) {
			System.out.println(sq.poll());
		}
		System.out.println("===half===");
		for (; i < args.length; ++i) {
			sq.offer(args[i]);
		}
		while (!sq.isEmpty()) {
			System.out.println(sq.poll());
		}
		System.out.println("↑SeqQueue--------LinkedQueue↓");
		LinkedQueue<String> lq = new LinkedQueue<String>();
		System.out.println("Offer and poll half of input");
		// offer
		i = 1;
		for (; i < args.length / 2; ++i) {
			lq.offer(args[i]);
		}
		// poll
		while (!lq.isEmpty()) {
			System.out.println(lq.poll());
		}
		System.out.println("===half===");
		for (; i < args.length; ++i) {
			lq.offer(args[i]);
		}
		while (!lq.isEmpty()) {
			System.out.println(lq.poll());
		}
	}

	public static void calculator(String exp) {
		Calculator c = new Calculator(exp);
		System.out.println(c.calc());
	}

    public static void convert(String src, int sBase, int dBase) {
            System.out.println(NumBaseConvertor.Convert(src, sBase, dBase));
    }
	
    
	public static void testTreeMain(String[] args) {
		Integer[] data = new Integer[args.length - 1];
		for (int i = 0; i < data.length; ++i) {
			data[i] = Integer.parseInt(args[i + 1]);
			if (data[i] == -1)
				data[i] = null;
		}

		BiTree<Integer> tree = new BiTree<>(data);

		PrintTree(tree);
		System.out.println("depth:" + tree.getDepth(tree.getRoot()));

		BiTreeNode<Integer> n = tree.searchInOrder(tree.getRoot(), data[data.length / 2]);
		n.setLeft(new BiTreeNode<Integer>(data[data.length / 2] * 3));
		PrintTree(tree);
		System.out.println("depth:" + tree.getDepth(tree.getRoot()));

	}

	private static <T> void PrintTree(BiTree<T> tree) {
		tree.clearQueue();
		System.out.println("=====preOrder=====");
		tree.preOrder(tree.getRoot());
		printQueue(tree.getQueue());
		tree.clearQueue();

		System.out.println("=====inOrder=====");
		tree.clearQueue();
		tree.inOrder(tree.getRoot());
		printQueue(tree.getQueue());
		tree.clearQueue();

		System.out.println("=====postOrder=====");
		tree.clearQueue();
		tree.postOrder(tree.getRoot());
		printQueue(tree.getQueue());
		tree.clearQueue();

		System.out.println("=====levelOrder=====");
		tree.clearQueue();
		tree.levelOrder();
		printQueue(tree.getQueue());
		tree.clearQueue();

		System.out.println("=====end=====");
	}

	private static <T> void printQueue(LinkedList<BiTreeNode<T>> queue) {
		for (BiTreeNode<T> n : queue) {
			System.out.print(n.getData()+" ");
		}
		System.out.println();
	}

	public static void testHuffmanMain(String[] args) {
		ArrayList<Symbol> ls = new ArrayList<>();
		for (int i = 1; i < args.length; i += 2) {
			ls.add(new Symbol(args[i], Double.parseDouble(args[i + 1])));
		}
		HuffmanTree ht = new HuffmanTree(ls);
		System.out.println(ht.getSymbolTable());
	}

	public static void testGraphMain(String[] args) {
		int n = Integer.parseInt(args[1]);
		double[][] graph = new double[n][n];
		for (int i = 2; i < args.length; ++i) {
			graph[(i - 2) / n][(i - 2) % n] = Double.parseDouble(args[i]);
		}

		for (int i = 0; i < n; ++i) {
			System.out.println("====i(" + i + ")====");
			GraphAlgorithm.bfs(graph, i);
			System.out.println();
			GraphAlgorithm.dfs(graph, i);
			System.out.println();

		}
		System.out.println("====PRIM====");
		printGraph(GraphAlgorithm.prim(graph));
		System.out.println("====KRUSKAL====");
		printGraph(GraphAlgorithm.kruskal(graph));
		System.out.println("====DIJKSTRA(" + 0 + " to " + n / 2 + ")====");
		System.out.println(GraphAlgorithm.dijkstra(graph, 0, n / 2));
	}

	static void printGraph(double[][] graph) {
		for (int i = 0; i < graph.length; ++i) {
			for (int j = 0; j < graph[i].length; ++j) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public static void testHashMain(String[] args) {
    MyHash<String, String> mh = new MyHash<>();
		List<String> keys = new ArrayList<>();
		for (int i = 0; i < (args.length - 1) / 2; ++i) {
			mh.put(args[2 * i + 1], args[2 * i + 2]);
			keys.add(args[2 * i + 1]);
		}

		for (int i = 0; i < keys.size(); ++i) {
			System.out.println("Key:"+keys.get(i)+"\t"+mh.get(keys.get(i)));
		}

		mh.put(keys.get(keys.size() / 2), "**FOR TEST**");
		System.out.println("=======");
		for (int i = 0; i < keys.size(); ++i) {
			System.out.println("Key:"+keys.get(i)+"\t"+mh.get(keys.get(i)));
		}

		mh.remove(keys.get(keys.size() / 2));  
		System.out.println("=======");
		for (int i = 0; i < keys.size(); ++i) {
			System.out.println("Key:"+keys.get(i)+"\t"+mh.get(keys.get(i)));
		}
	}
	
	public static void testPrinter(String[] args) {
		PrinterSimulator.main(args);
	}

	public static void testHeapMain(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 1; i < args.length; i++) {
			list.add(Integer.parseInt(args[i]));
		}
		//建堆
		System.out.println("input: " + list.toString());
		MyHeap<Integer> heap = new MyHeap<Integer>(list);
		System.out.println("heap: " + heap);
		//删除测试
		System.out.println("====remove half====");
		for(int i = 0; i < list.size()/2; i++) {
			System.out.print(heap.remove() + " ");
		}
		System.out.println("\n" + heap);
		//插入测试
		System.out.println("====insert 3====");
		int t = list.get(list.size()-1);
		heap.insert(t); heap.insert(t/2); heap.insert(t*2);
		System.out.println(heap);
	}
	
	public static void testSortMain(String[] args) {
		//init
		int n = Integer.parseInt(args[1]);
		int m = Integer.parseInt(args[2]);
		double[] data = new double[n+1];
		Random r = new Random();
		for(int i = 1; i <= n; i++) {
			data[i] = r.nextDouble();
		}
		long ans = 0;
		//test
		for(int i = 0; i < m; i++) {
			ans += SortAlgorithm.insertSort(data.clone(), n);
		}
		System.out.println("Insert:" + Long.toString(ans));
		ans = 0;
		for(int i = 0; i < m; i++) {
			ans += SortAlgorithm.selectionSort(data.clone(), n);
		}
		System.out.println("Selection:" + Long.toString(ans));
		ans = 0;
		for(int i = 0; i < m; i++) {
			ans += SortAlgorithm.bubbleSort(data.clone(), n);
		}
		System.out.println("Bubble:" + Long.toString(ans));
		ans = 0;
		for(int i = 0; i < m; i++) {
			ans += SortAlgorithm.shellSort(data.clone(), n);
		}
		System.out.println("Shell:" + Long.toString(ans));
		ans = 0;
		for(int i = 0; i < m; i++) {
			ans += SortAlgorithm.mergeSort(data.clone(), n);
		}
		System.out.println("Merge:" + Long.toString(ans));
		ans = 0;
		for(int i = 0; i < m; i++) {
			ans += SortAlgorithm.qSort(data.clone(), n);
		}
		System.out.println("QSort:" + Long.toString(ans));
		ans = 0;
		for(int i = 0; i < m; i++) {
			ans += SortAlgorithm.heapSort(data.clone(), n);
		}
		System.out.println("Heap:" + Long.toString(ans));
	}
}
