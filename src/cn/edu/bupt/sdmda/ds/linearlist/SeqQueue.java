package cn.edu.bupt.sdmda.ds.linearlist;

import java.util.Queue;

public class SeqQueue<T> extends SeqList<T> implements MyQueue<T>{
	
	private int _head, _tail;

	private boolean full() {
		return (_tail+1) % super._data.length == _head;
	}
	
	private boolean empty() {
		return _tail == _head;
	}
	
	public SeqQueue() {
		super();
		_head = 0; _tail = 0;
	}

	@Override
	public void offer(T t) {
		if(full()) {
			Object[] newdata = new Object[super._data.length * 2];
			//TODO copy old data to new array
		}
		else super.insert(_tail++, t);
	}

	@Override
	public T poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getHead() {
		// TODO Auto-generated method stub
    return null;
	}

}
