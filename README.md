## Participantes do Grupo

- André Leôncio Jales
- Andre Vieira Penchel
- Gustavo Alvarenga Ribeiro Carvalho
- Gustavo Pereira Felix

### Descrição do Trabalho

Nosso trabalho consiste na implementação de um sistema de gerenciamento de tarefas, categorias e rótulos utilizando estruturas de dados avançadas como Hash Extensível, Árvores B+ e Lista Invertida. O sistema permite a criação, leitura, atualização e exclusão (CRUD) de tarefas, categorias e rótulos, além de manter índices diretos e indiretos para otimizar as operações de busca e manipulação de dados.

### Experiência de Desenvolvimento

Durante o desenvolvimento deste projeto, enfrentamos diversos desafios que nos proporcionaram um grande aprendizado. A integração das estruturas de dados avançadas, como Hash Extensível e Árvores B+, com as operações de CRUD e a criação de índices diretos e indiretos para otimizar as buscas foram tarefas complexas, mas extremamente enriquecedoras. Embora as estruturas tenham sido fornecidas pelo professor, tivemos que entender seu funcionamento e aplicá-las corretamente no nosso sistema.

### Classes e Métodos

#### `Main.java`

* **main(String args)** : Método principal que inicializa o sistema, exibe o menu e executa as operações selecionadas.

#### `MenuTarefas.java`

* **MenuTarefas()** : Construtor que inicializa o menu de tarefas.
* **exibirMenu()** : Exibe o menu de tarefas e executa as operações selecionadas.

#### `VisaoTarefas.java`

* **VisaoTarefas()** : Construtor que inicializa a visão de tarefas.
* **menu()** : Exibe o menu de tarefas e executa as operações selecionadas.
* **incluirTarefa()** : Inclui uma nova tarefa.
* **buscarTarefa()** : Busca uma tarefa pelo nome.
* **alterarTarefa()** : Altera uma tarefa existente.
* **excluirTarefa()** : Exclui uma tarefa pelo nome.
* **listarTarefas()** : Lista todas as tarefas.
* **listarTarefasPorCategoria()** : Lista tarefas por categoria.
* **listarTarefasPorRotulo()** : Lista tarefas por rótulo.
* **listarTarefasPorTermo()** : Lista tarefas por termo.

#### `VisaoRotulos.java`

* **VisaoRotulos()** : Construtor que inicializa a visão de rótulos.
* **menu()** : Exibe o menu de rótulos e executa as operações selecionadas.
* **incluirRotulo()** : Inclui um novo rótulo.
* **buscarRotulo()** : Busca um rótulo pelo nome.
* **alterarRotulo()** : Altera um rótulo existente.
* **excluirRotulo()** : Exclui um rótulo pelo nome.
* **listarRotulos()** : Lista todos os rótulos.
* **listarTarefasPorRotulo()** : Lista tarefas associadas a um rótulo.

#### `VisaoCategorias.java`

* **VisaoCategorias()** : Construtor que inicializa a visão de categorias.
* **menu()** : Exibe o menu de categorias e executa as operações selecionadas.
* **incluirCategoria()** : Inclui uma nova categoria.
* **buscarCategoria()** : Busca uma categoria pelo nome.
* **alterarCategoria()** : Altera uma categoria existente.
* **excluirCategoria()** : Exclui uma categoria pelo nome.
* **listarCategorias()** : Lista todas as categorias.

#### `TextProcessor.java`

* **TextProcessor()** : Construtor que inicializa o processador de texto.
* **removerStopWords(String texto)** : Remove as stop words de um texto.
* **tokenizar(String texto)** : Tokeniza um texto em palavras.

#### `stopWords.txt`

Arquivo contendo uma lista de stop words que serão removidas dos textos processados.

#### `Tarefa.java`

* **Tarefa()** : Construtor padrão.
* **Tarefa(int idCategoria, String nome, LocalDate criacao, short status, short prioridade)** : Construtor com parâmetros.
* **Tarefa(int id, int idCategoria, String nome, LocalDate criacao, LocalDate conclusao, short status, short prioridade)** : Construtor completo.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.
* **toByteArray()** : Serializa o objeto em um array de bytes.
* **fromByteArray(byte b)** : Desserializa um array de bytes em um objeto Tarefa.
* **toString()** : Retorna uma representação em string do objeto.

#### `Rotulo.java`

* **Rotulo()** : Construtor padrão.
* **Rotulo(int id, String rotulo)** : Construtor com parâmetros.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.
* **toByteArray()** : Serializa o objeto em um array de bytes.
* **fromByteArray(byte b)** : Desserializa um array de bytes em um objeto Rotulo.
* **toString()** : Retorna uma representação em string do objeto.

#### `ParTarefaRotulo.java`

* **ParTarefaRotulo(int idTarefa, int idRotulo)** : Construtor que inicializa o par tarefa-rótulo.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `ParRotuloTarefa.java`

