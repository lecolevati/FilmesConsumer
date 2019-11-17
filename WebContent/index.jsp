<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filmes</title>
</head>
<body>
	<div align="center">
		<form action="filmes" method="post">
			<table>
				<tr>
					<td>ID Filme</td>
					<td><input type="number" min="1" name="idFilme" value="<c:if test="${not empty filme }"><c:out value="${filme.idFilme }"/></c:if>"></td>
				</tr>
				<tr>
					<td>Nome Filme</td>
					<td><input type="text" size="50" name="nomeFilme" value="<c:if test="${not empty filme }"><c:out value="${filme.nomeFilme }"/></c:if>"></td>
				</tr>
				<tr>
					<td>Código Gênero</td>
					<td><input type="number" min="1" max="16" name="codGenero" value="<c:if test="${not empty filme }"><c:out value="${filme.generoFilme.codGenero }"/></c:if>"></td>
				</tr>
				<tr>
					<td>Tipo Gênero</td>
					<td><input type="text" name="tipoGenero" value="<c:if test="${not empty filme }"><c:out value="${filme.generoFilme.tipoGenero }"/></c:if>"></td>
				</tr>
				<tr>
					<td><input type="submit" name="acao" value="insert"></td>
					<td><input type="submit" name="acao" value="getAllFilmes"></td>
				</tr>
			</table>
		</form>
	</div>
	<div align="center">
		<c:if test="${not empty erro }"><h1><c:out value="${erro }"/></h1></c:if>
		<c:if test="${not empty retorno }"><c:out value="${retorno }"/></c:if>
		<c:if test="${not empty lista }">
			<table border="1">
				<tr>
					<td>ID Filme</td>
					<td>Nome Filme</td>
					<td>Código Gênero</td>
					<td>Tipo Filme</td>
				</tr>
				<c:forEach items="${lista }" var="f">
				<tr>
					<td><c:out value="${f.idFilme }"/></td>
					<td><c:out value="${f.nomeFilme }"/></td>
					<td><c:out value="${f.generoFilme.codGenero }"/></td>
					<td><c:out value="${f.generoFilme.tipoGenero }"/></td>
				</tr>
				</c:forEach>															
			</table>
		</c:if>
	</div>
</body>
</html>