package com.fsabino.menu_business.converter;

import com.fsabino.menu_business.model.Item;
import com.fsabino.menu_business.repository.persistence.ItemData;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Function;

@Component
class ItemDataToItemConverter implements Function<ItemData, Item> {

    @Override
    public Item apply(ItemData itemData) {
        Item.ItemBuilder itemBuilder = Item.builder()
                .id(itemData.getId())
                .name(itemData.getName())
                .photo(itemData.getPhoto())
                .price(itemData.getPrice())
                .description(itemData.getDescription())
                .validEndDate(itemData.getValidEndDate())
                .validEndHour(itemData.getValidEndHour())
                .validStartDate(itemData.getValidStartHour())
                .validStartHour(itemData.getValidStartHour())
                .ranking(itemData.getRanking());

        String currency = itemData.getCurrency();
        if (!StringUtils.isEmpty(currency)) {
            itemBuilder.currency(Item.Currency.findCurrency(currency));
        }

        return itemBuilder.build();
    }
}
