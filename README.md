# Epam student's project Hotel

## Техническое задание:

### Общие требования к функциональности проекта:
1. Вход( sign in) и выход( sign out) в/из системы.
2. Регистрация.
3. Просмотр информации (например: просмотр всех курсов, имеющихся
кредитных
карт, счетов и т.д.)
4. Удаление информации (например: отмена заказа, медицинского назначения,
отказ
от курса обучения и т.д.)
5. Добавление и модификация информации (например: создать и
отредактировать
курс, создать и отредактировать заказ и т.д.)

### Описание проекта:
Система Заказ гостиницы. Клиент заполняет Заявку, указывая количество мест
вномере, класс апартаментов и время пребывания. Администратор
просматриваетпоступившую Заявку, выделяет наиболее подходящий из доступных
Номеров, после чегосистема выставляет Счет Клиенту.

## Сборка:
* Download and run Intellij IDEA Ultimate
* Build maven dependencies
* Install lombok plugin
* Set properties -> Enable annotation processing
* Download Tomcat 9
* Run -> Edit Configurations
* Add tomcat server
* Set folder with tomcat
* Add build war artifact
* Add H2 to Project database source
* Set H2 properties:
    * name: H2 - test
    * Connection type: URL only
    * user: sa
    * password:
    * URL: jdbc:h2:./db/test
* Run schema.sql
* Run test-data.sql
* Build Tomcat project
* Profit! 
* Profit!

## Ссылки:
* [model](https://drive.google.com/file/d/1yCnp8RrrslvOphUdVbeMQfNYsUUmlh57/view?usp=sharing)
* [trello](https://trello.com/b/M5towI6a/epam-first-project-spring-2019)

## Авторы:
* [Алексей Бондарев](https://github.com/alexeyboo) 
* [Виктор Смирнов](https://github.com/viktor533)
* [Артем Демченко](https://github.com/Mincer2412)
