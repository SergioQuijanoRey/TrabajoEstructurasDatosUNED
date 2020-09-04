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

	//@Pre: los indices i, j deben señalar posiciones validas de la lista, es decir
    //      1 <= i, j <= size(L)
    //@Pre: i != j
	public static <E> void swap(ListIF<E> L,int i, int j) {
        // Comprobacion de seguridad
        if(i < 1 || i > L.size()){
            System.out.println("ERROR! En el metodo swap. No se hace nada");
            System.out.println("\tEl primer indice no es valido");
            return; // Para salir de la funcion por un error
        }
        if (j < 1 || j > L.size()){
            System.out.println("ERROR! En el metodo swap. No se hace nada");
            System.out.println("\tEl segundo indice no es valido");
            return; // Para salir de la funcion por un error
        }
        if(i == j){
            System.out.println("ERROR! En el metodo swap. No se hace nada");
            System.out.println("\tLos indices deben ser distintos");
            return; // Para salir de la funcion por un error
        }

        // Hago el cambio con los metodos que ofrece la interfaz
        // Tomo los dos elementos con un get y los cambio con el set
        E first = L.get(i);
        E second = L.get(j);
        L.set(i, second);
        L.set(j, first);
	}


	//@Pre: L debe ser una lista con al menos los caracteres 'R', 'B', 'A'
    //@Pre: L no debe tener elementos que no sean los anteriores caractees
	public static void dutchFlag(ListIF<Character> L) {
        // Con estos tres pivotes, tengo controlados los limites de los tres grupos de letras
        int firstPivot = 1;                 // Superior del grupo de 'R'
        int secondPivot = 1;                // Superior del grupo de 'B' => grupo del medio
        int thirdPivot = L.size() + 1;      // Inferior del grupo de 'A'

        // Los elementos del grupo del medio son los que estan entre el segundo y tercer pivote
        // Estos son los elementos que no han sido ordenados, asi que iteramos hasta agotar
        // este grupo de elementos no ordenados
        while(secondPivot < thirdPivot){
            // Tomamos el primer elemento del grupo que no ha sido ordenado
            switch(L.get(secondPivot)){
                // Tiene que ir al grupo de las R
                // Asi que lo movemos hacia la izquierda
                case 'R':
                    swap(L, firstPivot, secondPivot);
                    firstPivot = firstPivot + 1;
                    secondPivot = secondPivot + 1;
                    break;

                // Tiene que ir al grupo de las R
                // Asi que lo movemos a la derecha
                case 'A':
                    thirdPivot = thirdPivot - 1;
                    swap(L, secondPivot, thirdPivot);
                    break;

                // Esta bien colocado
                // Asi que avanzamos al siguiente elemento no ordenado
                default:
                    secondPivot = secondPivot + 1;

            }
        }
	}


	public static void main(String [] args) {
		int n = 10;
		ListIF<Character> L = generateDutchFlagInput(n);
		printList(L);
		dutchFlag(L);
		printList(L);
	}


}
