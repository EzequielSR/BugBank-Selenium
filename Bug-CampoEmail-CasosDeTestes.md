# Validação incorreta do campo de e-mail na página de cadastro 🐞

# Descrição:
O sistema aceita e-mails incompletos ou mal formatados no campo de e-mail durante o cadastro de um novo usuário. Especificamente, o sistema permite o registro de e-mails no formato email@gmail.co (sem o domínio completo, como .com), sem exibir nenhuma mensagem de erro ou validação.

# Impacto:
* Usabilidade: O usuário pode cadastrar um e-mail inválido, o que pode causar problemas futuros, como falhas no envio de e-mails de confirmação ou recuperação de senha.

* Segurança: E-mails mal formatados podem ser usados para contornar validações ou criar contas inválidas.

* Experiência do Usuário: A falta de validação pode confundir o usuário, que pode não perceber que digitou um e-mail incorreto.

# Passos para Reproduzir o Bug:
1. Acesse a página de cadastro do BugBank.

2. Preencha os campos obrigatórios:

    2.1 Nome: Usuário Teste
    
    2.2 Senha: Senha123
    
    2.3 Confirmação de Senha: Senha123
    
    2.4 No campo E-mail, insira um e-mail incompleto, como email@gmail.co.

3. Clique no botão Registrar.

### Comportamento Esperado:
* O sistema deve exibir uma mensagem de erro informando que o e-mail está incompleto ou mal formatado.

* O registro não deve ser concluído até que um e-mail válido seja fornecido.

### Comportamento Atual:
* O sistema aceita o e-mail incompleto (email@gmail.co) e permite o registro sem exibir nenhuma mensagem de erro.

* O usuário é cadastrado com sucesso, mesmo com um e-mail inválido.

# Evidências:

Captura de tela mostrando o campo de e-mail com o valor "email@gmail.co" e o registro sendo concluído sem erros.

![image](https://github.com/user-attachments/assets/780093cd-6da3-432d-b19b-15384c865cca)

# Logs do Sistema:
Nenhum log de erro ou validação é gerado durante o processo de cadastro.

# Ambiente de Teste:
Navegador: Microsoft Edge (versão 95.0.1020.40).

Sistema Operacional: Windows 10.

Versão do Sistema: BugBank v1.0.0.

# Prioridade:
Alta: O bug afeta a funcionalidade básica do sistema e pode causar problemas futuros para os usuários.

# Sugestão de Correção:
Implementar uma validação robusta do campo de e-mail, utilizando expressões regulares (regex) para garantir que o e-mail esteja no formato correto (ex: usuario@dominio.com).

Exibir uma mensagem de erro clara e específica quando o e-mail estiver incompleto ou mal formatado.

Impedir o registro até que um e-mail válido seja fornecido.

* Exemplo de Validação com Regex:

```javascript
Copy
const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
if (!regexEmail.test(email)) {
    exibirMensagemErro("Por favor, insira um e-mail válido.");
}

```
