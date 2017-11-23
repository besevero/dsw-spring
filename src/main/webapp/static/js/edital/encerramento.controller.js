App.controller("encerramentoController", function($scope, $log, dataService) {
	var self = this;

	$scope.provas = [];
	
	/*
	 * Altera os filtros de consulta
	 */
	self.atualizaFiltro = function() {
		atualizaLista();

	
	/*
	 * Atualiza a lista de editais
	 */
	var atualizaLista = function() {
		dataService.carregaProvasEscritas.then(			
					function(data) {
						$log.log(data);
						$scope.provas = data.data;
					});
		}
	}

	atualizaLista();

});
