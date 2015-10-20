package main;

import process.NonVersionedProcessor;
import process.Processor;
import process.VersionedProcessor;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

//		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("out")));
//		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("err")));
//		System.setOut(out);
//		System.setErr(err);

		Processor processor = args.length == 0 ? new VersionedProcessor(false) : new NonVersionedProcessor(args[0]);
		processor.process();
	}

}
