package ru.job4j.design.lsp;

import java.util.List;

public interface Store<T> {
    boolean putItem(T item, float expirationRate);

    List<T> getItems();
}
