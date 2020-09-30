package cn.edu.bupt.sdmda.ds.linearlist;

import java.util.Arrays;

public class SeqList<T> implements LinearList<T>{

	private Object[] _data;
	private int _size;

	public SeqList(int s, T init){
		init(s, init);
	}

	public SeqList(){
		init(0, null);
	}

	@Override
	public void init(int s, T init) {
		_size = s;
		_data = new Object[s];
		for(int i = 0; i < s; i++){
			_data[i] = init;
		}
	}

	@Override
	public boolean isEmpty() {
		return (getSize() == 0);
	}

	@Override
	public int getSize() {
		return _size;
	}

	@Override
	public void clear() {
		_size = 0;
	}

	@Override
	public void insert(int i, T t) {
		if( !checkWritableRange(i) ) {
			throw new IndexOutOfBoundsException();
		}
		if(_size == _data.length) {
			Object[] newdata = new Object[_data.length + 1];
			System.arraycopy(_data, 0, newdata, 0, i);
			System.arraycopy(_data, i, newdata, i+1, _data.length - i);
			_data = newdata;
		}
		else {
			System.arraycopy(_data, i, _data, i+1, _size - i);
		}
		_size ++;
		_data[i] = t;
	}

	@Override
	public void delete(T t) {
		int i = find(t);
		deleteAt(i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T deleteAt(int i) {
		if( ! checkReadableRange(i) ) {
			throw new IndexOutOfBoundsException();
		}
		_size --;
		T res = (T)_data[i];
		System.arraycopy(_data, i+1, _data, i, _size-i);
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int i) {
		if( ! checkReadableRange(i) ) {
			throw new IndexOutOfBoundsException();
		}
		return (T)_data[i];
	}

	@Override
	public void set(int i, T t) {
		if( ! checkReadableRange(i) ) {
			throw new IndexOutOfBoundsException();
		}
		_data[i] = t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int find(T t) {
		for(int i = 0; i < getSize(); i++) {
			T cur = (T)_data[i];
			if( cur.equals(t)) return i;
		}
		return -1;
	}

	// private int getMiddile(T[] data, int left, int right) {
	// 	T mid = data[left];
	// 	while(left < right) {
	// 		while(left < right && mid < data[--right]);
	// 	}
	// }
 	// private void quickSort(T[] data, int start, int end) {
			
	// }

	@Override
	public LinearList<T> sort() {
		//211Object[] data = Arrays.copyOf(_data, getSize());
		
		return null;
	}

	private boolean checkReadableRange(int i){
		if( i >= 0 && i < getSize() ) return true;
		else return false;
	}

	private boolean checkWritableRange(int i){
		if ( i >= 0 && i <= getSize() ) return true;
		else return false;
	}
}
