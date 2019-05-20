package com.fsabino.menu_business.service;

import com.fsabino.menu_business.exception.MenuNotFoundException;
import com.fsabino.menu_business.model.Item;
import com.fsabino.menu_business.model.Menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MenuService {

    List<Menu> getAllMenus();

    List<Menu> getActiveMenus();

    BigDecimal getPriceByMenuUUID(UUID menuUUID) throws MenuNotFoundException;

    int getNumberActiveSubmenusByMenuUUID(UUID menuUUID) throws MenuNotFoundException;

    Map<BigDecimal, List<Item>> getItemsGrupedPriceByMenuUUID(UUID menuUUID) throws MenuNotFoundException;

    Map<Integer, List<Item>> getItemsGrupedRankingByMenuUUID(UUID menuUUID) throws MenuNotFoundException;
}
