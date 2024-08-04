package com.akashit.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.akashit.entity.CitizenPlan;
import com.akashit.repo.CitizenRepository;
import com.akashit.request.SearchRequest;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenRepository planRepo;

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
		List<CitizenPlan> records = planRepo.findAll();

		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("plans-data");

		// Create header row
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Status");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amt"); // Corrected index to 6

		// Populate data rows

		int rowCount = 1;
		for (CitizenPlan plan : records) {
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(plan.getCitizenId());
			row.createCell(1).setCellValue(plan.getCitizenName());
			row.createCell(2).setCellValue(plan.getPlanStatus());
			row.createCell(3).setCellValue(plan.getGender());
			if (plan.getPlanStartDate() != null) {
				row.createCell(4).setCellValue(plan.getPlanStartDate().toString());
			} else {
				row.createCell(4).setCellValue("N/A");
			}
			if (plan.getPlanEndDate() != null) {
				row.createCell(5).setCellValue(plan.getPlanEndDate().toString());
			} else {
				row.createCell(5).setCellValue("N/A");
			}
			if (plan.getBenefitAmt() != null) {
				row.createCell(6).setCellValue(plan.getBenefitAmt().toString());
			} else {
				row.createCell(6).setCellValue("N/A");
			}
		}

		FileOutputStream fos = new FileOutputStream(new File("plans.xls"));
		workbook.write(fos);
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
	    Document document = new Document(PageSize.A4);
	    PdfWriter.getInstance(document, response.getOutputStream());
	    document.open();

	    // Add title
	    Paragraph title = new Paragraph("Citizen Plans Info", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
	    title.setAlignment(Element.ALIGN_CENTER);
	    document.add(title);

	    // Create table with 7 columns
	    PdfPTable table = new PdfPTable(7);
	    table.setWidthPercentage(100);
	    table.setSpacingBefore(10f);
	    table.setSpacingAfter(10f);

	    // Add table headers
	    table.addCell("ID");
	    table.addCell("Citizen Name");
	    table.addCell("Plan Status");
	    table.addCell("Gender");
	    table.addCell("Plan Start Date");
	    table.addCell("Plan End Date");
	    table.addCell("Benefit Amount");

	    // Fetch records from the service
	    List<CitizenPlan> records = planRepo.findAll(); // Ensure this method exists in your service

	    // Add data rows
	    for (CitizenPlan plan : records) {
	        table.addCell(String.valueOf(plan.getCitizenId()));
	        table.addCell(plan.getCitizenName());
	        table.addCell(plan.getPlanStatus());
	        table.addCell(plan.getGender());

	        String status = plan.getPlanStatus();
	        if ("Denied".equalsIgnoreCase(status)) {
	            table.addCell("N/A");
	            table.addCell("N/A");
	            table.addCell("N/A");
	        } else {
	            table.addCell(plan.getPlanStartDate() != null ? plan.getPlanStartDate().toString() : "N/A");
	            table.addCell(plan.getPlanEndDate() != null ? plan.getPlanEndDate().toString() : "N/A");
	            table.addCell(plan.getBenefitAmt() != null ? plan.getBenefitAmt().toString() : "N/A");
	        }
	    }

	    // Add table to document
	    document.add(table);
	    document.close();

	    return true;
	}


}
