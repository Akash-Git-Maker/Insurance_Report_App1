package com.akashit.service;

import java.util.List;

import com.akashit.entity.CitizenPlan;
import com.akashit.request.SearchRequest;

public interface ReportService {

	public List<String> getPlanNames();

	public List<String> getPlanStatus();

	public List<CitizenPlan> search(SearchRequest request);

	public boolean exportPdf();

	public boolean exportExcel();

}
