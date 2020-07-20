package com.mehdi.auth.models;



import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Table(name = "bgcp_plan_appro")
public class Plan {
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Getter
	@Setter
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "code_chantier")
    private Chantier chantier; 
	@Getter
	@Setter
	private String bgcp_code_chantier;
	@Getter
	@Setter
	private int bgcp_statut;
	@Getter
	@Setter
	private boolean exist_pdf;
	@Getter
	@Setter
	private String bgc_nom_affaire;
	@Getter
	@Setter
	private String spec_phase;
	@Getter
	@Setter
	private String spec_emetteur;
	@Getter
	@Setter
	private int spec_lot;
	@Getter
	@Setter
	private String spec_niveau;
	@Getter
	@Setter
	private String spec_zone;
	@Getter
	@Setter
	private String spec_type;
	@Getter
	@Setter
	private String spec_conb;
	@Getter
	@Setter
	private int spec_numero;
	@Getter
	@Setter
	private String bgcp_lib_indice;
	@Getter
	@Setter
	private String bgcp_indice;
	@Getter
	@Setter
	private String bgcp_titre1;
	@Getter
	@Setter
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date r_creation_date;
	@Getter
	@Setter
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date bgcp_date_reel_emission;
	@Getter
	@Setter
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date bgcp_date_indice_;
	@Getter
	@Setter
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date r_modify_date;
	@Getter
	@Setter
	private String r_creator_name;
	@Getter
	@Setter
	@JsonIgnore
	@OneToMany(mappedBy = "plan",cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<Content> Content; 
	    
	

	/*
	@Getter
	@Setter
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private Date bgcp_date_reel_approbation;
	@Getter
	@Setter
	private String r_modifier;
	@Getter
	@Setter
	private int r_content_size;
	@Getter
	@Setter
	private Boolean bgcp_presence_natif;
	@Getter
	@Setter
	private Date bgcp_date_reel_execution;
	@Getter
	@Setter
	private int bgcd_nbpage;
	@Getter
	@Setter
	private Boolean bgcp_presence_original;
	@Getter
	@Setter
	private Boolean bgcp_is_synthese;
	@Getter
	@Setter
	private int bgcp_no_circuit_courant;
	@Getter
	@Setter
	private Boolean bgcp_presence_ref_courrier;
	@Getter
	@Setter
	private Date bgcp_date_prev_approbation;
	@Getter
	@Setter
	private Boolean bgcp_presence_note;
	@Getter
	@Setter
	private Boolean bgcp_presence_lison;
	@Getter
	@Setter
	private Date bgcp_date_annulation;
	@Getter
	@Setter
	private Date bgcd_date_diffusion;
	@Getter
	@Setter
	private Date bgcp_date_prev_execution;
	@Getter
	@Setter
	private Boolean bgcp_presence_bordereau;
	@Getter
	@Setter
	private Long a_content_type;
	*/
	
	
	public Plan() {}
}
