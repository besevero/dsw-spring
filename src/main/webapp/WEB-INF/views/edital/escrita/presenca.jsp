<%@include file="/WEB-INF/views/helper/template.jsp"%>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/third-party/ngTable/ng-table.min.css" />

<div id="contents" data-ng-controller="presencaController as ctrl">

	<div class="mdl-grid">
		<div class="mdl-cell mdl-cell--12-col page-header">
			<h3>
				<spring:message code="presenca.prova.escrita.titulo" />
			</h3>
		</div>
	</div>

	<div class="mdl-grid">
		<div class="mdl-cell mdl-cell--12-col page-filter">
			<div class="mdl-dialog__content presenca left">
				<input type="text" size="30"
					placeholder="<spring:message code='presenca.prova.escrita.list.label.name.filter'/>"
					data-ng-model="buscaCandidato"/>
			</div>
			<div class="mdl-dialog__content presenca left">
				<select class="wide"
					data-ng-model="filtros.codigoProva"
					data-ng-change="ctrl.selecionaProva(filtros.codigoProva)">
					<option value="" selected>
						<spring:message code='presenca.prova.escrita.list.label.select.filter.prova' />	
					</option>
					<c:forEach var="prova" items="${sessionScope.edital.provasEscritas}">
						<option value="${prova.codigo}">
							<c:out value="${prova.nome}"/> 
						</option>
					</c:forEach>
				</select>
			</div>
			<div class="mdl-dialog__content presenca left">
				<select class="wide" data-ng-model="tipoPresenca" data-ng-click=selecionaPresenca(tipoPresenca)>
					<option value="" selected>
						<spring:message code='presenca.prova.escrita.list.label.select.filter.presenca' />
					</option>
					<option value="true">
						<spring:message code='presenca.prova.escrita.list.label.select.filter.presenca.presentes' />
					</option>
					<option value="false">
						<spring:message code='presenca.prova.escrita.list.label.select.filter.presenca.ausentes' />
					</option>
				</select>
			</div>
			<div class="clear"></div>
		</div>

		<div class="mdl-cell mdl-cell--12-col">
			<table
				class="mdl-data-table mdl-js-data-table mdl-shadow--2dp wide paginated"
				style="font-size: 12px">
				<tr>
					<td class="mdl-data-table__cell--non-numeric">
						<spring:message code='presenca.prova.escrita.list.table.nome' />
					</td>
					<td class="mdl-data-table__cell--non-numeric">
						<spring:message code='presenca.prova.escrita.list.table.status' />
					</td>
				</tr>
				<tr data-ng-repeat="item in inscricoes | filter : buscaCandidato | filter : filtros.codigoProva : tipoPresenca"
					data-ng-show="filtros.codigoProva && tipoPresenca">

					<td class="mdl-data-table__cell--non-numeric">
						{{item.nomeCandidato}}					
					</td>
					
					<td class="mdl-data-table__cell--non-numeric">
							
							<span data-ng-repeat="subitem in item.provasEscritas
							| filter : { codigoProvaEscrita : filtros.codigoProva }
							| filter : { presenca : tipoPresenca }" >
								<!--
								Prova: {{subitem.codigoProvaEscrita}}
								Presença: {{subitem.presenca}}
								-->
						
								<input id="status" type="checkbox"
									data-ng-click="atualizaPresenca(item.idCandidato, !subitem.presenca)"
									data-ng-checked="{{subitem.presenca}}">		
							</span>
	
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/edital/presenca.controller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/edital/presenca.dataService.js"></script>
