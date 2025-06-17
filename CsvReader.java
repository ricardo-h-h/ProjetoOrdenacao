import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static int[] readCsv(String filePath) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Remove o cabeçalho se existir (ex: "valor")
                if (!line.trim().equalsIgnoreCase("Value")) {
                    numbers.add(Integer.parseInt(line.trim()));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler o arquivo: " + filePath);
            e.printStackTrace();
        }

        // Converte List<Integer> para int[]
        int[] array = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            array[i] = numbers.get(i);
        }
        return array;
    }
}