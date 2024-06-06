package datos;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Quicksort extends Thread {
	private static final int THRESHOLD = 10; // constante que determina el tamaño mínimo del subarray que va a
												// determinar si se usa el metodo secuencial o el metodo concurrente
	private int arr[];
	private int low, high;

	public Quicksort(int[] arr, int low, int high) {
		this.arr = arr;
		this.low = low;
		this.high = high;
	}

	public void run() {

		concurrenteQuicksort(arr, low, high);

	}

	public static void concurrenteQuicksort(int[] arr, int low, int high) {
		// Crea una instancia de ForkJoinPool, que es un tipo de ExecutorService
		// diseñado para tareas recursivas de "divide y vencerás"
		ForkJoinPool pool = new ForkJoinPool();
		// Inicia la ejecución de la tarea SortTask con el array y los límites
		// especificados
		pool.invoke(new SortTask(arr, low, high));
	}

	private static class SortTask extends RecursiveAction {
		// Variables finales para almacenar el array y los índices bajos y altos
		private final int[] arr;
		private final int low, high;

		// Constructor para inicializar las variables
		SortTask(int[] arr, int low, int high) {
			this.arr = arr;
			this.low = low;
			this.high = high;
		}

		// la funcion compute define cómo se va a dividir y ejecutar de manera paralela
		// una tarea
		@Override
		protected void compute() {
			// Si el tamaño del subarray es menor que el umbral (THRESHOLD), usa el método
			// secuencial
			if (high - low < THRESHOLD) {
				quickSortSecuencial(arr, low, high);
			} else {
				// obtiene el índice del pivote
				int pi = particion(arr, low, high);
				// Divide la tarea en dos nuevas tareas SortTask y las ejecuta en paralelo
				// usando invokeAll
				invokeAll(new SortTask(arr, low, pi - 1), new SortTask(arr, pi + 1, high));
			}
		}
	}

	public static void quickSortSecuencial(int[] arr, int low, int high) {
		if (low < high) {
			// obtiene el índice del pivote
			int pi = particion(arr, low, high);
			// Ordena los subarrays de forma recursiva
			quickSortSecuencial(arr, low, pi - 1);
			quickSortSecuencial(arr, pi + 1, high);
		}
	}

	private static int particion(int[] arr, int low, int high) {
		// Selecciona el último elemento como el pivote
		int pivot = arr[high];
		// Inicializa el índice i justo antes del inicio del subarray
		int i = low - 1;
		// Itera a través del subarray
		for (int j = low; j < high; j++) {
			// Si el elemento actual es menor que el pivote, incrementa i y permuta arr[i]
			// con arr[j]
			if (arr[j] < pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		// Coloca el pivote en su posición correcta
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		// Devuelve el índice del pivote
		return i + 1;
	}

}
