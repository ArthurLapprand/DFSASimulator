package Plot;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ScriptGenerator {
	
	LinkedBlockingQueue<FileName> filenames;
	
	public ScriptGenerator (LinkedBlockingQueue<FileName> filenames){
		this.filenames = filenames;
	}
	
	public void generateScript() throws InterruptedException {
		List<String> lines = new ArrayList<String>();
		String filename = "script.plt";
		Path file = Paths.get("output/" + filename);
		
		// GERA AS LINHAS DO SCRIPT
		lines.add("set xlabel \"Número de etiquetas\"");
		lines.add("set ylabel \"Número de slots\"");
		lines.add("set monochrome");
		lines.add("set key vertical top left");
		lines.add("set grid");
		lines.add("set pointsize 2");
		String plotString = ("plot ");		
		for (Iterator<FileName> iterator = this.filenames.iterator(); iterator.hasNext();) {
			FileName names = (FileName) iterator.next();
			plotString += "\"" + names.getFilename() + "\" u 1:2 t '" + names.getEstimator() + "' w lines, " + "\\";
		}
		lines.add(plotString);		
		
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
