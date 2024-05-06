# Projeto-DB
Gestor de Projetos DI UBI

## Tabelas 

### Tabela Projeto
- ID_projeto (PK)
- Nome
- Título
- Descrição
- Palavra_chave
- Data_Inicio
- Data_Fim
- ID_programa (FK)
- ID_entidade (FK)
- ID_investigadores (FK)

### Tabela Estado
- ID_Projeto
- ID_Estado

### Tabela Tipo de Estado
- ID_estado
- Tipo_estado

### Tabela Dominio
- ID_projeto (FK)
- Domínio_cientifico

### Tabela Area
- ID_projeto (FK)
- Area_cientifica

### Tabela Entidade
- ID_entidade (PK)
- Nome
- Email
- Telefone
- designacao
- Morada
- URL
- ID_pais (FK)

### Tabela País
- ID_pais (PK)
- Nome_País

### Tabela Zona Economica
- ID_projeto (FK)
- Zona_Economica

### Tabela Programa
- ID_programa (PK)
- Nome_programa

### Tabela Departamento_Investigação 
- ID_departamento (PK)
- Nome_departamento

### Tabela Membros_DIUBI
- ID_membro (PK)
- Numero_funcionario
- ORCID
- Funcao
- ID_unidade(FK)

### Tabela Publicação
- ID_publicacao (PK)
- ID_projeto (FK)
- DOI
- URL

### Tabela de Investigadores
- ID_investigadores (PK)
- ID_projeto (FK)
- ID_membro (FK)
