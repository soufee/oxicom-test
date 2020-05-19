## Использованный стек:
- Kotlin
- PostgreSQL
- Spring-Boot
- Spring Cloud
- Spring Data JPA
- Consul Service Discovery
- Docker
- RabbitMQ

## Предварительная конфигурация

Прежде, чем запускать приложение, необходимо произвести некоторые настройки.
В первую очередь, нужно определить IP хоста, на котором запускается докер. 
Его нужно прописать в переменной _host_ во всех четырех конфигурационных файлах микросервисов **application.properties**

В файле **application.properties** микросервиса _mailer-service_ нужно прописать данные mail хоста, с которого будет производиться рассылка сообщений:
    
    spring.mail.host=<yor host>
    spring.mail.username=<your email>
    spring.mail.password=<your password>
    spring.mail.port=<your port>

Необходимо запустить образ _Postgres_ в докере и создать в нем базу **postgres**, либо в **application.properties** сервиса _api-service_ задать другую существующую базу.
Удостовериться, что конфигурация БД смотрит на существующую базу.
Для запуска postgres в Docker можно выполнить команду

    docker run -d -p 5432:5432 --name my-postgres -e POSTGRES_PASSWORD=postgres postgres
    
## Порядок запуска
Т.к. docker-compose не работает, запускать нужно будет последовательно
1. Запустить БД - можно в Docker командой, приведенной выше. 
2. Запустить RabbitMQ в Docker

        docker run -d -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

3. Запустить Consul

        docker run -d -p 8500:8500 -p 8600:8600/udp consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0

4. Из корня выполнить следующие команды: 
        
        mvn clean install
        docker build -t mailer mailer-service/.
        docker build -t idgenerator id-generator/.
        docker build -t approver approval-service/.
        docker build -t api api-service/.
        docker run -d -p 9084:9084 mailer
        docker run -d -p 9081:9081 idgenerator
        docker run -d -p 9082:9082 approver
        docker run -d -p 9080:9080 api

5. Открыть в браузере "HOST_IP:9080", где HOST_IP это IP, на котором Consul разместил сервис.
6. Если сборка и запуск приложения произошли до конца, то должна быть открыта страница с двумя ссылками: 
    - _Оставить заявку_ - для формирования заявки
    - _Проверить статус заявки_ - для ввода UUID и проверки статуса подтверждения
7. Для проверки программы через Postman отправить POST запрос на адрес 
`HOST_IP:9080/api/send-verification` с телом  

`        {
	        "firstName":"123",
	        "lastName":"321",
	        "email":"a@a.ru",
	        "dateBirth":"1980-01-01",
	        "liveCity":"Moscow",
	        "registrationCity":"Moscow"
        }`
 Проверка статуса заявки осуществляется через GET запрос по урлу 
 
 `HOST_IP:9080/api/check-verification-status/{ID}` 
 
 где ID это UUID который получен как ответ в предыдущем запросе,
 а HOST_IP это тот IP, на котором сервис размещен в Consul
 
 **По всем вопросам обращаться в телеграм @Soufee1**