App.factory("dataService", ["$http", function ($http) {
	return {	
		pegaInscricoesProjetosPesquisa: function(params) {
			return $http.get(contextPath + "/edital/alinhamento/presenca?code=" + params.codigoProjetoPesquisa);
		},
		
		atualizaInscricoesProjetosPesquisa: function(codigo, id, status) {
			//debugger;
			console.log(csrf.name + "=" + csrf.value);
			return $http.post(contextPath + "/edital/escrita/alinhamento/atualiza?code=" + codigo + "&id=" + id + "&status=" + status,
						"", { headers: { "X-CSRF-TOKEN": csrf.value }});
		}
	};
}]);
