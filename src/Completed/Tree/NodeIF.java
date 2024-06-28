package Completed.Tree;

public interface NodeIF<T> {
    T getData();
    void setData(T data);

    NodeIF<T> getLeft();
    NodeIF<T> getRight();
    void setLeft(NodeIF<T> node);
    void setRight(NodeIF<T> node);

    boolean isLeaf();
    boolean twoChilds();
    NodeIF<T> copy();
    int getHeight();
    int getNodeNumber();
}
