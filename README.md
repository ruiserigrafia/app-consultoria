
# App Consultoria
![Atualização](https://img.shields.io/github/issues/detail/last-update/badges/shields/979.svg?style=plastic)

## Sobre o proejto
Projeto de desenvolvimento de uma aplicação, voltada para o gerenciamento de uma consultoria de avaliação física e nutricional.
## Ambiente para desenvolvimento
![JAVA](https://img.shields.io/badge/Java-Desenvolvimento-blue.svg)
![JAVAFX](https://img.shields.io/badge/JavaFX-Gráficos-blue.svg)
![FXML](https://img.shields.io/badge/FXML-Marcação-blue.svg)
![SceneBuilder](https://img.shields.io/badge/Gluon-Construtor-blue.svg)
![MySQL](https://img.shields.io/badge/MySQL-SGDB-blue.svg)

- [Java](https://www.java.com/pt_BR/) é uma liguagem de programa orientada a objeto.
- [JavaFX](https://openjfx.io/) é uma biblioteca gráfica da plataforma Java.
- [SeneBuilder Gluon](https://gluonhq.com/) é um construtor de grafos FXML.
- [MySQL](https://www.mysql.com/) é um SGDB com versão livre e comercial.

## Padrões para desenvolvimento
### MVC - Model View Controller
É um padrão de desenvolvimento que visa separar o projeto em três camadas, dvidindo as funcionalidades em:
- Model (Modelo do negócio): nesta camada fica tudo aquilo que se refere as regras do negócio do universo proposto para o sistema.
- View (Visão): esta camada é responsável pela interação do usuário com sistema.
- Controller (controle): esta camada fica responsável em realizar e controlar a comunicação com as Models e se preciso, com a camada de banco de dados.
### DAO - Data Access Object
É um padrão para o acesso de banco dados, fica responsável pela persistências dos dados de origem do banco de dados, separando as regras do mesmo com as de negócio.
## Preparação do ambiente
### JAVA
SE JDK versão 11 é a utilizada neste projeto e esta disponível em:
[ORACLE JAVA SE](https://www.oracle.com/technetwork/java/javase/downloads/index.html).

Baixar e instalar e estará pronto para utlização tanto com um editor de texto ou com uma [IDE](https://pt.wikipedia.org/wiki/Ambiente_de_desenvolvimento_integrado).
### JAVAFX
O javafx pode ser usado com um linguagem de marcação chamada FXML, que é uma linguagem de marcação que usa XML para, construção de graficos, basseado em nodos. E com classes de injeção no javaFX, fazem a associação dos nodos em suas classes correpondentes.

OpenJFX versão 11 é a versão utilizada neste projeto e esta disponível em: [Gluon javaFX](http://gluonhq.com/download/javafx-11-0-1-sdk-windows/).

Baixar e extrair o conteúdo para um diretório de sua preferência.

Link da documentação da [API](https://openjfx.io/javadoc/11/).

#### Configurando o IntelliJ
**Projetos não modular**

Vá para File -> Project Structure -> Projecte defina o SDK do projeto como 11 . Você também pode definir o nível de idioma para 11.
![JAVAFX DOC1](https://openjfx.io/openjfx-docs/images/ide/intellij/ide/idea03.png)
Vá para File -> Project Structure -> Libraries e adicione o SDK do JavaFX 11 como uma biblioteca ao projeto. Aponte para a pasta lib do JavaFX SDK.
![JAVAFX DOC2](https://openjfx.io/openjfx-docs/images/ide/intellij/ide/idea04.png)
Depois que a biblioteca for aplicada, as classes JavaFX serão reconhecidas pelo IDE.
![JAVAFX DOC3](https://openjfx.io/openjfx-docs/images/ide/intellij/ide/idea05.png)

    Aviso: Se você executar agora o projeto, ele será compilado, mas você receberá este erro:
    Error: JavaFX runtime components are missing, and are required to run this application
    Este erro é mostrado desde que o ativador do Java 11 verifica se a classe principal se estende javafx.application.Application. Se for esse o caso, é necessário ter o javafx.graphicsmódulo no caminho do módulo.

Para resolver o problema, clique em Run -> Edit Configurations...e adicione estas opções de VM:
- No Windows: *--module-path "\path\to\javafx-sdk-11\lib" --add-modules=javafx.controls,javafx.fxml*
- No Linux/Mac: *--module-path /path/to/javafx-sdk-11/lib --add-modules=javafx.controls,javafx.fxml*

Observe que o projeto padrão criado pelo IntelliJ usa FXML, portanto, javafx.fxml é necessário junto com javafx.controls. Se o seu projeto usa outros módulos, você precisará adicioná-los também. 

![JAVAFX DOC4](https://openjfx.io/openjfx-docs/images/ide/intellij/ide/idea06.png)

Como alternativa, você pode definir uma variável global que possa ser usada em projetos futuros. Vá para Preferences (File -> Settings) -> Appearance & Behavior -> Path Variablese defina o nome da variável como PATH_TO_FXe navegue até a pasta lib do JavaFX SDK para definir seu valor e clique em aplicar.
![JAVAFX DOC5](https://openjfx.io/openjfx-docs/images/ide/intellij/ide/idea07.png)
Então você pode se referir a essa variável global ao definir as opções da VM como:

    --module-path ${PATH_TO_FX} --add-modules=javafx.controls,javafx.fxml

**Projeto Modular**

Faça o download dos [jmods do JavaFX](https://gluonhq.com/products/javafx/) apropriados para o seu sistema operacional e descompacte-o em um local desejado, por exemplo .

    /Users/your-user/Downloads/javafx-jmods-11

IDE

Siga estas etapas para criar um projeto modular do JavaFX e usar as ferramentas do IDE para criá-lo e executá-lo. Alternativamente, você pode baixar um projeto semelhante a partir daqui .

Vá para **File -> Project Structure -> Project** e defina o SDK do projeto como 11 . Você também pode definir o nível de idioma como 11 e alterar o diretório de saída do compilador padrão **out** para **mods**.
![JAVAFX DOC6](https://openjfx.io/openjfx-docs/images/ide/intellij/modular/ide/idea00.png)
Vá para **File -> Project Structure -> Libraries** e adicione o SDK do JavaFX 11 como uma biblioteca ao projeto. Aponte para a **lib** pasta do JavaFX SDK.

Adicione a **module-info** classe, incluindo os módulos necessários **javafx.controls** e **javafx.fxml**. Como o FXML usa reflexão para acessar o controlador no módulo, isso precisa ser aberto para **javafx.fxml**. Finalmente, exporte o pacote **org.openjfx**.

![JAVAFX DOC7](https://openjfx.io/openjfx-docs/images/ide/intellij/modular/ide/idea01.png)

Com base nessa classe [MainApp](https://openjfx.io/IDE/IntelliJ/Modular/Java/hellofx/hellofx/src/org/openjfx/MainApp.java), adicione seu conteúdo à classe principal do projeto. Em seguida, adicione o [controlador](https://openjfx.io/IDE/IntelliJ/Modular/Java/hellofx/hellofx/src/org/openjfx/FXMLController.java) e o [FXML](https://openjfx.io/IDE/IntelliJ/Modular/Java/hellofx/hellofx/src/org/openjfx/scene.fxml) e os arquivos [css](https://openjfx.io/IDE/IntelliJ/Modular/Java/hellofx/hellofx/src/org/openjfx/styles.css).

Clique Run -> Edit Configurations...e adicione estas opções de VM:

- No Windows: *--module-path "%PATH_TO_FX%;mods\production"*
- No Linux/Mac: *--module-path $PATH_TO_FX:mods/production*

![JAVAFX DOC8](https://openjfx.io/openjfx-docs/images/ide/intellij/modular/ide/idea03.png)
Clique em aplicar e feche a caixa de diálogo.

Clique Run -> Run...para executar o projeto.

Para criar uma imagem de tempo de execução, execute os seguintes comandos:

| Windows | Linux/Mac |
|--- |--- |
|set PATH_TO_FX_MODS="path\to\javafx-jmods-11"|export PATH_TO_FX_MODS=path/to/javafx-jmods-11|
|jlink --module-path "%PATH_TO_FX_MODS%;mods\production" --add-modules=hellofx --output jre|$JAVA_HOME/bin/jlink --module-path $PATH_TO_FX_MODS:mods/production --add-modules=hellofx --output  |
|jre\bin\java -m hellofx/org.openjfx.MainApp|jre|
||jre/bin/java -m hellofx/org.openjfx.MainApp|


#### Configurando o NetBeans
**Projeto não modular**

Baixe o JavaFX SDK apropriado para o seu sistema operacional e descompacte-o em um local desejado, por exemplo.

    /Users/your-user/Downloads/javafx-sdk-11

Crie um novo global **Library** sob **Tools -> Libraries -> New Library**. Nomeie-o **JavaFX11** e inclua os jars sob a **lib** pasta do JavaFX 11 .

![javax NetBeans0](https://openjfx.io/openjfx-docs/images/ide/netbeans/ide/netbeans00.png)

    Nota: Certifique-se de não adicionar o **src.zip** arquivo, pois isso causará uma exceção ao executar o projeto.
    

Vá para Properties -> Libraries -> Compile -> Module-path -> + -> Add Library e adicione a JavaFX11biblioteca. Vá para Properties -> Libraries -> Compile -> Class-path -> + -> Add JAR/Folder e adicione os jars JavaFX 11 necessários ao projeto.
![javax NetBeans1](https://openjfx.io/openjfx-docs/images/ide/netbeans/ide/netbeans03.png)

Depois que o caminho de classe for definido, as classes JavaFX serão reconhecidas pelo IDE.
![Javax NetBeans2](https://openjfx.io/openjfx-docs/images/ide/netbeans/ide/netbeans04.png)
Você pode adicionar uma classe principal Main, com base nesta um , com um FXML arquivo e um [controller](https://openjfx.io/IDE/NetBeans/Non-Modular/Java/hellofx/src/hellofx/Controller.java).

![javax NetBeans3](https://openjfx.io/openjfx-docs/images/ide/netbeans/ide/netbeans05.png)

    Aviso: Se você executar agora o projeto, ele será compilado, mas você receberá este erro:

    Error: JavaFX runtime components are missing, and are required to run this application

    Este erro é mostrado desde que o ativador do Java 11 verifica se a classe principal se estende javafx.application.Application. Se for esse o caso, é necessário ter o javafx.graphicsmódulo no caminho do módulo.

Para resolver o problema, clique em Properties -> Rune adicione estas opções de VM:

    --add-modules=javafx.controls,javafx.fxml

![javax NetBeans3](https://openjfx.io/openjfx-docs/images/ide/netbeans/ide/netbeans06.png)

Clique em aplicar e feche a caixa de diálogo.

    Nota: Como já adicionamos a JavaFX11biblioteca ao caminho do módulo, não é necessário incluí-la aqui novamente. Observe também que, se JavaFX11não for adicionado ao caminho do módulo em primeiro lugar, o projeto não será executado, pois o NetBeans substituirá o caminho do módulo por um caminho vazio.

Caso seja necessário (ou outros módulos também são necessários), essas devem ser as opções da VM:
- No Windows: *--module-path %PATH_TO_FX% --add-modules=javafx.controls,javafx.fxml*
- No Linux: *--module-path $PATH_TO_FX --add-modules=javafx.controls,javafx.fxml*

**Projeto Modular**

Faça o download dos jmods do JavaFX apropriados para o seu sistema operacional e descompacte-o em um local desejado, por exemplo.

    /Users/your-user/Downloads/javafx-jmods-11

IDE

![javax NetBeans4](https://openjfx.io/openjfx-docs/images/ide/netbeans/modular/ide/netbeans01.png)

Vá para **Properties -> Libraries -> Compile -> Module-path -> + -> Add Library** e adicione a **JavaFX11** biblioteca.

Edite a **module-info** turma e inclua os módulos necessários **javafx.controls** e **javafx.fxml**. Como o FXML usa reflexão para acessar o controlador no módulo, isso precisa ser aberto para **javafx.fxml**. Finalmente, exporte o pacote **org.openjfx**.

![Javax NetBeans5](https://openjfx.io/openjfx-docs/images/ide/netbeans/modular/ide/netbeans02.png)

Com base nessa classe [MainApp](https://openjfx.io/IDE/NetBeans/Modular/Java/HelloFX/src/hellofx/classes/org/openjfx/MainApp.java), adicione seu conteúdo à classe principal do projeto. Em seguida, adicione o [controlller](https://openjfx.io/IDE/NetBeans/Modular/Java/HelloFX/src/hellofx/classes/org/openjfx/FXMLController.java) e o [FXML](https://openjfx.io/IDE/NetBeans/Modular/Java/HelloFX/src/hellofx/classes/org/openjfx/scene.fxml) e os arquivos [css](https://openjfx.io/IDE/NetBeans/Modular/Java/HelloFX/src/hellofx/classes/org/openjfx/styles.css).

![Javax NetBeans6](https://openjfx.io/openjfx-docs/images/ide/netbeans/modular/ide/netbeans03.png)

Sendo um projeto modular, e como já adicionamos a  biblioteca **JavaFX11** ao caminho do módulo, não há necessidade de adicionar argumentos da VM. Clique **Run -> Run...** para executar o projeto.

Para criar uma imagem de tempo de execução, crie uma biblioteca global em **NetBeans -> Tools -> Libraries -> New Library**. Nomeie-o **JavaFXMODS11** e inclua a pasta com o jmods do JavaFX 11 . Adicione esta biblioteca a **NetBeans -> Properties -> Libraries -> Run -> Modulepath**.
![Javax NetBeans7](https://openjfx.io/openjfx-docs/images/ide/netbeans/modular/ide/netbeans04.png)

Para criar a imagem de tempo de execução personalizada agora, vá para **NetBeans -> Properties -> Build -> Packaging** e selecione **Create JLINK distribution**, fornecendo um nome para o iniciador, como **HelloFX**.
![Javax NetBeans8](https://openjfx.io/openjfx-docs/images/ide/netbeans/modular/ide/netbeans05.png)

Aplique e feche a caixa de diálogo e clique no **Clean and Build** botão para criar a imagem.

Para executar a imagem:

- No Windows: *dist\jlink\HelloFX\bin\java -m hellofx/org.openjfx.MainApp*
- No Linux/Mac: *dist/jlink/HelloFX/bin/java -m hellofx/org.openjfx.MainApp*

#### Configurando o Eclipse
Inclua o novo JDK como **Installed JREs** em **Eclipse -> Preferences -> Java -> Installed JREs -> Add**.

    Nota: Se você receber esta mensagem quando adicionar o JDK 11

    You selected a JRE that this version of Eclipse JDT does not yet support fully. Some of the features may not work as expected.

    em seguida, instale o patch a partir do suporte do Eclipse MarketPlace: Java 11 para o Eclipse 2018-09 (4.9) .


Crie um novo **User Library** abaixo **Eclipse -> Window -> Preferences -> Java -> Build Path -> User Libraries -> New**.
![Eclipse1](https://openjfx.io/openjfx-docs/images/ide/eclipse/ide/eclipse01.png)

Nomeie-o **JavaFX11** e inclua os jars sob a **lib** pasta do JavaFX 11.

![Eclipse2](https://openjfx.io/openjfx-docs/images/ide/eclipse/ide/eclipse02.png)

![Eclipse3](https://openjfx.io/openjfx-docs/images/ide/eclipse/ide/eclipse03.png)

Forneça um nome para o projeto, como **HelloFX** e um local. Certifique-se de que o JDK 11 esteja selecionado. Você não precisa adicionar um **module-info**.javaarquivo. Você pode incluir a JavaFX11 biblioteca.

![Eclipse4](https://openjfx.io/openjfx-docs/images/ide/eclipse/ide/eclipse04.png)

Um projeto vazio será aberto.

Você pode adicionar uma classe principal **Main**, com base [nesta](https://openjfx.io/IDE/Eclipse/Non-Modular/Java/hellofx/src/hellofx/Main.java), com um [FXML](https://openjfx.io/IDE/Eclipse/Non-Modular/Java/hellofx/src/hellofx/hellofx.fxml) arquivo e um [ClasseControlller](https://openjfx.io/IDE/Eclipse/Non-Modular/Java/hellofx/src/hellofx/Controller.java).

![Eclipse4](https://openjfx.io/openjfx-docs/images/ide/eclipse/ide/eclipse05.png)

    Aviso: Se você agora executar o projeto, ele será compilado, mas você receberá este erro:

    Error: JavaFX runtime components are missing, and are required to run this application

    Este erro é mostrado desde que o ativador do Java 11 verifica se a classe principal se estende javafx.application.Application. Se for esse o caso, é necessário ter o javafx.graphicsmódulo no caminho do módulo.

Para resolver o problema, **clique em Run -> Run Configurations...  -> Java Application**, crie uma nova configuração de ativação para o seu projeto chamado `hellofx` e adicione estes argumentos da VM:

- No Windows: *--module-path "\path\to\javafx-sdk-11\lib" --add-modules=javafx.controls,javafx.fxml*
- NO Linux: *--module-path /path/to/javafx-sdk-11/lib --add-modules=javafx.controls,javafx.fxml*

Aviso: certifique-se de que a opção:

    Use the -XstartOnFirstThread argument when launching with SWT, não está selecionado.

![Eclipse5](https://openjfx.io/openjfx-docs/images/ide/eclipse/ide/eclipse06.png)

Clique em aplicar e feche a caixa de diálogo.

Como alternativa, você pode definir uma variável global que possa ser usada em projetos futuros. Vá para **Preferences (File -> Settings) -> Run/Debug -> String Substitution** e defina o nome da variável como **PATH_TO_FX** e navegue até a pasta lib do JavaFX SDK para definir seu valor e clique em aplicar.

![Eclipse6](https://openjfx.io/openjfx-docs/images/ide/eclipse/ide/eclipse10.png)

Então você pode se referir a essa variável global ao definir as opções da VM como:

    --module-path ${PATH_TO_FX} --add-modules=javafx.controls,javafx.fxml

Clique Run -> Run As -> Java Application -> Main - hellofxpara executar o projeto.

Variável de ambiente

Você pode substituir o caminho do módulo pela variável de ambiente **PATH_TO_FX** se adicionar o caminho a **Eclipse -> Preferences -> Run/Debug -> String Substitution -> New....**
![Eclipse6](https://openjfx.io/openjfx-docs/images/ide/eclipse/ide/eclipse07.png)

Isso permitirá que você use **${PATH_TO_FX}** como argumento vm:

E você pode distribuir o projeto ou reutilizá-lo em outros projetos também.

E(fx)clipse
Se você usar o plug-in [e(fx)clipse](http://www.eclipse.org/efxclipse/index.html) , verifique se está executando pelo menos a versão 3.4.1 que pode ser instalada usando essa [URL](http://download.eclipse.org/efxclipse/updates-released/3.4.1/site).

![Eclipse6](https://openjfx.io/openjfx-docs/images/ide/eclipse/ide/eclipse09.png)

Observe que você terá que fazer exatamente o mesmo que no projeto Java comum acima, incluindo a **JavaFX11** biblioteca e adicionando os argumentos necessários da VM.

**Projeto Modular*

IDE
Siga estas etapas para criar um projeto modular do JavaFX e usar as ferramentas do IDE para criá-lo e executá-lo. Alternativamente, você pode baixar um projeto semelhante a partir [daqui](https://openjfx.io/IDE/Eclipse/Modular/Java).

Forneça um nome para o projeto, como **HelloFX** e um local. Certifique-se de que o JDK 11 esteja selecionado. Certifique-se de ter selecionado a opção para criar um arquivo **module-info.java**. Você pode selecionar a pasta de saída como **HelloFX/bin/hellofx**. Você pode incluir a biblioteca **JavaFX11**. Um projeto vazio será aberto.

Edite a classe **module-info** e inclua os módulos necessários **javafx.controls** e **javafx.fxml**. Como o FXML usa reflexão para acessar o controlador no módulo, isso precisa ser aberto para **javafx.fxml**. Finalmente, exporte o pacote **org.openjfx**.

    Nota: O Eclipse mostrará um aviso ao criar a Applicationclasse:

    The type Stage from module javafx.graphics may not be accessible to clients due to missing 'requires transitive'
    Este aviso pode ser evitado adicionando os módulos transitivos ao arquivo:

![Eclipse7](https://openjfx.io/openjfx-docs/images/ide/eclipse/modular/ide/eclipse01.png)

Com base nessa classe [MainApp](https://openjfx.io/IDE/Eclipse/Modular/Java/HelloFX/src/org/openjfx/MainApp.java), adicione seu conteúdo à classe principal do projeto. Em seguida, adicione o [controller](https://openjfx.io/IDE/Eclipse/Modular/Java/HelloFX/src/org/openjfx/FXMLController.java) e o [FXML](https://openjfx.io/IDE/Eclipse/Modular/Java/HelloFX/src/org/openjfx/scene.fxml) e os arquivos [css](https://openjfx.io/IDE/Eclipse/Modular/Java/HelloFX/src/org/openjfx/styles.css).

Sendo um projeto modular, e como já adicionamos a biblioteca **JavaFX11** ao caminho do módulo, não há necessidade de adicionar argumentos da VM.

    Aviso: Se você executar o projeto sem opções, poderá receber uma exceção:

    Error occurred during initialization of boot layer
    java.lang.module.FindException: Module javafx.graphics not found, required by hellofx

    Essa exceção ocorre porque a tarefa do Eclipse não adiciona a Biblioteca de Usuários JavaFX11 ao module-path.

Este problema foi corrigido a partir do Eclipse [2018-12](https://www.eclipse.org/downloads/packages/release/2018-12/m2) (4.10.0M2 Build id: 20181108-1653).

Para versões mais antigas, isso pode ser resolvido adicionando os jars requeridos pelo JavaFX diretamente ao caminho do módulo **Run -> Run Configurations... -> Java Application -> Dependencies**.

![Eclipse8](https://openjfx.io/openjfx-docs/images/ide/eclipse/modular/ide/eclipse02.png)

Clique **Run -> Run Configurations... -> Java Application** para executar o projeto.

Crie uma imagem de tempo de execução personalizada
Para criar uma imagem de tempo de execução, execute os seguintes comandos:

|Windows|Linux/Mac|
|--- |--- |
|set PATH_TO_FX_MODS="path\to\javafx-jmods-11"|export PATH_TO_FX_MODS=path/to/javafx-jmods-11
|jlink --module-path "%PATH_TO_FX_MODS%;bin\hellofx" --add-modules=hellofx --output jre|$JAVA_HOME/bin/jlink --module-path $PATH_TO_FX_MODS:bin/hellofx --add-modules=hellofx --output jre
|jre\bin\java -m hellofx/org.openjfx.MainApp|jre/bin/java -m hellofx/org.openjfx.MainApp

### MySQL
