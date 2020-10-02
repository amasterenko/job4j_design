package ru.job4j.collection;

import java.util.*;
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
        if (size == 0) {
            return Collections.EMPTY_LIST.iterator();
        }
        return new SimpleLinkedListIterator<>(this);
    }

    private static class SimpleLinkedListIterator<E> implements Iterator<E> {
        SimpleLinkedList<E> list;
        private int expectedModCounter;
        private Node<E> pointer;

        public SimpleLinkedListIterator(SimpleLinkedList<E> list) {
            this.list = list;
            this.expectedModCounter = list.modCounter;
            this.pointer = list.first;
        }

        @Override
        public boolean hasNext() {
            return pointer != null;
        }

        @Override
        public E next() {
            if (expectedModCounter != list.modCounter) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E rsl = pointer.item;
            pointer = pointer.nextNode;
            return rsl;
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
