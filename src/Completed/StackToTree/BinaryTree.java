package Completed.StackToTree;

import Completed.Queue.LinkedQueue;
import Completed.Stack.LinkedStack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree <T>{
    public TreeNode<T> root;

    public BinaryTree(TreeNode<T> root){
        this.root = root;
    }
    public void preOrder(){
        preOrderRe(root);
    }
    private void preOrderRe(TreeNode<T> node){
        if(node == null)
            return;
        System.out.print(node.data+"  ");
        preOrderRe(node.left);
        preOrderRe(node.right);
    }
    private void preOrderSt(){
        if(root == null)
            return;
        LinkedStack<TreeNode<T>> st = new LinkedStack<>();
        st.push(root);
        while (!st.isEmpty()){
            TreeNode<T> node = st.pop();
            System.out.print(node.data+"  ");
            if(node.right!= null){
                st.push(node.right);
            }
            if(node.left!= null){
                st.push(node.left);
            }
        }
    }
    public Iterator<T> preIterator(){
        return new PreIterator();
    }
    private class PreIterator implements Iterator<T>{
        private LinkedStack<TreeNode<T>> st = new LinkedStack<TreeNode<T>>();;

        public PreIterator(){
            if(root != null)
                st.push(root);
        }

        @Override
        public boolean hasNext() {
            return !st.isEmpty();
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException("No next");
            }
            TreeNode<T> node = st.pop();
            if(node.right!= null){
                st.push(node.right);
            }
            if(node.left!= null){
                st.push(node.left);
            }
            return node.data;
        }
    }



    public void midOrder(){
        minOrderRe(root);
    }
    private void minOrderRe(TreeNode<T> node){
        if(node == null)
            return;
        minOrderRe(node.left);
        System.out.print(node.data+"  ");
        minOrderRe(node.right);

    }
    private void midOrderSt(){
        if(root == null)
            return;
        Stack<TreeNode<T>> st = new Stack<>();
        TreeNode<T> curr = root;
        while (curr!=null || !st.isEmpty()){
            while (curr!=null){
                st.push(curr);
                curr = curr.left;
            }

            curr = st.pop();
            System.out.print(curr.data+ "  ");
            curr = curr.right;
        }
    }
    public Iterator<T> midIterator(){
        return new MidIterator();
    }
    private class MidIterator implements Iterator<T>{
        private LinkedStack<TreeNode<T>> st = new LinkedStack<TreeNode<T>>();
        private TreeNode<T> curr;

        public MidIterator(){
            if(root != null){
                curr = root;
            }
        }
        @Override
        public boolean hasNext() {
            return curr!=null || !st.isEmpty();
        }

        @Override
        public T next() {
             if(!hasNext()){
                 throw new NoSuchElementException();
             }
            while (curr!=null){
                st.push(curr);
                curr = curr.left;
            }

            curr = st.pop();
            T res = curr.data;
            curr = curr.right;
            return res;
        }
    }


    public void postOrder(){
        postOrderRe(root);
    }
    private void postOrderRe(TreeNode<T> node){
        if (node == null)
            return;
        postOrderRe(node.left);
        postOrderRe(node.right);
        System.out.print(node.data+"  ");

    }
    private void postOrderSt(){
        if(root == null)
            return;
        LinkedStack<TreeNode<T>> st1 = new LinkedStack<TreeNode<T>>();
        LinkedStack<TreeNode<T>> st2 = new LinkedStack<TreeNode<T>>();
        st1.push(root);
        while (!st1.isEmpty()){
            TreeNode<T> node = st1.pop();
            st2.push(node);

            if(node.left!= null)
                st1.push(node.left);
            if(node.right!= null)
                st1.push(node.right);
        }
        while (!st2.isEmpty()){
            System.out.print(st2.pop().data+"  ");
        }
    }
    public Iterator<T> postIterator(){
        return new PostIterator();
    }
    private class PostIterator implements Iterator<T>{
        LinkedStack<TreeNode<T>> st1 = new LinkedStack<TreeNode<T>>();
        LinkedStack<TreeNode<T>> st2 = new LinkedStack<TreeNode<T>>();

        public PostIterator(){
            if(root == null)
                return;
            st1.push(root);
            while (!st1.isEmpty()){
                TreeNode<T> node = st1.pop();
                st2.push(node);

                if(node.left!= null)
                    st1.push(node.left);
                if(node.right!= null)
                    st1.push(node.right);
            }
        }
        @Override
        public boolean hasNext() {
            return !st2.isEmpty();
        }

        @Override
        public T next() {
            return st2.pop().data;
        }
    }


    public void levelOrder(){
        if(root == null)
            return;
        LinkedQueue<TreeNode<T>> queue = new LinkedQueue<TreeNode<T>>();
        queue.enqueue(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode<T> node = queue.dequeue();
                System.out.print(node.data+"  ");
                if(node.left!=null)
                    queue.enqueue(node.left);
                if(node.right!=null)
                    queue.enqueue(node.right);
            }
        }
    }
    public Iterator<T> levelIterator(){
        return new LevelIterator();
    }
    private class LevelIterator implements Iterator<T>{
        LinkedQueue<TreeNode<T>> queue = new LinkedQueue<TreeNode<T>>();
        LinkedQueue<TreeNode<T>> res = new LinkedQueue<TreeNode<T>>();


        public LevelIterator(){
            if(root == null)
                return;
            queue.enqueue(root);

            while (!queue.isEmpty()){
                int size = queue.size();
                for(int i=0;i<size;i++){
                    TreeNode<T> node = queue.dequeue();
                    res.enqueue(node);
                    if(node.left!=null)
                        queue.enqueue(node.left);
                    if(node.right!=null)
                        queue.enqueue(node.right);
                }
            }
        }
        @Override
        public boolean hasNext() {
            return !res.isEmpty();
        }

        @Override
        public T next() {
            return res.dequeue().data;
        }
    }


}//class over here
