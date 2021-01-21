# UC5 – Especificar Colaboradores da Organização <br/>

##### [Voltar ao início](https://github.com/ajorgesantosp/upskill_java1_g1/blob/main/README.md)

## Formato Breve<br/><br/>

O gestor da organização acede à plataforma, através dos seus dados de login (email e password) e preenche os campos fornecidos pelo sistema para indicar um novo colaborador da organização na plataforma.

## Formato completo<br/><br/>

### Actor primário:<br/>

Gestor da Organização

### Partes interessadas e seus interesses: <br/>

Gestor da Organização: Pretende que a organização tenha todos os colaboradores designados na plataforma
Organização: Pretende ter mais colaboradores na plataforma
Colaboradores: Pretendem ficar registados na plataforma e habilitados para usar a mesma.
Plataforma: Pretende ter mais utilizadores para a plataforma

### Pré-condições: <br/>

1. O Gestor tem de estar registado na plataforma e conseguir efectuar o login na mesma.
2. A organização tem de estar igualmente registada na plataforma.
3. A organização tem de ter colaboradores por especificar.

### Pós-condições: <br/>

O Gestor da Organização especifica todos os colaboradores da Organização.

### Cenário de sucesso principal:<br/>

1. O Gestor da Organização inicia o processo de inserir/especificar um colaborador.
2. O sistema solicita os dados.
3. O Gestor da Organização introduz os dados.
4. O sistema solicita a confirmação dos dados.
5. O Gestor da Organização confirma os dados.
6. O sistema devolve mensagem a confirmar o sucesso da operação.

### Fluxos alternativos:<br/>

1. O Gestor da Organização cancela o processo de inserir/especificar um colaborador.
   a. O caso de uso termina.
2. O Gestor da Organização verifica que não existem mais colaboradores para inserir.
   a. O caso de uso termina.
3. O Gestor da Organização introduz dados inválidos:
   a. O sistema permite a correção dos dados introduzidos (Passo 3).
   b. O Gestor da Organização não altera os dados.
   c. O caso de uso termina.
4. O sistema deteta que os dados (ou algum subconjunto dos dados) introduzidos devem ser únicos e que já existem no sistema:
   a. O sistema alerta o gestor para o facto.
   b. O sistema permite a sua alteração (passo 3).
   c. O Gestor da Organização não altera os dados. O caso de uso termina.

### Diagrama de sequência de Sistema (SSD)<br/>

![UC5_SSD.png](UC5_SSD.png)

### Diagrama de sequência<br/>

![UC5_DS.png](UC5_DS.png)

### Diagrama de classes<br/>

![UC5_DC.png](UC5_DC.png)
