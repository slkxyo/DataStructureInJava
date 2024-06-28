package Completed.Tree;

import java.util.Iterator;

public class BSTree<T extends Comparable<? super T>> extends BinaryTree<T> implements BSTreeIF<T> {

    public NodeIF<T> getRootNode(){
        return root;
    }
    public BSTree(NodeIF<? extends T> root){
        if(root == null)
            return;
        this.root = (NodeIF<T>) root;
    }
    public BSTree(){
        this(null);
    }
    @Override
    public void setTree(T da){
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTree(T da, BinaryTreeIF<T> left, BinaryTreeIF<T> right){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contain(T ele) {
        return get(ele)  != null;
    }

    @Override
    public T get(T ele) {
        return find(root,ele);
    }
    private T find(NodeIF<T> node, T ele){
        if(node == null)
            return null;
        if(node.getData().compareTo(ele) == 0){
            return node.getData();
        }
        else if(node.getData().compareTo(ele)>0){
            return find(node.getLeft(),ele);
        }
        else {
            return find(node.getRight(),ele);
        }
    }

    @Override
    public void add(T key) {
        root = add(root ,key);
    }
    private NodeIF<T> add(NodeIF<T> node, T key){
        if(node == null)
            return new Node<>(key);

        if(key.compareTo(node.getData())<0)
            node.setLeft(add(node.getLeft(),key));
        else if(key.compareTo(node.getData())>0)
            node.setRight(add(node.getRight(),key));

        return node;
    }

    @Override
    public void remove(T key) {
        root = remove(root,key);
    }
    private NodeIF<T> remove(NodeIF<T> node, T key){
        if(node == null){
            return null;
        }

        int res = node.getData().compareTo(key);
        if(res  < 0){
            node.setRight(remove(node.getRight(),key));
        }
        else if(res > 0){
            node.setLeft(remove(node.getLeft(),key));
        }
        else {
            if(node.getLeft() == null){
                return node.getRight();
            }
            if(node.getRight() == null){
                return  node.getLeft();
            }
            else {
                NodeIF<T> nodeBig = getMin(node.getRight());
                node.setData(nodeBig.getData());
                node.setRight(remove(node.getRight(),nodeBig.getData()));
            }

        }
        return node;
    }

    private NodeIF<T> getMin(NodeIF<T> node){
        if (node == null)
            return null;
        while (node.getLeft()!= null){
            node = node.getLeft();
        }
        return  node;
    }

    @Override
    public Iterator<T> iterator() {
        return levelOrderIterator();
    }

    public Iterator<T> level(){
        return super.levelOrderIterator();
    }
}
