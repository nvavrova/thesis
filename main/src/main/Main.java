package main;

import process.NonVersionedProcessor;
import process.Processor;
import process.VersionedProcessor;
import util.FileHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Main {

	public static void main(String[] args) throws IOException {

		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("out")));
		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("err")));
		System.setOut(out);
		System.setErr(err);

		Processor processor = args.length == 0 ? new VersionedProcessor(false) : new NonVersionedProcessor(args[0]);
		processor.process();
	}

}
