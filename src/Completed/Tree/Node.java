package Completed.Tree;

public class Node<T> implements NodeIF<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;



    public Node(){

    }
    public Node(T data){
        this.data = data;
    }
    public Node(T data, Node<T> left, Node<T> right){
        this.data =data;
        this.left = left;
        this.right = right;
    }
    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public NodeIF<T> getLeft() {
        return left;
    }

    @Override
    public NodeIF<T> getRight() {
        return right;
    }

    @Override
    public void setLeft(NodeIF<T> node) {
        this.left = (Node<T>) node;
    }

    @Override
    public void setRight(NodeIF<T> node) {
        this.right = (Node<T>) node;
    }

    @Override
    public boolean isLeaf() {
        return left == null&& right == null;
    }
    public boolean twoChilds(){
        return left!= null&&right!= null;
    }

    @Override
    public NodeIF<T> copy() {
        Node<T> newNode = new Node<>(data);
        if(left == null&& right == null)
            return newNode;
        if(right==null){
            newNode.left = (Node<T>)left.copy();
            return newNode;
        }
        if(left == null){
            newNode.right =(Node<T>) right.copy();
            return newNode;
        }
        newNode.left = (Node<T>)left.copy();
        newNode.right =(Node<T>) right.copy();
        return newNode;
    }

    @Override
    public int getHeight() {
//        if(left == null&&right == null)
//            return 1;
//        if(left == null)
//            return 1+right.getHeight();
//        if(right == null)
//            return left.getHeight()+1;
//        int leftH = left.getHeight();
//        int rigH = right.getHeight();
//        return leftH>rigH?leftH+1:rigH+1;
        return innerGetHeight(this);
    }
    private int innerGetHeight(NodeIF<T> node){
        int heihht =0;
        if(node!= null)
            heihht = 1+Math.max(innerGetHeight(node.getLeft()), innerGetHeight(node.getRight()));
        return heihht;
    }

    @Override
    public int getNodeNumber() {
//        if(left == null && right == null)
//            return 1;
//        int nu=1;
//        if(left != null)
//            nu+=left.getNodeNumber();
//        if(right!= null)
//            nu+= right.getNodeNumber();
//        return nu;
        return innerGetNodeNumber();
    }
    private int innerGetNodeNumber(){
        int leftNumber = 0;
        int rightNumber =0;
        if(left != null)
            leftNumber = left.innerGetNodeNumber();
        if(right!= null)
            rightNumber = right.innerGetNodeNumber();
        return 1 + leftNumber+ rightNumber;
    }
}
