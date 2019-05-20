package com.fsabino.menu_business.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static com.fsabino.menu_business.validation.Validations.checkEmptyParam;
import static com.fsabino.menu_business.validation.Validations.checkNullParam;

public class Item {

    public enum Currency {
        EURO("€"),
        PESO("$"),
        DOLLAR("USD");

        private String name;

        Currency(String name) {
            this.name = name;
        }

        @JsonValue
        public String getName() {
            return name;
        }

        @JsonCreator
        public static Currency findCurrency(String name) {
            return Arrays.stream(Currency.values())
                    .filter(e -> e.getName().equals(name))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException(String.format("Unsupported name %s.", name)));
        }
    }

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Currency currency;
    private Integer ranking;
    private Integer validStartDate;
    private Integer validEndDate;
    private Integer validStartHour;
    private Integer validEndHour;
    private String photo;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Integer getRanking() {
        return ranking;
    }

    public Integer getValidStartDate() {
        return validStartDate;
    }

    public Integer getValidEndDate() {
        return validEndDate;
    }

    public Integer getValidStartHour() {
        return validStartHour;
    }

    public Integer getValidEndHour() {
        return validEndHour;
    }

    public String getPhoto() {
        return photo;
    }

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    private Item(UUID id,
                 String name,
                 String description,
                 BigDecimal price,
                 Currency currency,
                 Integer ranking,
                 Integer validStartDate,
                 Integer validEndDate,
                 Integer validStartHour,
                 Integer validEndHour,
                 String photo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.ranking = ranking;
        this.validStartDate = validStartDate;
        this.validEndDate = validEndDate;
        this.validStartHour = validStartHour;
        this.validEndHour = validEndHour;
        this.photo = photo;
    }

    public static class ItemBuilder {

        private UUID id;
        private String name;
        private String description;
        private BigDecimal price;
        private Currency currency;
        private Integer ranking;
        private Integer validStartDate;
        private Integer validEndDate;
        private Integer validStartHour;
        private Integer validEndHour;
        private String photo;

        public ItemBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public ItemBuilder name(String name) {
            checkEmptyParam(name, () -> "name cannot be null");
            this.name = name;
            return this;
        }

        public ItemBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ItemBuilder price(BigDecimal price) {
            checkNullParam(price, () -> "price cannot be null");
            this.price = price;
            return this;
        }

        public ItemBuilder currency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public ItemBuilder ranking(Integer ranking) {
            checkNullParam(ranking, () -> "ranking cannot be null");
            this.ranking = ranking;
            return this;
        }

        public ItemBuilder validStartDate(Integer validStartDate) {
            this.validStartDate = validStartDate;
            return this;
        }

        public ItemBuilder validEndDate(Integer validEndDate) {
            this.validEndDate = validEndDate;
            return this;
        }

        public ItemBuilder validStartHour(Integer validStartHour) {
            this.validStartHour = validStartHour;
            return this;
        }

        public ItemBuilder validEndHour(Integer validEndHour) {
            this.validEndHour = validEndHour;
            return this;
        }

        public ItemBuilder photo(String photo) {
            this.photo = photo;
            return this;
        }

        public Item build() {
            return new Item(this.id,
                    this.name,
                    this.description,
                    this.price,
                    this.currency,
                    this.ranking,
                    this.validStartDate,
                    this.validEndDate,
                    this.validStartHour,
                    this.validEndHour,
                    this.photo);
        }
    }
}
