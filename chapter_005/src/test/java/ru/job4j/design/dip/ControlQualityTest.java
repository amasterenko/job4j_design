package ru.job4j.design.dip;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ControlQualityTest {
    @Test
    public void whenSortItemsByEachCondition() {
        Food bread1 = new Bread("White bread",
                LocalDate.of(2020, 12, 28),
                LocalDate.of(2020, 12, 20), 55, 0);
        Food bread2 = new Bread("Brown bread",
                LocalDate.of(2020, 12, 26),
                LocalDate.of(2020, 12, 18), 65, 0);
        Food juice = new Juice("Apple juice",
                LocalDate.of(2020, 12, 23),
                LocalDate.of(2020, 12, 18), 85, 0);
        Food chicken = new Chicken("Chicken",
                LocalDate.of(2020, 12, 21),
                LocalDate.of(2020, 12, 18), 200, 0);
        List<Food> foods = List.of(bread1, bread2, juice, chicken);
        Store<Food> wh = new Warehouse();
        Store<Food> shop = new Shop();
        Store<Food> trash = new Trash();
        List<Store<Food>> stores = List.of(wh, shop, trash);
        LocalDate currentDate = LocalDate.of(2020, 12, 22);
        ControlQuality control = new ControlQuality(stores);
        foods.forEach(f -> control.execute(f, currentDate));
        assertThat(wh.getItems(), is(List.of(bread1)));
        assertThat(shop.getItems(), is(List.of(bread2, juice)));
        //check discount
        assertThat(juice.getDiscount(), is(25));
        assertThat(trash.getItems(), is(List.of(chicken)));
    }

    @Test
    public void whenRedistributeItems() {
        Food bread1 = new Bread("White bread",
                LocalDate.of(2020, 12, 28),
                LocalDate.of(2020, 12, 20), 55, 0);
        Food bread2 = new Bread("Brown bread",
                LocalDate.of(2020, 12, 26),
                LocalDate.of(2020, 12, 18), 65, 0);
        Food juice = new Juice("Apple juice",
                LocalDate.of(2020, 12, 23),
                LocalDate.of(2020, 12, 18), 85, 0);
        Food chicken = new Chicken("Chicken",
                LocalDate.of(2020, 12, 21),
                LocalDate.of(2020, 12, 18), 200, 0);
        List<Food> foods = List.of(bread1, bread2, juice, chicken);
        Store<Food> wh = new Warehouse();
        Store<Food> shop = new Shop();
        Store<Food> trash = new Trash();
        List<Store<Food>> stores = List.of(wh, shop, trash);
        LocalDate currentDate = LocalDate.of(2020, 12, 22);
        ControlQuality control = new ControlQuality(stores);
        foods.forEach(f -> control.execute(f, currentDate));
        //assert sorting foods
        assertThat(wh.getItems(), is(List.of(bread1)));
        assertThat(shop.getItems(), is(List.of(bread2, juice)));
        assertThat(trash.getItems(), is(List.of(chicken)));
        //assert redistributing foods
        LocalDate currentNewDate = LocalDate.of(2021, 1, 1);
        control.redistribute(currentNewDate);
        assertThat(wh.getItems(), is(Collections.emptyList()));
        assertThat(shop.getItems(), is(Collections.emptyList()));
        assertThat(trash.getItems(), is(List.of(bread1, bread2, juice, chicken)));
    }
}