# Projeto-DB
Gestor de Projetos DI UBI

## Tabelas 

# 1 Tabela Projeto (Lista)
- ID_projeto (PK)
- Nome
- Título
- Descrição
- Palavra_chave
- data_inicio
- data_fim
- ID_programa (FK)
- ID_entidade (FK)
  
# 2 Tabela Estado
- ID_Projeto (FK)
- ID_tipoEstado(FK)

# 3 Tabela tipo_estado
- ID_tipoEstado (PK)
- Estado

# 4 Tabela Keywords
- ID_keywords (PK)
- keyword

# 5 Tabela keyword_project
- ID_projeto (FK)
- ID-keywords (FK)

# 6 Tabela Dominio
- ID_projeto (FK)
- ID_dominio (FK)

# 7 Tabela Tipo_Dominio
- ID_dominio (PK)
- dominioCientifico

# 8 Tabela Area
- ID_projeto (FK)
- ID_areaCientifica (FK)

# 9 Tabela Tipo_Area_Cientifica
- ID_areaCientifica (PK)
- areaCientifica
  
# 10 Tabela Entidade
- ID_entidade (PK)
- Nome
- Email
- Telefone
- designacao
- Morada
- URL
- ID_pais (FK)

# 11 Tabela País
- ID_pais (PK)
- Nome do País

# 12 Tabela Programa
- ID_programa (PK)
- Nome_programa

# 13 Tabela Departamento_Investigação 
- ID_departamento (FK)
- ID_membro (FK)

# 14 Tabela Nome_Departamento
- ID_departamento (PK)
- nomeDepartamento
  
# 15 Tabela Membros_DIUBI
- ID_membro (PK)
- Numero_funcionario
- ORCID
- Funcao
- ID_departamento(FK)

# 16 Tabela Publicação
- ID_publicacao (PK)
- ID_projeto (FK)
- DOI
- URL

# 17 Tabela Funcao_Membro
- ID_projeto (FK)
- ID_membro (FK)

# 18 Tabela Financiamento
- ID_projeto (FK)
- ID_tipoFinanciamento (FK)

# 19 Tabela Tipo_Financiamento
- ID_tipoFinanciamento (PK)
- Tipo (Interno ou Externo)

# 20 Tabela Identificadores
- ID_identificador
- Tipo (URL OU DOI)
- Valor

# 21 Tabela Identificadores_Projeto
- ID_projeto (FK)
- ID_identificador (FK)

# 22 Tabela Publicacao_Identificadores
- ID_publicacao (FK)
- ID_identificador (FK)
