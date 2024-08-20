# desafio-tecnico-syonet
Desafio Técnico de Backend: Sistema de Cadastro e Envio de Newsletter

### Objetivo 
Criar um sistema backend que permita o cadastro e envio de e-mails de newsletter. O sistema deve oferecer uma API Web para cadastro de clientes e notícias, e disparar e-mails diários com as notícias cadastradas e não processadas.

### Tecnologias
flyway, spring-boot-starter-mail, thymeleaf, h2-database

# Como rodar o código
 
### Configurar o GMAIL que envia a newsletter:

Acessar a configuração "Senhas de app" no GMAIL, cadastrar a aplicação e gerar uma senha que será a variável de ambiente "MAIL_PASSWORD".

### JAVA 17

### Configurar variáveis de ambiente

- MAIL_LOGIN (email)
- MAIL_PASSWORD (senha de email)
- H2_USER (usuario de acesso ao H2)
- H2_PASSWORD (senha de acesso ao H2)

### Executar comandos
- ./mvnw spring-boot:run
