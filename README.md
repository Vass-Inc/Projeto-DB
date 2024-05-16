# Projeto-DB
Gestor de Projetos DI UBI

## Tabelas 

# 1 Tabela Projeto (Lista)
- ID_projeto (PK)
- Nome
- Título
- Descrição
- Palavra_chave
- ID_programa (FK)
- ID_entidade (FK)
- URL
- DOI

# 2 Tabela Estado
- ID_Projeto (FK)
- ID_tipoEstado(FK)

# 3 Tabela tipo_estado
- ID_tipoEstado (PK)
- Estado

# 4 Tabela Data
- ID_projeto (FK)
- Data_inicio
- Data_fim

# 5 Tabela Dominio
- ID_projeto (FK)
- ID_dominio (FK)

# 6 Tabela Tipo_Dominio
- ID_dominio (PK)
- dominioCientifico

# 7 Tabela Area
- ID_projeto (FK)
- ID_areaCientifica (FK)

# 8 Tabela Tipo_Area_Cientifica
- ID_areaCientifica (PK)
- areaCientifica
  
# 9 Tabela Entidade
- ID_entidade (PK)
- Nome
- Email
- Telefone
- designacao
- Morada
- URL
- ID_pais (FK)

# 10 Tabela País
- ID_pais (PK)
- Nome do País

# 11 Tabela Programa
- ID_programa (PK)
- Nome_programa

# 12 Tabela Departamento_Investigação 
- ID_departamento (FK)
- ID_membro (FK)

# 13 Tabela Nome_Departamento
- ID_departamento (PK)
- nomeDepartamento
  
# 14 Tabela Membros_DIUBI
- ID_membro (PK)
- Numero_funcionario
- ORCID
- Funcao
- ID_departamento(FK)

# 15 Tabela Publicação
- ID_publicacao (PK)
- ID_projeto (FK)
- DOI
- URL

# 16 Tabela Funcao_Membro
- ID_projeto (FK)
- ID_membro (FK)

# 17 Tabela Financiamento
- ID_projeto (FK)
- ID_tipoFinanciamento (FK)

# 18 Tabela Tipo_Financiamento
- ID_tipoFinanciamento (PK)
- Tipo (Interno ou Externo)
