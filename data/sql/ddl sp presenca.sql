CREATE PROCEDURE AtualizaPresenca(vIdInscricao INT, vCodigoProvaEscrita VARCHAR(8), vPresente INT )
BEGIN
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION ROLLBACK;
	START TRANSACTION;

	UPDATE inscricaoprovaescrita
	SET presente = vPresente;
	WHERE idInscricao = vIdInscricao;
	AND codigoProvaEscrita = vCodigoProvaEscrita
	
	COMMIT;
END //