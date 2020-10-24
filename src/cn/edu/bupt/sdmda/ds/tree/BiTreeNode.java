package cn.edu.bupt.sdmda.ds.tree;

public class BiTreeNode<T> {
	T _data;
	BiTreeNode<T> _leftChild, _rightChild;

	public BiTreeNode(T data){
		_data = data;
		_leftChild = null;
		_rightChild = null;
	}

	public T getData(){
		return _data;
	}

	public boolean isLeaf(){
		// check current node is leaf or not
		return _leftChild == null && _rightChild == null;
	}

	public boolean addLeft(BiTreeNode<T> node) {
		// if left is null, add it and return true
		// else do nothing, return false
		if(_leftChild == null) {
			_leftChild = node;
			return true;
		}
		else
			return false;
	}

	public boolean addRight(BiTreeNode<T> node) {
		// if right is null, add it and return true
		// else do nothing, return false
		if(_rightChild == null) {
			_rightChild = node;
			return true;
		}
		else return false;
	}

	public void setLeft(BiTreeNode<T> node) {
		// change the left child of this node
		_leftChild = node;
	}

	public void setRight(BiTreeNode<T> node) {
		// change the right child of this node
		_rightChild = node;
	}

	public BiTreeNode<T> getLeft(){
		return _leftChild;
	}

	public BiTreeNode<T> getRight(){
		return _rightChild;
	}
}
