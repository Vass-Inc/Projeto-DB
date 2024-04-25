# Projeto-DB
Gestor de Projetos DI UBI

## Tabelas 

Tabela: **Projetos/Contratos**
   - **Campos:**
     - ID_projeto (Primary Key)
     - Estado (aprovado, cancelado, concluído, em curso, encerrado, renovado, em submissão)
     - Financiamento (competitivo ou não)
     - Nome_curto_PT
     - Nome_curto_EN
     - Título_PT
     - Título_EN
     - Descrição_PT
     - Descrição_EN
     - URL
     - DOI
     - Data_inicio
     - Data_fim
     - Palavras-chave_PT
     - Palavras-chave_EN
     - Domínio_científico
     - Área_científica
     - ID_entidade (Foreign Key referenciando a tabela Entidades)
     - ID_programa (Foreign Key referenciando a tabela Programas)
     - ID_IR (Foreign Key referenciando a tabela Investigadores)
     - Custo_total_elegível

Tabela: **Entidades**
   - **Campos:**
     - ID_entidade (Primary Key)
     - Nome
     - Descrição
     - Email
     - Telefone
     - Designação
     - Sigla
     - Morada
     - URL
     - País

Tabela: **Programas**
   - **Campos:**
     - ID_programa (Primary Key)
     - Nome_programa_PT
     - Nome_programa_EN

Tabela: **Membros_DIUBI**
   - **Campos:**
     - ID_membro (Primary Key)
     - Número_funcionário
     - ORCID
     - Percentagem_tempo_dedicado
     - Função (promotor, copromotor, líder, participante)
     - ID_unidade_investigação (Foreign Key referenciando a tabela Unidades_Investigação)

Tabela: **Unidades_Investigação**
   - **Campos:**
     - ID_unidade (Primary Key)
     - Nome_unidade_PT
     - Nome_unidade_EN

Tabela: **Publicações**
   - **Campos:**
     - ID_publicação (Primary Key)
     - ID_projeto (Foreign Key referenciando a tabela Projetos/Contratos)
     - DOI
     - URL
