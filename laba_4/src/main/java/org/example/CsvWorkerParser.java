package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс для парсинга CSV-файлов с информацией о работниках.
 */
public class CsvWorkerParser {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private final char delimiter;

    /**
     * Конструктор класса CsvWorkerParser.
     *
     * @param delimiter Символ-разделитель, используемый в CSV-файле.
     */
    public CsvWorkerParser(char delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Парсит работников из указанного CSV-файла.
     *
     * @param csvFile Имя CSV-файла, который нужно распарсить.
     * @return Список работников, распарсенных из CSV-файла.
     * @throws IOException Если произошла ошибка ввода-вывода.
     * @throws CsvException Если произошла ошибка при обработке CSV.
     * @throws ParseException Если произошла ошибка при парсинге даты.
     */
    public List<Worker> parseWorkers(String csvFile) throws IOException, CsvException, ParseException {
        List<Worker> workers = Collections.synchronizedList(new ArrayList<>());
        Map<String, DepartmentID> departments = new ConcurrentHashMap<>();

        try (InputStream in = getResourceAsStream(csvFile);
             CSVReader reader = createCSVReader(in)) {

            reader.iterator().forEachRemaining(line -> {
                try {
                    Worker worker = parseWorker(line, departments);
                    workers.add(worker);
                } catch (Exception e) {
                    handleParseError(line, e);
                }
            });
        }
        return workers;
    }

    /**
     * Получает InputStream для указанного CSV-файла.
     *
     * @param csvFile Имя CSV-файла.
     * @return InputStream для чтения файла.
     * @throws FileNotFoundException Если файл не найден.
     */
    private InputStream getResourceAsStream(String csvFile) throws FileNotFoundException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(csvFile);
        if (in == null) {
            throw new FileNotFoundException("CSV file not found: " + csvFile);
        }
        return in;
    }

    /**
     * Создает CSVReader для чтения данных из InputStream.
     *
     * @param in InputStream для чтения данных.
     * @return CSVReader для чтения CSV-файла.
     */
    private CSVReader createCSVReader(InputStream in) {
        return new CSVReaderBuilder(new InputStreamReader(in))
                .withSkipLines(1)
                .withCSVParser(new com.opencsv.CSVParserBuilder()
                        .withSeparator(delimiter)
                        .build())
                .build();
    }

    /**
     * Парсит строку CSV в объект Worker.
     *
     * @param csvLine Массив строк, представляющий строку CSV.
     * @param departments Карта для хранения идентификаторов отделов.
     * @return Объект Worker, созданный из данных CSV.
     * @throws ParseException Если произошла ошибка при парсинге даты.
     */
    private Worker parseWorker(String[] csvLine, Map<String, DepartmentID> departments)
            throws ParseException {
        if (csvLine.length < 6) {
            throw new IllegalArgumentException(
                    String.format("Invalid CSV line. Expected 6 columns, got %d", csvLine.length)
            );
        }

        return new Worker(
                Integer.parseInt(csvLine[0].trim()),
                csvLine[1].trim(),
                csvLine[2].trim(),
                departments.computeIfAbsent(
                        csvLine[4].trim(),
                        DepartmentID::new
                ),
                Double.parseDouble(csvLine[5].trim()),
                DATE_FORMAT.parse(csvLine[3].trim())
        );
    }

    /**
     * Обрабатывает ошибку парсинга строки CSV.
     *
     * @param line Строка CSV, которая не была распарсена.
     * @param e Исключение, возникшее при парсинге.
     */
    private void handleParseError(String[] line, Exception e) {
        System.err.printf("Error parsing line: %s. Reason: %s%n",
                Arrays.toString(line), e.getMessage());
    }
}
