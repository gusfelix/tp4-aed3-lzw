## Participantes do Grupo

- André Leôncio Jales
- Andre Vieira Penchel
- Gustavo Alvarenga Ribeiro Carvalho
- Gustavo Pereira Felix

### Descrição do Trabalho

Nosso trabalho consiste na implementação de um sistema de backup compactado dos arquivos de dados e índices utilizando o algoritmo LZW. O sistema permite a criação de backups compactados e a recuperação dos mesmos, possibilitando ao usuário escolher a versão a ser recuperada.

### Experiência de Desenvolvimento

Durante o desenvolvimento deste projeto, enfrentamos diversos desafios que nos proporcionaram um grande aprendizado. A integração do algoritmo LZW para compactação e descompactação dos arquivos, bem como a implementação de um sistema de backup e recuperação, foram tarefas complexas, mas extremamente enriquecedoras.

### Classes e Métodos

#### `Main.java`

* **main(String args)** : Método principal que inicializa o sistema, exibe o menu e executa as operações selecionadas.

#### `Arquivo.java`

* **Arquivo()** : Construtor que inicializa o arquivo genérico.
* **abrir(String nomeArquivo)** : Abre um arquivo.
* **fechar()** : Fecha o arquivo.
* **ler(int posicao)** : Lê um registro do arquivo.
* **escrever(int posicao, byte[] dados)** : Escreve um registro no arquivo.
* **tamanho()** : Retorna o tamanho do arquivo.

#### `ArvoreBMais.java`

* **ArvoreBMais()** : Construtor que inicializa a árvore B+.
* **inserir(int chave, int endereco)** : Insere uma chave e o endereço na árvore B+.
* **buscar(int chave)** : Busca o endereço associado a uma chave.
* **remover(int chave)** : Remove uma chave e o endereço da árvore B+.

#### `HashExtensivel.java`

* **HashExtensivel()** : Construtor que inicializa a tabela hash extensível.
* **inserir(int chave, int endereco)** : Insere uma chave e o endereço na tabela hash.
* **buscar(int chave)** : Busca o endereço associado a uma chave.
* **remover(int chave)** : Remove uma chave e o endereço da tabela hash.

#### `ListaInvertida.java`

* **ListaInvertida()** : Construtor que inicializa a lista invertida.
* **inserir(String termo, int idTarefa)** : Insere um termo e o ID da tarefa na lista invertida.
* **buscar(String termo)** : Busca tarefas associadas a um termo.
* **remover(String termo, int idTarefa)** : Remove um termo e o ID da tarefa da lista invertida.

#### `LZW.java`

* **codifica(byte[] dados)** : Compacta os dados usando o algoritmo LZW.
* **decodifica(byte[] dadosCompactados)** : Descompacta os dados usando o algoritmo LZW.

#### `ParIDEndereco.java`

* **ParIDEndereco(int id, long endereco)** : Construtor que inicializa o par ID-endereço.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `Registro.java`

* **Registro()** : Construtor que inicializa o registro genérico.
* **inserir(int chave, int endereco)** : Insere uma chave e o endereço no registro.
* **buscar(int chave)** : Busca o endereço associado a uma chave no registro.
* **remover(int chave)** : Remove uma chave e o endereço do registro.

#### `RegistroArvoreBMais.java`

* **RegistroArvoreBMais()** : Construtor que inicializa o registro da árvore B+.
* **inserir(int chave, int endereco)** : Insere uma chave e o endereço no registro.
* **buscar(int chave)** : Busca o endereço associado a uma chave no registro.
* **remover(int chave)** : Remove uma chave e o endereço do registro.

#### `RegistroHashExtensivel.java`

* **RegistroHashExtensivel()** : Construtor que inicializa o registro da tabela hash extensível.
* **inserir(int chave, int endereco)** : Insere uma chave e o endereço no registro.
* **buscar(int chave)** : Busca o endereço associado a uma chave no registro.
* **remover(int chave)** : Remove uma chave e o endereço do registro.

#### `VetorDeBits.java`

