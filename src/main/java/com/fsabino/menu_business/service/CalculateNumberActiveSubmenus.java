package com.fsabino.menu_business.service;

import com.fsabino.menu_business.repository.persistence.MenuData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
class CalculateNumberActiveSubmenus implements Function<MenuData, Long> {

    Logger log = LoggerFactory.getLogger(CalculateNumberActiveSubmenus.class);

    @Override
    public Long apply(MenuData menuData) {
        log.info("CalculateNumberActiveSubmenus MenuDataUUID={}", menuData);
        return menuData.flattened()
                .map(MenuData::getSubmenus)
                .filter(submenus -> submenus.size() > 0)
                .map(submenus -> submenus.stream()
                        .filter(MenuData::isActive)
                        .count())
                .count();
    }
}
