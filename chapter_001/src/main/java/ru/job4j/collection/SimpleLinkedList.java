package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class SimpleLinkedList<E> implements Iterable<E> {
    private int size = 0;
    private int modCounter = 0;
    private Node<E> first;
    private Node<E> last;

    public SimpleLinkedList() {
    }

    public int add(E value) {
        Node<E> newNode = new Node<>(value, last, null);
        if (size == 0) {
            first = newNode;
        }
        last = newNode;
        first.nextNode = last;
        modCounter++;
        return size++;
    }

    public E get(int index) {
        int checkedIndx = Objects.checkIndex(index, size);
        Node<E> curNode = first;
        for (int i = 0; i < checkedIndx; i++) {
            curNode = curNode.nextNode;
        }
        return curNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleLinkedListIterator<>(this);
    }

    private static class SimpleLinkedListIterator<E> implements Iterator<E> {
        SimpleLinkedList<E> list;
        private int expectedModCounter;
        private int pointer = 0;
        private int size;

        public SimpleLinkedListIterator(SimpleLinkedList<E> list) {
            this.list = list;
            this.expectedModCounter = list.modCounter;
            this.size = list.size;
        }

        @Override
        public boolean hasNext() {
            return pointer < size;
        }

        @Override
        public E next() {
            if (expectedModCounter != list.modCounter) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return list.get(pointer++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            throw new UnsupportedOperationException();
        }
    }

    private static class Node<E> {
        private E item;
        private Node<E> prevNode;
        private Node<E> nextNode;

        public Node() {
        }

        public Node(E element, Node<E> prev, Node<E> next) {
            this.item = element;
            this.prevNode = prev;
            this.nextNode = next;

        }
    }
}
