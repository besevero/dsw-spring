App.factory("dataService", ["$http", function ($http) {
	return {
		pegaProvasEscritas: function(params) {
			return null;
		},
		
		pegaInscricoesProvasEscritas: function(params) {
			return $http.get(contextPath + "/edital/escrita/presenca?edital=" + params.edital + "&code=" + params.codigoProva);
		},
		
		atualizaPresencaProvasEscritas: function(id) {
			return null;
		}
	};
}]);
