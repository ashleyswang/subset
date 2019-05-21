import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.io.BufferedReader; // for inputsteam to string
import java.nio.charset.StandardCharsets;

import static spark.Spark.*;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class SparkServer {

    public static void main(String[] args){
        port(8000);
        get("/hello", (req, res) -> "Hello World");

        post("/ocr", (req, res) -> {
            String response;
            Files.write(Paths.get("temp.png"), req.bodyAsBytes());
            Tesseract tesseract = new Tesseract();

            try {
                response = tesseract.doOCR(new File("temp.png"));
            } catch(TesseractException e) {
                response = e.toString();
            }

            res.header("Access-Control-Allow-Origin", "*");
            return response;
        });

        post("/format", (req, res) -> {
            Format formatter = new Format();
            res.header("Access-Control-Allow-Origin", "*");
            return formatter.formatText(req.body());
        });
    }
    
}