* **ParRotuloTarefa(int idRotulo, int idTarefa)** : Construtor que inicializa o par rótulo-tarefa.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `ParNomeId.java`

* **ParNomeId(String nome, int id)** : Construtor que inicializa o par nome-ID.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `ParCategoriaId.java`

* **ParCategoriaId(int idCategoria, int id)** : Construtor que inicializa o par categoria-ID.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `Categoria.java`

* **Categoria()** : Construtor padrão.
* **Categoria(int id, String nome)** : Construtor com parâmetros.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.
* **toByteArray()** : Serializa o objeto em um array de bytes.
* **fromByteArray(byte b)** : Desserializa um array de bytes em um objeto Categoria.
* **toString()** : Retorna uma representação em string do objeto.

#### `ArquivoTarefa.java`

* **ArquivoTarefa()** : Construtor que inicializa o arquivo de tarefas e os índices indiretos.
* **create(Tarefa t)** : Cria uma nova tarefa.
* **read(int id)** : Lê uma tarefa pelo ID.
* **delete(int id)** : Exclui uma tarefa pelo ID.
* **update(Tarefa novaTarefa)** : Atualiza uma tarefa existente.
* **readAll()** : Lê todas as tarefas.
* **readByCategoria(int categoriaId)** : Lê tarefas por categoria.
* **readByRotulo(int rotuloId)** : Lê tarefas por rótulo.
* **readByTermo(String termo)** : Lê tarefas por termo usando índice invertido.

#### `ArquivoRotulo.java`

* **ArquivoRotulo()** : Construtor que inicializa o arquivo de rótulos e os índices indiretos.
* **create(Rotulo r)** : Cria um novo rótulo.
* **read(String nome)** : Lê um rótulo pelo nome.
* **delete(String nome)** : Exclui um rótulo pelo nome.
* **update(Rotulo novoRotulo)** : Atualiza um rótulo existente.
* **readAll()** : Lê todos os rótulos.
* **readTarefasByRotulo(String nome)** : Lê tarefas associadas a um rótulo.

#### `ArquivoCategoria.java`

* **ArquivoCategoria()** : Construtor que inicializa o arquivo de categorias e o índice indireto de nomes.
* **create(Categoria c)** : Cria uma nova categoria.
* **read(String nome)** : Lê uma categoria pelo nome.
* **delete(String nome)** : Exclui uma categoria pelo nome.
* **update(Categoria novaCategoria)** : Atualiza uma categoria existente.
* **readAll()** : Lê todas as categorias.

#### `ControleTarefas.java`

* **ControleTarefas()** : Construtor que inicializa os arquivos de tarefas e categorias e a visão de tarefas.
* **iniciar()** : Inicia o controle de tarefas, exibindo o menu e executando as operações selecionadas.
* **incluirTarefa()** : Inclui uma nova tarefa.
* **buscarTarefa()** : Busca uma tarefa pelo nome.
* **alterarTarefa()** : Altera uma tarefa existente.
* **excluirTarefa()** : Exclui uma tarefa pelo nome.
* **listarTarefas()** : Lista todas as tarefas.
* **listarTarefasPorCategoria()** : Lista tarefas por categoria.
* **listarTarefasPorRotulo()** : Lista tarefas por rótulo.
* **listarTarefasPorTermo()** : Lista tarefas por termo.

#### `ControleRotulos.java`

* **ControleRotulos()** : Construtor que inicializa os arquivos de rótulos e tarefas e a visão de rótulos.
* **iniciar()** : Inicia o controle de rótulos, exibindo o menu e executando as operações selecionadas.
* **incluirRotulo()** : Inclui um novo rótulo.
* **buscarRotulo()** : Busca um rótulo pelo nome.
* **alterarRotulo()** : Altera um rótulo existente.
* **excluirRotulo()** : Exclui um rótulo pelo nome.
* **listarRotulos()** : Lista todos os rótulos.
* **listarTarefasPorRotulo()** : Lista tarefas associadas a um rótulo.

#### `ControleCategorias.java`

* **ControleCategorias()** : Construtor que inicializa os arquivos de categorias e tarefas e a visão de categorias.
* **iniciar()** : Inicia o controle de categorias, exibindo o menu e executando as operações selecionadas.
* **incluirCategoria()** : Inclui uma nova categoria.
* **buscarCategoria()** : Busca uma categoria pelo nome.
* **alterarCategoria()** : Altera uma categoria existente.
* **excluirCategoria()** : Exclui uma categoria pelo nome.
* **listarCategorias()** : Lista todas as categorias.

#### `RegistroHashExtensivel.java`

* **RegistroHashExtensivel()** : Construtor que inicializa o registro da tabela hash extensível.
* **inserir(int chave, int endereco)** : Insere uma chave e o endereço no registro.
* **buscar(int chave)** : Busca o endereço associado a uma chave no registro.
* **remover(int chave)** : Remove uma chave e o endereço do registro.

