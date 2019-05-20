package com.fsabino.menu_business.service;

import com.fsabino.menu_business.converter.ItemDataToItemConverter;
import com.fsabino.menu_business.model.Item;
import com.fsabino.menu_business.repository.persistence.MenuData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
class GroupedItemsByPrice extends GroupedItems<BigDecimal> {

    @Inject
    GroupedItemsByPrice(ItemDataToItemConverter itemDataToItemConverter) {
        super(itemDataToItemConverter);
    }

    @Override
    public Map<BigDecimal, List<Item>> apply(MenuData menuData, Function<Item, BigDecimal> function) {
        log.info("GroupedItemsByPrice menuData={}", menuData);
        return super.apply(menuData, function);
    }
}
