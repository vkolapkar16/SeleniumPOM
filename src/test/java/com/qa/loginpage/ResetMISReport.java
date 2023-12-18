package com.qa.loginpage;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.xuriti.dev.Utility.Utility;

public class ResetMISReport {

	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		
		String fileName="LPTC.xlsx";
		Utility.clearExcelData(fileName,0);

	}

}
