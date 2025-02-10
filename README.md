# **BugBank-Selenium**

Este projeto contém testes automatizados para o aplicativo web BugBank, utilizando o Selenium WebDriver com Java.

---

## **📚Índice**
- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades Testadas](#funcionalidades-testadas)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Como Executar](#como-executar)
- [Estrutura do Projeto](#estrutura-do-projeto)

---

## **💡Sobre o Projeto**
O BugBank é um site criado para treinar cenários de teste em um ambiente quase real. Este projeto tem como objetivo desenvolver e executar testes automatizados para garantir a integridade das principais funcionalidades da aplicação.

Os testes foram escritos para verificar tanto cenários positivos quanto negativos, cobrindo possíveis falhas que podem ocorrer durante o uso do sistema.

---

## **✅Funcionalidades Testadas**

1. **Cadastro de Usuário**
   - Criação de contas com ou sem saldo inicial.
   - Validação de campos obrigatórios.
   - Mensagens de erro para entradas inválidas.

2. **Login**
   - Login com credenciais válidas.
   - Mensagem de erro para credenciais inválidas.

3. **Transferências**
   - Transferências para outros usuários do BugBank.
   - Validação de saldos antes e depois da transferência.
   - Mensagens de erro para valores inválidos ou insuficientes.

4. **Extrato**
   - Consulta do histórico de transações.

---

## **👩‍💻Tecnologias Utilizadas**

- [Selenium](https://www.selenium.dev/) - Framework de testes end-to-end.
- **Java** - Linguagem de programação principal do projeto.
- **JUnity** -  Framework para criação e execução dos testes.
- **Maven** - Gerenciador de dependências e automação de build.

---

## **📋Pré-requisitos**

Certifique-se de ter instalado:

- **Java 17** (ou superior instalado)
- **Maven** instalado
- **EdgeDriver** compatível com a versão do Edge instalado

---

## **🚀Como Executar**

1. Clone o repositório no terminal da sua IDE:
   ```bash
   git clone https://github.com/EzequielSR/BugBank-Selenium.git
   ```

2. Acesse o diretório do projeto:
   ```bash
   cd BugBank-Selenium
   ```

3. Instale as depências:
   ```bash
   nvm install
   ```
4. Configure o EdgeDriver:
  * Baixe a versão correspondente do **EdgeDriver** conforme a versão do seu Microsoft Edge.
  * Adicione o caminho do *msedgedriver* às variáveis de ambiente do seu sistema.

   

6. Executando os Testes:
   Para executar todos os testes automatizados, utilize o seguinte comando
   ```bash
   mvn test
   ```
  Os resultados dos testes serão exibidos no terminal, indicando quais testes passaram ou falharam.
   
---
## **📂Estrutura do Projeto**

```plaintext
src/main/java/app/netlify/bugbank/
|  └──/java/
|        └── /app/
|              └── /netlify/
|                       └── /bugbank/
|                                ├── pages/
|                                |   ├── Cadastro.java       
|                                |   ├── Extrato.java       
|                                |   ├── Login.Java         
|                                |   └── Transferencia.java  
|                                |
|                                └── utils/
|                                    └── FecharModal.java  
|
├── test/java/
|         ├── BaseTest.java       
|         ├── CadastroTest.java       
|         ├── ExtratoTest.Java
|         ├── LoginTest.java         
|         └── TransferenciaTest.java
|
├── .gitignore                
├── pom.xml
├── README.md                 
```
---

