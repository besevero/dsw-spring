<%@include file="/WEB-INF/views/helper/template.jsp"%>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/third-party/ngTable/ng-table.min.css" />

<div id="contents">

	<div class="mdl-grid">
		<div class="mdl-cell mdl-cell--12-col page-header">
			<h3>
				<spring:message code="encerramento.prova.escrita.titulo" />
			</h3>
		</div>
	</div>

	<div class="mdl-grid">
		
		<table
			class="mdl-data-table mdl-js-data-table mdl-shadow--2dp wide paginated" style="font-size: 12px">
			<tr>
				<td class="mdl-data-table__cell--non-numeric">
					<spring:message code='encerramento.prova.escrita.pendencias' />
				</td>
			</tr>
			<c:forEach var="erro" items="${requestScope.candidatos}">
				<tr>
					<td class="mdl-data-table__cell--non-numeric">
						<c:out value="${erro}"/> 
					</td>
				</tr>
			</c:forEach>
		</table>
		
	</div>
</div>

