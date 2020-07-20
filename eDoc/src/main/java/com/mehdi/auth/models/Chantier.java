package com.mehdi.auth.models;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Table(	name = "bgc__Fiche_Chantier", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "codechantier")
		})
public class Chantier {
	@Getter  
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter  
	@Setter
	@NotBlank
	@Size(max = 120)
	private String nomchantier;
	
	@Getter  
	@Setter
	@NotBlank
	@Size(max = 120)
	private String codeedifice;
	
	
	@Getter  
	@Setter
	@NotBlank
	@Size(max = 120)
	private String codeprojet;
	
	@Getter  
	@Setter
	@NotBlank
	@Size(max = 120)
	private String codechantier;
	
	@Getter  
	@Setter
	@NotBlank
	@Size(max = 120)
	private String uo;
	
	@Getter  
	@Setter
	@NotBlank
	@Size(max = 20)
	private String datecreation;
	
	@Getter  
	@Setter
	@NotBlank
	@Size(max = 20)
	private String ownername;
	
	@Getter
	@Setter
	@JsonIgnore
	@OneToMany(mappedBy = "chantier")
	    private Set<Plan> plans;
	  
	@Getter
	@Setter
	@JsonIgnore
	@OneToMany(mappedBy = "chantier")
	private Set<Reference> references;
	
	 @ManyToMany(mappedBy = "chantiers")
	    private Set<Acteur> acteurs = new HashSet<>();

	public Chantier() {
	
	}

	
	

}
