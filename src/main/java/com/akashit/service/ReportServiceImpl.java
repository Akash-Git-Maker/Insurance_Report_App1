package com.akashit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.akashit.entity.CitizenPlan;
import com.akashit.repo.CitizenRepository;
import com.akashit.request.SearchRequest;
import com.akashit.util.ExportExcel;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenRepository planRepo;
	
	@Autowired
	private ExportExcel exportExcel;
	
	@Autowired
	private ExportPdf exportPdf;

	@Override
	public List<String> getPlanNames() {
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return planRepo.getplanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();

		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		List<CitizenPlan> plans = planRepo.findAll();
		exportExcel.generate(response, plans);
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
	    
		List<CitizenPlan> plans = planRepo.findAll();
		exportPdf.generate(response, plans);
	    return true;
	}
 

}
