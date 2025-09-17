package com.cdac.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "User")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User extends BaseEntity {

	@Column(name = "First Name", length = 30, nullable = false)
	private String firstName;
	@Column(name = "Last Name", length = 30, nullable = false)
	private String lastName;
	@Column(name = "E-Mail ID", length = 255, nullable = false, unique = true)
	private String email;
	@Column(name = "Password", length = 15, nullable = false, unique = true)
	private String password;
	@Column(name = "Phone No", length = 10, nullable = false, unique = true)
	private Long phoneNo;

	
	//one user to one busniess detail 
	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private BusinessDetails businessDetail;
	
	
	//one user to multiple bill 
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Bill> bills = new ArrayList<>();
	
	//ONE USER HAS MULTIPLE PRODUCTS

	
	
	
}
