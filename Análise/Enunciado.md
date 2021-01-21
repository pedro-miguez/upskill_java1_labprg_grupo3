# Enunciado do Projeto

##### [Voltar ao início](https://github.com/ajorgesantosp/upskill_java1_g1/blob/main/README.md)

## Plataforma de Outsourcing de Tarefas

### Iteração 1

#### Enunciado

A startup Tasks for Joe (T4J) dedica-se a facilitar e promover o contacto entre pessoas
que trabalham por conta própria (freelancers) e organizações que pretendem contratar
alguém externo (outsourcing) para a realização de determinadas tarefas. Para promover
e suportar o seu negócio, a T4J pretende desenvolver uma plataforma informática que,
por uma lado, permita que qualquer organização interessada possa registar-se na
plataforma de forma a poder publicar tarefas e gerirem elas próprias o processo de
adjudicação dessas tarefas a freelancers; e por outro lado, permita que os freelancers
acedam facilmente a essas tarefas e possam candidatar-se à realização das mesmas.

Desde já, perspetiva-se que a plataforma seja acedida por vários utilizadores com
diferentes papeis, tais como:

- Administrativos: são colaboradores da T4J afetos à gestão da plataforma e, em
  particular, por realizar na plataforma várias atividades de suporte ao negócio, tais
  como, definir áreas de atividade (e.g. IT, Marketing, Design), definir categorias de
  tarefas (e.g. desenvolvimento aplicações web, desenvolvimento de aplicações
  móveis) e especificar competências técnicas requeridas para a realização de tarefas.
- Gestor de Organização: pessoa indicada como gestor da organização aquando do
  registo da organização na plataforma. Assume-se que é um colaborador dessa
  organização, sendo responsável por especificar na plataforma outros colaboradores
  dessa mesma organização;
- Colaborador de Organização: pessoa registada na plataforma como sendo alguém
  que atua em representação de uma determinada organização. Entre outras
  responsabilidades, cabe-lhe especificar tarefas para posterior publicação pela
  organização respetiva;
- Freelancers: pessoas que se propõem a realizar as tarefas publicadas pelas
  organizações.

As interações dos utilizadores supramencionados devem ser precedidas de um processo
de autenticação. A utilização da plataforma por outras pessoas é restrita ao registo de
organizações. Aquando deste registo é obrigatório requerer o nome da organização, o
seu número de identificação fiscal (NIF), o endereço postal, um contacto telefónico, um
endereço web, um endereço de correio eletrónico (email) e os dados do colaborador
responsável pelo registo (nome, função, contacto telefónico, endereço de email). Após o
registo da organização, os seus colaboradores pode aceder imediatamente à plataforma.
Tanto uma área de atividade como uma competência técnica caracterizam-se através de
um código único, uma descrição breve e outra mais detalhada respetivamente. Uma
competência técnica caracteriza-se ainda por ser referente a uma dada área de
atividade. Por outro lado, cada categoria de tarefa caracteriza-se por um identificador
interno (automático), uma descrição, a área de atividade em que se enquadra (apenas
uma) e uma lista de competência técnicas tipicamente requeridas para a realização de
tarefas dessa categoria. Algumas destas competências têm caracter obrigatório e outras
apenas são desejáveis.

Cada tarefa caracteriza-se por ter uma referência única por organização, uma
designação, uma descrição informal e outra de carácter técnico, uma estimativa de
duração e custo bem como a categoria em que a mesma se enquadra. Enquanto a mesma
não for publicada, o acesso à tarefa é exclusivo aos colaboradores da organização
respetiva.

No desenvolvimento deste sistema a equipa de desenvolvimento deve: (i) adotar boas
práticas de identificação de requisitos e de análise e design de software OO; (ii)
implementar o núcleo principal do software em Java; (iii) adotar normas de codificação
reconhecidas e (iv) reutilizar o componente de gestão de utilizadores existente na T4J
baseado em identificador de utilizador (i.e. email) e palavra-passe (cf. documentação).
A designação comercial da plataforma e outros dados que venham a ser relevantes
devem ser especificados por configuração aquando da sua implantação.
