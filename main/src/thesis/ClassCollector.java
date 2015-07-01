package thesis;

import ast.*;
import ast.arg.Kwarg;
import ast.arg.SimpleArg;
import ast.expression.*;
import ast.expression.arithmetic.Arithmetic;
import ast.expression.atom.*;
import ast.expression.atom.Float;
import ast.expression.bitwise.And;
import ast.expression.bitwise.Or;
import ast.expression.bitwise.Xor;
import ast.expression.comprehension.CompFor;
import ast.expression.comprehension.CompIf;
import ast.expression.comprehension.CondComprehension;
import ast.expression.comprehension.EnumComprehension;
import ast.expression.logical.Not;
import ast.expression.primary.ArgList;
import ast.expression.primary.SliceBound;
import ast.expression.primary.SubscriptIndex;
import ast.expression.primary.SubscriptList;
import ast.expression.unary.Invert;
import ast.expression.unary.Minus;
import ast.expression.unary.Plus;
import ast.param.Param;
import ast.param.Params;
import ast.statement.compound.*;
import ast.statement.flow.*;
import ast.statement.simple.*;

import java.util.Collection;

/**
 * Created by Nik on 17-05-2015
 */
public class ClassCollector {

	private final ClassVisitor classVisitor;

	public ClassCollector(Collection<Py3Node> trees) {
		this.classVisitor = new ClassVisitor(trees);
	}

	public Classes getClasses() {
		return this.classVisitor.getClasses();
	}


	private class ClassVisitor extends DefaultVisitor<Void> {

		private final Classes pyClasses;
		private Class currentClass;

		public ClassVisitor(Collection<Py3Node> trees) {
			super();
			this.pyClasses = new Classes();
			for (Py3Node n : trees) {
				n.accept(this);
			}
		}

		public Classes getClasses() {
			return this.pyClasses;
		}

		@Override
		public Void visit(Decorator n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(DottedImport n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(DottedPath n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Module n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(SimplePath n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Suite n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Kwarg n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(SimpleArg n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Param n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Params n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Assert n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(AssignExpr n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(AssignYield n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Delete n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(ExprList n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Global n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Import n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(ImportFrom n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Nonlocal n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Pass n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Break n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Continue n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Raise n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Return n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(YieldValues n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(YieldFrom n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(ClassDef n) {
			this.currentClass = new Class(n.getLocSpan(), n.isController());
			this.visitChildren(n);
			this.pyClasses.add(this.currentClass);
			this.currentClass = null;
			return null;
		}

		@Override
		public Void visit(Except n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(For n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Function n) {
			if (this.amIInClass()) {
				if (n.isAccessor()) {
					this.currentClass.addAccessor(n.getNameString(), n.getLocSpan());
				}
				else {
					this.currentClass.addMethod(n.getNameString(), n.getLocSpan(), n.isController());
				}
			}
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(If n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Try n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(While n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(With n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(WithItem n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Comparison n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Conditional n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Lambda n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(LambdaNoCond n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Power n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Shift n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Arithmetic n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Bool n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(DictMaker n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Ellipsis n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Float n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Identifier n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Imaginary n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Int n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Literal n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(None n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(SetMaker n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Str n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(And n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Or n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Xor n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(CompFor n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(CompIf n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(CondComprehension n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(EnumComprehension n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(ast.expression.logical.And n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Not n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(ast.expression.logical.Or n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(ArgList n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(SliceBound n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(SubscriptIndex n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(SubscriptList n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Invert n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Minus n) {
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Plus n) {
			this.visitChildren(n);
			return null;
		}

		private Boolean amIInClass() {
			return this.currentClass != null;
		}
	}
}
