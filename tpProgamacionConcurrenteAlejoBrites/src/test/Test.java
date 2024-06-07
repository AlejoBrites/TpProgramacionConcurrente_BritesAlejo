package test;

import java.util.Arrays;

import datos.Funciones;
import datos.Quicksort;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// hago diferentes casos de tamaño para los arrays
		int caso1 = 1000000;
		int caso2 = 100000;
		int caso3 = 10000;
		int caso4 = 1000;
		int caso5 = 100;
		int caso6 = 10;

		int[] array;
		double tiempoInicial = 0;
		double tiempoFinal = 0;
		array = Funciones.generarArrayAleatorio(caso1, -1000, 10000);

		System.out.println(" Secuencial: ");
		// mostramos sin ordenar
		// Funciones.mostrarArray(array);
		int arrayCopia1[] = Arrays.copyOf(array, array.length);

		tiempoInicial = System.nanoTime();
		Quicksort.quickSortSecuencial(arrayCopia1, 0, arrayCopia1.length - 1);
		tiempoFinal = System.nanoTime() - tiempoInicial;
		// mostramos ordenado
		// Funciones.mostrarArray(arrayCopia1);
		System.out.println("\n El algoritmo secuencial tardo " + tiempoFinal / 1000 + " microsegundos en ordenarse");

		System.out.println("\n Concurrente: ");
		// mostramos sin ordenar
		// Funciones.mostrarArray(array);
		int arrayCopia2[] = Arrays.copyOf(array, array.length);

		tiempoInicial = System.nanoTime();
		Quicksort.concurrenteQuicksort(arrayCopia2, 0, arrayCopia2.length - 1);
		tiempoFinal = System.nanoTime() - tiempoInicial;
		// mostramos ordenado
		// Funciones.mostrarArray(arrayCopia2);
		System.out.println("\n El algoritmo concurrente tardo " + tiempoFinal / 1000 + " microsegundos en ordenarse");

	}

}
