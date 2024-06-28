package Completed.Tree;

interface BinaryTreeIF<T> extends TreeIF<T>, TreeIteratorIF<T> {
    void setTree(T rootData);
    void setTree(T rootDat, BinaryTreeIF<T> left, BinaryTreeIF<T> right) throws Exception;
}
