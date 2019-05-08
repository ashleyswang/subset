package com.example.vision;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobListOption;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.vision.v1.AnnotateFileResponse;
import com.google.cloud.vision.v1.AnnotateFileResponse.Builder;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.AsyncAnnotateFileRequest;
import com.google.cloud.vision.v1.AsyncAnnotateFileResponse;
import com.google.cloud.vision.v1.AsyncBatchAnnotateFilesResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Block;
import com.google.cloud.vision.v1.ColorInfo;
import com.google.cloud.vision.v1.CropHint;
import com.google.cloud.vision.v1.CropHintsAnnotation;
import com.google.cloud.vision.v1.DominantColorsAnnotation;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.GcsDestination;
import com.google.cloud.vision.v1.GcsSource;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageContext;
import com.google.cloud.vision.v1.ImageSource;
import com.google.cloud.vision.v1.InputConfig;
import com.google.cloud.vision.v1.LocalizedObjectAnnotation;
import com.google.cloud.vision.v1.LocationInfo;
import com.google.cloud.vision.v1.OperationMetadata;
import com.google.cloud.vision.v1.OutputConfig;
import com.google.cloud.vision.v1.Page;
import com.google.cloud.vision.v1.Paragraph;
import com.google.cloud.vision.v1.SafeSearchAnnotation;
import com.google.cloud.vision.v1.Symbol;
import com.google.cloud.vision.v1.TextAnnotation;
import com.google.cloud.vision.v1.WebDetection;
import com.google.cloud.vision.v1.WebDetection.WebEntity;
import com.google.cloud.vision.v1.WebDetection.WebImage;
import com.google.cloud.vision.v1.WebDetection.WebLabel;
import com.google.cloud.vision.v1.WebDetection.WebPage;
import com.google.cloud.vision.v1.WebDetectionParams;
import com.google.cloud.vision.v1.Word;

import com.google.protobuf.ByteString;

import com.google.protobuf.util.JsonFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.*;

public class ImgToString{

    private String filePathway = new String();
    private String unformattedCode = new String();
    private String formattedCode = new String();

    // Constructor
    public ImgToString(String filePathway){
	this.filePathway = filePathway;
	unformattedCode = "";
	formattedCode = "";
    }

    // Getters
    public String getFilePathway(){ return filePathway; }
    public String getUnformatted(){ return unformattedCode; }
    public String getFormatted(){ return formattedCode; }


