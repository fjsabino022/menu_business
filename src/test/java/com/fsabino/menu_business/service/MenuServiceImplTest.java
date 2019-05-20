package com.fsabino.menu_business.service;

import com.fsabino.menu_business.converter.MenuDataToMenuConverter;
import com.fsabino.menu_business.model.Menu;
import com.fsabino.menu_business.repository.MenuRepository;
import com.fsabino.menu_business.repository.persistence.MenuData;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MenuDataToMenuConverter menuDataToMenuConverter;

    @Mock
    private CalculateTotalPriceMenu calculateTotalPriceMenu;

    @Mock
    private GroupedItems<BigDecimal> groupedItemsByPrice;

    @Mock
    private GroupedItems<Integer> groupedItemsByRanking;

    @Mock
    private CalculateNumberActiveSubmenus calculateNumberActiveSubmenus;

    @InjectMocks
    private MenuServiceImpl subject;

    private UUID menuUUID1 = UUID.randomUUID();
    private UUID menuUUID2 = UUID.randomUUID();
    private MenuData menuData1 = MenuData.builder()
            .description("menu data mock")
            .active(true)
            .id(menuUUID1)
            .build();
    private MenuData menuData2 = MenuData.builder()
            .description("menu data mock 2")
            .active(false)
            .id(menuUUID2)
            .build();
    private Menu menu1 = Menu.builder()
            .description("menu mock")
            .active(true)
            .id(menuUUID1)
            .build();
    private Menu menu2 = Menu.builder()
            .description("menu mock 2")
            .active(false)
            .id(menuUUID2)
            .build();
    private List<MenuData> menusData = ImmutableList.<MenuData>builder()
            .add(menuData1)
            .add(menuData2)
            .build();

    @Test
    public void getAllMenusTest() {
        when(this.menuRepository.getAllMenuData()).thenReturn(menusData);
        when(this.menuDataToMenuConverter.apply(menuData1)).thenReturn(menu1);
        when(this.menuDataToMenuConverter.apply(menuData2)).thenReturn(menu2);

        List<Menu> result = this.subject.getAllMenus();

        assertTrue(!result.isEmpty());
        assertEquals(menuUUID1, result.get(0).getId());
        assertEquals(menuUUID2, result.get(1).getId());
        verify(this.menuRepository, times(1)).getAllMenuData();
        verify(this.menuDataToMenuConverter, times(2)).apply(any(MenuData.class));
    }

    @Test
    public void getAllMenusEmptyTest() {
        when(this.menuRepository.getAllMenuData()).thenReturn(emptyList());

        List<Menu> result = this.subject.getAllMenus();

        Assert.assertTrue(result.isEmpty());
        verify(this.menuRepository, times(1)).getAllMenuData();
        verify(this.menuDataToMenuConverter, never()).apply(any(MenuData.class));
    }

    @Test
    public void getActiveMenusTest() {
        when(this.menuRepository.getAllMenuData()).thenReturn(menusData);
        when(this.menuDataToMenuConverter.apply(menuData1)).thenReturn(menu1);

        List<Menu> result = this.subject.getActiveMenus();

        assertTrue(!result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(menuUUID1, result.get(0).getId());
        verify(this.menuRepository, times(1)).getAllMenuData();
        verify(this.menuDataToMenuConverter, times(1)).apply(menuData1);
        verify(this.menuDataToMenuConverter, never()).apply(menuData2);
    }

    @Test
    public void getActiveMenusEmptyTest() {
        when(this.menuRepository.getAllMenuData()).thenReturn(emptyList());

        List<Menu> result = this.subject.getActiveMenus();

        assertTrue(result.isEmpty());
        verify(this.menuRepository, times(1)).getAllMenuData();
        verify(this.menuDataToMenuConverter, never()).apply(any(MenuData.class));
    }
}