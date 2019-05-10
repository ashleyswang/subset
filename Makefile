CLASSPATH = Tess4J/dist/tess4j-3.4.8.jar:Tess4J/lib/*:.

classes: Format.class ImgToFormattedString.class ImgToString.class TextDetection.class Server.class subSet.class

run: classes eng.traineddata
	LC_ALL=C java -cp "$(CLASSPATH)" -Djava.awt.headless=true subSet

server: classes eng.traineddata
	LC_ALL=C java -cp "$(CLASSPATH)" -Djava.awt.headless=true Server

eng.traineddata:
	curl https://raw.githubusercontent.com/tesseract-ocr/tessdata/4.00/eng.traineddata > $@

%.class: %.java
	javac -cp "$(CLASSPATH)" $<

clean:
	rm -f *.class *.java~
