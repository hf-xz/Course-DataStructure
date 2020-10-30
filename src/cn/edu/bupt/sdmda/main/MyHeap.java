package cn.edu.bupt.sdmda.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyHeap<T extends Comparable> {
	
	ArrayList<T> _data;
	int _size;
	
	MyHeap(List<T> data) {
		_data = new ArrayList<T>();
		_data.add(null); _data.addAll(1,data);
		_size = data.size();
		for(int i = _size/2; i > 0; i--) {
			shiftDown(i);
		}
	}
	
	public String toString() {
		return _data.subList(1, _data.size()).toString() + " size:" + ((Integer)_size).toString();
	}
	
	public T peek() {
		return _data.get(1);
	}
	
	public T remove() {
		Collections.swap(_data,1,_size);
		_size--;
		shiftDown(1);
		return _data.remove(_size+1);
	}
	
	public void insert(T t) {
		_data.add(t);
		_size++;
		shiftUp(_size);
	}
	
	private void shiftUp(int i) {
		int par = i/2;
		while(par >= 1) {
			if(_data.get(i).compareTo(_data.get(par)) < 0) {
				Collections.swap(_data, par, i);
				i = par;
				par /= 2;
			}
			else break;
		}
	}
	
	private int leftChild(int i) {return i * 2;}
	private int rightChild(int i) {return i * 2 + 1;}
	private void shiftDown(int i) {
		int par = i;
		while(par * 2 <= _size) {
			int lc = leftChild(par), rc = rightChild(par);
			int min = lc;
			if(rc <= _size && _data.get(rc).compareTo(_data.get(lc)) < 0)
				min = rc;
			if(_data.get(min).compareTo(_data.get(par)) < 0) {
				Collections.swap(_data,min,par);
				par = min;
			}
			else break;
		}
	}
	
}
