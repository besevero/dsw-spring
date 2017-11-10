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

	var carregaProvas = function() {
		dataService.pegaProvasEscritas($scope.filtros).then(function(data) {
			$log.log(data);
			$scope.provas = data.data;
		});
	}

	$scope.atualizaPresenca = function(idCandidato, status) {
		$log.log("Checkbox: código da prova = " + $scope.filtros.codigoProva + "  idCandidato = " + idCandidato + "  status = " + status);
		dataService.atualizaPresencaProvasEscritas($scope.filtros.codigoProva, idCandidato, status);
	};

	$scope.selecionaProva = function() {
		$log.log("Option: código da prova: " + $scope.filtros.codigoProva);
	}

});

/*
 * Filtro de seleção de tipo de presença
 */

App.filter("filtroPresenca", function() {
	return function(alunos, tipoPresenca) {
		console.log("Option: tipo de presença: ", tipoPresenca);
		var filtered = [];
		angular.forEach(alunos, function(item) {
			if (!tipoPresenca) {
				tipoPresenca == item.provasEscritas[0].presenca;
				filtered.push(item);
			} else if (tipoPresenca == "presentes" && item.provasEscritas[0].presenca) {
				filtered.push(item);
			} else if (tipoPresenca == "ausentes" && !item.provasEscritas[0].presenca) {
				filtered.push(item);
			}
		});
		return filtered;
	};
});
