package com.fsabino.menu_business.repository.persistence;

import java.math.BigDecimal;
import java.util.UUID;

import static com.fsabino.menu_business.validation.Validations.checkEmptyParam;
import static com.fsabino.menu_business.validation.Validations.checkNullParam;

public class ItemData {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String currency;
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

    public String getCurrency() {
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

    public static ItemDataBuilder builder() {
        return new ItemDataBuilder();
    }

    private ItemData(UUID id,
                 String name,
                 String description,
                 BigDecimal price,
                 String currency,
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

    public static class ItemDataBuilder {

        private UUID id;
        private String name;
        private String description;
        private BigDecimal price;
        private String currency;
        private Integer ranking;
        private Integer validStartDate;
        private Integer validEndDate;
        private Integer validStartHour;
        private Integer validEndHour;
        private String photo;

        public ItemDataBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public ItemDataBuilder name(String name) {
            checkEmptyParam(name, () -> "name cannot be null");
            this.name = name;
            return this;
        }

        public ItemDataBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ItemDataBuilder price(BigDecimal price) {
            checkNullParam(price, () -> "price cannot be null");
            this.price = price;
            return this;
        }

        public ItemDataBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public ItemDataBuilder ranking(Integer ranking) {
            checkNullParam(ranking, () -> "ranking cannot be null");
            this.ranking = ranking;
            return this;
        }

        public ItemDataBuilder validStartDate(Integer validStartDate) {
            this.validStartDate = validStartDate;
            return this;
        }

        public ItemDataBuilder validEndDate(Integer validEndDate) {
            this.validEndDate = validEndDate;
            return this;
        }

        public ItemDataBuilder validStartHour(Integer validStartHour) {
            this.validStartHour = validStartHour;
            return this;
        }

        public ItemDataBuilder validEndHour(Integer validEndHour) {
            this.validEndHour = validEndHour;
            return this;
        }

        public ItemDataBuilder photo(String photo) {
            this.photo = photo;
            return this;
        }

        public ItemData build() {
            return new ItemData(this.id,
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
