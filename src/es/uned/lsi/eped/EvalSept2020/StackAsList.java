package es.uned.lsi.eped.EvalSept2020;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.ListIF;
import es.uned.lsi.eped.DataStructures.Stack;
import es.uned.lsi.eped.DataStructures.StackIF;

public class StackAsList<E> implements ListIF<E> {

	protected StackIF<E> stack;

	public StackAsList() {
		stack = new Stack<E>();
	}

	public IteratorIF<E> iterator() {
		return stack.iterator();
	}

	public int size() {
		return stack.size();
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public boolean contains(E e) {
		return stack.contains(e);
	}

	public void clear() {
		stack.clear();
	}

	public E get(int pos) {
		E top = stack.getTop();
		if ( pos == 1 ) {
			return top;
		} else {
			stack.pop();
			E result = get(pos-1);
			stack.push(top);
			return result;
		}
	}

	public void set(int pos, E e) {
		if ( pos == 1 ) {
			stack.pop();
			stack.push(e);
		} else {
			E top = stack.getTop();
			stack.pop();
			set(pos-1,e);
			stack.push(top);
		}
	}

	public void insert(int pos, E e) {
		if ( pos == 1 ) {
			stack.push(e);
		} else {
			E top = stack.getTop();
			stack.pop();
			insert(pos-1,e);
			stack.push(top);
		}
	}

	public void remove(int pos) {
		if ( pos == 1 ) {
			stack.pop();
		} else {
			E top = stack.getTop();
			stack.pop();
			remove(pos-1);
			stack.push(top);
		}
	}

}
