CLASSPATH = target/dependency/*:.

classes: Format.class ImgToFormattedString.class ImgToString.class TextDetection.class Server.class subSet.class SparkServer.class Compiler.class

run: classes eng.traineddata
	LC_ALL=C java -cp "$(CLASSPATH)" -Djava.awt.headless=true subSet

server: classes eng.traineddata
	LC_ALL=C java -cp "$(CLASSPATH)" -Djava.awt.headless=true Server

spark: classes eng.traineddata
	LC_ALL=C java -cp "$(CLASSPATH)" -Djava.awt.headless=true SparkServer

eng.traineddata:
	curl https://raw.githubusercontent.com/tesseract-ocr/tessdata/4.00/eng.traineddata > $@

dependencies:
	mvn dependency:copy-dependencies

%.class: %.java
	javac -cp "$(CLASSPATH)" $<

clean:
	rm -f *.class *.java~
