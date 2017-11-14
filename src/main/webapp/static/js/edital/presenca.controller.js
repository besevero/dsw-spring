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
		$log.log("Atualiza presença > código da prova = " + $scope.filtros.codigoProva + "  idCandidato = " + idCandidato + "  status = " + status);
		var codigoProva = $scope.filtros.codigoProva;
		dataService.atualizaPresencaProvasEscritas(codigoProva, idCandidato, status).then(atualizaLista);
	};
	

});
