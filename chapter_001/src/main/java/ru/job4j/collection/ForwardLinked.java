package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> secondToLastPointer;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = secondToLastPointer != null ? secondToLastPointer.next : head;
        tail.next = node;
        secondToLastPointer = tail;
    }

    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("ForwardLinked is empty!");
        }
        Node<T> nextNode = head.next;
        head.next = null;
        head = nextNode;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException("ForwardLinked is empty!");
        }
        if (head.next == null) {
            T rsl = head.value;
            head = null;
            return rsl;
        }
        T rsl = secondToLastPointer.next.value;
        secondToLastPointer.next = null;
        return rsl;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void revert() {
        if (head == null || head.next == null) {
            return;
        }
        Node<T> prevNode = null;
        Node<T> node = head;
        while (node != null) {
            Node<T> nextNode = node.next;
            node.next = prevNode;
            prevNode = node;
            node = nextNode;
        }
        head = prevNode;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
