DROP PROCEDURE IF EXISTS AtualizaPresencaProvaEscrita;
DELIMITER //
CREATE PROCEDURE AtualizaPresencaProvaEscrita(vIdInscricao INT, vCodigoProjetoPesquisa VARCHAR(8), vPresente INT)
BEGIN
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION ROLLBACK;
	START TRANSACTION;

	UPDATE inscricaoprovaalinhamento
	SET presenteProvaOral = vPresente
	WHERE idInscricao = vIdInscricao
	AND CodigoProjetoPesquisa = vCodigoProjetoPesquisa;
	
	COMMIT;
END //

DELIMITER ;