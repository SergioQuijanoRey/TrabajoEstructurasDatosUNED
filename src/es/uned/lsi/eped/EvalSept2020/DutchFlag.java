package es.uned.lsi.eped.EvalSept2020;

import java.util.Random;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.ListIF;

public class DutchFlag {

	//imprime el contenido de una lista por consola
	public static <E> void printList(ListIF<E> param) {
		IteratorIF<E> it = param.iterator();
		while (it.hasNext()) {
			System.out.print(it.getNext());
			if (it.hasNext()) { System.out.print(","); }
		}
		System.out.println();
		System.out.println("--------------------------");
	}
	
	
	//@Pre: n>=3
	/*genera una lista de caracteres con logitud mínima n
	 *tal que cumple la precondición del problema de la 
	 *bandera holandesa (al menos un elemento de cada color) */

	public static ListIF<Character> generateDutchFlagInput(int n) {
		ListIF<Character> L = new List<Character>();  
		Random R = new Random();
		//bR <-> se ha insertado al menos un valor R
		boolean bR = false;
		//bB <-> se ha insertado al menos un valor B
		boolean bB = false;
		//bA <-> se ha insertado al menos un valor A
		boolean bA = false;
		//b <-> hay al menos un elemento de cada valor R, B y A
		boolean b = false;
		int cont = 0;
		//mientras la lista no tenga el tamaño mínimo n ni un elemento de cada color
		//se siguen insertando elementos
		while(!(cont>=n && b)){
			int r = R.nextInt(Integer.MAX_VALUE);
			int size = L.size();
			//modulo 3 <-> distinguir 3 posibles casos (uno por color)
			//si el módulo es 0 -> R, módulo 1 -> B, módulo 2 -> A
			if(r%3==0){
				L.insert(size+1, 'R');
				//ya hay un elemento R -> bR=true
				bR = true;
			}
			if(r%3==1){
				L.insert(size+1, 'B');
				//ya hay un elemento B -> bB=true
				bB = true;
			}
			if(r%3==2){
				L.insert(size+1, 'A');
				//ya hay un elemento A -> bA=true
				bA = true;
			}
			//se ha aumentado en 1 el número de elementos -> cont++
			cont++;
			//se comprueba si hay al menos un elemento de cada color
			b = bR&&bB&&bA;
		}
		return L;
	}
	
	//@Pre: añadir precondición 
	public static <E> void swap(ListIF<E> L,int i, int j) {
		//programar método
	}
	
	
	//@Pre: añadir precondición 
	public static void dutchFlag(ListIF<Character> L) {
		//programar método
	}
	
	
	public static void main(String [] args) {
		int n = 10;
		ListIF<Character> L = generateDutchFlagInput(n);
		printList(L);
		dutchFlag(L);
		printList(L);
	}
	
	
}
