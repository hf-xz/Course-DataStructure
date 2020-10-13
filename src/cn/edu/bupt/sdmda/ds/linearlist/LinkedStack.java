package cn.edu.bupt.sdmda.ds.linearlist;

public class LinkedStack<T> extends MyLinkedList<T>  implements MyStack<T>{
	
	public LinkedStack() {
		super();
	}

	@Override
	public T pop() {
		if(super._size == 0) throw new IndexOutOfBoundsException();
		return super.deleteAt(0);
	}

	@Override
	public void push(T t) {
		super.insert(0, t);
	}

	@Override
	public T getTop(){
		if(super._size == 0) throw new IndexOutOfBoundsException();
		return super.get(0);
	}

	@Override
	public void insert(int i, T t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(T t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T deleteAt(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T get(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void set(int i, T t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int find(T t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public LinearList<T> sort() {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		String res = super.toString();
		res = "LinkedStack: top[" + res.substring(1,res.length()-1) + "]";
		return res;
	}

}
