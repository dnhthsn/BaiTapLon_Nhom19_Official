package com.baitaplon.benhnhanservice.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "benhnhan")
public class BenhNhan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idbenhnhan;
	
	
	private String tenbn;
	private String sdt;
	private String diachi;
}