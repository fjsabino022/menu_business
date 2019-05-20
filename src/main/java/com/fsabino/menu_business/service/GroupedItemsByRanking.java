package com.fsabino.menu_business.service;

import com.fsabino.menu_business.converter.ItemDataToItemConverter;
import com.fsabino.menu_business.model.Item;
import com.fsabino.menu_business.repository.persistence.MenuData;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
class GroupedItemsByRanking extends GroupedItems<Integer> {

    @Inject
    GroupedItemsByRanking(ItemDataToItemConverter itemDataToItemConverter) {
        super(itemDataToItemConverter);
    }

    @Override
    public Map<Integer, List<Item>> apply(MenuData menuData, Function<Item, Integer> function) {
        log.info("GroupedItemsByRanking menuData={}", menuData);
        return super.apply(menuData, function);
    }
}
