package com.fsabino.menu_business.model;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;

public class Menu {

    private UUID id;
    private String description;
    private boolean active;
    private List<Item> items;
    private List<SubMenu> submenus;

    public UUID getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<SubMenu> getSubmenus() {
        return submenus;
    }

    public static MenuBuilder builder() {
        return new MenuBuilder();
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                '}';
    }

    private Menu(UUID id,
                 String description,
                 boolean active,
                 List<Item> items,
                 List<SubMenu> submenus) {
        this.id = id;
        this.description = description;
        this.active = active;
        this.items = items;
        this.submenus = submenus;
    }

    public static class MenuBuilder {
        private UUID id;
        private String description;
        private boolean active;
        private List<Item> items = emptyList();
        private List<SubMenu> submenus = emptyList();

        public MenuBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public MenuBuilder description(String description) {
            this.description = description;
            return this;
        }

        public MenuBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public MenuBuilder items(List<Item> items) {
            this.items = items;
            return this;
        }

        public MenuBuilder submenus(List<SubMenu> submenus) {
            this.submenus = submenus;
            return this;
        }

        public Menu build() {
            return new Menu(this.id,
                    this.description,
                    this.active,
                    this.items,
                    this.submenus);
        }
    }
}
