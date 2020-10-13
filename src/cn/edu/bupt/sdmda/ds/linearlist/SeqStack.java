package cn.edu.bupt.sdmda.ds.linearlist;

public class SeqStack<T> extends SeqList<T> implements MyStack<T>{

	public SeqStack(){
		super();
	}

	@Override
	public T pop() {
		if(super.getSize() == 0) throw new IndexOutOfBoundsException();
		return super.deleteAt(super.getSize() - 1);
	}

	@Override
	public void push(T t) {
		super.insert(super.getSize(), t);
	}

	@Override
	public T getTop(){
		if(super.getSize() == 0) throw new IndexOutOfBoundsException();
		return super.get(super.getSize() - 1);
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
		String res = "stack: bottom[";
		for(int i = 0 ; i < super.getSize() ; i ++) {
			res += super._data[i].toString() + ", ";
		}
		if(res.length() > 14)
			res = res.substring(0, res.length() - 2);
		res += "]";
		return res;
	}
	
}
