package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
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
        Node<T> tail = head;
        Node<T> prevTail = head;
        while (tail.next != null) {
            prevTail = tail;
            tail = tail.next;
        }
        T rsl = tail.value;
        prevTail.next = null;
        return rsl;
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
