package Completed.Tree;

import java.util.Iterator;

interface TreeIteratorIF<T> {
    Iterator<T> preOrederIterator();
    Iterator<T> inOrderIterator();
    Iterator<T> postOrderIterator();
    Iterator<T> levelOrderIterator();
}
