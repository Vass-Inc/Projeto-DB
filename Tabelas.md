# Tabela Projeto
- ID_projeto (PK)
- Nome
- Título
- Descrição
- Palavra_chave
- ID_programa (FK)
- ID_entidade (FK)
- ID_investigadores (FK)

# Tabela Estado
- ID_Projeto
- Estado

# Tabela Data
- ID_projeto (FK)
- Data_inicio
- Data_fim

# Tabela Dominio
- ID_projeto (FK)
- Domínio_cientifico

# Tabela Area
- ID_projeto (FK)
- Area_cientifica

# Tabela Entidade
- ID_entidade (PK)
- Nome
- Email
- Telefone
- designacao
- Morada
- URL
- ID_pais (FK)

# Tabela País
- ID_pais (PK)
- Nome do País

# Tabela Programa
- ID_programa (PK)
- Nome_programa

# Tabela Departamento_Investigação 
- ID_departamento (PK)
- Nome_departamento

# Tabela Membros_DIUBI
- ID_membro (PK)
- Numero_funcionario
- ORCID
- Funcao
- ID_unidade(FK)

# Tabela Publicação
- ID_publicacao (PK)
- ID_projeto (FK)
- DOI
- URL

# Tabela de Investigadores
- ID_investigadores (PK)
- ID_projeto (FK)
- ID_membro (FK)
