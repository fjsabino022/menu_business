package com.fsabino.menu_business.controller;

import com.fsabino.menu_business.model.Item;
import com.fsabino.menu_business.model.Menu;
import com.fsabino.menu_business.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import static com.fsabino.menu_business.controller.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(MENUS_PATH)
public class MenuController extends GenericController {

    private MenuService menuService;

    @Inject
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(value = ALL_MENUS_PATH,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu>> getAllMenus() {
        log.info("GetAllMenus");

        List<Menu> menus = this.menuService.getAllMenus();

        log.info("GetAllMenus {}", menus);
        return ResponseEntity.ok(menus);
    }

    @GetMapping(value = ACTIVE_MENUS_PATH,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu>> getAllActiveMenus() {

        log.info("GetAllActiveMenus");
        List<Menu> menusActives = this.menuService.getActiveMenus();

        log.info("GetAllActiveMenus {}", menusActives);
        return ResponseEntity.ok(menusActives);
    }

    private static class Calculate implements Function<Menu, BigDecimal> {

        @Override
        public BigDecimal apply(Menu menu) {



//            BigDecimal bigDecimal =menuList.stream()
//                    .map(Menu::getItems)
//                    .map(itemsList -> itemsList.stream()
//                            .filter(item -> item.getPrice() != null)
//                            .map(Item::getPrice)
//                            .reduce(BigDecimal.ZERO, BigDecimal::add))
//                    .reduce(BigDecimal.ZERO, BigDecimal::add);


            return null;
        }
    }
}
