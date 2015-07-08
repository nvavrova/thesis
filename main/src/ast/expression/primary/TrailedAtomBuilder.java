package ast.expression.primary;

import ast.LocInfo;
import ast.expression.primary.atom.Atom;
import ast.expression.primary.atom.Identifier;
import ast.expression.primary.trailer.ArgList;
import ast.expression.primary.trailer.SubscriptSliceList;
import ast.expression.primary.trailer.Trailer;
import ast.expression.primary.trailer.TrailerVisitor;

import java.util.List;

/**
 * Created by Nik on 07-07-2015
 */
public class TrailedAtomBuilder {

	private TrailedAtom result;

	private TrailedAtomBuilder(LocInfo locInfo, Atom base, List<Trailer> trailers) {
		for (Trailer trailer : trailers) {
			base = this.attachTrailer(locInfo, base, trailer);
		}
		this.result = this.transformRecursively((TrailedAtom) base);
	}

	public static Atom attachTrailers(LocInfo locInfo, Atom base, List<Trailer> trailers) {
		if (trailers.size() == 0) {
			return base;
		}

		TrailedAtomBuilder tab = new TrailedAtomBuilder(locInfo, base, trailers);
		return tab.getResult();
	}


	private TrailedAtom attachTrailer(LocInfo locInfo, Atom base, Trailer trailer) {
		return TABVisitor.buildTrailedAtom(locInfo, base, trailer);
	}

	private TrailedAtom getResult() {
		return this.result;
	}

	//transformation - necessary so that certain function calls (e.g. self.func()) won't get counted as identifiers (variables)
	private TrailedAtom transformRecursively(TrailedAtom target) {
		target = this.transform(target);
		if (target.getBase() instanceof TrailedAtom) {
			TrailedAtom trailedBase = (TrailedAtom) target.getBase();
			target.setBase(this.transformRecursively(trailedBase));
		}
		return target;
	}

	private TrailedAtom transform(TrailedAtom target) {
		if (target instanceof Call) {
			Call call = (Call) target;
			return this.transform(call);
		}
		return target;
	}

	private TrailedAtom transform(Call target) {
		Atom targetBase = target.getBase();
		if (targetBase instanceof AttributeRef) {
			AttributeRef attrRef = (AttributeRef) targetBase;
			Call newCall = new Call(target.getLocInfo(), attrRef.getAttribute(), target.getArgs());
			return new DirectCall(target.getLocInfo(), attrRef.getBase(), newCall);
		}
		return target;
	}


	private static class TABVisitor implements TrailerVisitor<TrailedAtom> {

		private final LocInfo locInfo;
		private final Atom base;

		public static TrailedAtom buildTrailedAtom(LocInfo locInfo, Atom base, Trailer trailer) {
			TABVisitor tabVisitor = new TABVisitor(locInfo, base);
			return trailer.accept(tabVisitor);
		}

		private TABVisitor(LocInfo locInfo, Atom base) {
			this.locInfo = locInfo;
			this.base = base;
		}

		@Override
		public TrailedAtom visit(Identifier n) {
			return new AttributeRef(this.locInfo, this.base, n);
		}

		@Override
		public TrailedAtom visit(ArgList n) {
			return new Call(this.locInfo, this.base, n);
		}

		@Override
		public TrailedAtom visit(SubscriptSliceList n) {
			return new Slice(this.locInfo, this.base, n);
		}
	}

}
