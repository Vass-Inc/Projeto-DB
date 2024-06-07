USE PROJETO;

INSERT INTO TipoEstado (estado) VALUES 
('Iniciado'),
('Em Progresso'),
('Conclu�do');

INSERT INTO TipoDominio (dominioCientifico) VALUES 
('Ci�ncias Naturais'),
('Engenharia e Tecnologia'),
('Ci�ncias M�dicas e da Sa�de');

INSERT INTO TipoArea (areaCientifica) VALUES 
('Biologia'),
('F�sica'),
('Qu�mica');

INSERT INTO Membros_DIUBI (numeroFuncionario, orcid, funcao) VALUES 
('001', 123456789, 1),
('002', 987654321, 2),
('003', 112233445, 3);

INSERT INTO TipoFinanciamento (Tipo) VALUES 
(1),
(0);

INSERT INTO NomeDepartamento (nomeDepartamento) VALUES 
('Departamento de F�sica'),
('Departamento de Qu�mica'),
('Departamento de Biologia');

INSERT INTO Programa (nomePrograma) VALUES 
('Programa de Investiga��o Cient�fica'),
('Programa de Desenvolvimento Tecnol�gico'),
('Programa de Sa�de P�blica');

INSERT INTO Publicacao (tipo, valor) VALUES 
('Artigo Cient�fico', 'Nature'),
('Livro', 'Springer'),
('Confer�ncia', 'IEEE');

INSERT INTO Keywords (keyword) VALUES 
('Biotecnologia'),
('Nanotecnologia'),
('Intelig�ncia Artificial');

INSERT INTO Pais (nomePais) VALUES 
('Portugal'),
('Espanha'),
('Brasil');

INSERT INTO NomeDepartamento (nomeDepartamento) VALUES 
('Departamento de Ci�ncias da Computa��o'),
('Departamento de Matem�tica'),
('Departamento de Engenharia Civil');

INSERT INTO Projetos (nomeCurto, titulo, descricao, palavraChave, dataInicio, dataFim) VALUES 
('ProjBio', 'Projeto de Biotecnologia', 'Desenvolvimento de novas t�cnicas biotecnol�gicas', 'Biotecnologia', '2023-01-01', '2024-01-01'),
('ProjNano', 'Projeto de Nanotecnologia', 'Pesquisa em nanomateriais', 'Nanotecnologia', '2023-02-01', '2024-02-01'),
('ProjIA', 'Projeto de Intelig�ncia Artificial', 'Desenvolvimento de sistemas de IA', 'Intelig�ncia Artificial', '2023-03-01', '2024-03-01');

INSERT INTO Entidade (nome, email, telefone, designacao, morada, ID_pais) VALUES 
('Universidade de Lisboa', 'contact@ulisboa.pt', '351123456789', 'Institui��o de Ensino Superior', 'Avenida da Universidade, Lisboa', 1),
('Universidade do Porto', 'info@up.pt', '351987654321', 'Institui��o de Ensino Superior', 'Rua da Universidade, Porto', 1),
('Universidade de Coimbra', 'mail@uc.pt', '351456789123', 'Institui��o de Ensino Superior', 'Pra�a da Universidade, Coimbra', 2);

INSERT INTO ProjetosEntidade (ID_projeto, ID_entidade) VALUES
(1, 2),
(2, 3),
(3, 1)

INSERT INTO Departamento (ID_departamento, ID_membro) VALUES 
(1, 1),
(2, 2),
(3, 3);

INSERT INTO EstadoProjeto (ID_tipoEstado, ID_projeto) VALUES 
(1, 1),
(2, 2),
(3, 3);

INSERT INTO ProjetosEntidade (ID_entidade, ID_projeto) VALUES 
(1, 1),
(2, 2),
(3, 3);

INSERT INTO DominioProjeto (ID_dominio, ID_projeto) VALUES 
(1, 1),
(2, 2),
(3, 3);

INSERT INTO AreaProjeto (ID_projeto, ID_areaCientifica) VALUES 
(1, 1),
(2, 2),
(3, 3);

INSERT INTO PublicacaoProjeto (ID_projeto, ID_publicacao) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO Financiamento (ID_projeto, ID_programa, ID_tipoFinanciamento) VALUES 
(1, 1, 1),
(2, 2, 1),
(3, 3, 0);

INSERT INTO FuncaoProjeto (ID_projeto, ID_membro) VALUES 
(1, 1),
(2, 2),
(3, 3);

INSERT INTO KeywordProjeto (ID_projeto, ID_keyword) VALUES 
(1, 1),
(2, 2),
(3, 3);
