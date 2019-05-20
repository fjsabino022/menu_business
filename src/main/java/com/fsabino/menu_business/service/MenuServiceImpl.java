package com.fsabino.menu_business.service;

import com.fsabino.menu_business.converter.MenuDataToMenuConverter;
import com.fsabino.menu_business.exception.MenuNotFoundException;
import com.fsabino.menu_business.model.Item;
import com.fsabino.menu_business.model.Menu;
import com.fsabino.menu_business.repository.MenuRepository;
import com.fsabino.menu_business.repository.persistence.MenuData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class MenuServiceImpl implements MenuService {

    Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    private final MenuRepository menuRepository;
    private final MenuDataToMenuConverter menuDataToMenuConverter;
    private final CalculateTotalPriceMenu calculateTotalPriceMenu;
    private final GroupedItems<BigDecimal> groupedItemsByPrice;
    private final GroupedItems<Integer> groupedItemsByRanking;
    private final CalculateNumberActiveSubmenus calculateNumberActiveSubmenus;

    @Inject
    public MenuServiceImpl(MenuRepository menuRepository,
                           MenuDataToMenuConverter menuDataToMenuConverter,
                           CalculateTotalPriceMenu calculateTotalPriceMenu,
                           GroupedItemsByPrice groupedItemsByPrice,
                           GroupedItemsByRanking groupedItemsByRanking,
                           CalculateNumberActiveSubmenus calculateNumberActiveSubmenus) {
        this.menuRepository = menuRepository;
        this.menuDataToMenuConverter = menuDataToMenuConverter;
        this.calculateTotalPriceMenu = calculateTotalPriceMenu;
        this.groupedItemsByPrice = groupedItemsByPrice;
        this.groupedItemsByRanking = groupedItemsByRanking;
        this.calculateNumberActiveSubmenus = calculateNumberActiveSubmenus;
    }

    @Override
    public List<Menu> getAllMenus() {
        log.info("GetAllMenus");
        return menuRepository.getAllMenuData().stream()
                .map(this.menuDataToMenuConverter)
                .collect(toList());
    }

    @Override
    public List<Menu> getActiveMenus() {
        log.info("GetActiveMenus");
        return menuRepository.getAllMenuData().stream()
                .filter(MenuData::isActive)
                .map(menuDataToMenuConverter)
                .collect(toList());
    }

    @Override
    public BigDecimal getPriceByMenuUUID(UUID menuUUID) throws MenuNotFoundException {
        log.info("GetPriceByMenuUUID menuUUID={}", menuUUID);
        return this.menuRepository.getMenuDataByMenuUUID(menuUUID)
                .map(this.calculateTotalPriceMenu)
                .orElseThrow(() -> new MenuNotFoundException(String.format("Not Found Menu menuId=%s", menuUUID)));
    }

    @Override
    public int getNumberActiveSubmenusByMenuUUID(UUID menuUUID) throws MenuNotFoundException {
        log.info("GetNumberActiveSubmenusByMenuUUID menuUUID={}", menuUUID);
        return this.menuRepository.getMenuDataByMenuUUID(menuUUID)
                .map(this.calculateNumberActiveSubmenus)
                .map(Long::intValue)
                .orElseThrow(() -> new MenuNotFoundException(String.format("Not Found Menu menuId=%s", menuUUID)));
    }

    @Override
    public Map<BigDecimal, List<Item>> getItemsGrupedPriceByMenuUUID(UUID menuUUID) throws MenuNotFoundException {
        log.info("GetItemsGrupedPriceByMenuUUID menuUUID={}", menuUUID);
        return this.menuRepository.getMenuDataByMenuUUID(menuUUID)
                .map(menuData -> this.groupedItemsByPrice.apply(menuData, Item::getPrice))
                .orElseThrow(() -> new MenuNotFoundException(String.format("Not Found Menu menuId=%s", menuUUID)));
    }

    @Override
    public Map<Integer, List<Item>> getItemsGrupedRankingByMenuUUID(UUID menuUUID) throws MenuNotFoundException {
        log.info("GetItemsGrupedRankingByMenuUUID menuUUID={}", menuUUID);
        return this.menuRepository.getMenuDataByMenuUUID(menuUUID)
                .map(menuData -> this.groupedItemsByRanking.apply(menuData, Item::getRanking))
                .orElseThrow(() -> new MenuNotFoundException(String.format("Not Found Menu menuId=%s", menuUUID)));
    }
}
