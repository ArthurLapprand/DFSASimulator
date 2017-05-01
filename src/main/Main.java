package main;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import Plot.FileName;
import Plot.ScriptGenerator;
import Simulador.DFSASimulator;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		LinkedBlockingQueue<FileName> filenames = new LinkedBlockingQueue<FileName>();
		Thread simulator = new Thread(new DFSASimulator(64, 100, 100, 10, 2000, "LowerBound", filenames));
		System.out.println("Iniciando...");
		simulator.start();
		simulator.join();
		System.out.println("Terminou.");
		ScriptGenerator sg = new ScriptGenerator(filenames);
		sg.generateScript();
	}

}
