SET SQL_SAFE_UPDATES = 0;
delete from InscricaoProvaAlinhamento;
delete from InscricaoProvaEscrita;
delete from Inscricao;

#
# Nova Inscricao
#

CALL UsuarioInsere("Fulano 0001", "fulano0001@somewhere.com", "$2a$10$HflP3AJrzwM.he3.gW78a.FZvW6uVpfSiwWGmVVwTTQL78REd.6UO", 0, @id);

INSERT INTO Inscricao(dataRegistro, dataAtualizacao, idEdital, idCandidato, cotaNegros, cotaDeficientes, homologadoInicial, homologado, dispensadoProvaInicial, dispensado, jsonProjetos)
SELECT NOW(), NOW(), 1, id, 1, 0, 1, 1, 0, 0, '[{\"codigo\":\"ACWE\",\"intencoes\":\"minhas intencoes de pesquisa\"},{\"codigo\":\"HEUR\",\"intencoes\":\"minhas intencoes de pesquisa\"},{\"codigo\":\"SIGS\",\"intencoes\":\"minhas intencoes de pesquisa\"},{\"codigo\":\"TRAN\",\"intencoes\":\"minhas intencoes de pesquisa\"}]'
FROM Usuario WHERE nome = 'Fulano 0001';

SET @idInscricao = LAST_INSERT_ID();

INSERT INTO InscricaoProvaEscrita(idInscricao, codigoProvaEscrita, presente, notaFinal, jsonQuestoesInicial, jsonQuestoesRecurso)
VALUES (@idInscricao, 'FSI', 0, 0, '', '');

INSERT INTO InscricaoProvaEscrita(idInscricao, codigoProvaEscrita, presente, notaFinal, jsonQuestoesInicial, jsonQuestoesRecurso)
VALUES (@idInscricao, 'ING', 0, 0, '', '');

INSERT INTO InscricaoProvaEscrita(idInscricao, codigoProvaEscrita, presente, notaFinal, jsonQuestoesInicial, jsonQuestoesRecurso)
VALUES (@idInscricao, 'EDG', 0, 0, '', '');

INSERT INTO InscricaoProvaAlinhamento(idInscricao, codigoProjetoPesquisa, presenteProvaOral, notaFinal, jsonSubcriteriosInicial, jsonSubcriteriosRecurso)
VALUES (@idInscricao, 'ACWE', 0, 0, '', '');

INSERT INTO InscricaoProvaAlinhamento(idInscricao, codigoProjetoPesquisa, presenteProvaOral, notaFinal, jsonSubcriteriosInicial, jsonSubcriteriosRecurso)
VALUES (@idInscricao, 'HEUR', 0, 0, '', '');

INSERT INTO InscricaoProvaAlinhamento(idInscricao, codigoProjetoPesquisa, presenteProvaOral, notaFinal, jsonSubcriteriosInicial, jsonSubcriteriosRecurso)
VALUES (@idInscricao, 'SIGS', 0, 0, '', '');

INSERT INTO InscricaoProvaAlinhamento(idInscricao, codigoProjetoPesquisa, presenteProvaOral, notaFinal, jsonSubcriteriosInicial, jsonSubcriteriosRecurso)
VALUES (@idInscricao, 'TRAN', 0, 0, '', '');

#
# Nova Inscricao
#

CALL UsuarioInsere("Fulano 0002", "fulano0002@somewhere.com", "$2a$10$HflP3AJrzwM.he3.gW78a.FZvW6uVpfSiwWGmVVwTTQL78REd.6UO", 0, @id);

INSERT INTO Inscricao(dataRegistro, dataAtualizacao, idEdital, idCandidato, cotaNegros, cotaDeficientes, homologadoInicial, homologado, dispensadoProvaInicial, dispensado, jsonProjetos)
SELECT NOW(), NOW(), 1, id, 0, 0, 1, 1, 0, 0, '[{\"codigo\":\"OBID\",\"intencoes\":\"minhas intencoes de pesquisa\"},{\"codigo\":\"PROC\",\"intencoes\":\"minhas intencoes de pesquisa\"},{\"codigo\":\"OPER\",\"intencoes\":\"minhas intencoes de pesquisa\"}]'
FROM Usuario WHERE nome = 'Fulano 0002';

SET @idInscricao = LAST_INSERT_ID();

INSERT INTO InscricaoProvaEscrita(idInscricao, codigoProvaEscrita, presente, notaFinal, jsonQuestoesInicial, jsonQuestoesRecurso)
VALUES (@idInscricao, 'FSI', 0, 0, '', '');

INSERT INTO InscricaoProvaEscrita(idInscricao, codigoProvaEscrita, presente, notaFinal, jsonQuestoesInicial, jsonQuestoesRecurso)
VALUES (@idInscricao, 'ING', 0, 0, '', '');

