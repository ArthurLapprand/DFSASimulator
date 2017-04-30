package Plot;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ResultGenerator {
	
	String estimador;
	private Result[] results;
	int qTD_INICIAL_SLOTS;
	int qTD_INICIAL_TAGS;
	int qTD_PASSOS;
	int qTD_SIM_PASSO;
	
	public ResultGenerator (String estimador, Result[] rslts,
			int qTD_INICIAL_SLOTS,
			int qTD_INICIAL_TAGS,
			int qTD_PASSOS,
			int qTD_SIM_PASSO) {
		this.estimador = estimador;
		this.results = rslts;
		this.qTD_INICIAL_SLOTS = qTD_INICIAL_SLOTS;
		this.qTD_INICIAL_TAGS = qTD_INICIAL_TAGS;
		this.qTD_PASSOS = qTD_PASSOS;
		this.qTD_SIM_PASSO = qTD_SIM_PASSO;
	}
	
	public void outputResults(LinkedBlockingQueue<FileName> filenames) {
		List<String> lines = new ArrayList<String>();
		String filename = this.estimador + "-" +
				this.qTD_INICIAL_SLOTS + "-" +
				this.qTD_INICIAL_TAGS + "-" +
				this.qTD_PASSOS + "-" +
				this.qTD_SIM_PASSO +
				".txt";
		FileName fn = new FileName(this.estimador, filename);
		filenames.add(fn);
		Path file = Paths.get("output/" + filename);
		
		// GERA AS LINHAS DO ARQUIVO
		for (int i = 0; i < results.length; i++) {
			lines.add(results[i].getQTD_TAGS() + " " + results[i].getTotalSlots());
		}
		
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
