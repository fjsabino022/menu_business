package com.fsabino.menu_business.service;

import com.fsabino.menu_business.converter.MenuDataToMenuConverter;
import com.fsabino.menu_business.model.Menu;
import com.fsabino.menu_business.repository.MenuRepository;
import com.fsabino.menu_business.repository.persistence.MenuData;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuDataToMenuConverter menuDataToMenuConverter;

    @Inject
    public MenuServiceImpl(MenuRepository menuRepository,
                           MenuDataToMenuConverter menuDataToMenuConverter) {
        this.menuRepository = menuRepository;
        this.menuDataToMenuConverter = menuDataToMenuConverter;
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuRepository.getAllMenuData().stream()
                .map(this.menuDataToMenuConverter)
                .collect(toList());
    }

    @Override
    public List<Menu> getActiveMenus() {
        return menuRepository.getAllMenuData().stream()
                .filter(MenuData::isActive)
                .map(menuDataToMenuConverter)
                .collect(toList());
    }

    @Override
    public BigDecimal getPriceByMenuUUID(UUID menuId) {

        Optional<MenuData> menuData = this.menuRepository.getMenuDataByMenuUUID(menuId);


        return Optional.empty();
    }
}
