package ru.redmoon.hsm_stat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Этот класс построчно читает файлы и кладёт каждую строку коллекцию Moments,
 * индексированную по 1 секунде
 */
class Worker {
    Worker(Path file_path, Integer alert_quantity, String mode) throws IOException {
        // Формат даты: 12.04.2021 16:35:41
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        // Анонимный объект для "лямбды" ниже
        var ref = new Object() {
            LocalDateTime dateTimeCursor = null;
            AtomicInteger lineCursor = new AtomicInteger(1);
//            AtomicInteger RequestSentCoursor = new AtomicInteger(0);
//            AtomicInteger ReplyReceivedCoursor = new AtomicInteger(0);
//            Map<LocalDateTime, Moment> MomentMap = new HashMap<LocalDateTime, Moment>();
            Moment momentCursor = null;
        };

        try (Stream<String> stream = Files.lines(Paths.get(file_path.toString()))) {
            stream.forEach(line -> {
                // На каждой строке читаем подстроку с датой и по паттерну преобразуем её в объект времени
                String timeString = line.substring(2, 21);
                LocalDateTime dateTime = LocalDateTime.parse(timeString, formatter);

                // Обновляем курсор времени для первого и последующего циклов
                if (ref.momentCursor == null) {
                    ref.momentCursor = new Moment(dateTime);
                    ref.dateTimeCursor = dateTime;
                    System.out.println("date;time;sent;received");

                } else if (0 != ref.dateTimeCursor.compareTo(dateTime)) {
                    if ( ref.momentCursor.getRequestsSent() > alert_quantity || 0 == mode.compareToIgnoreCase("verbose") ) {
                        System.out.println(ref.momentCursor);
                    }

                    ref.dateTimeCursor = dateTime;
                    ref.momentCursor = new Moment(dateTime);
//                    ref.RequestSentCoursor.set(0);
//                    ref.ReplyReceivedCoursor.set(0);
                }

                /*
                  Смотрим, есть ли в строке события
                  "Request sent to the RG SM"
                  "Reply received from the RG SM"
                */
                if (line.toLowerCase().contains("request sent to the rg sm")) {
                    ref.momentCursor.addRequestsSent();
                }

                if (line.toLowerCase().contains("reply received from the rg sm")) {
                    ref.momentCursor.addRepliesReceived();
                }

                ref.lineCursor.set(ref.lineCursor.get() + 1);
            });
        }
    }
}
