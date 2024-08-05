package com.akashit.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akashit.entity.CitizenPlan;
import com.akashit.repo.CitizenRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExportExcel {
	
	@Autowired
	private CitizenRepository planRepo;
	
	public void generate (HttpServletResponse response, List<CitizenPlan> plans) throws Exception {
		
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
		
	}
	}


