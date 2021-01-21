# Enunciado Adenda 2

##### [Voltar ao início](https://github.com/ajorgesantosp/upskill_java1_g1/blob/main/README.md)

### Respostas a questões em aberto

1. Não podem existir organizações (i) com o mesmo NIF nem (ii)
   com o mesmo endereço de correio eletrónico (email).
2. As palavras-passe de todos os utilizadores do sistema devem: - Ser geradas pela plataforma recorrendo a um algoritmo
   externo (i.e. concebido por terceiros) e configurado apenas
   aquando da implantação do sistema. - Remetidas para o email do utilizador, que deve ser (o email)
   único no sistema.
3. Relativamente a cada categoria de tarefa, esclarece-se que: - Apesar de ser uma raridade, a mesma pode não indicar
   qualquer competência técnica como sendo obrigatória e/ou
   desejável. - Ao especificar-se uma competência técnica como requerida
   (de forma obrigatória ou desejável) deve ser indicado o grau
   de proficiência mínimo exigido (e.g. baixo, médio, alto).
   Salienta-se que os graus de proficiência variam em função
   da competência técnica em causa.
4. Aquando da especificação de uma competência técnica, é
   também necessário definir quais os graus de proficiência
   aplicáveis à mesma. Salienta-se que a quantidade de graus de
   proficiência e sua designação (e valor) variam de uma
   competência técnica para outra. O valor associado a cada grau
   (e.g. 1 ou 2 ou 3, etc..) é único por competência técnica e quanto
   maior este for, maior é o nível de exigência associado.
5. A estimativa de duração de uma tarefa é indicada em dias.
6. A informação monetária (e.g. custo estimado de uma tarefa) é
   indicada em POTs (moeda virtual interna à plataforma).

### Novos Requisitos

Cabe aos administrativos registar na plataforma a informação relativa
aos freelancers. Para além do seu nome, NIF, endereço postal,
contacto telefónico e email é necessário indicar (i) as habilitações
académicas (grau, designação do curso, instituição que concedeu o
grau e média de curso) do freelancer e (ii) os reconhecimentos de
competências técnicas recebidos/atribuídos aquando do processo de
verificação e validação de candidatos a freelancers conduzido pelo
departamento de recursos humanos da T4J e/ou por outras entidades
parceiras. Cada reconhecimento ocorre numa determinada data, é
referente a uma competência técnica e reconhece que o freelancer
possui um determinado grau de proficiência nessa competência. Após
o registo de um freelancer, este deve poder aceder à plataforma.

Os colaboradores das organizações podem publicar as tarefas por si
anteriormente criadas. Da publicação de uma tarefa resulta um anúncio
onde consta (i) o período de publicitação da tarefa na plataforma; (ii) o
período de apresentação de candidaturas pelos freelancers; (iii) o
período de seriação e decisão de atribuição da tarefa a um freelancer
pela organização; e (iv) pelo tipo de regimento aplicável. Este último
estipula as regras gerais pelas quais se regem os processos de
candidatura, de seriação e de atribuição de tarefa no âmbito de um
anúncio. De momento devem ser suportados os seguintes tipos: - Seriação subjetiva com atribuição opcional: estipula que o
processo de seriação dos candidatos assenta em critérios
subjetivos definidos pela organização e que esta, no final, não
está obrigada a atribuir a tarefa a nenhum dos candidatos; - Seriação subjetiva com atribuição obrigatória: semelhante ao
anterior, mas no qual a organização tem obrigatoriamente de
atribuir a tarefa a um dos candidatos (desde que exista pelo
menos um); - Seriação e atribuição automática com base no segundo
preço mais baixo: como o próprio nome indica garante aos
candidatos que o processo de seriação e atribuição assenta
exclusivamente no preço apresentado pelos candidatos.

Contudo, ao longo do tempo, prevê-se a adoção de mais e variados
tipos de regimento. Como tal, pretende-se que a adição de novos tipos
de regimento esteja facilitada e, se possível, possa até ser realizada por
terceiros. Um freelancer apenas pode candidatar-se a um anúncio para
o qual é elegível, isto é, quando lhe é reconhecido possuir o grau de
proficiência mínimo exigido a todas as competências técnicas
obrigatórias para a tarefa em causa. Ao efetuar uma candidatura, este
deve obrigatoriamente indicar o valor pretendido pela realização da
tarefa bem com o número de dias necessários à sua realização após
atribuição da mesma. Opcionalmente, pode incluir um texto de
apresentação e/ou motivação.

Findo o período de apresentação de candidaturas de um anúncio, é
despoletado o processo de seriação dos candidatos em concordância
com o tipo de regimento aplicável. No caso dos processos de seriação
não automáticos, cabe ao colaborador que publicou a tarefa realizar o
mesmo. Em resultado deste processo todas as candidaturas são
classificadas/ordenadas (i.e. desde o 1º até ao Nº lugar). É também
importante conhecer-se a data/hora em que o processo decorreu e os
participantes no mesmo (outros colaboradores da organização).
Em termos visuais, a interface gráfica da aplicação deve assentar numa
paleta de cores estruturada em duas cores (primária e secundária) e
esta ser configurável aquando da sua implantação. A equipa de
desenvolvimento deve ainda especificar um conjunto relevante de
testes de cobertura e mutação (e.g. unitários, funcionais e de
integração) que assegure a qualidade do sistema desenvolvido.
