package cn.edu.bupt.sdmda.ds.tree;

import java.util.LinkedList;

public class BiTree<T> {
	BiTreeNode<T> _root;
  // a queue to store the order of traverse
	LinkedList<BiTreeNode<T>> _queue = new LinkedList<>();
	
  // Create a tree whose root is root and data is data[loc]
	private void createTree(BiTreeNode<T> root, T[] data, int loc){
    // if the left child of root is valid,
		if(loc*2 >= data.length || data[loc*2] == null) {
			root.setLeft(null);
		}
    // create a tree whose root is it
		else {
			BiTreeNode<T> leftChild = new BiTreeNode<T>(data[loc*2]);
			root.setLeft(leftChild);
			createTree(leftChild, data, loc*2);
		}
    // if the right child of root is valid,
		if(loc*2+1 >= data.length || data[loc*2+1] == null) {
			root.setRight(null);
		}
    // create a tree whose root is it
		else {
			BiTreeNode<T> rightChild = new BiTreeNode<T>(data[loc*2+1]);
			root.setRight(rightChild);
			createTree(rightChild, data, loc*2+1);
		}
	}

	public BiTreeNode<T> getRoot(){
		return _root;
	}

	public BiTree(T[] data) {
		// note that the loc starts from 1 not 0
		_root = new BiTreeNode<T>(data[1]);
		createTree(_root, data, 1);
	}

	public BiTree(BiTreeNode<T> root) {
		_root = root;
	}

	private int max(int a, int b) {
		return a > b ? a : b;
	}
	public int getDepth(BiTreeNode<T> _node) {
		// if _node is null return 0
		if(_node == null) return 0;
		// return MAX(depth of left, depth of right)+1
		else return max(getDepth(_node.getLeft()),getDepth(_node.getRight())) + 1;
	}

	public void preOrder(BiTreeNode<T> _node){
		if(_node == null) return;
		// note offer the correct _node to _queue
		_queue.add(_node);
		preOrder(_node.getLeft());
		preOrder(_node.getRight());
	}

	public void inOrder(BiTreeNode<T> _node){
		if(_node == null) return;
		// note offer the correct _node to _queue
		inOrder(_node.getLeft());
		_queue.add(_node);
		inOrder(_node.getRight());
	}

	public void postOrder(BiTreeNode<T> _node){
		if(_node == null) return;
		// note offer the correct _node to _queue
		postOrder(_node.getLeft());
		postOrder(_node.getRight());
		_queue.add(_node);
	}

	public void levelOrder() {
		// note offer the correct note to _queue
		LinkedList<BiTreeNode<T>> q = new LinkedList<>();
		q.add(_root);
		while(!q.isEmpty()) {
			BiTreeNode<T> cur = q.poll();
			if(cur == null) continue;
			_queue.add(cur);
			q.add(cur.getLeft());
			q.add(cur.getRight());
		}
	}

	public LinkedList<BiTreeNode<T>> getQueue(){
		return _queue;
	}


	public void clearQueue(){
		// make queue empty
		_queue.clear();
	}

  // search item in the tree whose root is _node in preOrder
	public BiTreeNode<T> searchPreOrder(BiTreeNode<T> _node, T item) {
	// if current node is item, return
	// search the next element in the treeld, item);
		if(_node == null) return null;
		if(_node.getData().equals(item)) return _node;
		BiTreeNode<T> lRes, rRes;
		lRes = searchPreOrder(_node.getLeft(), item);
		rRes = searchPreOrder(_node.getRight(), item);
		return lRes != null ? lRes : rRes;
	}

  // search item in the tree whose root is _node in inOrder
	public BiTreeNode<T> searchInOrder(BiTreeNode<T> _node, T item) {
    // if current node is item, return
    // search the next element in the treeld, item);
		if(_node == null) return null;
		BiTreeNode<T> lRes; lRes = searchInOrder(_node.getLeft(), item);
		if(lRes != null) return lRes;
		if(_node.getData().equals(item)) return _node;
		return searchInOrder(_node.getRight(), item);
	}

  // search item in the tree whose root is _node in postOrder
	public BiTreeNode<T> searchPostOrder(BiTreeNode<T> _node, T item) {
    // if current node is item, return
    // search the next element in the treeld, item);
		if(_node == null) return null;
		BiTreeNode<T> lRes, rRes;
		lRes = searchPostOrder(_node.getLeft(), item);
		if(lRes != null) return lRes;
		rRes = searchPostOrder(_node.getRight(), item);
		if(rRes != null) return rRes;
		return _node.getData().equals(item) ? _node : null;
	}

  // search item in this tree in levelOrder
	public BiTreeNode<T> SearchLevelOrder(T item) {
    // if current node is item, return
    // search the next element in the treeld, item);
		LinkedList<BiTreeNode<T>> q = new LinkedList<>();
		q.add(_root);
		while(!q.isEmpty()) {
			BiTreeNode<T> cur = q.poll();
			if(cur.getData().equals(item)) return cur;
			q.add(cur.getLeft());
			q.add(cur.getRight());
		}
		return null;
	}

}
