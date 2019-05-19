package com.fsabino.menu_business.service;

import com.fsabino.menu_business.model.Menu;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuService {

    List<Menu> getAllMenus();

    List<Menu> getActiveMenus();

    BigDecimal getPriceByMenuUUID(UUID menuId);
}
