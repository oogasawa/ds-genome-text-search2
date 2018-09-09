<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<div class="jumbotron text-center" id="header">
  <h1>DS Genome text search</h1>
  <form action="/search" method="GET">
  <input type="text" id="query" name="query" size="40" value="${query}" autofocus />
  <input type="submit" value="submit" />
  <input type="button" value="clear" onclick="document.getElementById('query').value=''" />

  </form>
  Powered by <a href="http://lucene.apache.org/">Apache Lucene</a>'s
  <a href="https://static.javadoc.io/org.apache.lucene/lucene-core/7.4.0/org/apache/lucene/util/automaton/RegExp.html">RegExp query system.</a>
</div>

