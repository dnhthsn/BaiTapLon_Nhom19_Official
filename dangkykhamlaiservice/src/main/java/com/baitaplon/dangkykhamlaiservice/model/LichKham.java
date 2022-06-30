package com.baitaplon.dangkykhamlaiservice.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class LichKham {
	private Integer idlichkham;
	
	
	private String giokham;
	private String ngaykham;
	
	private Integer idbacsi;
	private BacSi bacSi;
	
	private Integer idbenhnhan;

	private BenhNhan benhNhan;
}
