### Jblog04, 05 Package Structure

<pre>
[src]
  |--- [main]
  |       |--- [java]
  |       |       |--- com
  |       |       |     |--- poscoict
  |       |       |     |       |--- config
  |       |       |     |       |       |--- app
  |       |       |     |       |       |     |--- DBConfig.java
  |       |       |     |       |       |     |--- MyBatisConfig.java
  |       |       |     |       |       |
  |       |       |     |       |       |--- web
  |       |       |     |       |       |     |--- MvcConfig.java
  |       |       |     |       |       |     |--- SecurityConfig.java
  |       |       |     |       |       |     |--- MessageConfig.java
  |       |       |     |       |       |     |--- FileuploadConfig.java
  |       |       |     |       |
  |       |       |     |       |--- jblog
  |       |       |     |       |      |--- config
  |       |       |     |       |      |       |--- AppConfig.java
  |       |       |     |       |      |       |--- WebConfig.java
  |       |       |     |       |      |
  |       |       |     |       |      |--- controller
  |       |       |     |       |      |--- exception
  |       |       |     |       |      |--- interceptor
  |       |       |     |       |      |--- repository
  |       |       |     |       |      |--- security
  |       |       |     |       |      |--- service
  |       |       |     |       |      |--- vo
  |       |
  |       |--- [resources]
  |       |       |--- com
  |       |       |     |--- poscoict
  |       |       |     |       |--- jblog
  |       |       |     |       |      |--- config
  |       |       |     |       |      |       |--- app
  |       |       |     |       |      |       |     |--- mybatis
  |       |       |     |       |      |       |     |       |--- mappers
  |       |       |     |       |      |       |     |       |--- configuration.xml
  |       |       |     |       |      |       |     |
  |       |       |     |       |      |       |     |--- jdbc.properties
  |       |       |     |       |      |       |
  |       |       |     |       |      |       |--- web
  |       |       |     |       |      |       |     |--- messages
  |       |       |     |       |      |       |     |--- fileupload.properties
<pre>