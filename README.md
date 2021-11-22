# Развертывание Java приложения на Heroku
### Перед началом работы необходимо иметь: <br>
1) Heroku аккаунт,<br>
2) существующее Java приложение, которое использует Maven в качестве инструмента сборки,<br>
3) установленный Heroku CLI,<br>
4) установленный Maven,<br>
5) Java JDK<br>
***
## Предварительные настройки
### pom.xml
Файл pom.xml должен содержать все необходимые зависимости, а также включать в себя `maven-dependency-plugin`.<br>
Этот плагин нужен для того, чтобы Maven скопировал jar файлы, которые использует приложение, в директорию `target/dependency` <br>
### Procfile
Procfile - это текстовый файл без какого-либо расширения, находящийся в корне проекта, который определяет тип процесса и команду, которая запустит приложение<br>
Должен выглядеть примерно так:<br>
  `worker: java $JAVA_OPTS -cp target/classes:target/dependency/* main.java.Main`
### Сборка проекта
Ддя сборки проекта с помощью Maven необходимо набрать в консоли `mvn clean install`<br>
Результат выполнения можно увидеть в папке target, которая появится после сборки.<br>
Чтобы сборка была корректной, структура проекта должна соответствовать Maven.<br>
***
## Деплой приложения
В консоли выпоняем следующие команды:<br>
1.`heroku login` - вход в аккаунт heroku<br>
2.`heroku create название_приложения` - создание приложения на heroku<br>
3.`git add .`<br>
 &nbsp; `git commit -m "Update"`  &nbsp; &nbsp; &nbsp; - добавляем, коммитим и загружаем файлы в репозиторий heroku<br>
 &nbsp; `git push heroku master`<br>
4.`heroku ps:scale worker=1` - установка количества dynos для процесса, при этом приложение перезапускается<br>
5.Готово.
