# hsm-stat
Программа для агрегации/подсчёта событий отправки и получения пакетов в логах [HSM Thales payShield 9000](https://dpsys.ru/products/platezhnye-hsm/thales-payshield-9000)
 
Позволяет контролировать количество пакетов при нагрузке и следить за лимитами лицензий. Формат запуска: 
```
$ java - jar hsm_stat.jar filename [alert_quantity] [mode]
```

| Параметр  | По умолчанию | Описание |
| ------------- | ------------- |------------- |
| filename  | нет  | имя (или путь) обрабатываемого файла |
| alert_quantity | 50  | на сколько пакетов реагировать в режиме default |
| mode | default  | verbose режим выводит все секунды, а default -- только алерты |

Примеры:
```
$ java - jar hsm_stat.jar /home/doctorrr/crypto3.log
$ java - jar hsm_stat.jar /home/doctorrr/crypto1.log 45
$ java - jar hsm_stat.jar /home/doctorrr/crypto2.log 50 verbose
```
[инструкция на YouTube](https://www.youtube.com/watch?v=9v9jmRlm03M)
![howto](http://i3.ytimg.com/vi/9v9jmRlm03M/maxresdefault.jpg)

[Скачать исполняемый файл](https://github.com/Doctorrr/hsm-stat/releases)
