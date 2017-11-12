App.factory("dataService", ["$http", function ($http) {
	return {
		pegaProvasEscritas: function(params) {
			return $http.get(contextPath + "/edital/escrita/presenca?prova=" + params.codigoProva);
		},
		
		pegaInscricoesProvasEscritas: function(params) {
			return $http.get(contextPath + "/edital/escrita/presenca?code=" + params.codigoProva);
		},
		
		atualizaPresencaProvasEscritas: function(params) {
			return $http.get(contextPath + "/edital/escrita/presenca?code=" + "FSI" + "&id=" + "1"+ "&status=" + "false");
		}
	};
}]);
