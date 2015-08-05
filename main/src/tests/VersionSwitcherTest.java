package tests;

import main.VersionSwitcher;
import model.Class;
import model.Module;
import model.Project;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nik on 05-08-2015
 */
public class VersionSwitcherTest {

	@Test
	public void modulePresence() throws IOException, InterruptedException {
		File projectFolder = new File("version_switch");
		VersionSwitcher versionSwitcher = new VersionSwitcher(projectFolder);

		Project project = versionSwitcher.getFirstProjectVersion();
		Map<String, Module> moduleMap = project.getModules().stream()
				.collect(Collectors.toMap(k -> k.getFilePath(), v -> v));

		assert (moduleMap.containsKey(projectFolder.getAbsolutePath() + "\\constant.py"));
		assert (moduleMap.containsKey(projectFolder.getAbsolutePath() + "\\removed.py"));
		assert (!moduleMap.containsKey((projectFolder.getAbsolutePath() + "\\added.py")));
		assert (!moduleMap.containsKey((projectFolder.getAbsolutePath() + "\\modified.py")));

		assert (!versionSwitcher.isAtLatestVersion());

		project = versionSwitcher.getNextProjectVersion();
		moduleMap = project.getModules().stream()
				.collect(Collectors.toMap(k -> k.getFilePath(), v -> v));

		assert (moduleMap.containsKey(projectFolder.getAbsolutePath() + "\\constant.py"));
		assert (!moduleMap.containsKey((projectFolder.getAbsolutePath() + "\\removed.py")));
		assert (moduleMap.containsKey((projectFolder.getAbsolutePath() + "\\added.py")));
		assert (moduleMap.containsKey((projectFolder.getAbsolutePath() + "\\modified.py")));

		project = versionSwitcher.getNextProjectVersion();
		moduleMap = project.getModules().stream()
				.collect(Collectors.toMap(k -> k.getFilePath(), v -> v));

		assert (moduleMap.containsKey(projectFolder.getAbsolutePath() + "\\constant.py"));
		assert (!moduleMap.containsKey((projectFolder.getAbsolutePath() + "\\removed.py")));
		assert (moduleMap.containsKey((projectFolder.getAbsolutePath() + "\\added.py")));
		assert (moduleMap.containsKey((projectFolder.getAbsolutePath() + "\\modified.py")));
	}

	@Test
	public void linking() throws IOException, InterruptedException {
		File projectFolder = new File("version_switch");
		VersionSwitcher versionSwitcher = new VersionSwitcher(projectFolder);
		versionSwitcher.getFirstProjectVersion();

		Project project = versionSwitcher.getNextProjectVersion();
		Map<String, Module> moduleMap = project.getModules().stream()
				.collect(Collectors.toMap(k -> k.getFilePath(), v -> v));


		Module added = moduleMap.get(projectFolder.getAbsolutePath() + "\\added.py");
		Map<String, Class> addedClasses = getClasses(added);
		Class addedCls = addedClasses.get("AddedCls");

		Module modified = moduleMap.get(projectFolder.getAbsolutePath() + "\\modified.py");
		Map<String, Class> modifiedClasses = getClasses(modified);
		Class modCls = modifiedClasses.get("ModifiedCls");

		assert (addedCls.getDependencies().contains(modCls));

		project = versionSwitcher.getNextProjectVersion();
		moduleMap = project.getModules().stream()
				.collect(Collectors.toMap(k -> k.getFilePath(), v -> v));

		added = moduleMap.get(projectFolder.getAbsolutePath() + "\\added.py");
		addedClasses = getClasses(added);
		addedCls = addedClasses.get("AddedCls");

		modified = moduleMap.get(projectFolder.getAbsolutePath() + "\\modified.py");
		modifiedClasses = getClasses(modified);
		modCls = modifiedClasses.get("ModifiedCls2");

		assert (addedCls.getDependencies().contains(modCls));
	}

	private Map<String, Class> getClasses(Module module) {
		return module.getClasses().stream().collect(Collectors.toMap(k -> k.getName(), v -> v));
	}

}