* **VetorDeBits()** : Construtor que inicializa o vetor de bits.
* **setBit(int posicao, boolean valor)** : Define o valor de um bit na posição especificada.
* **getBit(int posicao)** : Retorna o valor de um bit na posição especificada.

#### `ControleBackups.java`

* **ControleBackups()** : Construtor que inicializa o controle de backups.
* **iniciar()** : Inicia o controle de backups, exibindo o menu e executando as operações selecionadas.
* **fazerBackup()** : Cria um novo backup compactado usando o algoritmo LZW.
* **restaurarBackup()** : Restaura um backup existente descompactando os arquivos.
* **excluirBackup()** : Exclui um backup existente.

#### `ControleCategorias.java`

* **ControleCategorias()** : Construtor que inicializa os arquivos de categorias e tarefas e a visão de categorias.
* **iniciar()** : Inicia o controle de categorias, exibindo o menu e executando as operações selecionadas.
* **incluirCategoria()** : Inclui uma nova categoria.
* **buscarCategoria()** : Busca uma categoria pelo nome.
* **alterarCategoria()** : Altera uma categoria existente.
* **excluirCategoria()** : Exclui uma categoria pelo nome.
* **listarCategorias()** : Lista todas as categorias.

#### `ControleRotulos.java`

* **ControleRotulos()** : Construtor que inicializa os arquivos de rótulos e tarefas e a visão de rótulos.
* **iniciar()** : Inicia o controle de rótulos, exibindo o menu e executando as operações selecionadas.
* **incluirRotulo()** : Inclui um novo rótulo.
* **buscarRotulo()** : Busca um rótulo pelo nome.
* **alterarRotulo()** : Altera um rótulo existente.
* **excluirRotulo()** : Exclui um rótulo pelo nome.
* **listarRotulos()** : Lista todos os rótulos.

#### `ControleTarefas.java`

* **ControleTarefas()** : Construtor que inicializa os arquivos de tarefas, categorias e rótulos e a visão de tarefas.
* **iniciar()** : Inicia o controle de tarefas, exibindo o menu e executando as operações selecionadas.
* **incluirTarefa()** : Inclui uma nova tarefa.
* **buscarTarefa()** : Busca uma tarefa pelo nome.
* **alterarTarefa()** : Altera uma tarefa existente.
* **excluirTarefa()** : Exclui uma tarefa pelo nome.
* **listarTarefas()** : Lista todas as tarefas.
* **listarTarefasPorCategoria()** : Lista tarefas por categoria.
* **listarTarefasPorRotulo()** : Lista tarefas por rótulo.

#### `ArquivoCategoria.java`

* **ArquivoCategoria()** : Construtor que inicializa o arquivo de categorias e o índice indireto de nomes.
* **create(Categoria c)** : Cria uma nova categoria.
* **read(String nome)** : Lê uma categoria pelo nome.
* **delete(String nome)** : Exclui uma categoria pelo nome.
* **update(Categoria novaCategoria)** : Atualiza uma categoria existente.
* **readAll()** : Lê todas as categorias.

#### `ArquivoRotulo.java`

* **ArquivoRotulo()** : Construtor que inicializa o arquivo de rótulos e os índices indiretos.
* **create(Rotulo r)** : Cria um novo rótulo.
* **read(String nome)** : Lê um rótulo pelo nome.
* **delete(String nome)** : Exclui um rótulo pelo nome.
* **update(Rotulo novoRotulo)** : Atualiza um rótulo existente.
* **readAll()** : Lê todos os rótulos.

#### `ArquivoTarefa.java`

* **ArquivoTarefa()** : Construtor que inicializa o arquivo de tarefas e os índices indiretos.
* **create(Tarefa t)** : Cria uma nova tarefa.
* **read(String nome)** : Lê uma tarefa pelo nome.
* **delete(String nome)** : Exclui uma tarefa pelo nome.
* **update(Tarefa novaTarefa)** : Atualiza uma tarefa existente.
* **readAll()** : Lê todas as tarefas.

#### `Backup.java`

