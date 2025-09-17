package com.cdac.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product extends BaseEntity {

	@Column(name = "Product Name", length = 20, nullable = false, unique = true)
	private String name;
	@Column(name = "Selling Price", nullable = false)
	private double sellingPrice;
	@Column(name = "MRP", nullable = false)
	private double costPrice;
	@Column(name = "Left in Stock", nullable = false)
	private int inStock;
	@Column(name = "Discount", nullable = false)
	private double discount;
	@Column(name = "Description", length = 100, nullable = false)
	private String description;
	@Column(name = "GST", nullable = false)
	private double gst;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

}
