package datos;

import java.util.Random;

public class Funciones {

	// funcion simple que mustra el array
	public static void mostrarArray(int[] vec) {
		System.out.println("\n----------------------------------------");
		for (int arr1 : vec) {
			System.out.print(arr1 + " | ");
		}
	}

	// funcion simple que carga el array
	public static int[] generarArrayAleatorio(int n, int min, int max) {
		// Declaramos el array
		int[] arr = new int[n];

		// Generamos los números aleatorios para el array
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			arr[i] = random.nextInt(max - min + 1) + min;
		}

		return arr;
	}

}
