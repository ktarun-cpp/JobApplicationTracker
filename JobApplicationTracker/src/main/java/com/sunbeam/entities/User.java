package com.sunbeam.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 20)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	@Column(length = 20)
	private String email;
	@Column(length = 10)
	private String phoneNo;
	@Column(length = 20)
	private String password;
	
	
	//this is one to many relationship 
	//one user can have multiple applications
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Application> application = new ArrayList<>();
	
	
	//helper method to create application
	public  void addApp(Application entity) {
		this.application.add(entity);
		entity.setUser(this);
	}
}
