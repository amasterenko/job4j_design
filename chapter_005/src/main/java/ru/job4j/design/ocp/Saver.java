package ru.job4j.design.ocp;
/**
 * Используется ссылка на конкретный класс Storage.
 * При необходимости использовать другой тип хранилища,
 * потребуются изменения в классе Saver
 */

public class Saver<T> {
    private Storage<T> storage;

    public Saver(Storage<T> storage) {
        this.storage = storage;
    }

    public boolean save() {
        return false;
    }
}
