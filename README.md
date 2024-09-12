# Trabalho Prático 1 - CRUD Genérico - AEDS 3

## Descrição do Projeto

Este projeto implementa um CRUD genérico para gerenciar tarefas. As operações de CRUD (Create, Read, Update, Delete) são realizadas em um arquivo de dados, permitindo a inclusão, leitura, alteração e exclusão de registros de qualquer entidade. Neste projeto, a entidade gerenciada é a Tarefa.

## Estrutura do Registro

Os registros são compostos por três campos:

- **Lápide**: Byte que indica se o registro é válido ou excluído.
- **Indicador de tamanho do registro**: Número inteiro (short) que indica o tamanho do vetor de bytes.
- **Vetor de bytes**: Bytes que descrevem a entidade (obtido por meio do método `toByteArray()` do próprio objeto da entidade).

## Operações do Arquivo

As operações de CRUD são implementadas na classe `Arquivo`. As operações não solicitam dados ao usuário nem apresentam informações para ele. As interfaces dos métodos são:

- `int create(T objeto)`: Recebe um objeto e o armazena no arquivo, gerando um ID sequencial.
- `T read(int id)`: Recebe um ID e retorna o objeto correspondente.
- `boolean update(T objeto)`: Recebe um objeto e atualiza o registro no arquivo.
- `boolean delete(int id)`: Recebe um ID e marca o registro como excluído.

## Índice

Foi utilizado um índice direto implementado com uma tabela hash extensível para armazenar a chave primária (ID) e o endereço do registro. A tabela hash oferece métodos para criar, ler, atualizar e deletar elementos.

## Classes e Métodos

### Classe `Tarefa`

Representa a entidade Tarefa com atributos como ID, nome, data de criação, data de conclusão, status e prioridade. Implementa a interface `Registro`.

### Classe `Arquivo<T extends Registro>`

Gerencia o arquivo de dados e as operações de CRUD. Utiliza a tabela hash extensível para o índice direto.

### Classe `ParIDEndereco`

Implementa a interface `RegistroHashExtensivel` e representa a chave primária e o endereço do registro.

### Classe `HashExtensivel<T extends RegistroHashExtensivel<T>>`

Implementa a tabela hash extensível para gerenciar o índice direto.

### Interface `Registro`

Define os métodos que devem ser implementados pelas entidades gerenciadas pelo CRUD.

### Interface `RegistroHashExtensivel<T>`

Define os métodos que os objetos a serem incluídos na tabela hash extensível devem conter.

## Experiência de Desenvolvimento

Implementamos todos os requisitos especificados. A operação mais desafiadora foi a criação da classe `Tarefa` e a ligação desta com as operações de CRUD. No entanto, conseguimos alcançar os resultados esperados e o projeto está funcionando corretamente.

## Checklist

- **O trabalho possui um índice direto implementado com a tabela hash extensível?**
  Sim, implementamos um índice direto utilizando uma tabela hash extensível para armazenar a chave primária (ID) e o endereço do registro. A tabela hash oferece métodos para criar, ler, atualizar e deletar elementos.
- **A operação de inclusão insere um novo registro no fim do arquivo e no índice e retorna o ID desse registro?**
  Sim, a operação de inclusão (`create`) insere um novo registro no fim do arquivo e atualiza o índice com o novo ID e o endereço do registro. O método retorna o ID gerado para o novo registro.
- **A operação de busca retorna os dados do registro, após localizá-lo por meio do índice direto?**
  Sim, a operação de busca (`read`) utiliza o índice direto para localizar o endereço do registro no arquivo e retorna os dados do registro correspondente.
- **A operação de alteração altera os dados do registro e trata corretamente as reduções e aumentos no espaço do registro?**
  Sim, a operação de alteração (`update`) atualiza os dados do registro no arquivo, tratando corretamente as mudanças no tamanho do registro. Se o novo registro for maior ou menor que o original, o método ajusta o espaço ocupado no arquivo.
- **A operação de exclusão marca o registro como excluído e o remove do índice direto?**
  Sim, a operação de exclusão (`delete`) marca o registro como excluído no arquivo e remove a entrada correspondente do índice direto.
- **O trabalho está funcionando corretamente?**
  Sim, todas as operações de CRUD foram testadas e estão funcionando corretamente, conforme especificado no enunciado do trabalho.
- **O trabalho está completo?**
  Sim, implementamos todos os requisitos especificados no enunciado do trabalho, incluindo a criação, leitura, atualização e exclusão de registros, bem como a implementação do índice direto com a tabela hash extensível.
- **O trabalho é original e não a cópia de um trabalho de outro grupo?**
  Sim, o trabalho é original e foi desenvolvido exclusivamente pelo nosso grupo, sem copiar de outros grupos. Com apoio do material disponibilizado pelo professor.

## Autores

- André Leôncio Jales
- Andre Vieira Penchel
- Gustavo Alvarenga Ribeiro Carvalho
- Gustavo Pereira Felix
