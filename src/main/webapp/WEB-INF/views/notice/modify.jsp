<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <html lang="ko">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 수정</title>
    <link rel="stylesheet" href="/resources/css/comunity_page_write_layer.css">
  </head>
  <body>
    <div class="container">
      <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
      <section>
        <div class="board_write_wrap">
          <table class="board_write">
            <caption class="caption_txt">Movie 커뮤니티</caption>
            <form action="/notice/update.do" method="post" enctype="multipart/form-data">
            <!-- 		수정할 때, 리다이렉트 될 때 사용 -->
			<input type="hidden" name="noticeNo" value="${notice.noticeNo }">
<!-- 		기존 업로드 파일 체크할 때 사용 -->
			<input type="hidden" name="noticeFilename" value="${notice.noticeFilename }">
			<input type="hidden" name="noticeFileRename" value="${notice.noticeFileRename }">
			<input type="hidden" name="noticeFilepath" value="${notice.noticeFilepath }">
			<input type="hidden" name="noticeFilelength" value="${notice.noticeFilelength }">
            <thead>
              <tr>
                <th id="title">제목</th>
                <th id="title_write"><input id="title_area" type="text" name="noticeSubject" value="${notice.noticeSubject }"></th>
              </tr>
              <tr>
                <th>작성자</th>
                <th><input id="title_area" type="text" name="noticeWriter" value="${notice.noticeWriter }" readonly></th>
              </tr>
            </thead>
            <tbody>
            	<th>내용</th>
            	<th id="detail_write"><textarea name="noticeContent" rows="20" cols="108" >${notice.noticeContent }</textarea></th>
            </tbody>
            	<th>첨부파일</th>
            	<th>
            	<a href="../resources/nuploadFiles/${notice.noticeFilename }">${notice.noticeFilename }</a>
            	<input type="file" name="uploadFile">
            	</th>
          </table>
          <div class="write">
            <input type="submit" value="수정하기" name="submit">
            <input type="reset" value="초기화" name="reset">
            <button type="button" onclick="showNoticeList();">목록</button>
<!--             <a href="/community/comunity_page.html">취소</a> -->
          </div>
          </form>
          </div>
        </div>
      </section>
      <footer>
        <p>
          Remember movie. 서울특별시 강북구 수유동 <br>
          대표 : 강정대 사업자등록번호 : 001-01-00001 <br>
          대표번호 : 02-999-9999 팩스번호 : 02-888-8888 홈페이지 : Remember movie.com <br>
          Copyright Remember movie. All Rights Reserved
        </p> 
      </footer>
    </div>
  </body>
</html>