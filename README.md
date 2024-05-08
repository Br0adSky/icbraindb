# Серверное приложение для визуального интерфейса ICBrainDB

## Запуск

Для запуска проекта необходимо выполнить следующие действия:
Выполнить сборку проекта:

```shell
mvn package
```

В папке build должен будет появиться архив с дистрибутивом и конфигурацией. Чтобы запустить проект, архив необходимо
распаковать в требуемую папку и запустить [start.sh](assemble/distr/start.sh).
В файле [config/application.yaml](assemble/distr/application.yaml) находится конфигурация приложения, в
файле [config/logback.xml](assemble/distr/logback.xml) находится конфигурация логирования.

## Конфигурирование

Описание настроек приложения:

1. _spring.datasource.url_ - URL адрес БД. Чтобы указать схему, необходимо на конце адреса написать:
   _?currentSchema=brain_
2. _spring.datasource.username_ - Имя пользователя, под которым сервис будет работать с БД;
3. _spring.datasource.password_ - Пароль пользователя, под которым сервис будет работать с БД;
4. _security.oauth2.resourceserver.jwt.jwk-set-uri_ - URL адрес до JWK.
5. _server.port_ - порт сервера;
6. _client.urls_ - адреса клиентов, взаимодействующих с этим сервером (для CORS. Будет работать только для API,
   изменяющих состояние БД);

Если необходимо, чтобы сервер работал по TLS необходимо указать следующие настройки:

1. _server.ssl.enabled_ - включение/выключение TLS (возможные значения true/false);
2. _server.ssl.key-store-type_ - тип хранилища ключевого контейнера (JSK, PKCS12);
3. _server.ssl.key-store_ - путь до ключевого контейнера;
4. _server.ssl.key-store-password_ - пароль от ключевого контейнера;
5. _server.ssl.protocol_ - использовать TLS или SSL (для TLS значение TLS);
6. _server.ssl.enabled-protocols_ - возможные протоколы взаимодействия (TLSv1.1, TLSv1.2, TLSv1.3);
7. _server.ssl.client-auth=need_ - для двустороннего TLS;
8. _server.ssl.trust-store_ - путь до контейнера с доверенными сертификатами;
9. _server.ssl.trust-store-password_ - пароль от контейнера с доверенными сертификатами;

В файле [config/logback.xml](assemble/distr/logback.xml) находятся стандартные настройки логирования, подробное описание
можно найти на официальном [сайте](https://logback.qos.ch/manual/configuration.html).

Для реализации механизма авторизации в данном приложении используется протокол OIDC и сервер
авторизации [Keycloak](https://www.keycloak.org/).
Скачать дистрибутив Keycloak можно с официального [сайта](https://www.keycloak.org/downloads). При разработке и отладке
была использована версия **24.0.2**, поэтому необходимо использовать версию не ниже этой.  
Для загрузки шаблона темы, которая будет использоваться при авторизации, необходимо скопировать
папку [theme/icbraindb](assemble/theme/icbraindb)
из дистрибутива приложения и вставить в папку **themes** дистрибутива keycloak. Также при запуске сервера необходимо
указать шаблоны рилмов, расположенные в папке [realms](assemble/realms). Варианты как можно это сделать при запуске
приложения описаны [здесь](https://www.keycloak.org/server/importExport).  
При запуске в режиме _production_ Keycloak не будет работать без TLS. Конфигурирование TLS, имени хоста и другие
настройки подробно описаны [здесь](https://www.keycloak.org/server/configuration-production).  
