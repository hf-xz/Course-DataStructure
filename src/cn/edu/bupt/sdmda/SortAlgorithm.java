package cn.edu.bupt.sdmda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import cn.edu.bupt.sdmda.main.MyHeap;

public class SortAlgorithm {
	public static long insertSort(double[] data, int n) {
		long startTime = System.currentTimeMillis();
		double t; int j;
		 for (int i = 1; i <= n; i++) {
			 data[0] = data[i];
			 t = data[i];
			 for(j = i-1; j >= 0 && data[j] > t; j--) {
				 data[j+1]=data[j];			
			 }
			 data[j+1] = t;
		 }
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	
	public static long selectionSort(double[] data, int n) {
		long startTime = System.currentTimeMillis();
		double t;
		for(int i = 1; i <= n; i++) {
			double mn = Double.MAX_VALUE;
			int mnj = -1, j = i-1;
			while(++j <= n)
				if(data[j] < mn) {
					mn = data[j];
					mnj = j;
				}
			t = data[i];
			data[i] = data[mnj];
			data[mnj] = t;
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	
	public static long bubbleSort(double[] data, int n) {
		long startTime = System.currentTimeMillis();
		int cur = n, tcur;
		double t;
		while(cur > 1) {
			tcur = 0;
			for(int i = 1; i < cur; i++) {
				if(data[i] > data[i+1]) {
					t = data[i];
					data[i] = data[i+1];
					data[i+1] = t;
					tcur = i;
				}
			}
			cur = tcur;
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	
	public static long shellSort(double[] data, int n) {
		long startTime = System.currentTimeMillis();
		
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	
	public static long mergeSort(double[] data, int n) {
		long startTime = System.currentTimeMillis();
		_mergesort(data, 1, n);
		long endTime = System.currentTimeMillis();
//		for(int i = 1 ; i <= n; i++) {
//			System.out.printf("%.4f ",data[i]);
//		}
//		System.out.println();
		return endTime - startTime;
	}
	
	private static void _mergesort(double[] data, int l, int r) {
		if(l >= r) return;
		int mid = (l+r) >> 1;
		_mergesort(data, l, mid);
		_mergesort(data, mid + 1, r);
		int ll = l, rr = mid+1, n = r-l+1;
		double[] res = new double[n];
		for(int i = 0; i < n; i++) {
			if(ll <= mid && rr <= r) {
				if(data[ll] <= data[rr])
					res[i] = data[ll++];
				else
					res[i] = data[rr++];
			}
			else if(ll > mid)
				res[i] = data[rr++];
			else 
				res[i] = data[ll++];
		}
		System.arraycopy(res, 0, data, l, n);
	}
	
	public static long qSort(double[] data, int n) {
		long startTime = System.currentTimeMillis();
		_qsort(data, 1, n);
		long endTime = System.currentTimeMillis();
//		for(int i = 1 ; i <= n; i++) {
//			System.out.printf("%.4f ",data[i]);
//		}
//		System.out.println();
		return endTime - startTime;
	}
	
	private static void _qsort(double[] data, int l, int r) {
		if(l >= r) return;
		int ll = l, rr = r;
		double pivot = data[l];
		while(l < r) {
			while(l < r && data[r] >= pivot) r--;
			data[l] = data[r];
			while(l < r && data[l] <= pivot) l++;
			data[r] = data[l];
		}
		data[l] = pivot;
		_qsort(data,ll,l-1); _qsort(data,l+1,rr);
	}
	
	public static long heapSort(double[] data, int n) {
		long startTime = System.currentTimeMillis();
		
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
}
