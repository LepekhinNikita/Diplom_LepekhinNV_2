# Перечень автоматизируемых сценариев


### Позитивные сценарии:

1. В сервисе платежей (Payment Gate) ввод валидных данных и номера карты 4444 4444 4444 4441
2. В кредитном сервисе (Credit Gate) при вводе валидных данных и номера карты 4444 4444 4444 4441
3. В сервисе платежей (Payment Gate) заявка отклонена при вводе валидных данных и номера карты 4444 4444 4444 4442.
4. В кредитном сервисе(Credit Gate) заявка отклонена при вводе невалидных данных и номера карты 4444 4444 4444 4442.

### Негативные сценарии:
## Поле "Номер карты"
1. Заполнение заявки в сервисе платежей (Payment Gate) несуществующим номером карты
2. Заполнение заявки в кредитном сервисе(Credit Gate) несуществующим номером карты
3. Заполнение заявки в сервисе платежей (Payment Gate) недостающее количество символов
4. Заполнение заявки в кредитном сервисе(Credit Gate) недостающее количество символов
5. Заполнение заявки в сервисе платежей (Payment Gate) с пустым "Номером карты"
6. Заполнение заявки в кредитном сервисе(Credit Gate) с пустым "Номером карты"

## Работа с полем "Месяц"
1. Заполнение заявки в сервисе платежей (Payment Gate) невалидными данными в поле "Месяц"
2. Заполнение заявки в кредитном сервисе(Credit Gate) невалидными данными в поле "Месяц"
3. Заполнение заявки в сервисе платежей (Payment Gate) несуществующими данными в поле "Месяц"
4. Заполнение заявки в кредитном сервисе(Credit Gate) несуществующими данными в поле "Месяц"
5. Заполнение заявки в сервисе платежей (Payment Gate) истекшим сроком действия карты
6. Заполнение заявки в кредитном сервисе(Credit Gate) истекшим сроком действия карты
7. Заполнение заявки в сервисе платежей (Payment Gate) с пустым полем "Месяц"
8. Заполнение заявки в кредитном сервисе(Credit Gate) с пустым полем "Месяц"

## Работа с полем "Год"

1. Заполнение заявки в сервисе платежей (Payment Gate) истекший срок действия
2. Заполнение заявки в в кредитном сервисе(Credit Gate) истекший срок действия
3. Заполнение заявки в сервисе платежей (Payment Gate) с пустым полем
4. Заполнение заявки в кредитном сервисе(Credit Gate) с пустым полем

## Работа с полем "Владелец"
1. Заполнение заявки в сервисе платежей (Payment Gate) только имя русскими буквами
   2.Заполнение заявки в кредитном сервисе(Credit Gate) только имя русскими буквами
3. Заполнение заявки в сервисе платежей (Payment Gate) только имя латинскими буквами
   4.Заполнение заявки в кредитном сервисе(Credit Gate) имя латинскими буквами
5. Заполнение заявки в сервисе платежей (Payment Gate) только фамилия русскими буквами
   6.Заполнение заявки  в кредитном сервисе(Credit Gate) только фамилия русскими буквами
7. Заполнение заявки в сервисе платежей (Payment Gate) только фамилия латинскими буквами
   8.Заполнение заявки в кредитном сервисе(Credit Gate) только фамилия латинскими буквами
9. Заполнение заявки в сервисе платежей (Payment Gate) имя и фамилия через дефис
   10.Заполнение заявки в кредитном сервисе(Credit Gate) имя и фамилия через дефис
11. Заполнение заявки в сервисе платежей (Payment Gate) имя и фамилия в количестве 300 букв
    12.Заполнение заявки в кредитном сервисе(Credit Gate) имя и фамилия в количестве 300 букв
13. Заполнение заявки в сервисе платежей (Payment Gate) цифры вместо букв
    14.Заполнение заявки в кредитном сервисе(Credit Gate) цифры вместо букв
