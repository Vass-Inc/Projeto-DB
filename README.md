# Projeto-DB
Gestor de Projetos DI UBI

## Tabelas 

# 1 Tabela Projeto (Lista)
- ID_projeto (PK)
- Nome
- Título
- Descrição
- data_inicio
- data_fim
  
# 2 Tabela Estado
- ID_Projeto (FK)
- ID_tipoEstado(FK)

# 3 Tabela tipo_estado
- ID_tipoEstado (PK)
- Estado

# 4 Tabela Keywords
- ID_keyword(PK)
- keyword

# 5 Tabela Keywords_projeto
- ID_keyword(FK)
- ID_projeto (FK)

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
- ID_projeto (FK)

# 11 Tabela País
- ID_pais (PK)
- Nome do País

# 12 Tabela Programa
- ID_programa (PK)
- Nome_programa
- ID_projeto (FK)

# 13 Tabela Departamento_Investigação 
- ID_departamento (FK)
- ID_membro (FK)

# 14 Tabela Departamento
- ID_departamento (PK)
- nomeDepartamento
  
# 15 Tabela Membros_DIUBI
- ID_membro (PK)
- Numero_funcionario
- ORCID
- Funcao

# 16 Tabela Funcao_Membro
- ID_projeto (FK)
- ID_membro (FK)

# 17 Tabela Publicação
- ID_publicacao (PK)
- Tipo (URL OU DOI)
- Valor

# 18 Tabela Publicacao_Identificadores
- ID_publicacao (FK)
- ID_projeto (FK)

# 19 Tabela Financiamento
- ID_projeto (FK)
- ID_tipoFinanciamento (FK)

# 20 Tabela Tipo_Financiamento
- ID_tipoFinanciamento (PK)
- Tipo (Interno ou Externo)
- competitivo (sim ou nao)
