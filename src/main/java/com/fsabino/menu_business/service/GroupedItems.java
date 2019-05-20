package com.fsabino.menu_business.service;

import com.fsabino.menu_business.converter.ItemDataToItemConverter;
import com.fsabino.menu_business.model.Item;
import com.fsabino.menu_business.repository.persistence.MenuData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Component
abstract class GroupedItems<T extends Number> implements
        BiFunction<MenuData, Function<Item, T>, Map<T, List<Item>>> {

    Logger log = LoggerFactory.getLogger(GroupedItems.class);

    private final ItemDataToItemConverter itemDataToItemConverter;

    @Inject
    GroupedItems(ItemDataToItemConverter itemDataToItemConverter) {
        this.itemDataToItemConverter = itemDataToItemConverter;
    }

    public Map<T, List<Item>> apply(MenuData menuData, Function<Item, T> function) {
        List<List<Item>> items = menuData.flattened()
                .map(MenuData::getItems)
                .map(itemsList -> itemsList.stream()
                        .map(this.itemDataToItemConverter)
                        .collect(toList()))
                .collect(toList());

        log.info("GroupedItems {}", items);
        return this.flattenListOfListsStream(items).stream()
                .collect(groupingBy(function));
    }

    private List<Item> flattenListOfListsStream(List<List<Item>> list) {
        return list.stream()
                .flatMap(Collection::stream)
                .collect(toList());
    }
}
