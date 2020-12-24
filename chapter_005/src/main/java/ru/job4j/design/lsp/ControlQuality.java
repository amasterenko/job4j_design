package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class ControlQuality {
    private List<Store<Food>> stores;

    public ControlQuality(List<Store<Food>> stores) {
        this.stores = stores;
    }

    public void execute(Food item, LocalDate currentDate) {
        long lifePeriod = Period.between(item.getCreateDate(), item.getExpiredDate()).getDays();
        float  expirationRate = (float) Period.between(item.getCreateDate(), currentDate).getDays() / lifePeriod;
        for (Store<Food> store : stores) {
            if (store.putItem(item, expirationRate)) {
                break;
            }
        }
    }

}
