# UC1-Registar Organização

##### [Voltar ao início](https://github.com/ajorgesantosp/upskill_java1_g1/blob/main/README.md)

## 1. Engenharia de Requisitos

### Formato Breve

Um utilizador afecto à organização inicia o registo da organização. O sistema solicita os dados necessários, nomeadamente o nome da organização, o número de identificação fiscal (NIF), o endereço postal, um contacto telefónico, um endereço web, um endereço de correio electrónico (email). O sistema solicita os dados do colaborador que ficará responsável pela especificação de outros colaboradores, nomeadamente o nome, função, contacto telefónico, e endereço de email.
O sistema valida e apresenta os dados ao colaborador da organização, pedindo que os confirme. O sistema guarda os dados na base de dados, e informa o colaborador da organização que o registo foi feito com sucesso.

### Formato Completo

**Actor Principal:**

    Colaborador da Organização

**Partes interessadas e seus interesses:**

    Organização: pretende ficar registada na plataforma.

    T4J: pretende que as organizações se registem.

**Pré-condições:**

    A plataforma tem de permitir o registo de organizações.

**Pós-condições:**

    A organização fica registada na plataforma.

#### SSD

![UC1_SSD.png](UC1_SSD.png)

##### Diagrama de Sequências

![UC1_Registar_Organização.png](UC1_Registar_Organização.png)
