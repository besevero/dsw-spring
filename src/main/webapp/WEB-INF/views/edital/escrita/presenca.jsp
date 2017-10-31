<%@include file="/WEB-INF/views/helper/template.jsp"%>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/third-party/ngTable/ng-table.min.css" />

<div id="contents" data-ng-controller="presencaController as ctrl">

	<div class="mdl-grid">
		<div class="mdl-cell mdl-cell--12-col page-header">
			<h3>
				<spring:message code="edital.presenca.prova.escrita.titulo" />
			</h3>
		</div>
	</div>

	<div class="mdl-grid">
		<div class="mdl-cell mdl-cell--12-col page-filter">
			<div class="mdl-dialog__content presenca left">
				<input type="text" data-ng-change='ctrl.atualizaFiltro()'
					data-ng-model="filtros.nome"
					placeholder="<spring:message code='edital.presenca.prova.list.label.name.filter'/>"
					size="40" />
			</div>
			<div class="mdl-dialog__content presenca left">
				<select ng-model="navCtrl.editalSelecionado" class="wide"
					ng-options="edital.id as edital.nome for edital in navCtrl.editais"
					ng-init="navCtrl.editalSelecionado=${user.idEdital}">
					<option value="" disabled selected><spring:message
							code='edital.presenca.prova.list.label.select.filter.presence' /></option>
				</select>
			</div>
			<div class="mdl-dialog__content presenca left">
				<select ng-model="navCtrl.editalSelecionado" class="wide"
					ng-options="edital.id as edital.nome for edital in navCtrl.editais"
					ng-init="navCtrl.editalSelecionado=${user.idEdital}">
					<option value="" disabled selected><spring:message
							code='edital.presenca.prova.list.label.select.filter.proof' /></option>
				</select>
			</div>
			<div class="clear"></div>
		</div>

		<div class="mdl-cell mdl-cell--12-col">
			<table
				class="mdl-data-table mdl-js-data-table mdl-shadow--2dp wide paginated"
				style="font-size: 12px">
				<tr data-ng-repeat="item in inscricoes">
					<td class="mdl-data-table__cell--non-numeric"
						header-class="'text-left'"
						data-title="'<spring:message code='edital.list.table.name'/>'"
						data-ng-click="ctrl.edita(item.id)">{{item.nomeCandidato}}</td>
					<td class="mdl-data-table__cell--non-numeric"
						header-class="'text-left'"
						data-title="'<spring:message code='edital.list.table.status'/>'"
						data-ng-click="ctrl.edita(item.id)">{{item.nomeStatus}} 
						<input type="checkbox" name="vehicle" value="Car" checked>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/edital/presenca.controller.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/edital/presenca.dataService.js"></script>
