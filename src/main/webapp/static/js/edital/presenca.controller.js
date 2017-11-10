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
		dataService.pegaProvasEscritas($scope.filtros).then(
				function(data) {
					$log.log(data);
					$scope.provas = data.data;
				});
	}
	
 $scope.atualizaPresenca = function(id, status) {
	 $log.log($scope.filtros.codigoProva + " " + id + " " + status);
	dataService.atualizaPresencaProvasEscritas($scope.filtros.codigoProva, id, status)
 };
 
 $scope.selecionaProva = function() {
	 $log.log("Selecionou prova: " + $scope.filtros.codigoProva);
 }
	
});

/*
 * Filtro de seleção de tipo de presença
 */

App.filter("filterByPresence", function() {
	return function(users, selectOption) {
		console.log("filterByPresence", selectOption);
		var filtered = [];
		angular.forEach(users, function(item) {
			if (!selectOption) {
				selectOption == item.provasEscritas[0].presenca;
				filtered.push(item);
			} else if (selectOption == "presentes" && item.provasEscritas[0].presenca) {
				filtered.push(item);
			} else if (selectOption == "ausentes" && !item.provasEscritas[0].presenca) {
				filtered.push(item);
			}
		});
		return filtered;
	};
});
