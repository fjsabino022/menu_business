package com.fsabino.menu_business.converter;

import com.fsabino.menu_business.model.Menu;
import com.fsabino.menu_business.repository.persistence.MenuData;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Component
public class MenuDataToMenuConverter implements Function<MenuData, Menu> {

    private final ItemDataToItemConverter itemDataToItemConverter;

    @Inject
    public MenuDataToMenuConverter(ItemDataToItemConverter itemDataToItemConverter) {
        this.itemDataToItemConverter = itemDataToItemConverter;
    }

    @Override
    public Menu apply(MenuData menuData) {
        return Menu.builder()
                .active(menuData.isActive())
                .id(menuData.getId())
                .description(menuData.getDescription())
                .items(menuData.getItems().stream()
                        .map(this.itemDataToItemConverter)
                        .collect(toList()))
                .submenus(menuData.getSubmenus().stream()
                        .map(this)
                        .collect(toList()))
                .build();
    }
}
