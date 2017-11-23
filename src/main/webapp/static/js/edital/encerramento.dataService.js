App.factory("dataService", ["$http", function ($http) {
	return {
		carregaProvasEscritas: function() {
			return $http.get(contextPath + "/edital/escrita/encerramento);
		}
	};
}]);
