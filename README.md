# POAS_redactor
*Модуль редактора*

## Создание модуля редактора
```java
AnchorPane redactorPane = new AnchorPane();
RedactorModule redactorModule = new RedactorModule();
redactorModule.init(redactorPane);
```

## Создание модуля редактора (с открытием программы из файла)
```java
AnchorPane redactorPane = new AnchorPane();
RedactorModule redactorModule = new RedactorModule();
File rTranProgram = new File(Resources.getResource("Test.rtran").getFile());
redactorModule.init(redactorPane, rTranProgram);
```

## Загрузка программы из файла в редактор
```java
File rTranProgram = new File(Resources.getResource("Test.rtran").getFile());
redactorModule.load(rTranProgram);
```

## Сохранение программы по запомненному пути
```java
redactorModule.save();
```

## Сохранение программы в новый файл
```java
File destination = ...
redactorModule.saveAs(destination);
```

*Пример использования редактора содержится в [TestMainWindow.java](src/test/java/mainWindow/TestMainWindow.java)*
