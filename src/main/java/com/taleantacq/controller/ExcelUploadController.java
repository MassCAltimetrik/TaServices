package com.taleantacq.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taleantacq.domain.ExcelFileRequest;
import com.taleantacq.service.IExcelService;

@Controller
public class ExcelUploadController {

	private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

	@Autowired
	IExcelService excelService;
	
	@PostMapping("/uploadExcel")
	@ResponseBody
	public ResponseEntity<String> readExcelFile(@ModelAttribute ExcelFileRequest excelRequest) throws IOException {
		logger.info("uploadExcelFile: "+excelRequest.getFile());
		File convertedFile = convertToFile((MultipartFile)excelRequest.getFile());
		logger.info("Converted Excel File: "+excelRequest.getFile());
		excelService.uploadExcel(convertedFile,excelRequest);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}
	
	
	private File convertToFile(MultipartFile file) throws IOException {
		File convertFile = new File(file.getOriginalFilename());
		FileOutputStream fileOutput = new FileOutputStream(convertFile);
		fileOutput.write(file.getBytes());
		fileOutput.close();
		return convertFile;
	}
}
