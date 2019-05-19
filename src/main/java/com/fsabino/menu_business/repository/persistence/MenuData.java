package com.fsabino.menu_business.repository.persistence;

import com.fsabino.menu_business.model.Menu;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class MenuData {

    private UUID id;
    private String description;
    private boolean active;
    private List<ItemData> items;
    private List<MenuData> submenus;

    public UUID getId() {
        return id;
    }

    public List<ItemData> getItems() {
        return items;
    }

    public List<MenuData> getSubmenus() {
        return submenus;
    }

    public static MenuDataBuilder builder() {
        return new MenuDataBuilder();
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Stream<MenuData> flattened() {
        return Stream.concat(Stream.of(this), submenus.stream().flatMap(MenuData::flattened));
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                '}';
    }

    private MenuData(UUID id,
                 String description,
                 boolean active,
                 List<ItemData> items,
                 List<MenuData> submenus) {
        this.id = id;
        this.description = description;
        this.active = active;
        this.items = items;
        this.submenus = submenus;
    }

    public static class MenuDataBuilder {

        private UUID id;
        private String description;
        private boolean active;
        private List<ItemData> items = emptyList();
        private List<MenuData> submenus = emptyList();

        public MenuDataBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public MenuDataBuilder description(String description) {
            this.description = description;
            return this;
        }

        public MenuDataBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public MenuDataBuilder items(List<ItemData> items) {
            this.items = items;
            return this;
        }

        public MenuDataBuilder submenus(List<MenuData> submenus) {
            this.submenus = submenus;
            return this;
        }

        public MenuData build() {
            return new MenuData(this.id,
                    this.description,
                    this.active,
                    this.items,
                    this.submenus);
        }
    }
}
