# Enunciado Adenda 1

##### [Voltar ao início](https://github.com/ajorgesantosp/upskill_java1_g1/blob/main/README.md)

## Novos requisitos

O gestor da organização dá início à publicação de uma tarefa. Nesse processo define a data da publicação, as datas de início e de fim de aceitação de candidaturas, e ainda um texto com indicações sobre o processo de candidatura. A data da publicação terá de ser superior à data atual, a data de início de candidaturas terá de ser superior à data da publicação, e a data de fim de candidaturas superior à respetiva data de início. A tarefa passa ao estado “publicada”.

O freelancer pode efetuar candidaturas a tarefas que tenham sido publicadas e que estejam em período de aceitação de candidaturas. Para o efeito define um orçamento contendo o obrigatoriamente o custo do serviço a executar, o tempo de execução, e opcionalmente alguma informação em formato PDF com documentos que considere relevantes para sustentar a sua candidatura. A tarefa passa ao estado “candidatada”. Podem existir várias candidaturas, por diferentes freelancers, para a mesma tarefa.

Após o período de submissão de candidaturas o gestor da organização inicia o processo de adjudicação. O processo consiste em escolher, para uma determinada tarefa, uma das candidaturas recebidas. Além da data de adjudicação também deve ser associado um texto justificativo da razão da escolha, e o sistema deverá enviar um email para o freelancer e outro para o colaborador que submeteu a tarefa, informando-os da adjudicação. A tarefa passa ao estado “em curso”.

O colaborador da organização regista o término de uma tarefa, definindo o valor real para a data de fim, um texto caracterizando a experiência com o freelancer e uma classificação (0-5) representativa do nível de satisfação com o trabalho desenvolvido. A tarefa passa ao estado “terminada”.

O colaborador pode, a qualquer instante após a adjudicação, alterar a tarefa. A tarefa passa ao estado “suspensa” ou “cancelada” consoante a opção tomada. Da mesma forma, o colaborador pode também reativar uma tarefa suspensa. Todos estes eventos devem ser registados, datados e terá de existir obrigatoriamente um texto justificando a necessidade da alteração.
