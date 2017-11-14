App.factory("dataService", ["$http", function ($http) {
	return {
		pegaProvasEscritas: function(params) {
			return $http.get(contextPath + "/edital/escrita/presenca?prova=" + params.codigoProva);
		},
		
		pegaInscricoesProvasEscritas: function(params) {
			return $http.get(contextPath + "/edital/escrita/presenca?code=" + params.codigoProva);
		},
		
		atualizaPresencaProvasEscritas: function(params, params2, params3) {
			// debugger;
			return $http.get(contextPath + "/edital/escrita/presenca?code=" + params+ "&id=" + params2+ "&status=" + params3);
		}
	};
}]);
