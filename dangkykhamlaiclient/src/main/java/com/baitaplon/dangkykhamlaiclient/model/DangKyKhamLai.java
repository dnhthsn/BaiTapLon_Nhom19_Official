package com.baitaplon.dangkykhamlaiclient.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class DangKyKhamLai  {
	private int type;
	private String sdt;
	private String tenBacSi;
	private int idBacSi;
	private String ngayKham;
	private String gioKham;

}
