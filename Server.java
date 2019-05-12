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

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Server {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/ocr", new OCRReader()); // if I see a request to ocr use ocrreader
        server.createContext("/format", new FormatString());
        server.setExecutor(null); // try taking it out after demo
        server.start();
    }

    static class OCRReader implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            InputStream in = t.getRequestBody();
            Files.copy(in, Paths.get("temp.png"), StandardCopyOption.REPLACE_EXISTING);

            String response;
            //Format formatter = new Format();
            Tesseract tesseract = new Tesseract();

            try {
                response = tesseract.doOCR(new File("temp.png"));
                //response = formatter.formatText(response);
            } catch(TesseractException e) {
                response = e.toString();
            }

            Headers headers = t.getResponseHeaders();
            headers.add("Access-Control-Allow-Origin", "*");

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class FormatString implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            InputStream in = t.getRequestBody();
            
            Convert convert = new Convert();
            String response = convert.convert(in, StandardCharsets.UTF_8);
            Format formatter = new Format();

            response = formatter.formatText(response);

            Headers headers = t.getResponseHeaders();
            headers.add("Access-Control-Allow-Origin", "*");

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
