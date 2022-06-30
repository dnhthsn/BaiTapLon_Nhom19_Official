package com.baitaplon.dangkykhamlaiclient.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.baitaplon.dangkykhamlaiclient.model.BacSi;
import com.baitaplon.dangkykhamlaiclient.model.DangKyKhamLai;
import com.baitaplon.dangkykhamlaiclient.model.LichKham;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;


@Controller

public class ClientController {
	
	
	
	private RestTemplate rest = new RestTemplate();
	
	@Autowired
	private EurekaClient client;
	
	private InstanceInfo instance;
	
	@RequestMapping("/newDK")
	public String showNewDKForm(Model model) {
		DangKyKhamLai dkkl = new DangKyKhamLai();

		model.addAttribute("dkkl", dkkl);

		return "new_dkkl";
	}
	
	@RequestMapping(value = "/dkKhamLai", method = RequestMethod.POST)
	public String guiDonDK(@ModelAttribute("dkkl") DangKyKhamLai dkkl, Model model) {
		try {
			instance = client.getNextServerFromEureka("dangkykhamlaiservice", false);
			String url = instance.getHomePageUrl() + "dangkykhamlai/";
			System.out.println(url);
			List<DangKyKhamLai> listDKKL = Arrays.asList(rest.postForObject(url, dkkl, DangKyKhamLai[].class));
			if(listDKKL.size() == 1) {
				return "dk_thanhcong";
			} else {
				DangKyKhamLai dkkl1 = new DangKyKhamLai();
				DangKyKhamLai dkkl2 = new DangKyKhamLai();
				
				dkkl1.setSdt(listDKKL.get(1).getSdt());
				dkkl1.setGioKham(listDKKL.get(1).getGioKham());
				dkkl1.setNgayKham(listDKKL.get(1).getNgayKham());
				dkkl1.setIdBacSi(listDKKL.get(1).getIdBacSi());
				dkkl1.setTenBacSi(listDKKL.get(1).getTenBacSi());
				model.addAttribute("dkkl1", dkkl1);
				
				
				dkkl2.setSdt(listDKKL.get(2).getSdt());
				dkkl2.setGioKham(listDKKL.get(2).getGioKham());
				dkkl2.setNgayKham(listDKKL.get(2).getNgayKham());
				dkkl2.setIdBacSi(listDKKL.get(2).getIdBacSi());
				dkkl2.setTenBacSi(listDKKL.get(2).getTenBacSi());
				model.addAttribute("dkkl2", dkkl2);

				return "dk_khathi";
			}
		}
		 catch(Exception e) {
			 e.printStackTrace();
			 return "dkkl_khonghople";
		 }
	}
	
	@RequestMapping(value = "/dkKhamLai1", method = RequestMethod.POST)
	public String guiDonDKKhaThi1(@ModelAttribute("dkkl") DangKyKhamLai dkkl1) {
		String url = instance.getHomePageUrl() + "dangkykhamlai/";
		DangKyKhamLai dkkl = dkkl1;
		List<DangKyKhamLai> listDKKL = Arrays.asList(rest.postForObject(url, dkkl, DangKyKhamLai[].class));
		return "dk_thanhcong";
	}
	
	@RequestMapping(value = "/dkKhamLai2", method = RequestMethod.POST)
	public String guiDonDKKhaThi2(@ModelAttribute("dkkl") DangKyKhamLai dkkl2) {
		String url = instance.getHomePageUrl() + "dangkykhamlai/";
		DangKyKhamLai dkkl = dkkl2;
		List<DangKyKhamLai> listDKKL = Arrays.asList(rest.postForObject(url, dkkl, DangKyKhamLai[].class));
		return "dk_thanhcong";
	}
	
	
	//-----------Below is the older code
//	private RestTemplate rest = new RestTemplate();
//	
//	@RequestMapping("/newDK")
//	public String showNewDKForm(Model model) {
//		DangKyKhamLai dkkl = new DangKyKhamLai();
//
//		model.addAttribute("dkkl", dkkl);
//
//		return "new_dkkl";
//	}
//	
//	@RequestMapping(value = "/dkKhamLai", method = RequestMethod.POST)
//	public String guiDonDK(@ModelAttribute("dkkl") DangKyKhamLai dkkl, Model model) {
//		try {
//			List<DangKyKhamLai> listDKKL = Arrays.asList(rest.postForObject("http://localhost:8085/dangkykhamlai/", dkkl, DangKyKhamLai[].class));
//			if(listDKKL.size() == 1) {
//				return "dk_thanhcong";
//			} else {
//				DangKyKhamLai dkkl1 = new DangKyKhamLai();
//				DangKyKhamLai dkkl2 = new DangKyKhamLai();
//				
//				dkkl1.setSdt(listDKKL.get(1).getSdt());
//				dkkl1.setGioKham(listDKKL.get(1).getGioKham());
//				dkkl1.setNgayKham(listDKKL.get(1).getNgayKham());
//				dkkl1.setIdBacSi(listDKKL.get(1).getIdBacSi());
//				dkkl1.setTenBacSi(listDKKL.get(1).getTenBacSi());
//				model.addAttribute("dkkl1", dkkl1);
//				
//				
//				dkkl2.setSdt(listDKKL.get(2).getSdt());
//				dkkl2.setGioKham(listDKKL.get(2).getGioKham());
//				dkkl2.setNgayKham(listDKKL.get(2).getNgayKham());
//				dkkl2.setIdBacSi(listDKKL.get(2).getIdBacSi());
//				dkkl2.setTenBacSi(listDKKL.get(2).getTenBacSi());
//				model.addAttribute("dkkl2", dkkl2);
//
//				return "dk_khathi";
//			}
//		}
//		 catch(Exception e) {
//			 e.printStackTrace();
//			 return "dkkl_khonghople";
//		 }
//	}
//	
//	@RequestMapping(value = "/dkKhamLai1", method = RequestMethod.POST)
//	public String guiDonDKKhaThi1(@ModelAttribute("dkkl") DangKyKhamLai dkkl1) {
//		DangKyKhamLai dkkl = dkkl1;
//		List<DangKyKhamLai> listDKKL = Arrays.asList(rest.postForObject("http://localhost:8085/dangkykhamlai/", dkkl, DangKyKhamLai[].class));
//		return "dk_thanhcong";
//	}
//	
//	@RequestMapping(value = "/dkKhamLai2", method = RequestMethod.POST)
//	public String guiDonDKKhaThi2(@ModelAttribute("dkkl") DangKyKhamLai dkkl2) {
//		DangKyKhamLai dkkl = dkkl2;
//		List<DangKyKhamLai> listDKKL = Arrays.asList(rest.postForObject("http://localhost:8085/dangkykhamlai/", dkkl, DangKyKhamLai[].class));
//		return "dk_thanhcong";
//	}
}
