package vc;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nik on 29-07-2015
 */
public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		GitHandler gitHandler = new GitHandler(new File("data/salt"));


//		File folder = new File(args[0]);
//		List<File> projectFolders = FileHelper.getSubfolders(folder);
//		for (File projectFolder : projectFolders) {
//			GitHandler gitHandler = new GitHandler(projectFolder);
//			gitHandler.exec("rev-parse origin").forEach(System.out::println);
//		}




//		Runtime rt = Runtime.getRuntime();
//		String cmd = "ls";
//		Process proc = rt.exec(cmd);
//
//		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream());
//		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream());
//
//		errorGobbler.start();
//		outputGobbler.start();
//
//		int exitVal = proc.waitFor();
//		System.out.println("ExitValue: " + exitVal);
	}
}
