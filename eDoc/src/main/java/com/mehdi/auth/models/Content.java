package com.mehdi.auth.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Table(name = "dmr_content")
public class Content {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Getter
	@Setter
	@Column(name = "set_client")
	private String setClient;
	@Getter
	@Setter
	@Column(name = "full_format")
	private String fullFormat;
	@Getter
	@Setter
	@Column(name = "set_file")
	private String setFile;
	@Getter
	@Setter
	@Column(name = "set_download")
	private String setDownload;
	@Getter
	@Setter
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "set_time")
	private Date setTime;
	@Getter
	@Setter
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "parent_id")
	private Plan plan;
	public Content() {
		super();
	}
	
}
