package com.fsabino.menu_business.repository;

import com.fsabino.menu_business.repository.persistence.ItemData;
import com.fsabino.menu_business.repository.persistence.MenuData;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MenuRepositoryMock implements MenuRepository {

    @Override
    public List<MenuData> getAllMenuData() {
        return createDummyMenusData();
    }

    @Override
    public Optional<MenuData> getMenuDataByMenuUUID(UUID uuid) {
        return createDummyMenusData().stream()
                .filter(menuData -> menuData.getId().equals(uuid))
                .findFirst();
    }

    private List<MenuData> createDummyMenusData() {

        final UUID UUID_MENU_1 = UUID.fromString("2c5e46db-ba06-42c6-85cd-c94430e0177b");
        final UUID UUID_MENU_2 = UUID.fromString("d671f843-d74c-4176-bf68-2a0782587f75");
        final UUID UUID_MENU_3 = UUID.fromString("2c354859-8907-4d15-9faf-37491be654c2");

        MenuData menu1 = MenuData.builder()
                .id(UUID_MENU_1)
                .active(true)
                .description("Menu 1")
                .items(ImmutableList.<ItemData>builder()
                        .add(ItemData.builder()
                                .id(UUID.randomUUID())
                                .photo("photo1")
                                .currency("$")
                                .description("It's item 1")
                                .name("item1")
                                .price(new BigDecimal(10.0))
                                .ranking(1)
                                .build())
                        .add(ItemData.builder()
                                .id(UUID.randomUUID())
                                .photo("photo")
                                .currency("$")
                                .description("It's item 40")
                                .name("item40")
                                .price(new BigDecimal(10.0))
                                .ranking(1)
                                .build())
                        .add(ItemData.builder()
                                .id(UUID.randomUUID())
                                .photo("photo2")
                                .currency("€")
                                .description("It's item 2")
                                .name("item2")
                                .price(new BigDecimal(20.50))
                                .ranking(3)
                                .build())
                        .build())
                .build();

        MenuData menu2 = MenuData.builder()
                .id(UUID_MENU_2)
                .active(false)
                .description("Menu2")
                .items(ImmutableList.<ItemData>builder()
                        .add(ItemData.builder()
                                .id(UUID.randomUUID())
                                .photo("photo4")
                                .currency("€")
                                .description("It's item 4")
                                .name("item4")
                                .price(new BigDecimal(20))
                                .validEndDate(7)
                                .validStartDate(1)
                                .validEndHour(23)
                                .validEndHour(8)
                                .ranking(2)
                                .build())
                        .build())
                .submenus(ImmutableList.<MenuData>builder()
                        .add(MenuData.builder()
                                .id(UUID.randomUUID())
                                .active(true)
                                .description("sub menu")
                                .items(ImmutableList.<ItemData>builder()
                                        .add(ItemData.builder()
                                                .id(UUID.randomUUID())
                                                .photo("photo5")
                                                .currency("€")
                                                .description("It's item 5")
                                                .name("item5")
                                                .price(new BigDecimal(25))
                                                .ranking(2)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();

        MenuData menu3 = MenuData.builder()
                .id(UUID_MENU_3)
                .active(true)
                .description("Menu4")
                .items(ImmutableList.<ItemData>builder()
                        .add(ItemData.builder()
                                .id(UUID.randomUUID())
                                .photo("photo6")
                                .currency("€")
                                .description("It's item 6")
                                .name("item6")
                                .price(new BigDecimal(20))
                                .validEndDate(7)
                                .validStartDate(1)
                                .validEndHour(23)
                                .validEndHour(8)
                                .ranking(3)
                                .build())
                        .build())
                .submenus(ImmutableList.<MenuData>builder()
                        .add(MenuData.builder()
                                .id(UUID.randomUUID())
                                .active(true)
                                .description("Sub menu")
                                .items(ImmutableList.<ItemData>builder()
                                        .add(ItemData.builder()
                                                .id(UUID.randomUUID())
                                                .photo("photo7")
                                                .currency("€")
                                                .description("It's item 7")
                                                .name("item7")
                                                .price(new BigDecimal(21))
                                                .validEndDate(7)
                                                .validStartDate(1)
                                                .validEndHour(23)
                                                .validEndHour(8)
                                                .ranking(4)
                                                .build())
                                        .build())
                                .build()
                        )
                        .add(MenuData.builder()
                                .description("Sub menu")
                                .items(ImmutableList.<ItemData>builder()
                                        .add(ItemData.builder()
                                                .id(UUID.randomUUID())
                                                .photo("photo8")
                                                .currency("€")
                                                .description("It's item 8")
                                                .name("item 8")
                                                .price(new BigDecimal(22))
                                                .validEndDate(7)
                                                .validStartDate(1)
                                                .validEndHour(23)
                                                .validEndHour(8)
                                                .ranking(4)
                                                .build())
                                        .build())
                                .submenus(ImmutableList.<MenuData>builder()
                                        .add(MenuData.builder()
                                                .id(UUID.randomUUID())
                                                .active(true)
                                                .description("Sub menu")
                                                .items(ImmutableList.<ItemData>builder()
                                                        .add(ItemData.builder()
                                                                .id(UUID.randomUUID())
                                                                .photo("photo")
                                                                .currency("€")
                                                                .description("It's item ")
                                                                .name("item")
                                                                .price(new BigDecimal(23))
                                                                .validEndDate(7)
                                                                .validStartDate(1)
                                                                .validEndHour(23)
                                                                .validEndHour(8)
                                                                .ranking(5)
                                                                .build())
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();

        return ImmutableList.<MenuData>builder()
                .add(menu1)
                .add(menu2)
                .add(menu3)
                .build();
    }
}