INSERT INTO InscricaoProvaEscrita(idInscricao, codigoProvaEscrita, presente, notaFinal, jsonQuestoesInicial, jsonQuestoesRecurso)
VALUES (@idInscricao, 'RDP', 0, 0, '', '');

INSERT INTO InscricaoProvaAlinhamento(idInscricao, codigoProjetoPesquisa, presenteProvaOral, notaFinal, jsonSubcriteriosInicial, jsonSubcriteriosRecurso)
VALUES (@idInscricao, 'OBID', 0, 0, '', '');

INSERT INTO InscricaoProvaAlinhamento(idInscricao, codigoProjetoPesquisa, presenteProvaOral, notaFinal, jsonSubcriteriosInicial, jsonSubcriteriosRecurso)
VALUES (@idInscricao, 'PROC', 0, 0, '', '');

INSERT INTO InscricaoProvaAlinhamento(idInscricao, codigoProjetoPesquisa, presenteProvaOral, notaFinal, jsonSubcriteriosInicial, jsonSubcriteriosRecurso)
VALUES (@idInscricao, 'OPER', 0, 0, '', '');

#
# Nova Inscricao
#

CALL UsuarioInsere("Fulano 0003", "fulano0003@somewhere.com", "$2a$10$HflP3AJrzwM.he3.gW78a.FZvW6uVpfSiwWGmVVwTTQL78REd.6UO", 0, @id);

INSERT INTO Inscricao(dataRegistro, dataAtualizacao, idEdital, idCandidato, cotaNegros, cotaDeficientes, homologadoInicial, homologado, dispensadoProvaInicial, dispensado, jsonProjetos)
SELECT NOW(), NOW(), 1, id, 0, 1, 1, 1, 0, 0, '[{\"codigo\":\"JOGO\",\"intencoes\":\"minhas intencoes de pesquisa\"},{\"codigo\":\"OBID\",\"intencoes\":\"minhas intencoes de pesquisa\"}]'
FROM Usuario WHERE nome = 'Fulano 0003';

SET @idInscricao = LAST_INSERT_ID();

INSERT INTO InscricaoProvaEscrita(idInscricao, codigoProvaEscrita, presente, notaFinal, jsonQuestoesInicial, jsonQuestoesRecurso)
VALUES (@idInscricao, 'FSI', 0, 0, '', '');

INSERT INTO InscricaoProvaEscrita(idInscricao, codigoProvaEscrita, presente, notaFinal, jsonQuestoesInicial, jsonQuestoesRecurso)
VALUES (@idInscricao, 'ING', 0, 0, '', '');

INSERT INTO InscricaoProvaAlinhamento(idInscricao, codigoProjetoPesquisa, presenteProvaOral, notaFinal, jsonSubcriteriosInicial, jsonSubcriteriosRecurso)
VALUES (@idInscricao, 'JOGO', 0, 0, '', '');

INSERT INTO InscricaoProvaAlinhamento(idInscricao, codigoProjetoPesquisa, presenteProvaOral, notaFinal, jsonSubcriteriosInicial, jsonSubcriteriosRecurso)
VALUES (@idInscricao, 'OBID', 0, 0, '', '');

#
# Nova Inscricao
#

CALL UsuarioInsere("Fulano 0004", "fulano0004@somewhere.com", "$2a$10$HflP3AJrzwM.he3.gW78a.FZvW6uVpfSiwWGmVVwTTQL78REd.6UO", 0, @id);

INSERT INTO Inscricao(dataRegistro, dataAtualizacao, idEdital, idCandidato, cotaNegros, cotaDeficientes, homologadoInicial, homologado, dispensadoProvaInicial, dispensado, jsonProjetos)
SELECT NOW(), NOW(), 1, id, 1, 0, 1, 1, 0, 0, '[{\"codigo\":\"HEUR\",\"intencoes\":\"minhas intencoes de pesquisa\"}]'
FROM Usuario WHERE nome = 'Fulano 0004';

SET @idInscricao = LAST_INSERT_ID();

INSERT INTO InscricaoProvaEscrita(idInscricao, codigoProvaEscrita, presente, notaFinal, jsonQuestoesInicial, jsonQuestoesRecurso)
VALUES (@idInscricao, 'FSI', 0, 0, '', '');

INSERT INTO InscricaoProvaEscrita(idInscricao, codigoProvaEscrita, presente, notaFinal, jsonQuestoesInicial, jsonQuestoesRecurso)
VALUES (@idInscricao, 'ING', 0, 0, '', '');

INSERT INTO InscricaoProvaEscrita(idInscricao, codigoProvaEscrita, presente, notaFinal, jsonQuestoesInicial, jsonQuestoesRecurso)
VALUES (@idInscricao, 'EDG', 0, 0, '', '');

INSERT INTO InscricaoProvaAlinhamento(idInscricao, codigoProjetoPesquisa, presenteProvaOral, notaFinal, jsonSubcriteriosInicial, jsonSubcriteriosRecurso)
VALUES (@idInscricao, 'HEUR', 0, 0, '', '');