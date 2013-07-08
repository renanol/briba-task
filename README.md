Este é projeto foi motivado pela Kamaleon TI, uma empresa de desenvolvimento de sistemas web com a tecnologia java. Inicalmente foi feita várias pesquisas sobre sistemas de gerenciamento de projetos, no qual existem vários no mercado, temos como exemplo o Redmine, mas nenhum atendia as necessidades da empresa, daí foi iniciado o projeto do Briba Task.
Nesse projeto é utilizado várias tecnologias, para um desenvolvimento produtivo utilizamos o Spring roo.

Instalação Para Produção

1 - Instale o JDK
2 - Instale o Glassfish 3
3 - Instale o Maven
4 - Instale o Postgresql
5 - Baixe o projeto do git
6 - Na pasta raiz do projeto rode > mvn install , com esse comando o sistema irá instalar as dependencia gerar o arquivo .war para fazermos o deploy no glassfish.
7 - Agora no pg admin crie o banco de dados.
8 - O banco de dados do briba task é gerenciado pelo servidor glassfish, é necessário configurar o jndi, crie um pool de conexão e no jdbc coloque o nome de jndi/BRIBA
9 - Agora no glass fish faça o deploy do arquivo .war que está dentro da pasta target.
10 - Vá no pgadmin e rode o script do arquivo dump_instalacao.sql que está na raiz do projeto.
11 - Feito isso é só logar com login sistema e senha 123456

Instalação Para Desenvolvimento

Faça todos os passos anterior, mas para desenvolver é necessário instalar o Eclipse STS.




