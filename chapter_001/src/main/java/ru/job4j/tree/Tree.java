package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> foundParent = findBy(parent);
        if (foundParent.isPresent() && findBy(child).isEmpty()) {
            foundParent.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    private Optional<Node<E>> findByCondition(Predicate<Node<E>> predicate) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByCondition(e -> Objects.equals(e.value, value));
    }

    public boolean isBinary() {
        return findByCondition(e -> e.children.size() > 2).isEmpty();
    }
}
