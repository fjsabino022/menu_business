package com.fsabino.menu_business.service;

import com.fsabino.menu_business.repository.persistence.ItemData;
import com.fsabino.menu_business.repository.persistence.MenuData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Function;

@Component
class CalculateTotalPriceMenu implements Function<MenuData, BigDecimal> {

    Logger log = LoggerFactory.getLogger(CalculateTotalPriceMenu.class);

    //TODO(fsabino): we could multiplicate each currency to transform to peso
    @Override
    public BigDecimal apply(MenuData menuData) {
        log.info("CalculateTotalPriceMenu MenuDataUUID={}", menuData);
        return menuData.flattened()
                .map(MenuData::getItems)
                .map(items -> items.stream()
                        .map(ItemData::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
