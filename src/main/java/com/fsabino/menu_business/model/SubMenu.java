package com.fsabino.menu_business.model;

public class SubMenu {

    private Menu menu;

    public Menu getMenu() {
        return menu;
    }

    private SubMenu(Menu menu) {
        this.menu = menu;
    }

    public static SubMenuBuilder builder() {
        return new SubMenuBuilder();
    }

    public static class SubMenuBuilder {

        private Menu menu;

        public SubMenuBuilder menu(Menu menu) {
            this.menu = menu;
            return this;
        }

        public SubMenu build() {
            return new SubMenu(this.menu);
        }
    }
}
