package cn.edu.bupt.sdmda.ds.hash;

import java.util.LinkedList;

public class MyHash<K, V> implements IHashTable<K, V> {

    private LinkedList<Node>[] _tables;
    private final int INIT_CAP = 1 << 4;
    // capacity of this hash table
    private int _capacity;
    // number of stored elements in the hash table
    private int _eleNum;
    // a factor less than 1
    // if _eleNum >= factor*_capasity, we should resize
    private double _factor;

    public MyHash() {
        // init _table
    	_tables = new LinkedList[INIT_CAP];
    	for(int i = 0; i < INIT_CAP; i++) _tables[i] = new LinkedList<Node>();
        _capacity = INIT_CAP;
        _eleNum = 0;
        _factor = 0.75f;
    }

    @Override
    public void put(K key, V val) {
        // get index of key
        // construct a node of K,V
        // find a linkedList in _tables
        // find if a node with the same key is contained in the linkedList
        // if contained, replace it with new value
        // if not, insert it to the linkedlist
        // REMEMBER the element number is increased
    	// REMEMBER to check if resize is necessary
    	int idx = getIdx(key);
    	Node newNode = new Node(key,val);
    	LinkedList<Node> curList = _tables[idx];
    	int pos = curList.indexOf(newNode);
    	if(pos != -1) curList.set(pos, newNode);
    	else curList.add(newNode);
    	if(++_eleNum >= _factor*_capacity) resize();
    }

    public void resize() {
    	_capacity *= 2;
    	LinkedList<Node>[] oldTables = _tables;
    	_tables = new LinkedList[_capacity];
    	for(LinkedList<Node> curList : oldTables) {
    		for(Node i : curList)
    			put(i._key, i._val);
    	}
    }

    @Override
    public V get(K key) {
        // get the value of key
        // if not found, return null
    	int idx = getIdx(key);
    	LinkedList<Node> curList = _tables[idx];
    	for(Node i : curList)
    		if(i._key == key) return i._val; 
        return null;
    }

    @Override
    public V remove(K key) {
    	--_eleNum;
    	int idx = getIdx(key);
    	LinkedList<Node> curList = _tables[idx];
    	Node tmp = new Node(key,null);
    	for(Node i : curList)
			if(i.equals(tmp)) {
				curList.remove(i);
				return i._val;
			}
        return null;
    }

    @Override
    public int getIdx(K key) {
        // design a method to get index from key
    	int h = key.hashCode();
    	h = h ^ (h>>>16);
    	h = h & (_capacity-1);
        return h;
    }


    // Node represent a K,V pair
    class Node {
        K _key;
        V _val;

        Node(K key, V val) {
            _key = key;
            _val = val;
        }

        // override this method to make
        // node1.equals(node2) return true
        // when node1.key == node2._key
        // node1.val and node2.val is NOT CONSIDERATE HERE!
        @Override
        public boolean equals(Object obj) {
            return _key == ((Node)obj)._key;
        }
    }
}
