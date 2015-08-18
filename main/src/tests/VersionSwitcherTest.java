package tests;

import main.VersionSwitcher;
import model.Class;
import model.Module;
import model.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nik on 05-08-2015
 */
public class VersionSwitcherTest {

	private static String FIRST_COMMIT_SHA = "9f8b0bbde687e9be6e98ab1cf3c86b70a68f2a0d";
	private static String SECOND_COMMIT_SHA = "da90c30cb322c709be447c6cd6e5ad01347e5716";
	private static String THIRD_COMMIT_SHA = "2a74af043df5cd37c95f5d752cbca254ead192d8";

	private File projectFolder;
	private VersionSwitcher versionSwitcher;

	@Before
	public void setUp() throws Exception {
		this.projectFolder = new File("version_switch");
		this.versionSwitcher = new VersionSwitcher(this.projectFolder);
	}

	@After
	public void tearDown() throws Exception {
		try {
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void modulePresence() throws Exception {
		Project project = this.versionSwitcher.getFirstProjectVersion();
		Map<String, Module> moduleMap = project.getModules().stream()
				.collect(Collectors.toMap(k -> k.getFilePath(), v -> v));

		assert (moduleMap.containsKey(this.getFullFilePath("constant")));
		assert (moduleMap.containsKey(this.getFullFilePath("removed")));
		assert (!moduleMap.containsKey((this.getFullFilePath("added"))));
		assert (!moduleMap.containsKey((this.getFullFilePath("modified"))));

		assert (!this.versionSwitcher.isAtLatestVersion());

		project = this.versionSwitcher.getNextProjectVersion();
		moduleMap = project.getModules().stream()
				.collect(Collectors.toMap(k -> k.getFilePath(), v -> v));

		assert (moduleMap.containsKey(this.getFullFilePath("constant")));
		assert (!moduleMap.containsKey((this.getFullFilePath("removed"))));
		assert (moduleMap.containsKey((this.getFullFilePath("added"))));
		assert (moduleMap.containsKey((this.getFullFilePath("modified"))));

		project = this.versionSwitcher.getNextProjectVersion();
		moduleMap = project.getModules().stream()
				.collect(Collectors.toMap(k -> k.getFilePath(), v -> v));

		assert (moduleMap.containsKey(this.getFullFilePath("constant")));
		assert (!moduleMap.containsKey((this.getFullFilePath("removed"))));
		assert (moduleMap.containsKey((this.getFullFilePath("added"))));
		assert (moduleMap.containsKey((this.getFullFilePath("modified"))));
	}

	@Test
	public void checkCurrentCommit() throws Exception {
		Project project = this.versionSwitcher.getFirstProjectVersion();
		assert (project.getVersion().equals(FIRST_COMMIT_SHA));

		project = this.versionSwitcher.getNextProjectVersion();
		assert (project.getVersion().equals(SECOND_COMMIT_SHA));

		project = this.versionSwitcher.getNextProjectVersion();
		assert (project.getVersion().equals(THIRD_COMMIT_SHA));
	}

	@Test
	public void linking() throws Exception {
		this.versionSwitcher.getFirstProjectVersion();

		Project project = this.versionSwitcher.getNextProjectVersion();
		Map<String, Module> moduleMap = project.getModules().stream()
				.collect(Collectors.toMap(k -> k.getFilePath(), v -> v));

		Module added = moduleMap.get(this.getFullFilePath("added"));
		Map<String, Class> addedClasses = this.getClasses(added);
		Class addedCls = addedClasses.get("AddedCls");

		Module modified = moduleMap.get(this.getFullFilePath("modified"));
		Map<String, Class> modifiedClasses = this.getClasses(modified);
		Class modCls = modifiedClasses.get("ModifiedCls");

		assert (addedCls.getDependencies().contains(modCls));

		project = this.versionSwitcher.getNextProjectVersion();
		moduleMap = project.getModules().stream()
				.collect(Collectors.toMap(k -> k.getFilePath(), v -> v));

		added = moduleMap.get(this.getFullFilePath("added"));
		addedClasses = this.getClasses(added);
		addedCls = addedClasses.get("AddedCls");

		modified = moduleMap.get(this.getFullFilePath("modified"));
		modifiedClasses = this.getClasses(modified);
		modCls = modifiedClasses.get("ModifiedCls2");

		assert (addedCls.getDependencies().contains(modCls));
	}

	private String getFullFilePath(String fileName) {
		return this.projectFolder.getAbsolutePath() + "\\" + fileName + ".py";
	}

	private Map<String, Class> getClasses(Module module) {
		return module.getClasses().stream().collect(Collectors.toMap(k -> k.getName(), v -> v));
	}

}
