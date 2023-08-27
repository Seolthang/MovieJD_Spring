<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
  <html lang="ko">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>커뮤니티</title>
    <link rel="stylesheet" href="/resources/css/comunity_page_layer.css">
  </head>
  <body>
    <div class="container">
      <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
        <div class="board_list_wrap">
          <table class="board_list">
            <caption class="caption_txt">Movie 커뮤니티</caption>
            <thead>
              <tr>
                <th>NO.</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>작성날짜</th>
                <th>첨부파일</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach var="notice" items="${sList }" varStatus="i">
            	<tr>
            		<td>${notice.noticeNo }</td>
            		<td><a href="/notice/detail.do?noticeNo=${notice.noticeNo }">${notice.noticeSubject }</a></td>
            		<td>${notice.noticeWriter }</td>
            		<td>
            			<fmt:formatDate pattern="yyyy-MM-dd" value="${notice.nCreateDate }"/>
            		</td>
            		<td>
            			<c:if test="${!empty notice.noticeFilename }">O</c:if>
            			<c:if test="${empty notice.noticeFilename }">X</c:if>
            		</td>
            	</tr>
              	</c:forEach>
            </tbody>
          <tr align="center">
          	<td colspan="5">
          		<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
          			<c:url var="pageUrl" value="/notice/search.do">
          				<c:param name="page" value="${p }"></c:param>
          				<c:param name="searchCondition" value="${searchCondition }"></c:param>
          				<c:param name="searchKeyword" value="${searchKeyword }"></c:param>
          			</c:url>
          			<a href="${pageUrl }">${p }</a>&nbsp;
          		</c:forEach>
          	</td>
          </tr>
          <tr>
          	<td colspan="5">
          		<form action="/notice/search.do" method="get">
          			<select name="searchCondition">
          				<option value="all" <c:if test="${searchCondition == 'all' }">selected</c:if>>전체</option>
          				<option value="writer" <c:if test="${searchCondition == 'writer' }">selected</c:if>>작성자</option>
          				<option value="subject" <c:if test="${searchCondition == 'subject' }">selected</c:if>>제목</option>
          				<option value="content" <c:if test="${searchCondition == 'content' }">selected</c:if>>내용</option>
          			</select>
          			<input type="text" name="searchKeyword" placeholder="검색어를 입력하세요" value="${searchKeyword }">
          			<input type="submit" value="검색">
          		</form>
          	</td>
          </tr>
          </table>
          <div class="write">
            <a href="/notice/insert.do">글쓰기</a>
          </div>
          </div>
        </div>
      <footer>
        <p>
          Remember movie. 서울특별시 강북구 수유동 <br>
          대표 : 강정대 사업자등록번호 : 001-01-00001 <br>
          대표번호 : 02-999-9999 팩스번호 : 02-888-8888 홈페이지 : Remember movie.com <br>
          Copyright Remember movie. All Rights Reserved
        </p> 
      </footer>
  </body>
</html>