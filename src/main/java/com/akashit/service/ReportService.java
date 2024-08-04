package com.akashit.service;

import java.util.List;

import com.akashit.entity.CitizenPlan;
import com.akashit.request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	public List<String> getPlanNames();

	public List<String> getPlanStatus();

	public List<CitizenPlan> search(SearchRequest request);

	public boolean exportPdf(HttpServletResponse response) throws Exception;

	public boolean exportExcel(HttpServletResponse response) throws Exception;

}
