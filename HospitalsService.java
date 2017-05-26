package com.faikturan.hastaneotomasyonu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.faikturan.hastaneotomasyonu.entity.Hastaneler;
import com.faikturan.hastaneotomasyonu.entity.Ilceler;
import com.faikturan.hastaneotomasyonu.facade.HospitalFacade;

public class HospitalsService {

	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private HospitalFacade hospitalFacade;
	
	List<String> hospitalNames;
	
	public DistrictService getDistrictService() {
		return districtService;
	}

	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

	public HospitalFacade getHospitalFacade() {
		return hospitalFacade;
	}

	public void setHospitalFacade(HospitalFacade hospitalFacade) {
		this.hospitalFacade = hospitalFacade;
	}

	public List<String> getHospitalNames() {
		return hospitalNames;
	}

	public void setHospitalNames(List<String> hospitalNames) {
		this.hospitalNames = hospitalNames;
	}

	List<Hastaneler> hospitalResults;

	public List<Hastaneler> getHospitalResults() {
		return hospitalResults;
	}

	public void setHospitalResults(List<Hastaneler> hospitalResults) {
		this.hospitalResults = hospitalResults;
	}

	public List<String> getAllHospitalNames(String currentDistrict) throws Exception {
		System.out.println("Güncel Ýlçe Adý:" +currentDistrict);
		int districtId=0;
		hospitalNames = new ArrayList<>();
		
		for (Ilceler i : districtService.getDistrictResults()) {
			if (currentDistrict.equals(i.getIlce())) {
				districtId = i.getId();
				break;
			}
		}
		
		System.out.println("Güncel Ýlçe ID'si" +districtId);
		Map parameters = new HashMap();
		parameters.put("ilceid", districtId);
		hospitalResults = 
				hospitalFacade.
				findListByNamedQuery("Hastaneler.findByIlceid", parameters);
		if (hospitalResults != null) {
			System.out.println("---hospitalresults is not null!---\n");
		}
		
		for (Hastaneler h : hospitalResults) {
			hospitalNames.add(h.getHastaneadi());
		}
		return hospitalNames;
	}
	
	

}
