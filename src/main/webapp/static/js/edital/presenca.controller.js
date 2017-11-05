App.controller("presencaController", function ($scope, $log, dataService) {
	var self = this;
	
	/**
	 * Filtros
	 */
	$scope.filtros = {
		codigoProva: "FSI",
		nome: ""
	}
	
	$scope.inscricoes = [];
	
	/*
	 * Altera os filtros de consulta
	 */
	self.atualizaFiltro = function () {
		atualizaLista();
	}
	
	/*
	 * Atualiza a lista de editais
	 */
	var atualizaLista = function() {
		dataService.pegaInscricoesProvasEscritas($scope.filtros).then(function(data) {
			$log.log(data);
			$scope.inscricoes = data.data;
		});
	}

	atualizaLista();
});