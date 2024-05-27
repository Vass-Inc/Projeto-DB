-- AdicionarProjeto

CREATE PROCEDURE AdicionarProjeto
    @nomeCurto VARCHAR(100),
    @titulo VARCHAR(100),
    @descricao VARCHAR(255),
    @palavraChave VARCHAR(100),
    @dataInicio DATE,
    @dataFim DATE,
    @nomeEntidade VARCHAR(120),
    @emailEntidade VARCHAR(155),
    @telefoneEntidade CHAR(15),
    @designacaoEntidade VARCHAR(255),
    @moradaEntidade VARCHAR(255),
    @nomeDepartamento VARCHAR(255),
    @estado VARCHAR(155),
    @dominioCientifico VARCHAR(255),
    @areaCientifica VARCHAR(255),
    @keyword VARCHAR(255),
    @ID_projeto INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRANSACTION;

    BEGIN TRY
        -- Inserir projeto
        INSERT INTO Projetos (nomeCurto, titulo, descricao, palavraChave, dataInicio, dataFim)
        VALUES (@nomeCurto, @titulo, @descricao, @palavraChave, @dataInicio, @dataFim);

        -- Obter ID do projeto recém-inserido
        SET @ID_projeto = SCOPE_IDENTITY();

        -- Inserir entidade
        DECLARE @ID_entidade INT;
        INSERT INTO Entidade (nome, email, telefone, designacao, morada)
        VALUES (@nomeEntidade, @emailEntidade, @telefoneEntidade, @designacaoEntidade, @moradaEntidade);
        SET @ID_entidade = SCOPE_IDENTITY();

        -- Atualizar o projeto com o ID da entidade
        UPDATE Projetos
        SET ID_entidade = @ID_entidade
        WHERE ID_projeto = @ID_projeto;

        -- Inserir estado do projeto
        DECLARE @ID_tipoEstado INT;
        SELECT @ID_tipoEstado = ID_tipoEstado FROM TipoEstado WHERE estado = @estado;
        IF @ID_tipoEstado IS NULL
        BEGIN
            INSERT INTO TipoEstado (estado) VALUES (@estado);
            SET @ID_tipoEstado = SCOPE_IDENTITY();
        END;
        INSERT INTO EstadoProjeto (ID_tipoEstado, ID_projeto) VALUES (@ID_tipoEstado, @ID_projeto);

        -- Inserir domínio científico do projeto
        DECLARE @ID_dominio INT;
        SELECT @ID_dominio = ID_dominio FROM TipoDominio WHERE dominioCientifico = @dominioCientifico;
        IF @ID_dominio IS NULL
        BEGIN
            INSERT INTO TipoDominio (dominioCientifico) VALUES (@dominioCientifico);
            SET @ID_dominio = SCOPE_IDENTITY();
        END;
        INSERT INTO DominioProjeto (ID_dominio, ID_projeto) VALUES (@ID_dominio, @ID_projeto);

        -- Inserir área científica do projeto
        DECLARE @ID_areaCientifica INT;
        SELECT @ID_areaCientifica = ID_areaCientifica FROM TipoArea WHERE areaCientifica = @areaCientifica;
        IF @ID_areaCientifica IS NULL
        BEGIN
            INSERT INTO TipoArea (areaCientifica) VALUES (@areaCientifica);
            SET @ID_areaCientifica = SCOPE_IDENTITY();
        END;
        INSERT INTO AreaProjeto (ID_areaCientifica, ID_projeto) VALUES (@ID_areaCientifica, @ID_projeto);

        -- Inserir palavra-chave do projeto
        DECLARE @ID_keyword INT;
        SELECT @ID_keyword = ID_keyword FROM Keywords WHERE keyword = @keyword;
        IF @ID_keyword IS NULL
        BEGIN
            INSERT INTO Keywords (keyword) VALUES (@keyword);
            SET @ID_keyword = SCOPE_IDENTITY();
        END;
        INSERT INTO KeywordProjeto (ID_keyword, ID_projeto) VALUES (@ID_keyword, @ID_projeto);

        -- Inserir departamento do projeto
        DECLARE @ID_departamento INT;
        SELECT @ID_departamento = ID_departamento FROM NomeDepartamento WHERE nomeDepartamento = @nomeDepartamento;
        IF @ID_departamento IS NULL
        BEGIN
            INSERT INTO NomeDepartamento (nomeDepartamento) VALUES (@nomeDepartamento);
            SET @ID_departamento = SCOPE_IDENTITY();
        END;

        -- Commit transaction
        COMMIT TRANSACTION;

    END TRY
    BEGIN CATCH
        -- Rollback transaction if any error occurs
        ROLLBACK TRANSACTION;
        THROW;
    END CATCH;
END;

-- ObterDetalhes

CREATE PROCEDURE ObterDetalhesProjeto @IDProjeto INT
AS
BEGIN
    SELECT 
        p.nomeCurto,
        p.titulo,
        p.descricao,
        p.palavraChave,
        p.dataInicio,
        p.dataFim,
        e.nome AS nomeEntidade,
        e.email AS emailEntidade,
        e.telefone AS telefoneEntidade,
        e.designacao AS designacaoEntidade,
        e.morada AS moradaEntidade,
        pais.nomePais AS nomePaisEntidade
    FROM 
        Projetos p
    INNER JOIN 
        Entidade e ON p.ID_entidade = e.ID_entidade
    INNER JOIN 
        Pais ON e.ID_pais = pais.ID_pais
    WHERE 
        p.ID_projeto = @IDProjeto;
END

