package com.mehdi.auth.models;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Table(name = "bgc_table_ref")
public class Reference {
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Getter
	@Setter
	@Column(name = "bgc__nom")
	private String nom;
	
	@Getter
	@Setter
	@Column(name = "bgc__designation")
	private String designation;
	
	@Getter
	@Setter
	@Column(name = "bgc__valeur_char")
	private String valeur;
	
	@Getter
	@Setter
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_chantier")
    private Chantier chantier;

	public Reference() {
		super();
	}
}
