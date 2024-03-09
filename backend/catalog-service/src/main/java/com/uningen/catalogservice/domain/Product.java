package com.uningen.catalogservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Product name should not be blank")
    @NotNull(message = "Product name should not be null")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Product description should not be blank")
    @NotNull(message = "Product description should not be null")
    @Column(name = "description")
    private String description;

    @Positive(message = "Product price must be greater than zero")
    @Column(name = "price")
    private double price;

    @NotBlank(message = "Product picture url should not be blank")
    @NotNull(message = "Product picture url should not be null")
    @Column(name = "picture_url")
    private String pictureUrl;

    @NotBlank(message = "Product category should not be blank")
    @NotNull(message = "Product category should not be null")
    @Column(name = "category")
    private String category;

    @NotBlank(message = "Product brand should not be blank")
    @NotNull(message = "Product brand should not be null")
    @Column(name = "brand")
    private String brand;

    @Positive(message = "Product quantity must be greater than zero")
    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @CreatedDate
    @Column(name = "created_date")
    Instant createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    Instant lastModifiedDate;

    @Version
    @Column(name = "version")
    int version;

    public Product() {
    }

    public Product(Long id, @NotNull(message = "Product name should not be null") String name, @NotNull(message = "Product description should not be null") String description, double price, @NotNull(message = "Product picture url should not be null") String pictureUrl, @NotNull(message = "Product category should not be null") String category, @NotNull(message = "Product brand should not be null") String brand, int quantityInStock, Instant createdDate, Instant lastModifiedDate, int version) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.category = category;
        this.brand = brand;
        this.quantityInStock = quantityInStock;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.version = version;
    }

    public static Product of(String name, String description, double price, String pictureUrl, String category, String brand, int quantityInStock) {
        return new Product(null, name, description, price, pictureUrl, category, brand, quantityInStock, null, null, 0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;

        if (Double.compare(product.price, price) != 0) return false;
        if (quantityInStock != product.quantityInStock) return false;
        if (version != product.version) return false;
        if (!Objects.equals(id, product.id)) return false;
        if (!name.equals(product.name)) return false;
        if (!description.equals(product.description)) return false;
        if (!pictureUrl.equals(product.pictureUrl)) return false;
        if (!category.equals(product.category)) return false;
        if (!brand.equals(product.brand)) return false;
        if (!createdDate.equals(product.createdDate)) return false;
        return lastModifiedDate.equals(product.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + pictureUrl.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + brand.hashCode();
        result = 31 * result + quantityInStock;
        result = 31 * result + createdDate.hashCode();
        result = 31 * result + lastModifiedDate.hashCode();
        result = 31 * result + version;
        return result;
    }
}

