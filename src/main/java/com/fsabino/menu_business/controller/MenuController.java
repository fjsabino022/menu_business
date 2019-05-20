package com.fsabino.menu_business.controller;

import com.fsabino.menu_business.exception.MenuNotFoundException;
import com.fsabino.menu_business.model.Item;
import com.fsabino.menu_business.model.Menu;
import com.fsabino.menu_business.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @GetMapping(value = GROUPED_PRICE_MENUS_PATH,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<BigDecimal, List<Item>>> getItemsGroupedByPrice(@PathVariable UUID menuUUID) {
        log.info("GetItemsGroupedByPrice");
        try {
            return ResponseEntity.ok(this.menuService.getItemsGrupedPriceByMenuUUID(menuUUID));
        } catch (MenuNotFoundException e) {
            log.error("Menu NotFound GetItemsGroupedByPrice", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = GROUPED_RANKING_MENUS_PATH,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Integer, List<Item>>> getItemsGroupedByRanking(@PathVariable UUID menuUUID) {
        log.info("GetItemsGroupedByRanking");
        try {
            return ResponseEntity.ok(this.menuService.getItemsGrupedRankingByMenuUUID(menuUUID));
        } catch (MenuNotFoundException e) {
            log.error("Menu NotFound GetItemsGroupedByRanking", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
