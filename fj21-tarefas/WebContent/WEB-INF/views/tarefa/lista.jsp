<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="resources/js/jquery.js"></script>
		<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	</head>
	<body>
		<script type="text/javascript">
			function finalizaAgora(id){
				$.post("finalizaTarefa", {'id' : id}, function(){
					$("#tarefa_"+id).html("Finalizado");
				});
			}
		</script>
		<a href="novaTarefa">Criar nova tarefa</a>
		<br /><br />
		
		<table>
			<tr>
				<th>Id</th>
				<th>Descrição</th>
				<th>Finalizado?</th>
				<th>Data de finalização</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${tarefas}" var="tarefa">
				<tr>
					<td>${tarefa.id}</td>
					<td>${tarefa.descricao}</td>
					<c:if test="${tarefa.finalizado eq false}">
						<td id="tarefa_${tarefa.id}">
							<a href="#" onClick="finalizaAgora(${tarefa.id})">
								Finaliza agora!
							</a>
						</td>
					</c:if>
					<c:if test="${tarefa.finalizado eq true}">
						<td>Finalizado</td>
					</c:if>
					<td>
						<input type="text" id="dataNascimento" name="dataNascimento" value="<fmt:formatDate id="dataFinalizacao" value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy"/>"/>
						<script type="text/javascript">
							$("#dataFinalizacao").datepicker({dateFormat: 'dd/mm/yy', changeYear: true, changeMonth: true});
						</script>
					</td>
					<td><a href="removeTarefa?id=${tarefa.id}">Remover</a></td>
					<td><a href="mostraTarefa?id=${tarefa.id}">Alterar</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>