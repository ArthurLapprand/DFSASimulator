package Plot;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
	
	public void generateScript() throws InterruptedException, IOException {
		List<String> lines = new ArrayList<String>();
		String filename = "script.plt";
		Path file = Paths.get("output/" + filename);
		
		// GERA AS LINHAS DO SCRIPT
		lines.add("set xlabel \"Número de etiquetas\"");
		lines.add("set ylabel \"Número de slots\"");
		//lines.add("set monochrome");
		lines.add("set key vertical top left");
		lines.add("set grid");
		lines.add("set pointsize 2");
		String plotString = ("plot ");		
		for (Iterator<FileName> iterator = this.filenames.iterator(); iterator.hasNext();) {
			FileName names = (FileName) iterator.next();
			plotString += "\"" + names.getFilename() + "\" u 1:2 t '" + names.getEstimator() + "' w linespoints, " + "\\";
		}
		lines.add(plotString);		
		
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("gnuplot \"" + file.toAbsolutePath() + "\"");
		ProcessBuilder builder = new ProcessBuilder("gnuplot " + file.toAbsolutePath() + "");
//		ProcessBuilder builder = new ProcessBuilder("gnuplot " + System.getProperty("user.dir") + "\\" + file.toString());
		builder.redirectErrorStream(true);
		Process process = builder.start();
		
//		String[] command = {"cmd"};
//		    Process p = null;
//			try {
//				p = Runtime.getRuntime().exec(command);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		    new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
//		    new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
//		    PrintWriter stdin = new PrintWriter(p.getOutputStream());
//		    stdin.println("cd " + System.getProperty("user.dir") + "/output");
//		    stdin.println("gnuplot -p " + filename);
//		    stdin.close();
//		    int returnCode = p.waitFor();
//		    System.out.println("Return code = " + returnCode);
	}
}
