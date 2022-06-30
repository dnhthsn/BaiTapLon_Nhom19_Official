package com.baitaplon.lichkhamservice.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "lichkham")
public class LichKham  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idlichkham;
	
	
	private String giokham;
	private String ngaykham;
	
	private Integer idbacsi;
	@ManyToOne
	@JoinColumn(name = "idbacsi", insertable = false, updatable = false)
	private BacSi bacSi;
	
	private Integer idbenhnhan;
	@ManyToOne
	@JoinColumn(name = "idbenhnhan", insertable = false, updatable = false)
	private BenhNhan benhNhan;
}
