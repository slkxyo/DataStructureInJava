package Completed.Tree;

import Completed.Queue.ArrayQueue;
import Completed.Stack.LinkedStack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements BinaryTreeIF<T> {
    protected NodeIF<T> root;




    public BinaryTree(){
    }
    public BinaryTree(NodeIF<? extends T> root){
        this.root = (NodeIF<T>) root;
    }
    public BinaryTree(T rootData){
        root = new Node<>(rootData);
    }
    public BinaryTree(T rootData, BinaryTree<T> left, BinaryTree<T> right){

    }
    @Override
    public void setTree(T rootData) {
        root = new Node<>(rootData);
    }

    @Override
    public void setTree(T rootData, BinaryTreeIF<T> left, BinaryTreeIF<T> right) throws Exception {
        priSetTree(rootData,(BinaryTree<T>) left,(BinaryTree<T>) right);
    }
    private void priSetTree(T rootData,BinaryTree<T> left,BinaryTree<T> right)throws Exception{
        if(left==null||right==null){
            throw new Exception("setTree() cant't use null value!");
        }
        NodeIF<T> newNode = new Node<>(rootData);
        if(left.root == null && right.root == null){
            root = newNode;
            return;
        }
        if(left.root == null){
            newNode.setRight(right.root.copy());
            root = newNode;
            return;
        }
        if(right.root == null){
            newNode.setLeft(left.root.copy());
            root = newNode;
            return;
        }
        newNode.setRight(right.root.copy());
        newNode.setLeft(left.root.copy());
        root = newNode;
    }
    @Override
    public T getRoot() {
        return root.getData();
    }

    @Override
    public int getHeight() {
        return root.getHeight();
    }

    @Override
    public int getNodeNumber() {
        return root == null?0:root.getNodeNumber();
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public void clear() {
        root = null;
    }


    @Override
    public Iterator<T> preOrederIterator() {
        return new PreOreder();
    }
    private class PreOreder implements Iterator<T>{
        private LinkedStack<NodeIF<T>> st = new LinkedStack<NodeIF<T>>();

        public PreOreder(){
            if(root == null)
                return;
            st.push(root);
        }
        @Override
        public boolean hasNext() {
            return !st.isEmpty();
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException();
            NodeIF<T> node = st.pop();
            T res = node.getData();
            if(node.getRight()!= null  )
                st.push(node.getRight());
            if(node.getLeft()!= null  )
                st.push(node.getLeft());
            return res;
        }
    }

    @Override
    public Iterator<T> inOrderIterator() {
        return new InOrder();
    }
    private class InOrder implements Iterator<T>{
        LinkedStack<NodeIF<T>> st = new LinkedStack<>();
        NodeIF<T> curr;

        public InOrder(){
            if (root == null)
                return;
            curr = root;
        }
        @Override
        public boolean hasNext() {
            return curr != null||!st.isEmpty();
        }

        @Override
        public T next() {
            while (curr!=null){
                st.push(curr);
                curr = curr.getLeft();
            }

            NodeIF<T> node = st.pop();
            T res = node.getData();
            curr = node.getRight();
            return res;
        }
    }

    @Override
    public Iterator<T> postOrderIterator() {
        return new PostOrder();
    }
    private class PostOrder implements  Iterator<T>{
        private LinkedStack<NodeIF<T>> st1 = new LinkedStack<NodeIF<T>>();
        private LinkedStack<NodeIF<T>> st2 = new LinkedStack<NodeIF<T>>();

        public PostOrder(){
            if (root==null)
                return;
            st1.push(root);
            while (!st1.isEmpty()){
                NodeIF<T> node = st1.pop();
                st2.push(node);

                if(node.getLeft() != null)
                    st1.push(node.getLeft());
                if(node.getRight() != null)
                    st1.push(node.getRight());
            }
        }
        @Override
        public boolean hasNext() {
            return !st2.isEmpty();
        }

        @Override
        public T next() {
            return st2.pop().getData();
        }
    }

    @Override
    public Iterator<T> levelOrderIterator() {
        return new LevelOrder();
    }
    private class LevelOrder implements Iterator<T> {
        ArrayQueue<NodeIF<T>> q1 =new ArrayQueue<NodeIF<T>>();
        ArrayQueue<NodeIF<T>> q2 =new ArrayQueue<NodeIF<T>>();

        public LevelOrder(){
            if (root == null)
                return;
            q1.enqueue(root);
            while (!q1.isEmpty()){
                int size = q1.size();
                for(int i=0;i<size;i++){
                    NodeIF<T> node = q1.dequeue();
                    q2.enqueue(node);
                    if(node.getLeft()!= null)
                        q1.enqueue(node.getLeft());
                    if(node.getRight()!= null)
                        q1.enqueue(node.getRight());
                }
            }
        }
        @Override
        public boolean hasNext() {
            return !q2.isEmpty();
        }

        @Override
        public T next() {
            return q2.dequeue().getData();
        }
    }
    public BinaryTree<T> copyTree(){
        return new BinaryTree<T>(root.copy());
    }


}//class end