    // Functions
   /*
   * Performs document text detection on a local image file.
   *
   * @param filePath The path to the local file to detect document text on.
   * @param out A {@link PrintStream} to write the results to.
   * @throws Exception on errors while closing the client.
   * @throws IOException on Input/Output errors.
   */
  // [START vision_fulltext_detection]
  public static void translate(String filePath, PrintStream out) throws Exception,
       IOException {
    List<AnnotateImageRequest> requests = new ArrayList<>();

    ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

    Image img = Image.newBuilder().setContent(imgBytes).build();
    Feature feat = Feature.newBuilder().setType(Type.DOCUMENT_TEXT_DETECTION).build();
    AnnotateImageRequest request =
        AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
    requests.add(request);

    try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
      BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
      List<AnnotateImageResponse> responses = response.getResponsesList();
      client.close();

      for (AnnotateImageResponse res : responses) {
        if (res.hasError()) {
          out.printf("Error: %s\n", res.getError().getMessage());
          return;
        }

        // For full list of available annotations, see http://g.co/cloud/vision/docs
        TextAnnotation annotation = res.getFullTextAnnotation();
        for (Page page: annotation.getPagesList()) {
          String pageText = "";
          for (Block block : page.getBlocksList()) {
            String blockText = "";
            for (Paragraph para : block.getParagraphsList()) {
              String paraText = "";
              for (Word word: para.getWordsList()) {
                String wordText = "";
                for (Symbol symbol: word.getSymbolsList()) {
                  wordText = wordText + symbol.getText();
                  out.format("Symbol text: %s (confidence: %f)\n", symbol.getText(),
                      symbol.getConfidence());
                }
                out.format("Word text: %s (confidence: %f)\n\n", wordText, word.getConfidence());
                paraText = String.format("%s %s", paraText, wordText);
              }
              // Output Example using Paragraph:
              out.println("\nParagraph: \n" + paraText);
              out.format("Paragraph Confidence: %f\n", para.getConfidence());
              blockText = blockText + paraText;
            }
            pageText = pageText + blockText;
          }
        }
        out.println("\nComplete annotation:");
        out.println(annotation.getText());
      }
    }
  }
	
    public void format(String t){
	//Scanner s = new Scanner(System.in);
	//System.out.print("Enter a one liner: ");
	//String t = s.nextLine();
	//t+= ' ';

	// t is now the entire string of unformatted code

	int tabCounter = 0;
	boolean comment = false;
	boolean[] forloop;
	forloop = new boolean[2];
	forloop[0] = false;
	forloop[1] = false;
	for(int j = 0; j <t.length(); j++){
		char ch = t.charAt(j);
		// for loops
		if(ch == 'f' && t.charAt(j+1) == 'o' && t.charAt(j+2) == 'r' && (t.charAt(j+3) == '('
		|| t.charAt(j+3) == ' ')){
			forloop[0] = true;
			forloop[1] = true;
			formattedCode+="f";
		}
		// one line comments
		else if(ch == '/' && t.charAt(j+1) == '/'){
		    comment = true;
		    formattedCode+="/";
		}
		else if(ch == '\\' && t.charAt(j+1) == 'n'){
		    if (comment){
			formattedCode+='\n';
			for(int i = 0; i < tabCounter; i++){
			    formattedCode+="\t";
			}
		    }
		    j++;
		}
		else if(ch == ' '){
		    if (!(t.charAt(j-1) == ';') && !(t.charAt(j-2) == '\\' && t.charAt(j-1) == 'n')){
			formattedCode+=" ";
		    }
		}
		// semi colons
		else if(ch == ';'){
			if(forloop[0] == true){
				formattedCode+=ch;
				forloop[0] = false;
			}
			else if(forloop[1] == true){
				formattedCode+=ch;
				forloop[1] = false;
			}
			else{
				formattedCode+=ch;
				formattedCode+="\n";
				if (t.charAt(j+1) != '}'){
				    for(int i = 0; i < tabCounter; i++){
					formattedCode+="\t";
				    }
				}
				else {
				    for(int i=0; i < tabCounter-1; i++){
					formattedCode+="\t";
				    }
				}
			}
		}
		else if(ch == '{'){
			tabCounter++;
			formattedCode+="{";
			formattedCode+="\n";
			for(int i = 0; i < tabCounter; i++){
				formattedCode+="\t";
			}
		}
		else if(ch == '}'){
		    /*
			if(t.charAt(j-1) != '}' && t.charAt(j-1) != ';' && t.charAt(j-1) != ' '){
			    System.out.println();
			}
		    */
			tabCounter--;
			if(t.charAt(j-1) == '}'){
				for(int i = 0; i < tabCounter; i++){
					formattedCode+="\t";
				}
			}
			formattedCode+="}";
			if(t.charAt(j+1) != '}'){
				for(int i = 0; i < tabCounter; i++){
					formattedCode+="\t";
				}
			}
		}
		else{
			formattedCode+=ch;
		}
	}

	//System.out.println();

    }

    public void exportAs(String outfile) throws IOException {
	// outfile is the name of the file to be downloaded from user

	FileOutputStream f = null;

	try{
	    f = new FileOutputStream(outfile);

	    for(char ch: formattedCode.toCharArray()){
		f.write(ch);
	    }
	} finally {
	    if (f != null){
		f.close();
	    }
	}
    }
    
}
