package ru.job4j.design.tictactoe;

import java.util.Optional;

public interface Rules<T, S> {
    Optional<Player<T, S>> nextTurnPlayer();

    boolean isNextTurnPossible();

    Optional<Player<T, S>> getWinner();

}
