package com.akashit.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akashit.entity.CitizenPlan;
import com.akashit.repo.CitizenRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExportPdf {
	
	@Autowired
	private CitizenRepository planRepo;

	public void generate(HttpServletResponse response, List<CitizenPlan> plans) throws Exception {
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

	}
}