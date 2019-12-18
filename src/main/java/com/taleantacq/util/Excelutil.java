package com.taleantacq.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.taleantacq.jpa.CandidateDetails;

@PropertySource("classpath: EmailPath.properties")
public  final class Excelutil {
	
	@Value("$(excel.write.error.file)")
	private String errorFilePath;
    
	@Autowired
	Environment env;

	public  String generateExcelForCandidateErrors(List<CandidateDetails> errorCandidateList) throws IOException {
		 //FileInputStream file = new FileInputStream(new File("C:\\Users\\Yadak\\Desktop\\Candidate_data_with_errors.xlsx"));

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a Sheet
        XSSFSheet sheet = workbook.createSheet("Employee");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        String columns[]=new String[]{"First Name","Last Name","Mobile Number","Email","Errors"};
        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }


        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(CandidateDetails candidateDetail: errorCandidateList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(candidateDetail.getFirstName());

            row.createCell(1)
                    .setCellValue(candidateDetail.getLastName());

            Cell dateOfBirthCell = row.createCell(2);
            dateOfBirthCell.setCellValue(candidateDetail.getPrimaryMobileNumber());

            row.createCell(3)
                    .setCellValue(candidateDetail.getEmail());
            row.createCell(4)
            .setCellValue(candidateDetail.getErrors().toString());
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        
        // Write the output to a file
       // FileOutputStream fileOut = new FileOutputStream("C://Users//Yadak//Desktop//Candidate_details_error.xlsx");
        String filepath=env.getProperty("excel.write.error.file");
        FileOutputStream fileOut = new FileOutputStream(filepath);
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
        
     return filepath;   
	}

	
}
