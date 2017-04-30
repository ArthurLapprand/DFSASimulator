package main;

import java.util.concurrent.LinkedBlockingQueue;

import Plot.FileName;
import Plot.ScriptGenerator;
import Simulador.DFSASimulator;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		LinkedBlockingQueue<FileName> filenames = new LinkedBlockingQueue<FileName>();
		Thread simulator = new Thread(new DFSASimulator(64, 100, 100, 10, 10000, "LowerBound", filenames));
		simulator.start();
		simulator.join();
		ScriptGenerator sg = new ScriptGenerator(filenames);
		sg.generateScript();
	}

}
