package Completed.Tree;

import java.util.Iterator;

public class AvlTree<T extends Comparable<? super T>> extends BSTree<T>{

    public AvlTree(){

    }
    public AvlTree(T node){
        root = new Node<>(node);
    }

    @Override
    public void add(T ele) {
        root = add(root,ele);
    }
    private NodeIF<T> add(NodeIF<T> node,T key){
        if(node == null)
            return new Node<>(key);

        if(key.compareTo(node.getData()) < 0)
            node.setLeft(add(node.getLeft(),key));
        else if(key.compareTo(node.getData())>0)
            node.setRight(add(node.getRight(),key));
        else
            return node;

        int balance = getBalance(node);
        if(balance>1 && key.compareTo(node.getLeft().getData()) < 0)
            return rightRotate(node);
        if(balance<-1 && key.compareTo(node.getRight().getData())>0)
            return leftRotate(node);
        if(balance>1 && key.compareTo(node.getLeft().getData())>0){
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if(balance<-1 && key.compareTo(node.getRight().getData())<0){
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }
    public void remove(T key){
        root = remove(root,key);
    }
    private NodeIF<T> remove(NodeIF<T> node , T key){
        if(node == null)
            return null;

        if(key.compareTo(node.getData())>0)
            node.setRight(remove(node.getRight(),key));
        else if(key.compareTo(node.getData())<0)
            node.setLeft(remove(node.getLeft(),key));
        else {
            if(node.getLeft()==null || node.getRight()==null){
                NodeIF<T> temp = null;
                if(node.getLeft() == temp)
                    temp = node.getRight();
                else
                    temp = node.getLeft();

                if(temp == null){
                    return null;
                }
                else
                    node =temp;
            }
            else {
                T min = getMin(node.getRight()).getData();
                node.setData(min);
                node.setRight(remove(node.getRight(),min));
            }
        }

        int banlance = getBalance(node);
        if (banlance>1 && getBalance(node.getLeft())>=0)
            return rightRotate(node);
        if (banlance>1 && getBalance(node.getLeft())<0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if(banlance<-1 && getBalance(node.getRight())>0){
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }
        if(banlance<-1 && getBalance(node.getRight())<=0)
            return leftRotate(node);

        return node;
    }
    private NodeIF<T> getMin(NodeIF<T> node){
        while (node.getLeft() != null){
            node = node.getLeft();
        }
        return node;
    }
    private int getBalance(NodeIF<T> node){
        return node == null?0:getHeight(node.getLeft()) - getHeight(node.getRight());
    }
    private int getHeight(NodeIF<T> node){
        return node == null? 0:node.getHeight();
    }
    private NodeIF<T> leftRotate(NodeIF<T> y){
        NodeIF<T> x = y.getRight();
        y.setRight(x.getLeft());
        x.setLeft(y);
        return x;
    }
    private NodeIF<T> rightRotate(NodeIF<T> y){
        NodeIF<T> x = y.getLeft();
        y.setLeft(x.getRight());
        x.setRight(y);
        return x;
    }

}
