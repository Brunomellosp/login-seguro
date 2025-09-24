<<<<<<< HEAD
# Login Seguro — Spring Boot + Thymeleaf + MongoDB Atlas

Sistema de autenticação e autorização com **Spring Security** e **MongoDB Atlas**, com **Spring Session (MongoDB)** para armazenamento de sessões.
Pensado para ser **modular e temável**, permitindo adaptação posterior ao tema do seu PFC sem mexer na lógica central.

## Stack
- Java 17, Spring Boot 3.3.x
- Spring Security, Spring Data MongoDB, Spring Session (MongoDB)
- Thymeleaf + thymeleaf-extras-springsecurity6
- MongoDB Atlas (nuvem)

## Recursos
- Cadastro seguro de usuários (hash BCrypt, validações)  
- Login/Logout com CSRF habilitado  
- Controle de acesso por **roles** (`USER` e `ADMIN`)  
- **/admin** protegido por `ROLE_ADMIN`  
- Sessões HTTP armazenadas no Mongo (collection `sessions`)  
- Templates com **tema** configurável via `APP_THEME` (`default` ou `dark`)  
- Inicialização de um usuário **ADMIN** automático no primeiro start (senha gerada ou lida de `ADMIN_INIT_PASSWORD`)

## Como rodar localmente

1. **Pré-requisitos**  
   - JDK 17+  
   - Maven 3.9+  
   - Conta no **MongoDB Atlas** (gratuita)  

2. **Crie um cluster no MongoDB Atlas**  
   - Crie um database, por exemplo: `loginseguro`  
   - Crie um usuário e copie a **Connection String** (SRV).  
   - Ajuste para incluir o nome do database ao final, por ex.:  
     `mongodb+srv://<user>:<pass>@<cluster>/loginseguro?retryWrites=true&w=majority&appName=<appname>`

3. **Defina variáveis de ambiente** (recomendado)  
   - No Linux/Mac:
     ```bash
     export MONGODB_URI='mongodb+srv://<user>:<pass>@<cluster>/loginseguro?retryWrites=true&w=majority&appName=<appname>'
     export APP_THEME=default
     export ADMIN_EMAIL=admin@example.com
     export ADMIN_USERNAME=admin
     # opcional: export ADMIN_INIT_PASSWORD='TroqueEstaSenha123!'
     ```
   - No Windows (PowerShell):
     ```powershell
     setx MONGODB_URI "mongodb+srv://<user>:<pass>@<cluster>/loginseguro?retryWrites=true&w=majority&appName=<appname>"
     setx APP_THEME "default"
     setx ADMIN_EMAIL "admin@example.com"
     setx ADMIN_USERNAME "admin"
     # opcional:
     # setx ADMIN_INIT_PASSWORD "TroqueEstaSenha123!"
     ```
   Reinicie o terminal após `setx`.

4. **Build e execução**
   ```bash
   mvn spring-boot:run
   # ou
   mvn clean package
   java -jar target/login-seguro-0.0.1-SNAPSHOT.jar
   ```

5. **Acessar**
   - http://localhost:0
   - Login inicial (se for o primeiro start e você **não** definiu `ADMIN_INIT_PASSWORD`):  
     verifique a senha temporária **gerada** no console.  
   - Após logar, vá para **/admin** para testar autorização.

## Estrutura do projeto (resumo)
```
src/main/java/com/example/loginseguro
├─ LoginSeguroApplication.java        # @EnableMongoHttpSession
├─ config/
│  ├─ SecurityConfig.java             # Spring Security (form login, roles, CSRF, logout)
│  ├─ ThemeConfig.java                # Injeta appTheme no model (Thymeleaf)
│  └─ DataInitializer.java            # Admin inicial
├─ controller/
│  ├─ HomeController.java             # "/" e "/admin"
│  └─ AuthController.java             # "/login" e "/register"
├─ model/
│  ├─ Role.java
│  └─ User.java                       # @Document + @Indexed unique
├─ repo/
│  └─ UserRepository.java
└─ service/
   ├─ CustomUserDetailsService.java   # UserDetailsService + registro
   └─ UserRegistrationDTO.java
src/main/resources
├─ application.yml                    # usa MONGODB_URI e APP_THEME
├─ templates/                         # Thymeleaf
│  ├─ fragments/{head,navbar}.html
│  ├─ layout.html
│  ├─ index.html
│  ├─ login.html
│  └─ register.html
└─ static/themes/{default,dark}/styles.css
```

## Decisões de design
- **Camadas separadas** (controller/service/repo/model/config) para facilitar evolução e testes.
- **Thymeleaf** desacoplado com fragmentos e suporte a **tema** via var global `appTheme`.
- **Spring Security** com `SecurityFilterChain` (Spring 6) e `DaoAuthenticationProvider` + `BCryptPasswordEncoder`.
- **MongoDB Atlas** como banco único de usuários + **Spring Session** usando Mongo para persistir sessões.
- **Validações** no DTO de cadastro e checagens de unicidade (username/email).
- **CSRF** habilitado por padrão; formulários incluem o token oculto.

## Adaptação de tema (futuro PFC)
- Para criar novos temas, adicione uma pasta em `src/main/resources/static/themes/<nomeDoTema>` com um `styles.css`.
- Defina `APP_THEME=<nomeDoTema>` na configuração do seu ambiente de execução.  
- Se desejar layouts diferentes, crie novos fragmentos/partials e resolva-os via lógica condicional em Thymeleaf.

## Produção (Dicas)
- Defina `ADMIN_INIT_PASSWORD` explicitamente.
- Ative HTTPS e cabeçalhos de segurança (reverse proxy como Nginx/Caddy).
- Configure **índices únicos** em `users` (Spring Data já cria pelas anotações).
- Restrinja IPs e privilégios do usuário do Atlas.

## Licença
MIT
=======
# login-seguro
>>>>>>> ee0a577d27b418e694f97056877cae3d8e2986f0
