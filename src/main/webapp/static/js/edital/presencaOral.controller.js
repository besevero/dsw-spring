App.controller("presencaController", function($scope, $log, dataService) {
	var self = this;

	/**
	 * Filtros
	 */
	$scope.filtros = {
		codigoProva : null,
		nome : ""
	}

	$scope.inscricoes = [];
	
	/*
	 * Chama o dataSevice para carregar uma prova selecionada pelo usuário
	 */
	self.selecionaProva = function(codigoProva) {
		$scope.filtros.codigoProva = codigoProva;
		console.log("$scope.filtros.codigoProva =", $scope.filtros.codigoProva);
		dataService.pegaInscricoesProvasEscritas($scope.filtros).then(atualizaLista);
	}
	
	/*
	 * Altera os filtros de consulta
	 */
	self.atualizaFiltro = function() {
		atualizaLista();
	}

	$scope.selecionaPresenca = function (tipoPresenca) {
		console.log("Tipo de presença selecionado:", tipoPresenca)
	}
	
	/*
	 * Atualiza a lista de editais
	 */
	var atualizaLista = function() {
		if ($scope.filtros.codigoProva == null) {
			console.log("dataService desativado")
		} 
		else {
			dataService.pegaInscricoesProvasEscritas($scope.filtros).then(			
					function(data) {
						$log.log(data);
						$scope.inscricoes = data.data;
					});
		}
	}

	atualizaLista();

	$scope.atualizaPresenca = function(idCandidato, status) {
		$log.log("Atualiza presença > código da prova = " + $scope.filtros.codigoProva + "  idCandidato = " + idCandidato + "  status = " + status);
		var codigoProva = $scope.filtros.codigoProva;
		dataService.atualizaPresencaProvasEscritas(codigoProva, idCandidato, status).then(atualizaLista);
	};
	
	$scope.pegaInscricoes = function() {
		var inscricoes = $scope.inscricoes.filter(function(inscricao) {
			var existeProva = inscricao.provasEscritas.some(function(prova) {
				return prova.codigoProvaEscrita == $scope.filtros.codigoProva;
			});
			return existeProva;
		});
		
		inscricoes = inscricoes.filter(function(inscricao) {
			var estaPresente = inscricao.provasEscritas.some(function(prova) {
				return prova.presenca;
			});
			
			if ($scope.tipoPresenca == true) {
				return estaPresente;
			} 
			else
			if ($scope.tipoPresenca == false) {
				return !estaPresente;
			}
		});
		return inscricoes;
	}
	

});
