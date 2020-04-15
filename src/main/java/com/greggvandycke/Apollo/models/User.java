package com.greggvandycke.Apollo.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String username;
	private String password;
	private String email;
	private String token;
	private Boolean enabled;

	@ManyToOne
	private Role role;

	@Transient
	private List<Role> roles = new ArrayList<>();

	@Transient
	private String beautifyRoleName;

	// Temp field used when add or update user
	@Transient
	private String userPassword;


	public List<Role> getRoles() {
		roles.clear();
		roles.add(role);
		return roles;
	}


	public String getBeautifyRoleName() {
		if (role == null) {
			return beautifyRoleName;
		}
		if (role.getName() == RoleName.ROLE_ADMIN) {
			beautifyRoleName = "Admin";
		} else {
			beautifyRoleName = "User";
		}
		return beautifyRoleName;
	}
}
