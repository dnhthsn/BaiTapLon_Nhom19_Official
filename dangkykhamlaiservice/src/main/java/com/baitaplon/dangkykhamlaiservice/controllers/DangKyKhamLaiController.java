package com.baitaplon.dangkykhamlaiservice.controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.baitaplon.dangkykhamlaiservice.model.BacSi;
import com.baitaplon.dangkykhamlaiservice.model.BenhNhan;
import com.baitaplon.dangkykhamlaiservice.model.DangKyKhamLai;
import com.baitaplon.dangkykhamlaiservice.model.LichKham;


@RestController
@RequestMapping(path = "/", produces = "application/json;charset=UTF-8")
public class DangKyKhamLaiController {
	private RestTemplate rest = new RestTemplate();
	
	
	
	@PostMapping("/dangkykhamlai")
	public ResponseEntity<List<DangKyKhamLai>> start(@RequestBody DangKyKhamLai dkkl) {
		List<DangKyKhamLai> result = new ArrayList<DangKyKhamLai>();
		result.add(dkkl);
		if(dkkl.getType() == 0) {
			// gui don ban dau
			
			String sdt = dkkl.getSdt();
			String ngayKham = dkkl.getNgayKham();
			String gioKham= dkkl.getGioKham();
			BenhNhan bn = rest.getForObject("http://localhost:8082/benhnhansdt/{sdt}", BenhNhan.class,
					sdt);
			
			if(bn == null) {
				return null;
			}
			
			// Lay lich kham cu cua benh nhan
			LichKham lichKhamCu = rest.getForObject("http://localhost:8083/lichkhamcu/{id}", 
					LichKham.class, bn.getIdbenhnhan());
			
			BacSi bacSi = rest.getForObject("http://localhost:8081/bacsi/{id}", BacSi.class,
					lichKhamCu.getIdbacsi());
			
			// lay ra danh sach lich kham cua bac si
			
			List<LichKham> dsLichKhamBacSi = Arrays.asList(rest.getForObject("http://localhost:8083/lichkhambacsi/{id}", LichKham[].class,
					bacSi.getIdbacsi()));
			
					// kiem tra xem bac si co ban khong
			
			boolean checkBan = false;
			for(int i = 0; i < dsLichKhamBacSi.size(); i++) {
				if((dsLichKhamBacSi.get(i).getNgaykham()).equals(ngayKham) && (dsLichKhamBacSi.get(i).getGiokham().equals(gioKham))) {
					checkBan = true;
					break;
				}
			}
			
			
			//gio kham 7:30, 8:00, 8:30, 9:00, 9:30, 10:00
			//         10:30, 11:00, 11:30, 12:00, 12:30, 13:00, 13:30, 14:00
			//			14:30, 15:00, 15:30, 16:00
			ArrayList<String> listTFKham = new ArrayList<>();
			listTFKham.add("7:30");
			listTFKham.add("8:00");
			listTFKham.add("8:30");
			listTFKham.add("9:00");
			listTFKham.add("9:30");
			listTFKham.add("10:00");
			listTFKham.add("10:30");
			listTFKham.add("11:00");
			listTFKham.add("11:30");
			listTFKham.add("12:00");
			listTFKham.add("12:30");
			listTFKham.add("13:00");
			listTFKham.add("13:30");
			listTFKham.add("14:00");
			listTFKham.add("14:30");
			listTFKham.add("15:00");
			listTFKham.add("15:30");
			listTFKham.add("16:00");
			ArrayList<Boolean> checkTG = new ArrayList<Boolean>();
			for(int i = 0; i < listTFKham.size(); i++) {
				checkTG.add(false);
			}
			
			if(checkBan == true) {
				// tao don kha thi
				LichKham lichKhamMoi1 = new LichKham();
				String gioKham1 = "";
				BacSi bacsi1 = bacSi;
				LichKham lichKhamMoi2 = new LichKham();
				String gioKham2 = gioKham;
				BacSi bacsi2 = new BacSi();
				bacsi2.setTenbs("");
				bacsi2.setIdbacsi(0);
				
				for(int i = 0; i < listTFKham.size(); i++) {
					for(int j = 0; j < dsLichKhamBacSi.size(); j++) {
						if(((dsLichKhamBacSi.get(j)).getNgaykham()).equals(ngayKham) && (listTFKham.get(i)).equals((dsLichKhamBacSi.get(j)).getGiokham())) {
							checkTG.set(i, true);
						}
					}
				}
				for(int i = 0; i < checkTG.size(); i++) {
					if(checkTG.get(i) == false) {
						//thoi gian dung duoc cua bac si cu
						gioKham1 = listTFKham.get(i);
						break;
					}
				}
				
				List<BacSi> listBacSi = Arrays.asList(rest.getForObject("http://localhost:8081/bacsi", BacSi[].class));
				// moi bac si mot ngay co toi da 18 lich kham
				List<LichKham> listLichKhamNgay = Arrays.asList(rest.getForObject("http://localhost:8083/lichkham/{ngaydangky}", LichKham[].class, ngayKham));
				ArrayList<Integer> soLuongKhamCuaBS = new ArrayList<Integer>();
				for(int i = 0; i < listBacSi.size(); i++) {
					soLuongKhamCuaBS.add(0);
				}
				
				
				
				for(int i = 0; i < listBacSi.size(); i++) {
					for(int j = 0; j < listLichKhamNgay.size(); j++) {
						if(listBacSi.get(i).getIdbacsi() == listLichKhamNgay.get(j).getIdbacsi()) {
							soLuongKhamCuaBS.set(i, soLuongKhamCuaBS.get(i) + 1);
						}
					}
				}
				
				for(int i = 0; i < soLuongKhamCuaBS.size(); i++) {
					if(listBacSi.get(i).getIdbacsi() != bacSi.getIdbacsi()) {
						if(soLuongKhamCuaBS.get(i) < 18) {
							bacsi2 = listBacSi.get(i);
						}
					}
				}
				
				
//				private int type;
//				private String sdt;
//				private List<LichKham> lichKham;
//				private List<BacSi> bacSi;
//				private String ngayKham;
//				private String gioKham;
				
				DangKyKhamLai dkklMoi1 = new DangKyKhamLai();
				dkklMoi1.setType(1);
				dkklMoi1.setSdt(sdt);
				dkklMoi1.setTenBacSi(bacsi1.getTenbs());
				dkklMoi1.setIdBacSi(bacsi1.getIdbacsi());
				dkklMoi1.setGioKham(gioKham1);
				dkklMoi1.setNgayKham(ngayKham);
				
				DangKyKhamLai dkklMoi2 = new DangKyKhamLai();
				dkklMoi2.setType(1);
				dkklMoi2.setSdt(sdt);
				dkklMoi2.setTenBacSi(bacsi2.getTenbs());
				dkklMoi2.setIdBacSi(bacsi2.getIdbacsi());
				dkklMoi2.setGioKham(gioKham2);
				dkklMoi2.setNgayKham(ngayKham);
				
				
				result.add(dkklMoi1);	
				result.add(dkklMoi2);
				
			} else {
				// luu lich kham cho benh nhan
				LichKham lichKhamMoi = new LichKham();
				lichKhamMoi.setGiokham(gioKham);
				lichKhamMoi.setNgaykham(ngayKham);
				lichKhamMoi.setBacSi(bacSi);
				lichKhamMoi.setIdbacsi(bacSi.getIdbacsi());
				lichKhamMoi.setBenhNhan(bn);
				lichKhamMoi.setIdbenhnhan(bn.getIdbenhnhan());
				rest.postForObject("http://localhost:8083/lichkham/them", lichKhamMoi, LichKham.class);
				// goi notify de gui mail
				List<String> list = new ArrayList<String>();
				list.add(bn.getDiachi());
				list.add("Xin Chào "+ bn.getTenbn());
				list.add("Bạn đã thực hiện đăng ký khám với bác sĩ " + bacSi.getTenbs() + " vào lúc " + gioKham + " ngày " + ngayKham);
				rest.postForObject("http://localhost:8084/notify", list, String.class);
			}
			
		}else {
			// luu don kha thi
			String sdt = dkkl.getSdt();
			String ngayKham = dkkl.getNgayKham();
			String gioKham= dkkl.getGioKham();
			int idBacSi = dkkl.getIdBacSi();
			BenhNhan bn = rest.getForObject("http://localhost:8082/benhnhansdt/{sdt}", BenhNhan.class,
					sdt);
			
			BacSi bacSi = rest.getForObject("http://localhost:8081/bacsi/{id}", BacSi.class,
					idBacSi);
			
			// luu lich kham cho benh nhan
			LichKham lichKhamMoi = new LichKham();
			lichKhamMoi.setGiokham(gioKham);
			lichKhamMoi.setNgaykham(ngayKham);
			lichKhamMoi.setBacSi(bacSi);
			lichKhamMoi.setIdbacsi(bacSi.getIdbacsi());
			lichKhamMoi.setBenhNhan(bn);
			lichKhamMoi.setIdbenhnhan(bn.getIdbenhnhan());
			rest.postForObject("http://localhost:8083/lichkham/them", lichKhamMoi, LichKham.class);
			// goi notify de gui mail
			List<String> list = new ArrayList<String>();
			list.add(bn.getDiachi());
			list.add("Xin Chào "+ bn.getTenbn());
			list.add("Bạn đã thực hiện đăng ký khám với bác sĩ " + bacSi.getTenbs() + " vào lúc " + gioKham + " ngày " + ngayKham);
			rest.postForObject("http://localhost:8084/notify", list, String.class);
				
		}
		return new ResponseEntity<List<DangKyKhamLai>>(result, HttpStatus.OK);
		
	}
}
