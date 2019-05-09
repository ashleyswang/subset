import net.sourceforge.tess4j.Tesseract;
import java.io.File;

public class TextDetection {

	public String detectText(String filePathway) throws Exception {
		Tesseract tesseract = new Tesseract();
		return tesseract.doOCR(new File(filePathway));
	}
	
	public void printText(String filePathway) throws Exception {
		System.out.println(detectText(filePathway));
	}
}
