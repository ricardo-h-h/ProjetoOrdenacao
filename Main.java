import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String[] fileTypes = {"aleatorio", "crescente", "decrescente"};
        int[] sizes = {100, 1000, 10000};
        String dataFolder = "conjuntosDeDados/";

        System.out.println("Iniciando a análise de algoritmos de ordenação...");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("%-25s %-15s %-20s %-20s %-20s\n",
                "Conjunto de Dados", "Tamanho", "Bubble Sort (ms)", "Insertion Sort (ms)", "Quick Sort (ms)");
        System.out.println("-------------------------------------------------------------------------------------------");

        for (String type : fileTypes) {
            for (int size : sizes) {
                String fileName = String.format("%s_%d.csv", type, size);
                String filePath = dataFolder + fileName;

                // Lê os dados do arquivo para cada algoritmo, garantindo que o array original não seja modificado
                int[] dataForBubble = CsvReader.readCsv(filePath);
                int[] dataForInsertion = Arrays.copyOf(dataForBubble, dataForBubble.length);
                int[] dataForQuick = Arrays.copyOf(dataForBubble, dataForBubble.length);

                // Medição do tempo para cada algoritmo
                long bubbleTime = measureTime(() -> Sorters.bubbleSort(dataForBubble));
                long insertionTime = measureTime(() -> Sorters.insertionSort(dataForInsertion));
                long quickTime = measureTime(() -> Sorters.quickSort(dataForQuick, 0, dataForQuick.length - 1));

                System.out.printf("%-25s %-15d %-20d %-20d %-20d\n",
                        type, size, bubbleTime, insertionTime, quickTime);
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Análise concluída.");
    }

    // Interface funcional para passar os métodos de ordenação como lambda
    @FunctionalInterface
    interface SortingFunction {
        void sort();
    }

    // Método para medir o tempo de execução em milissegundos
    private static long measureTime(SortingFunction sortingFunction) {
        long startTime = System.nanoTime();
        sortingFunction.sort();
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; // Converte para milissegundos
    }
}