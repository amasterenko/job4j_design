package ru.job4j.design.tictactoe;

import java.util.Optional;

public interface Player<T, S> {
    Optional<Cell<S>> play(T in, S out);
}
