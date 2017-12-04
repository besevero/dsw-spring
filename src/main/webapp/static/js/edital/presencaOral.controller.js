App.controller("presencaController", function($scope, $log, dataService) {
	var self = this;

	/**
	 * Filtros
	 */
	$scope.filtros = {
		codigoProjetoPesquisa : null,
		nome : ""
	}

	$scope.inscricoes = [];
	
	/*
	 * Chama o dataSevice para carregar um projeto de pesquisa selecionado pelo usuário
	 */
	self.selecionaProjeto = function(codigoProjetoPesquisa) {
		$scope.filtros.codigoProjetoPesquisa = codigoProjetoPesquisa;
		console.log("$scope.filtros.codigoProjetoPesquisa =", $scope.filtros.codigoProjetoPesquisa);
		dataService.pegaInscricoesProjetosPesquisa($scope.filtros).then(atualizaLista);
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
		if ($scope.filtros.codigoProjetoPesquisa == null) {
			console.log("dataService desativado")
		} 
		else {
			dataService.pegaInscricoesProjetosPesquisa($scope.filtros).then(			
					function(data) {
						$log.log(data);
						$scope.inscricoes = data.data;
					});
		}
	}

	atualizaLista();

	/*
	 * Atualiza o status de presença de um candidato inscrito em um projeto de pesquisa
	 */
	
	$scope.atualizaPresenca = function(idCandidato, status) {
		$log.log("Atualiza presença > código do projeto de pesquisa = " + $scope.filtros.codigoProjetoPesquisa + "  idCandidato = " + idCandidato + "  status = " + status);
		var codigoProva = $scope.filtros.codigoProjetoPesquisa;
		dataService.atualizaPresencaProvasEscritas(codigoProjetoPesquisa, idCandidato, status).then(atualizaLista);
	};
	

	/*
	 * Filtro que seleciona candidatos presentes ou ausentes em uma projeto de pesquisa
	 */
	
	$scope.pegaInscricoes = function() {
		var inscricoes = $scope.inscricoes.filter(function(inscricao) {
			var existeProjeto = inscricao.projetosPesquisa.some(function(prova) {
				return prova.codigoProvaEscrita == $scope.filtros.codigoProjetoPesquisa;
			});
			return existeProjeto;
		});
		
		inscricoes = inscricoes.filter(function(inscricao) {
			var estaPresente = inscricao.projetosPesquisa.some(function(prova) {
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
