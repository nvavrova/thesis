package tests;

import org.junit.Test;
import ast.LocCounter;

import java.io.File;

/**
 * Created by Nik on 27-07-2015
 */
public class LocCounterTest {

	@Test
	public void countLoc() {
		File f = new File("count_loc.py");

		LocCounter c = new LocCounter(f.getAbsolutePath());
		assert (c.count() == 15);
		assert (c.count(6, 8) == 2);
		assert (c.count(4, 8) == 4);
		assert (c.count(16, 23) == 5);
		assert (c.count(25, 26) == 1);
	}
}