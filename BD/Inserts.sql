USE PROJETO;

-- Insert statements for TipoEstado
INSERT INTO TipoEstado (estado) VALUES 
('Iniciado'),
('Em Progresso'),
('Concluído');

-- Insert statements for TipoDominio
INSERT INTO TipoDominio (dominioCientifico) VALUES 
('Ciências Naturais'),
('Engenharia e Tecnologia'),
('Ciências Médicas e da Saúde');

-- Insert statements for TipoArea
INSERT INTO TipoArea (areaCientifica) VALUES 
('Biologia'),
('Física'),
('Química');

-- Insert statements for Membros_DIUBI
INSERT INTO Membros_DIUBI (numeroFuncionario, orcid, funcao) VALUES 
('001', 123456789, 1),
('002', 987654321, 2),
('003', 112233445, 3);

-- Insert statements for TipoFinanciamento
INSERT INTO TipoFinanciamento (Tipo) VALUES 
(1),
(0);

-- Insert statements for NomeDepartamento
INSERT INTO NomeDepartamento (nomeDepartamento) VALUES 
('Departamento de Física'),
('Departamento de Química'),
('Departamento de Biologia');

-- Insert statements for Programa
INSERT INTO Programa (nomePrograma) VALUES 
('Programa de Investigação Científica'),
('Programa de Desenvolvimento Tecnológico'),
('Programa de Saúde Pública');

-- Insert statements for Publicacao
INSERT INTO Publicacao (tipo, valor) VALUES 
('Artigo Científico', 'Nature'),
('Livro', 'Springer'),
('Conferência', 'IEEE');

-- Insert statements for Keywords
INSERT INTO Keywords (keyword) VALUES 
('Biotecnologia'),
('Nanotecnologia'),
('Inteligência Artificial');

-- Insert statements for Pais
INSERT INTO Pais (nomePais) VALUES 
('Portugal'),
('Espanha'),
('Brasil');

-- Insert statements for NomeDepartamento
INSERT INTO NomeDepartamento (nomeDepartamento) VALUES 
('Departamento de Ciências da Computação'),
('Departamento de Matemática'),
('Departamento de Engenharia Civil');

-- Insert statements for Projetos
INSERT INTO Projetos (nomeCurto, titulo, descricao, palavraChave, dataInicio, dataFim, ID_entidade) VALUES 
('ProjBio', 'Projeto de Biotecnologia', 'Desenvolvimento de novas técnicas biotecnológicas', 'Biotecnologia', '2023-01-01', '2024-01-01', 1),
('ProjNano', 'Projeto de Nanotecnologia', 'Pesquisa em nanomateriais', 'Nanotecnologia', '2023-02-01', '2024-02-01', 2),
('ProjIA', 'Projeto de Inteligência Artificial', 'Desenvolvimento de sistemas de IA', 'Inteligência Artificial', '2023-03-01', '2024-03-01', 3);

-- Insert statements for Entidade
INSERT INTO Entidade (nome, email, telefone, designacao, morada, ID_projeto, ID_pais) VALUES 
('Universidade de Lisboa', 'contact@ulisboa.pt', '351123456789', 'Instituição de Ensino Superior', 'Avenida da Universidade, Lisboa', 1, 1),
('Universidade do Porto', 'info@up.pt', '351987654321', 'Instituição de Ensino Superior', 'Rua da Universidade, Porto', 2, 2),
('Universidade de Coimbra', 'mail@uc.pt', '351456789123', 'Instituição de Ensino Superior', 'Praça da Universidade, Coimbra', 3, 3);

-- Insert statements for Departamento
INSERT INTO Departamento (ID_departamento, ID_membro) VALUES 
(1, 1),
(2, 2),
(3, 3);

-- Insert statements for EstadoProjeto
INSERT INTO EstadoProjeto (ID_tipoEstado, ID_projeto) VALUES 
(1, 1),
(2, 2),
(3, 3);

-- Insert statements for EntidadeProjeto
INSERT INTO ProjetosEntidade (ID_entidade, ID_projeto) VALUES 
(1, 1),
(2, 2),
(3, 3);

-- Insert statements for DominioProjeto
INSERT INTO DominioProjeto (ID_dominio, ID_projeto) VALUES 
(1, 1),
(2, 2),
(3, 3);

-- Insert statements for AreaProjeto
INSERT INTO AreaProjeto (ID_projeto, ID_areaCientifica) VALUES 
(1, 1),
(2, 2),
(3, 3);

-- Insert statements for PublicacaoProjeto
INSERT INTO PublicacaoProjeto (ID_projeto, ID_identificadores) VALUES 
(1, 1),
(2, 2),
(3, 3);

-- Insert statements for Financiamento
INSERT INTO Financiamento (ID_projeto, ID_programa, ID_tipoFinanciamento) VALUES 
(1, 1, 1),
(2, 2, 1),
(3, 3, 0);

-- Insert statements for FuncaoProjeto
INSERT INTO FuncaoProjeto (ID_projeto, ID_membro) VALUES 
(1, 1),
(2, 2),
(3, 3);

-- Insert statements for KeywordProjeto
INSERT INTO KeywordProjeto (ID_projeto, ID_keyword) VALUES 
(1, 1),
(2, 2),
(3, 3);
