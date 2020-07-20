package com.mehdi.auth.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Table(name = "bgc__Acteur", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class Acteur {
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@NotBlank
	@Size(max = 20)
	private String username;

	@Getter
	@Setter
	@NotBlank
	@Size(max = 120)
	private String password;

	@Getter
	@Setter

	@Size(max = 70)
	private String useraddress;

	@Getter
	@Setter
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "bgc__Acteur_Chantier", joinColumns = @JoinColumn(name = "Acteur_id"), inverseJoinColumns = @JoinColumn(name = "Chantier_id"))
	private Set<Chantier> chantiers = new HashSet<>();
	
	
	
	@Getter
	@Setter
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "Acteur_roles", 
				joinColumns = @JoinColumn(name = "Acteur_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();


	public Acteur() {
		super();
	}

	public Acteur(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
