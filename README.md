
# Учёт студентов

Это Spring Shell приложение.
Оно поддерживает команды для работы с сущностью "студент".


## Автор

- [@landsreyk](https://github.com/AndrewKozyrev)


## Установка

Для установки нужно склонировать проект, включить Docker и запустить скрипт [run-app-docker.bat](run-app-docker.bat).
Данный скрипт использует docker для создания образа из Dockerfile и запуска контейнера.

```bash
  ./run-app-docker.bat
```
Для запуска вручную, без использования Docker, следует выполнить следующую последовательность команд.
```bash
  ./mvnw.cmd clean package -DskipTests
```
```bash
  java -jar ./target/students-console-app.jar
```
## Документация

Для получения списка допустимых команд нужно ввести `help` в консоли.
Файл с конфигурацией `application.yml` нужно разместить в директории `app/` от корня проекта. В этом файле есть свойство `app.enable-startup-listener`, которое отвечает за инициализацию репозитория студентов случайными данными.

### Список доступных команд

* add, add-student: Добавить нового студента.
* cls, clear-students: Полностью очистить список студентов.
* list, list-students: Вывод всех студентов в консоль.
* delete, delete-student: Удалить студента по идентификатору (id).

### Подробное описание команд

**1. Добавление студента**
```
NAME
        add - Добавить нового студента.

SYNOPSIS
        add [--fn String] [--ln String] [--a Integer] --help

OPTIONS
        --fn or --first-name String
        First name
        [Mandatory]
        
        --ln or --last-name String
        Last name
        [Mandatory]
        
        --a or --age Integer
        Age
        [Mandatory]
        
        --help or -h 
        help for add
        [Optional]


ALSO KNOWN AS
        add-student
```

**2. Вывод студентов в консоль**
```
NAME
        list - Вывод всех студентов в консоль.

SYNOPSIS
        list --help

OPTIONS
        --help or -h
        help for list
        [Optional]


ALSO KNOWN AS
        list-students
```

**3. Удаление студента по идентификатору**
```
NAME
       delete - Удалить студента по идентификатору (id).

SYNOPSIS
       delete [--id Long] --help 

OPTIONS
       --id Long
       ID of a student to be deleted
       [Mandatory]

       --help or -h 
       help for delete
       [Optional]


ALSO KNOWN AS
       delete-student
```

**4. Очистка списка студентов**
```
NAME
       cls - Полностью очистить список студентов.

SYNOPSIS
       cls --help 

OPTIONS
       --help or -h 
       help for cls
       [Optional]


ALSO KNOWN AS
       clear-students
``` 