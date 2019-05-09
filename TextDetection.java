import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class TextDetection {

	public String detectText(String filePathway) throws Exception {
		
		String inputFilePath = "F:/Tesseract/English.tif"; //file with specified language data
		
		Tesseract tesseract = new Tesseract();
		
		tesseract.setDatapath(filePathway); //folder with image
		
		String fullText = tesseract.doOCR(new File(inputFilePath));
		
		return fullText;
	}
	
	public void printText() throws Exception {
		
		String inputFilePath = "F:/Tesseract/English.tif"; //file with specified language data
		
		Tesseract tesseract = new Tesseract();
		
		tesseract.setDatapath(filePathway); //folder with image
		
		String fullText = tesseract.doOCR(new File(inputFilePath));
		
		System.out.println(fullText);
	}
}
