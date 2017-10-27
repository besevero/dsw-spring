App.factory("dataService", ["$http", function ($http) {
	return {
		pegaProvas: function(params) {
			return $http.get(contextPath + "/edital/escrita/presenca?page=" + params.page + "&size=" + params.size + "&nome=" + (params.nome || "") + "&prova=" + params.prova + "&presenca=" + params.presenca);
		},
		
		pegaInscricaoProvas: function(params) {
			return $http.get(contextPath + "/edital/escrita/presenca?page=" + params.page + "&size=" + params.size + "&nome=" + (params.nome || "") + "&prova=" + params.prova + "&presenca=" + params.presenca);
		},
		
		atualizaPresenca: function(id) {
			return $http.post(contextPath + "/edital/escrita/presenca" + id + "?" + csrf.presenca + "=" + csrf.value);
		}
	};
}]);
