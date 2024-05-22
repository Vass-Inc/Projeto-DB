USE PROJETO

CREATE TABLE Tipo_de_Estado
(
  ID_tipoEstado INT NOT NULL IDENTITY(1,1),
  estado VARCHAR(155) NOT NULL,
  PRIMARY KEY (ID_tipoEstado)
);

CREATE TABLE Tipo_de_Dominio
(
  ID_dominio INT NOT NULL IDENTITY(1,1),
  dominioCientifico VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID_dominio)
);

CREATE TABLE Tipo_de_Area
(
  ID_areaCientifica INT NOT NULL IDENTITY(1,1),
  areaCientifica VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID_areaCientifica)
);

CREATE TABLE Membros_DIUBI
(
  ID_membro INT NOT NULL IDENTITY(1,1),
  numeroFuncionario VARCHAR(255) NOT NULL,
  orcid INT NOT NULL,
  funcao INT NOT NULL,
  PRIMARY KEY (ID_membro),
  UNIQUE (orcid)
);

CREATE TABLE Tipo_Financiamento
(
  ID_tipoFinanciamento INT NOT NULL IDENTITY(1,1),
  Tipo bit NOT NULL,
  PRIMARY KEY (ID_tipoFinanciamento)
);

CREATE TABLE Nome_Departamento
(
  ID_departamento INT NOT NULL IDENTITY(1,1),
  nomeDepartamento VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID_departamento)
);

CREATE TABLE Programa
(
  ID_programa INT NOT NULL IDENTITY(1,1),
  nomePrograma VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID_programa)
);

CREATE TABLE Publicacao
(
  ID_publicacao INT NOT NULL IDENTITY(1,1),
  tipo VARCHAR(90) NOT NULL,
  valor VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID_publicacao)
);

CREATE TABLE Keywords
(
  ID_keyword INT NOT NULL IDENTITY(1,1),
  keyword VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID_keyword)
);

CREATE TABLE Departamento_
(
  ID_departamento INT NOT NULL,
  ID_membro INT NOT NULL,
  FOREIGN KEY (ID_departamento) REFERENCES Nome_Departamento(ID_departamento),
  FOREIGN KEY (ID_membro) REFERENCES Membros_DIUBI(ID_membro)
);

CREATE TABLE Estado_do_Projeto
(
  ID_tipoEstado INT NOT NULL,
  ID_projeto INT NOT NULL,
  FOREIGN KEY (ID_tipoEstado) REFERENCES Tipo_de_Estado(ID_tipoEstado),
  FOREIGN KEY (ID_projeto) REFERENCES Projetos(ID_projeto)
);

CREATE TABLE Projetos
(
  ID_projeto INT NOT NULL IDENTITY(1,1),
  nomeCurto VARCHAR(100) NOT NULL,
  titulo VARCHAR(100) NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  palavraChave VARCHAR(100) NOT NULL,
  dataInicio DATE NOT NULL,
  dataFim DATE NOT NULL,
  ID_entidade INT NOT NULL,
  PRIMARY KEY (ID_projeto),
  FOREIGN KEY (ID_entidade) REFERENCES Entidade(ID_entidade)
);

CREATE TABLE Entidade
(
  ID_entidade INT NOT NULL IDENTITY(1,1),
  nome VARCHAR(120) NOT NULL,
  email VARCHAR(155) NOT NULL,
  telefone CHAR(15) NOT NULL,
  designacao VARCHAR(255) NOT NULL,
  morada VARCHAR(255) NOT NULL,
  ID_projeto INT NOT NULL,
  ID_pais INT NOT NULL,
  PRIMARY KEY (ID_entidade),
  FOREIGN KEY (ID_projeto) REFERENCES Projetos(ID_projeto),
  FOREIGN KEY (ID_pais) REFERENCES Pais(ID_pais)
);

CREATE TABLE Domínio_Cientifico
(
  ID_dominio INT NOT NULL,
  ID_projeto INT NOT NULL,
  FOREIGN KEY (ID_dominio) REFERENCES Tipo_de_Dominio(ID_dominio),
  FOREIGN KEY (ID_projeto) REFERENCES Projetos(ID_projeto)
);

CREATE TABLE Área_Cientifica
(
  ID_projeto INT NOT NULL,
  ID_areaCientifica INT NOT NULL,
  FOREIGN KEY (ID_projeto) REFERENCES Projetos(ID_projeto),
  FOREIGN KEY (ID_areaCientifica) REFERENCES Tipo_de_Area(ID_areaCientifica)
);

CREATE TABLE Pais
(
  ID_pais INT NOT NULL,
  nomePais VARCHAR(120) NOT NULL,


);

CREATE TABLE Publicacao_Identificadores
(
  ID_projeto INT NOT NULL,
  ID_identificadores INT NOT NULL,
  FOREIGN KEY (ID_projeto) REFERENCES Projetos(ID_projeto),
  FOREIGN KEY (ID_identificadores) REFERENCES Publicacao(ID_publicacao)
);

CREATE TABLE Financiamento
(
  ID_projeto INT NOT NULL,
  ID_programa INT NOT NULL,
  ID_tipoFinanciamento INT NOT NULL,
  FOREIGN KEY (ID_projeto) REFERENCES Projetos(ID_projeto),
  FOREIGN KEY (ID_programa) REFERENCES Programa(ID_programa),
  FOREIGN KEY (ID_tipoFinanciamento) REFERENCES Tipo_Financiamento(ID_tipoFinanciamento)
);

CREATE TABLE Funcao_Membro
(
  ID_projeto INT NOT NULL,
  ID_membro INT NOT NULL,
  FOREIGN KEY (ID_projeto) REFERENCES Projetos(ID_projeto),
  FOREIGN KEY (ID_membro) REFERENCES Membros_DIUBI(ID_membro)
);

CREATE TABLE Keyword_Projeto
(
  ID_projeto INT NOT NULL,
  ID_keyword INT NOT NULL,
  FOREIGN KEY (ID_projeto) REFERENCES Projetos(ID_projeto),
  FOREIGN KEY (ID_keyword) REFERENCES Keywords(ID_keyword)
);