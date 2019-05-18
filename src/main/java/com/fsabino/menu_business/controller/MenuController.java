package com.fsabino.menu_business.controller;

import com.fsabino.menu_business.model.Item;
import com.fsabino.menu_business.model.Menu;
import com.fsabino.menu_business.model.SubMenu;
import com.google.common.collect.ImmutableList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.fsabino.menu_business.controller.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(MENUS_PATH)
public class MenuController extends GenericController {

    @GetMapping(value = ALL_MENUS_PATH,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu>> getAllMenus() {
        log.info("GetAllMenus");
        List<Menu> menus = menusDammy();

        log.info("GetAllMenus {}", menus);
        return ResponseEntity.ok(menus);
    }

    @GetMapping(value = ACTIVE_MENUS_PATH,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu>> getAllActiveMenus() {

        log.info("GetAllActiveMenus");
        List<Menu> menusActives = menusDammy().stream()
                .filter(Menu::isActive)
                .collect(Collectors.toList());

        log.info("GetAllActiveMenus {}", menusActives);
        return ResponseEntity.ok(menusActives);
    }

    private List<Menu> menusDammy() {

        Menu menu1 = Menu.builder()
                .id(UUID.randomUUID())
                .active(false)
                .description("Menu 1")
                .items(ImmutableList.<Item>builder()
                        .add(Item.builder()
                                .id(UUID.randomUUID())
                                .photo("photo1")
                                .currency(Item.Currency.PESO)
                                .description("It's item 1")
                                .name("item1")
                                .price(new BigDecimal(10.0))
                                .build())
                        .add(Item.builder()
                                .id(UUID.randomUUID())
                                .photo("photo2")
                                .currency(Item.Currency.EURO)
                                .description("It's item 2")
                                .name("item2")
                                .price(new BigDecimal(20.50))
                                .build())
                        .build())
                .build();

        Menu menu2 = Menu.builder()
                .id(UUID.randomUUID())
                .active(false)
                .description("Menu2")
                .items(ImmutableList.<Item>builder()
                        .add(Item.builder()
                                .id(UUID.randomUUID())
                                .photo("photo4")
                                .currency(Item.Currency.EURO)
                                .description("It's item 4")
                                .name("item4")
                                .price(new BigDecimal(20))
                                .validEndDate(7)
                                .validStartDate(1)
                                .validEndHour(23)
                                .validEndHour(8)
                                .build())
                        .build())
                .submenus(ImmutableList.<SubMenu>builder()
                        .add(SubMenu.builder()
                                .menu(Menu.builder()
                                        .id(UUID.randomUUID())
                                        .active(true)
                                        .description("sub menu")
                                        .items(ImmutableList.<Item>builder()
                                                .add(Item.builder()
                                                        .id(UUID.randomUUID())
                                                        .photo("photo5")
                                                        .currency(Item.Currency.EURO)
                                                        .description("It's item 5")
                                                        .name("item5")
                                                        .price(new BigDecimal(25))
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();

        ////////////////////////////////////

        Menu menu3 = Menu.builder()
                .id(UUID.randomUUID())
                .active(false)
                .description("Menu4")
                .items(ImmutableList.<Item>builder()
                        .add(Item.builder()
                                .id(UUID.randomUUID())
                                .photo("photo6")
                                .currency(Item.Currency.PESO)
                                .description("It's item 6")
                                .name("item6")
                                .price(new BigDecimal(20))
                                .validEndDate(7)
                                .validStartDate(1)
                                .validEndHour(23)
                                .validEndHour(8)
                                .build())
                        .build())
                .submenus(ImmutableList.<SubMenu>builder()
                        .add(SubMenu.builder()
                                .menu(Menu.builder()
                                        .id(UUID.randomUUID())
                                        .active(true)
                                        .description("Sub menu")
                                        .items(ImmutableList.<Item>builder()
                                                .add(Item.builder()
                                                        .id(UUID.randomUUID())
                                                        .photo("photo7")
                                                        .currency(Item.Currency.PESO)
                                                        .description("It's item 7")
                                                        .name("item7")
                                                        .price(new BigDecimal(20))
                                                        .validEndDate(7)
                                                        .validStartDate(1)
                                                        .validEndHour(23)
                                                        .validEndHour(8)
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .add(SubMenu.builder()
                                .menu(Menu.builder()
                                        .description("Sub menu")
                                        .items(ImmutableList.<Item>builder()
                                                .add(Item.builder()
                                                        .id(UUID.randomUUID())
                                                        .photo("photo8")
                                                        .currency(Item.Currency.PESO)
                                                        .description("It's item 8")
                                                        .name("item 8")
                                                        .price(new BigDecimal(20))
                                                        .validEndDate(7)
                                                        .validStartDate(1)
                                                        .validEndHour(23)
                                                        .validEndHour(8)
                                                        .build())
                                                .build())
                                        .submenus(ImmutableList.<SubMenu>builder()
                                                .add(SubMenu.builder()
                                                        .menu(Menu.builder()
                                                                .id(UUID.randomUUID())
                                                                .active(true)
                                                                .description("Sub menu")
                                                                .items(ImmutableList.<Item>builder()
                                                                        .add(Item.builder()
                                                                                .id(UUID.randomUUID())
                                                                                .photo("photo")
                                                                                .currency(Item.Currency.PESO)
                                                                                .description("It's item ")
                                                                                .name("item")
                                                                                .price(new BigDecimal(20))
                                                                                .validEndDate(7)
                                                                                .validStartDate(1)
                                                                                .validEndHour(23)
                                                                                .validEndHour(8)
                                                                                .build())
                                                                        .build())
                                                                .build())
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();

        return ImmutableList.<Menu>builder()
                .add(menu1)
                .add(menu2)
                .add(menu3)
                .build();
    }
}
