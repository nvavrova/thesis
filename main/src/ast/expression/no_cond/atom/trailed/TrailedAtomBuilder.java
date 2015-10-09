package ast.expression.no_cond.atom.trailed;

import ast.expression.no_cond.atom.Atom;
import ast.expression.no_cond.atom.Identifier;
import ast.expression.no_cond.trailer.ArgList;
import ast.expression.no_cond.trailer.SubscriptSliceList;
import ast.expression.no_cond.trailer.Trailer;
import ast.expression.no_cond.trailer.TrailerVisitor;

import java.util.List;

/**
 * Created by Nik on 07-07-2015
 */
public class TrailedAtomBuilder {

	private TrailedAtom result;

	private TrailedAtomBuilder(Integer locInfo, Atom base, List<Trailer> trailers) {
		for (Trailer trailer : trailers) {
			base = this.attachTrailer(locInfo, base, trailer);
		}
		this.result = this.transformRecursively((TrailedAtom) base);
	}

	public static Atom attachTrailers(Integer locInfo, Atom base, List<Trailer> trailers) {
		if (trailers.size() == 0) {
			return base;
		}

		TrailedAtomBuilder tab = new TrailedAtomBuilder(locInfo, base, trailers);
		return tab.getResult();
	}


	private TrailedAtom attachTrailer(Integer locInfo, Atom base, Trailer trailer) {
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

			List<Identifier> attributes = attrRef.getAttributes();
			Identifier lastId = attributes.remove(attributes.size() - 1);
			Call newCall = new Call(target.getLocInfo(), lastId, target.getArgs());

			Atom base = attributes.size() == 0 ? attrRef.getBase() : attrRef;
			return new DirectCall(target.getLocInfo(), base, newCall);
		}
		return target;
	}


	private static class TABVisitor implements TrailerVisitor<TrailedAtom> {

		private final Integer locInfo;
		private final Atom base;

		public static TrailedAtom buildTrailedAtom(Integer locInfo, Atom base, Trailer trailer) {
			TABVisitor tabVisitor = new TABVisitor(locInfo, base);
			return trailer.accept(tabVisitor);
		}

		private TABVisitor(Integer locInfo, Atom base) {
			this.locInfo = locInfo;
			this.base = base;
		}

		@Override
		public TrailedAtom visit(Identifier n) {
			if (this.base instanceof AttributeRef) {
				return new AttributeRef(this.locInfo, (AttributeRef) this.base, n);
			}
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
