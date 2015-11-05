package model;

/**
 * Created by Nik on 05-11-2015
 */
public interface ContentContainerVisitor<T> {
	T visit(Module m);
	T visit(Class m);
	T visit(Subroutine m);
}