15. Заполнение заявки в сервисе платежей (Payment Gate) 1 символ
    16.Заполнение заявки в кредитном сервисе(Credit Gate) 1 символ
17. Заполнение заявки в сервисе платежей (Payment Gate) с пустым полем "Владелец"
    18.Заполнение заявки в кредитном сервисе(Credit Gate) с пустым полем "Владелец"
19. Заполнение заявки в сервисе платежей (Payment Gate) вместо имени пробел
    20.Заполнение заявки в кредитном сервисе(Credit Gate) вместо имени пробел
21. Заполнение заявки в сервисе платежей (Payment Gate) вместо имени спец символ
    22.Заполнение заявки в кредитном сервисе(Credit Gate) вместо имени  спец символ


## Работа с полем CVC/CVV

1. Заполнение заявки в сервисе платежей (Payment Gate) 1 символ
2. Заполнение заявки в кредитном сервисе(Credit Gate) 1 символ
3. Заполнение заявки в сервисе платежей (Payment Gate) 2 символа
4. Заполнение заявки в кредитном сервисе(Credit Gate) 2 символа
5. Заполнение заявки в сервисе платежей (Payment Gate) с пустым полем CVC/CVV
6. Заполнение заявки в кредитном сервисе(Credit Gate) с пустым полем CVC/CVV

## Работа с пустой заявкой

1. Заполнение заявки в сервисе платежей (Payment Gate) с пустыми полями
2. Заполнение заявки в кредитном сервисе(Credit Gate) с пустыми полями


# Перечень используемых инструментов с обоснованием выбора
- IntelliJ IDEA 2022.3.2 (Community Edition) - среда разработки, поддерживающая множество плагинов, систем управления
- JUnit 5- одна из самых популярных платформ модульного тестирования
- Docker- это платформа для разработки, доставки и запуска контейнерных приложений. Docker позволяет создавать контейнеры, автоматизировать их запуск и развертывание, управляет жизненным циклом. Он позволяет запускать множество контейнеров на одной хост-машине
- Selenide это автоматизированная система тестирования программного обеспечения, используемая для написания программных кодов, которая помогает создавать и отправлять HTTP-запросы на Server.
- MySQL
- PostgreSQL
- REST Assured -библиотека для тестирования RESTful API, позволяет пользователям обрабатывать HTTP-запросы, которые можно записать в достаточно читабельном формате
- Java Faker-библиотека, которую можно использовать для создания широкого спектра реально выглядящих данных от адресов до ссылок на популярные культуры.
- Google Chrome Версия 113.0.5672.127 (Официальная сборка), (64 бит)-один из самых популярных браузеров.
- GitHub-крупнейший веб-сервис для хостинга IT-проектов и их совместной разработки, основанный на системе контроля Git.
- Gradle-система автоматической сборки и управления зависимостями

# Перечень и описание возможных рисков при автоматизации

1. Отсутствие документации и ТЗ. Непонятно какое поведение программы является "нормальным"
1. В задаче даны две карты с 16ти значными номерами, в реале количество цифр в номере карты может отличаться
1. Нет test-id в css-селекторах, что усложняет написание автотестов.
1. Одновременное использование двух ДБ
1. "Боевая" система будет отличаться от SUT.

# Интервальная оценка с учётом рисков (в часах)

1. Создание проекта и запуск SUT-3 часа.
1. Написание тест-плана - 3 часа.
1. Написание автотестов - 50 часов. Ориентировочная дата сдачи 09 июня 2023.
1. Дополнительное время на устранение замечаний - 15 часов. Ориентировочная дата сдачи 12 июня 2023
1. Подготовка отчетов по итогам тестирования - 8 часов. Ориентировочная дата сдачи 13 июня 2023
1. Подготовка учетных документов по итогам автоматизации - 8 часов. Ориентировочная дата сдачи 15 июня 2023