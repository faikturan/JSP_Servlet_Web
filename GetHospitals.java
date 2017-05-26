package com.faikturan.hastaneotomasyonu.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.faikturan.hastaneotomasyonu.service.HospitalsService;


@ManagedBean(name="getHospitals")
@ViewScoped
public class GetHospitals implements Serializable {

	@ManagedProperty(value="#{saveAppointments}")
	private SaveAppointments saveAppointments;

	private int districtId;
	String currentDistrict;
	
	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getCurrentDistrict() {
		return currentDistrict;
	}

	public void setCurrentDistrict(String currentDistrict) {
		this.currentDistrict = currentDistrict;
	}

	public SaveAppointments getSaveAppointments() {
		return saveAppointments;
	}

	public void setSaveAppointments(SaveAppointments saveAppointments) {
		this.saveAppointments = saveAppointments;
	}

	public List<String> fillList() throws Exception
	{
		ApplicationContext context = 
				FacesContextUtils.getWebApplicationContext
				(FacesContext.getCurrentInstance());
		HospitalsService  hospitalsService = 
				 (HospitalsService) context.getBean("hospitalService");
		return hospitalsService.getAllHospitalNames(saveAppointments.currentDistrict);
		
	}
}
