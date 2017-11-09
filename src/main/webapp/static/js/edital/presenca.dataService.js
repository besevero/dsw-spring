App.factory("dataService", ["$http", function ($http) {
	return {
		pegaProvasEscritas: function(params) {
			return null;
		},
		
		pegaInscricoesProvasEscritas: function(params) {
			return $http.get(contextPath + "/edital/escrita/presenca?code=" + params.codigoProva);
		},
		
		atualizaPresencaProvasEscritas: function(params) {
			return $http.get(contextPath + "/edital/escrita/presenca?code=" + params.codigoProva + "?id=" + params.id + "?update=" + params.update);
		}
	};
}]);
