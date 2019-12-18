package com.taleantacq.service;

import java.io.File;

import com.taleantacq.domain.CandidateResponse;
import com.taleantacq.domain.ExcelFileRequest;

public interface IExcelService {

	public CandidateResponse uploadExcel(File file, ExcelFileRequest excelRequest) ;
}
