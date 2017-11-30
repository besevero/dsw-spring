App.factory("dataService", ["$http", function ($http) {
	return {
		pegaProvasEscritas: function(params) {
			return $http.get(contextPath + "/edital/escrita/presenca?prova=" + params.codigoProva);
		},
		
		pegaInscricoesProvasEscritas: function(params) {
			return $http.get(contextPath + "/edital/escrita/presenca?code=" + params.codigoProva);
		},
		
		atualizaPresencaProvasEscritas: function(codigo, id, status) {
			//debugger;
			console.log(csrf.name + "=" + csrf.value);
			return $http.post(contextPath + "/edital/escrita/presenca/atualiza?code=" + codigo + "&id=" + id + "&status=" + status,
						"", { headers: { "X-CSRF-TOKEN": csrf.value }});
		}
	};
}]);
