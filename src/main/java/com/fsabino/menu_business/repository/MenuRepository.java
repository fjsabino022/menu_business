package com.fsabino.menu_business.repository;

import com.fsabino.menu_business.repository.persistence.MenuData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuRepository {

    List<MenuData> getAllMenuData();

    Optional<MenuData> getMenuDataByMenuUUID(UUID uuid);
}