#### `RegistroArvoreBMais.java`

* **RegistroArvoreBMais()** : Construtor que inicializa o registro da árvore B+.
* **inserir(int chave, int endereco)** : Insere uma chave e o endereço no registro.
* **buscar(int chave)** : Busca o endereço associado a uma chave no registro.
* **remover(int chave)** : Remove uma chave e o endereço do registro.

#### `Registro.java`

* **Registro()** : Construtor que inicializa o registro genérico.
* **inserir(int chave, int endereco)** : Insere uma chave e o endereço no registro.
* **buscar(int chave)** : Busca o endereço associado a uma chave no registro.
* **remover(int chave)** : Remove uma chave e o endereço do registro.

#### `ParIDEndereco.java`

* **ParIDEndereco(int id, int endereco)** : Construtor que inicializa o par ID-endereço.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `ListaInvertida.java`

* **ListaInvertida()** : Construtor que inicializa a lista invertida.
* **inserir(String termo, int idTarefa)** : Insere um termo e o ID da tarefa na lista invertida.
* **buscar(String termo)** : Busca tarefas associadas a um termo.
* **remover(String termo, int idTarefa)** : Remove um termo e o ID da tarefa da lista invertida.

#### `HashExtensivel.java`

* **HashExtensivel()** : Construtor que inicializa a tabela hash extensível.
* **inserir(int chave, int endereco)** : Insere uma chave e o endereço na tabela hash.
* **buscar(int chave)** : Busca o endereço associado a uma chave.
* **remover(int chave)** : Remove uma chave e o endereço da tabela hash.

#### `ElementoLista.java`

* **ElementoLista(int id, int proximo)** : Construtor que inicializa o elemento da lista.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `ArvoreBMais.java`

* **ArvoreBMais()** : Construtor que inicializa a árvore B+.
* **inserir(int chave, int endereco)** : Insere uma chave e o endereço na árvore B+.
* **buscar(int chave)** : Busca o endereço associado a uma chave.
* **remover(int chave)** : Remove uma chave e o endereço da árvore B+.

#### `Arquivo.java`

* **Arquivo()** : Construtor que inicializa o arquivo genérico.
* **abrir(String nomeArquivo)** : Abre um arquivo.
* **fechar()** : Fecha o arquivo.
* **ler(int posicao)** : Lê um registro do arquivo.
* **escrever(int posicao, byte[] dados)** : Escreve um registro no arquivo.
* **tamanho()** : Retorna o tamanho do arquivo.

### Perguntas Frequentes

1. **O índice invertido com os termos das tarefas foi criado usando a classe ListaInvertida?**
   Sim, o índice invertido com os termos das tarefas foi criado utilizando a classe `ListaInvertida`. Esta classe permite inserir termos e IDs de tarefas, buscar tarefas associadas a um termo e remover termos e IDs de tarefas da lista invertida.

2. **O CRUD de rótulos foi implementado?**
   Sim, o CRUD de rótulos foi implementado. A classe `ArquivoRotulo` contém métodos para criar (`create`), ler (`read`), atualizar (`update`) e excluir (`delete`) rótulos. Além disso, é possível listar todos os rótulos e ler tarefas associadas a um rótulo específico.

3. **No arquivo de tarefas, os rótulos são incluídos, alterados e excluídos em uma árvore B+?**
   Sim, no arquivo de tarefas, os rótulos são incluídos, alterados e excluídos utilizando uma árvore B+. A classe `ArvoreBMais` é responsável por gerenciar a inserção, busca e remoção de chaves e endereços, garantindo uma estrutura eficiente para essas operações.

4. **É possível buscar tarefas por palavras usando o índice invertido?**
   Sim, é possível buscar tarefas por palavras utilizando o índice invertido. A classe `ListaInvertida` permite buscar tarefas associadas a um termo específico, facilitando a localização de tarefas com base em palavras-chave.

5. **É possível buscar tarefas por rótulos usando uma árvore B+?**
   Sim, é possível buscar tarefas por rótulos utilizando uma árvore B+. A classe `ArvoreBMais` permite buscar endereços associados a chaves específicas, o que facilita a localização de tarefas com base em rótulos.

6. **O trabalho está completo?**
   Sim, o trabalho está completo. Todas as funcionalidades especificadas foram implementadas, incluindo a criação, leitura, atualização e exclusão de tarefas, categorias e rótulos, além da utilização de estruturas de dados avançadas para otimizar as operações de busca e manipulação de dados.

7. **O trabalho é original e não a cópia de um trabalho de um colega?**
   Sim, o trabalho é original e não é a cópia de um trabalho de um colega. Todo o código foi desenvolvido pela nossa equipe, seguindo as especificações e requisitos do projeto.
