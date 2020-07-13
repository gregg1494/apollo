package com.greggvandycke.neutron.models;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends Auditable<String> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String token;

	// Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.
	@NotNull
	private Boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_favorites",
			joinColumns = {
					@JoinColumn(name = "user_id", referencedColumnName = "id",
							nullable = false, updatable = false)},
			inverseJoinColumns = {
					@JoinColumn(name = "movie_id", referencedColumnName = "id",
							nullable = false, updatable = false)})
	private List<Movie> favorites = new ArrayList<>();

	@ManyToOne
	private Role role;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
			joinColumns = {
				@JoinColumn(name = "user_id",referencedColumnName = "id")},
			inverseJoinColumns = {
				@JoinColumn(name = "role_id", referencedColumnName = "id")})
	@Transient
	private List<Role> roles = new ArrayList();

	@Transient
	private String beautifyRoleName;

	// Temp field used when add or update user
	@Transient
	private String userPassword;

	public User(String firstName, String lastName, String username, String password, String email, Boolean enabled) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		roles.clear();
		roles.add(role);
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getBeautifyRoleName() {
		if (role == null) {
			return beautifyRoleName;
		}
		if (role.getRoleName() == RoleName.ROLE_ADMIN) {
			beautifyRoleName = "Admin";
		} else {
			beautifyRoleName = "User";
		}
		return beautifyRoleName;
	}

	public void setBeautifyRoleName(String beautifyRoleName) {
		this.beautifyRoleName = beautifyRoleName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", token='" + token + '\'' +
				", enabled=" + enabled +
				'}';
	}
}