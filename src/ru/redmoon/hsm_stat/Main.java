package ru.redmoon.hsm_stat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Программа для посчёта событий в логах HSM Thales payShield 9000
 * Позволяет контролировать количество событий при нагрузке
 *
 * Автор: Алексей Бабак
 * +7 967-066-07-42
 *
 * Этот класс только принимает и проверяет аргументы командной строки:
 * hsm_stat.jar <filename> <alert_quantity=50> <mode=default/verbose>
 *     filename -- путь до файла
 *     alert_quantity -- лимит событий в секунду
 *     mode -- выводить каждую секунду, или только те, где событий больше alert_quantity
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String mode = "default";
        Integer alert_quantity = 50;
        Path file_path = null;

        switch (args.length) {
            case 0:
                System.out.println("Params: hsm_stat.jar <filename> <alert_quantity=50> <mode=default/verbose>");
                throw new Exception("No filename parameter");
            case 3:
                mode = args[2];
                if (0 != mode.compareToIgnoreCase("verbose") && 0 != mode.compareToIgnoreCase("default")) {
                    throw new Exception("allowed modes: default, verbose");
                }
            case 2:
                alert_quantity = Integer.valueOf(args[1]);
            case 1:
                file_path = Paths.get(args[0]);

                if (Files.notExists(file_path)) {
                    throw new Exception( file_path + " -- file not exists");
                }
                if (Files.isDirectory(file_path)) {
                    throw new Exception( file_path + " -- is a directory");
                }
                break;
        }

        System.out.println("---- Start:");
        System.out.printf("filename: %s%n", file_path);
        System.out.printf("alert_quantity: %s%n", alert_quantity);
        System.out.printf("mode: %s%n", mode);

        System.out.println("---- Calc:");
        Worker worker = new Worker(file_path , alert_quantity , mode);

        System.out.println("---- Done.");
    }
}
