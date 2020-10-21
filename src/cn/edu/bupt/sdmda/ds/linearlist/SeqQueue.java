package cn.edu.bupt.sdmda.ds.linearlist;

import java.util.Queue;

public class SeqQueue<T> extends SeqList<T> implements MyQueue<T>{
	
	private int _head, _tail;

	private boolean full() {
		return super._data.length == 0 || (_tail+1) % super._data.length == _head;
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
		super.insert(_tail, t);
		_tail = (_tail + 1) % super._data.length;
		if(full()) {
			Object[] newdata = new Object[super._data.length * 2];
			int tot = 0, len = newdata.length;
			while(!empty()) {
				newdata[tot++] = super._data[_head];
				_head = (_head+1) % len;
			}
			_head = 0; _tail =tot;
			super._data = newdata;
		}
	}

	@Override
	public T poll() {
		if(empty()) throw new IndexOutOfBoundsException();
		_tail = (_tail - 1) % super._data.length;
		return (T)super._data[_tail];
	}

	@Override
	public T getHead() {
		if(empty()) throw new IndexOutOfBoundsException();
		int tmp = _head;
		_head = (_head + 1) % super._data.length;
		return (T)super._data[tmp];
	}

	@Override
	public boolean isEmpty() {
		return empty();
	}
}
