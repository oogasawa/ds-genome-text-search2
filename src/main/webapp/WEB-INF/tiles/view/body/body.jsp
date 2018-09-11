<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<div class="container">

<div class="row">
<div class="col-sm-3">
Total hits: <c:out value="${totalHits}" /><br/>
(<c:out value="${totalPages}" /> pages)
</div>
<div class="col-sm-9">
<ul class="pagination">
  <c:forEach var="p" items="${pageList}">
  <li class="page-item ${(p == page)?'active':''}">
  <a class="page-link" href="/search?query=${query}&page=${p}"><c:out value="${p}" /></a></li>
  </c:forEach>
</ul>
</div>
</div>

<div class="row">
  <div class="table-responsive">

  <table class="table table-hover">

  <tbody>

  <c:forEach var="resultInfo" items="${resultInfoList}">
  <tr>
  <td>
  <a href="${resultInfo.jbrowseLinkUrl}">
  <img src="/static/images/506708890-612x612.jpg" width="30" height="30" />
  </a>
  </td>
  <td>${resultInfo.seqid}</td>
  <td>${resultInfo.source}</td>
  <td>${resultInfo.type}</td>
  <td>${resultInfo.start}</td>
  <td>${resultInfo.end}</td>
  <td>${resultInfo.score}</td>
  <td>${resultInfo.strand}</td>
  <td>${resultInfo.phase}</td>
  <td>${resultInfo.attributes}</td>
  </tr>

  </c:forEach>

  </tbody>
  </table>

</div>
  </div>
</div>

