package com.cdac.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
	
@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
public class Category extends BaseEntity {

	@Column(name = "Category", unique = true, nullable = false)
	private String name;
	@Column(name = "Description", nullable = false)
	private String description;

	
}
