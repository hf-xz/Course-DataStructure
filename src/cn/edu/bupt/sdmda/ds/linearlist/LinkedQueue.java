package cn.edu.bupt.sdmda.ds.linearlist;

public class LinkedQueue<T> extends MyLinkedList<T> implements MyQueue<T> {
	public LinkedQueue() {
		super();
	}

	@Override
	public void offer(T t) {
		super.insert(super.getSize(), t);
	}

	@Override
	public T poll() {
		return super.deleteAt(super.getSize()-1);
	}

	@Override
	public T getHead() {
		return super.deleteAt(0);
	}

}
