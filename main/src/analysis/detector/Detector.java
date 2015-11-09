package analysis.detector;

import analysis.DesignDefect;
import analysis.Metrics;
import model.*;
import model.Class;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nik on 04-11-2015
 */
public abstract class Detector {

	private Map<ContentContainer, Set<DesignDefect>> defects;

	protected Metrics metrics;
	private final PreliminaryVisitor preliminaryVisitor;
	private boolean finished;

	public Detector() {
		this.preliminaryVisitor = new PreliminaryVisitor();
	}

	public void addMetrics(Metrics metrics) {
		this.metrics = metrics;
		this.finished = false;
	}

	public void process(ContentContainer contentContainer) {
		if (this.finished) {
			throw new IllegalStateException();
		}
		this.processChecked(contentContainer);
	}

	private void processChecked(ContentContainer contentContainer) {
		Boolean defective = this.preliminaryVisitor.checkForDefect(contentContainer);
		if (defective) {
			if (!this.defects.containsKey(contentContainer)) {
				this.defects.put(contentContainer, new HashSet<>());
			}
			this.defects.get(contentContainer).add(new DesignDefect());
		}
	}

	public Map<String, Set<DesignDefect>> finish() {
		Map<String, Set<DesignDefect>> result = new HashMap<>();
		for (ContentContainer cc : this.defects.keySet()) {
			for (DesignDefect designDefect : this.defects.get(cc)) {
				this.confirmAndAdd(result, cc, designDefect);
			}
		}
		return result;
	}

	private void confirmAndAdd(Map<String, Set<DesignDefect>> result, ContentContainer cc, DesignDefect designDefect) {
		Boolean confirmed = this.confirmDefect(cc.getFullPath());
		if (confirmed) {
			if (!result.containsKey("TODO")) {
				result.put("TODO", new HashSet<>());
			}
			result.get("TODO").add(designDefect);
		}
	}

	//override these where necessary
	protected Boolean isPreliminarilyDefective(Module module) {
		return false;
	}
	protected Boolean isPreliminarilyDefective(Class cls) {
		return false;
	}
	protected Boolean isPreliminarilyDefective(Subroutine subroutine) {
		return false;
	}
	protected abstract Boolean confirmDefect(String fullPath);

	private class PreliminaryVisitor implements ContentContainerVisitor<Boolean> {

		public Boolean checkForDefect(ContentContainer contentContainer) {
			return contentContainer.accept(this);
		}

		@Override
		public Boolean visit(Module m) {
			return isPreliminarilyDefective(m);
		}

		@Override
		public Boolean visit(model.Class m) {
			return isPreliminarilyDefective(m);
		}

		@Override
		public Boolean visit(Subroutine m) {
			return isPreliminarilyDefective(m);
		}
	}
}