* **Backup()** : Construtor que inicializa o backup.
* **fazerBackup()** : Cria um novo backup compactado usando o algoritmo LZW.
* **restaurarBackup()** : Restaura um backup existente descompactando os arquivos.
* **listarBackups()** : Lista todos os backups disponíveis.

#### `Categoria.java`

* **Categoria()** : Construtor que inicializa a categoria.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `ParCategoriaId.java`

* **ParCategoriaId(int id, String nome)** : Construtor que inicializa o par categoria-ID.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `ParNomeId.java`

* **ParNomeId(int id, String nome)** : Construtor que inicializa o par nome-ID.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `ParRotuloTarefa.java`

* **ParRotuloTarefa(int idRotulo, int idTarefa)** : Construtor que inicializa o par rótulo-tarefa.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `ParTarefaRotulo.java`

* **ParTarefaRotulo(int idTarefa, int idRotulo)** : Construtor que inicializa o par tarefa-rótulo.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `Rotulo.java`

* **Rotulo()** : Construtor que inicializa o rótulo.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `Tarefa.java`

* **Tarefa()** : Construtor que inicializa a tarefa.
* **Getters e Setters** : Métodos para acessar e modificar os atributos da classe.

#### `TextProcessor.java`

* **TextProcessor()** : Construtor que inicializa o processador de texto.
* **processarTexto(String texto)** : Processa o texto removendo stop words e normalizando os termos.

#### `VisaoBackups.java`

* **VisaoBackups()** : Construtor que inicializa a visão de backups.
* **exibirMenu()** : Exibe o menu de backups.
* **exibirBackups()** : Exibe a lista de backups disponíveis.

#### `VisaoCategorias.java`

* **VisaoCategorias()** : Construtor que inicializa a visão de categorias.
* **exibirMenu()** : Exibe o menu de categorias.
* **exibirCategorias()** : Exibe a lista de categorias.

#### `VisaoRotulos.java`

* **VisaoRotulos()** : Construtor que inicializa a visão de rótulos.
* **exibirMenu()** : Exibe o menu de rótulos.
* **exibirRotulos()** : Exibe a lista de rótulos.

#### `VisaoTarefas.java`

* **VisaoTarefas()** : Construtor que inicializa a visão de tarefas.
* **exibirMenu()** : Exibe o menu de tarefas.
* **exibirTarefas()** : Exibe a lista de tarefas.

### Checklist

- **Há uma rotina de compactação usando o algoritmo LZW para fazer backup dos arquivos?**
  Sim, implementamos uma rotina de compactação que utiliza o algoritmo LZW para fazer backup dos arquivos de dados e índices. Cada arquivo é tratado como um vetor de bytes e compactado sequencialmente em um único arquivo de backup.
- **Há uma rotina de descompactação usando o algoritmo LZW para recuperação dos arquivos?**
  Sim, implementamos uma rotina de descompactação que utiliza o algoritmo LZW para recuperar os arquivos de dados e índices a partir do backup compactado. O usuário pode escolher a versão do backup a ser recuperada.
- **O usuário pode escolher a versão a recuperar?**
  Sim, o sistema permite que o usuário escolha a versão do backup a ser recuperada. Todas as versões de backup são listadas e o usuário pode selecionar a desejada para recuperação.
- **Qual foi a taxa de compressão alcançada por esse backup?**
  A taxa de compressão alcançada foi de aproximadamente 26.95%. Comparando o tamanho dos arquivos originais (2092 bytes) com os arquivos compactados (1528 bytes), observamos uma redução significativa no tamanho dos dados armazenados.
- **O trabalho está funcionando corretamente?**
  Sim, o trabalho está funcionando corretamente. Todas as funcionalidades de compactação, descompactação, criação e recuperação de backups foram testadas e estão operando conforme o esperado.
- **O trabalho está completo?**
  Sim, o trabalho está completo. Implementamos todas as funcionalidades requisitadas, incluindo a compactação e descompactação dos arquivos, a criação de backups e a recuperação de versões específicas.
- **O trabalho é original e não a cópia de um trabalho de um colega?**
  Sim, o trabalho é original e foi desenvolvido inteiramente pelo nosso grupo.
