# Spring Security com JWT
Inicialmente a ideia desse projeto pequeno é apenas para montar um sistema de Login com um pouco mais de segurança usando Spring Security 2.7+ e também a biblioteca do io.jsonwebtoken para realizar esse pequeno projeto, vamos lá para a parte básica que será a configuração dele para rodar na maquina conforme as configurações que eu utilizei.

## Requerimento e Versões utilizadas

 - Java 17
 - Spring 2.7.5
 - MySQL (Recomendo pessoalmente uso do HeidiSQL para facilitar o uso do SGBD)
 - Lombok (Necessário instalar ele no eclipse [Site instalação lombok](https://projectlombok.org))

### Configurações básicas
````
DATABASE CONFIG
spring.datasource.url= ## ALTERAR A URL ##
spring.datasource.username= ## INSERIR O USARNAME DO SEU BANCO PARA ACESSAR ##
spring.datasource.password= ## INSERIR A SENHA DO SEU BANCO CASO TENHA ##
spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect

 TOKEN CONFIG ##
token.app.jwtSecret= secretSecretKey
token.app.jwtExpirationMs= 120000 //Tempo do Token, esse tempo é baseado em Milissegundos.
````
Após configurar essa parte, favor acessar um terminal de comando e rodar o código: **mvn clean install eclipse:eclipse** dentro da pasta do projeto.

### CRÉDITOS
Esse código foi feito e alterado em cima da mini aula de JWT que é do [Bezkoder](https://github.com/bezkoder), caso queira acessar esse projeto que esta uito bem feito por ele no qual ele abrange muito mais coisas do que só JWT vou disponibilizar o link para vocês, ele mostra em diversos frameworks webs e com mais conteúdos aplicações de segurança.

Link do projeto: https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication
