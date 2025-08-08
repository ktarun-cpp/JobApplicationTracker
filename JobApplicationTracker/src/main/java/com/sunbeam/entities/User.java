package com.sunbeam.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class User implements UserDetails {
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
	@Column(length = 250)
	private String password;
	private String role = "User";
	
	
	
	//this is one to many relationship 
	//one user can have multiple applications
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Application> application = new ArrayList<>();
	
	
	//helper method to create application
	public  void addApp(Application entity) {
		this.application.add(entity);
		entity.setUser(this);
	}
	//helper method to delete application
	public void deleteApp(Long id) {
		this.application.removeIf(app->app.getId().equals(id));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(this.role);
		return grantedAuthorities;
	}


	@Override
	public String getUsername() {
		return email;
	}
}
