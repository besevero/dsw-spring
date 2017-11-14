App.controller("presencaController", function($scope, $log, dataService) {
	var self = this;

	/**
	 * Filtros
	 */
	$scope.filtros = {
		codigoProva : "FSI",
		nome : ""
	}

	$scope.inscricoes = [];
	
	/*
	 * Chama o dataSevice para carregar uma prova selecionada pelo usuário
	 */
	self.selecionaProva = function(codigoProva) {
		$scope.filtros.codigoProva = codigoProva;
		console.log("$scope.filtros.codigoProva =", $scope.filtros.codigoProva);
		$scope.filtros.codigoProva = codigoProva;
		dataService.pegaInscricoesProvasEscritas($scope.filtros).then(atualizaLista);
	}
	
	/*
	 * Altera os filtros de consulta
	 */
	self.atualizaFiltro = function() {
		atualizaLista();
	}

	/*
	 * Atualiza a lista de editais
	 */
	var atualizaLista = function() {
		dataService.pegaInscricoesProvasEscritas($scope.filtros).then(
				function(data) {
					$log.log(data);
					$scope.inscricoes = data.data;
				});
	}

	atualizaLista();

	$scope.atualizaPresenca = function(idCandidato, status) {
		$log.log("Checkbox > código da prova = " + $scope.filtros.codigoProva + "  idCandidato = " + idCandidato + "  status = " + status);
		var codigoProva = $scope.filtros.codigoProva;
		dataService.atualizaPresencaProvasEscritas(codigoProva, idCandidato, status).then(atualizaLista);
	};
	

});

/*
 * Filtro de seleção de prova escrita e tipo de presença
 */

App.filter("filtroPresenca", function() {
	return function verificaStatus(alunos, tipoPresenca, codigoProvaSelecionado, statusPresenca) {
		console.log("Select tipo de presença > código da prova selecionado: " + codigoProvaSelecionado + ", tipo de presença selecionado: " + tipoPresenca + ", status: " + statusPresenca);
		var filtered = [];
		angular.forEach(alunos, function(item) {
			
			var quantidadeProvas = item.provasEscritas.length;
			// console.log("Nº de provas do candidato", + item.idCandidato + ": " + quantidadeProvas);
			
			for (i = 0; i < quantidadeProvas; i++) {
				
				var codigoProvaCandidato = item.provasEscritas[i].codigoProvaEscrita;
				var presencaProvaCandidato = item.provasEscritas[i].presenca;
				
				if (!tipoPresenca || !codigoProvaSelecionado) {
					
				}
				
				else if (codigoProvaSelecionado == codigoProvaCandidato && presencaProvaCandidato == true && tipoPresenca == "presentes") {
					console.log("Prova " + item.provasEscritas[i].codigoProvaEscrita + " do candidato = " + item.idCandidato + " encontrada no vetor " + i);				
					filtered.push(item);
				}
				
				else if (codigoProvaCandidato == codigoProvaSelecionado && presencaProvaCandidato == false && tipoPresenca == "ausentes") {
					filtered.push(item);
				}
			}
		});
		return filtered;
	};
});
