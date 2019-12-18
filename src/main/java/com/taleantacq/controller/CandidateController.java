package com.taleantacq.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.taleantacq.comon.TAErrorCodes;
import com.taleantacq.domain.CallDetailRequest;
import com.taleantacq.domain.CallStaticsResponse;
import com.taleantacq.domain.CandidateResponse;
import com.taleantacq.domain.ProfileResponse;
import com.taleantacq.service.ICandidateService;

@Controller
@RequestMapping("/file")
public class CandidateController {

	private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

	@Autowired
	ICandidateService candidateServiceImpl;

	@GetMapping("/getFiles")
	public List getFileData() {
		//		List<CSVData> ls=candidateServiceImpl.getFileDataInList();
		return null;
	}

	@GetMapping("/getCandidateList/{taId}/{pageNumber}")
	@ResponseBody
	public CandidateResponse getCandidateList(@PathVariable("taId")Integer taID ,@PathVariable("pageNumber") Integer pageNumber) throws SQLException {
		return candidateServiceImpl.getCandidateDetails(taID,pageNumber);
	}

	@PostMapping("/uploadExcelFile/{taId}")
	@ResponseBody
	public String readExcelFile(@RequestParam("file" )MultipartFile file,@PathVariable("taId") Integer taid ) throws IOException {
		logger.info("uploadExcelFile: "+file);
		File convertedFile = convertToFile(file);
		logger.info("Converted Excel File: "+file);
		candidateServiceImpl.readExcel(convertedFile,taid);
		return "Success";
	}

	private File convertToFile(MultipartFile file) throws IOException {

		File convertFile = new File(file.getOriginalFilename());
		FileOutputStream fileOutput = new FileOutputStream(convertFile);
		fileOutput.write(file.getBytes());
		fileOutput.close();
		return convertFile;
	}

}