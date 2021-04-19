# hsm-stat
Программа для агрегации/подсчёта событий отправки и получения пакетов в логах [HSM Thales payShield 9000](https://dpsys.ru/products/platezhnye-hsm/thales-payshield-9000)
 
Позволяет контролировать количество пакетов при нагрузке и следить за лимитами лицензий. Формат запуска: 
```
java - jar hsm_stat.jar filename alert_quantity=50 mode=default/verbose
```

| Имя параметра  | По умолчанию | Описание |
| ------------- | ------------- |------------- |
| filename  | нет  | имя обрабатываемого файла |
| alert_quantity | 50  | на сколько пакетов реагировать в режиме default |
| mode | default  | verbose режим выводит все секунды, а не только алерты |

![howto](http://i3.ytimg.com/vi/9v9jmRlm03M/maxresdefault.jpg)
[YouTube howto](https://www.youtube.com/watch?v=9v9jmRlm03M)
