USE [PROJETO]
GO
/****** Object:  StoredProcedure [dbo].[AddProject]    Script Date: 5/27/2024 9:29:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[AddProject]
    @nomeCurto VARCHAR(100),
    @titulo VARCHAR(100),
    @descricao VARCHAR(255),
    @palavraChave VARCHAR(100),
    @dataInicio DATE,
    @dataFim DATE,
    @keywords NVARCHAR(MAX),
    @estado VARCHAR(155),
    @dominioCientifico VARCHAR(255),
    @areaCientifica VARCHAR(255),
    @membros NVARCHAR(MAX),
    @entidadeNome VARCHAR(120),
    @entidadeEmail VARCHAR(155),
    @entidadeTelefone CHAR(15),
    @entidadeDesignacao VARCHAR(255),
    @entidadeMorada VARCHAR(255),
    @ID_pais INT,
    @programaNome VARCHAR(255),
    @tipoFinanciamento BIT
AS
BEGIN
    BEGIN TRANSACTION;

    -- Insert the project
    DECLARE @ID_projeto INT;
    INSERT INTO Projetos (nomeCurto, titulo, descricao, palavraChave, dataInicio, dataFim)
    VALUES (@nomeCurto, @titulo, @descricao, @palavraChave, @dataInicio, @dataFim);
    SET @ID_projeto = SCOPE_IDENTITY();

    -- Insert keywords and associate with project
    DECLARE @ID_keyword INT;
    DECLARE @keyword NVARCHAR(255);
    DECLARE keyword_cursor CURSOR FOR
    SELECT value FROM STRING_SPLIT(@keywords, ',');
    OPEN keyword_cursor;
    FETCH NEXT FROM keyword_cursor INTO @keyword;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM Keywords WHERE keyword = @keyword)
        BEGIN
            INSERT INTO Keywords (keyword) VALUES (@keyword);
            SET @ID_keyword = SCOPE_IDENTITY();
        END
        ELSE
        BEGIN
            SELECT @ID_keyword = ID_keyword FROM Keywords WHERE keyword = @keyword;
        END

        INSERT INTO Keyword_Projeto (ID_projeto, ID_keyword)
        VALUES (@ID_projeto, @ID_keyword);

        FETCH NEXT FROM keyword_cursor INTO @keyword;
    END

    CLOSE keyword_cursor;
    DEALLOCATE keyword_cursor;

    -- Insert state of the project
    DECLARE @ID_tipoEstado INT;
    SELECT @ID_tipoEstado = ID_tipoEstado FROM Tipo_de_Estado WHERE estado = @estado;

    IF @ID_tipoEstado IS NULL
    BEGIN
        ROLLBACK TRANSACTION;
        THROW 50000, 'Invalid estado.', 1;
    END

    INSERT INTO Estado_do_Projeto (ID_tipoEstado, ID_projeto)
    VALUES (@ID_tipoEstado, @ID_projeto);

    -- Insert scientific domain
    DECLARE @ID_dominio INT;
    SELECT @ID_dominio = ID_dominio FROM Tipo_de_Dominio WHERE dominioCientifico = @dominioCientifico;

    IF @ID_dominio IS NULL
    BEGIN
        ROLLBACK TRANSACTION;
        THROW 50000, 'Invalid dominioCientifico.', 1;
    END

    INSERT INTO Domínio_Cientifico (ID_dominio, ID_projeto)
    VALUES (@ID_dominio, @ID_projeto);

    -- Insert scientific area
    DECLARE @ID_areaCientifica INT;
    SELECT @ID_areaCientifica = ID_areaCientifica FROM Tipo_de_Area WHERE areaCientifica = @areaCientifica;

    IF @ID_areaCientifica IS NULL
    BEGIN
        ROLLBACK TRANSACTION;
        THROW 50000, 'Invalid Area Cientifica.', 1;
    END

    INSERT INTO Área_Cientifica (ID_areaCientifica, ID_projeto)
    VALUES (@ID_areaCientifica, @ID_projeto);

    -- Insert members and associate with the project
    DECLARE @ID_membro INT;
    DECLARE @membro NVARCHAR(255);
    DECLARE membro_cursor CURSOR FOR
    SELECT value FROM STRING_SPLIT(@membros, ',');
    OPEN membro_cursor;
    FETCH NEXT FROM membro_cursor INTO @membro;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        SELECT @ID_membro = ID_membro FROM Membros_DIUBI WHERE numeroFuncionario = @membro;

        IF @ID_membro IS NULL
        BEGIN
            ROLLBACK TRANSACTION;
            THROW 50000, 'Invalid membro.', 1;
        END

        INSERT INTO Funcao_Membro (ID_projeto, ID_membro)
        VALUES (@ID_projeto, @ID_membro);

        FETCH NEXT FROM membro_cursor INTO @membro;
    END

    CLOSE membro_cursor;
    DEALLOCATE membro_cursor;

    -- Insert entity
    INSERT INTO Entidade (nome, email, telefone, designacao, morada, ID_projeto, ID_pais)
    VALUES (@entidadeNome, @entidadeEmail, @entidadeTelefone, @entidadeDesignacao, @entidadeMorada, @ID_projeto, @ID_pais);

    -- Insert financing
    DECLARE @ID_programa INT;
    DECLARE @ID_tipoFinanciamento INT;

    SELECT @ID_programa = ID_programa FROM Programa WHERE nomePrograma = @programaNome;
    IF @ID_programa IS NULL
    BEGIN
        INSERT INTO Programa (nomePrograma) VALUES (@programaNome);
        SET @ID_programa = SCOPE_IDENTITY();
    END

    SELECT @ID_tipoFinanciamento = ID_tipoFinanciamento FROM Tipo_Financiamento WHERE Tipo = @tipoFinanciamento;

    IF @ID_tipoFinanciamento IS NULL
    BEGIN
        ROLLBACK TRANSACTION;
        THROW 50000, 'Invalido Tipo de Financiamento.', 1;
    END

    INSERT INTO Financiamento (ID_projeto, ID_programa, ID_tipoFinanciamento)
    VALUES (@ID_projeto, @ID_programa, @ID_tipoFinanciamento);

    COMMIT TRANSACTION;
END;
