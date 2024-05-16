async function logout(contextPath) {

	let url = `${contextPath}/logout`;

	try {
		let response = await fetch(url);
		let data = await response.json();
		if (data.result == "success") {
			window.location.href = `${contextPath}/jsp/login.jsp`
		}
	} catch (error) {
		console.log(error);
		alertify.error('logout 과정에 문제가 생겼습니다.');
	}
}


function makePaginationHtml(listRowCount, pageLinkCount, currentPageIndex, totalListItemCount, htmlTargetId) {

	var targetUI = document.querySelector("#" + htmlTargetId);

	var pageCount = Math.ceil(totalListItemCount / listRowCount);

	var startPageIndex = 0;
	if ((currentPageIndex % pageLinkCount) == 0) { //10, 20...맨마지막
		startPageIndex = ((currentPageIndex / pageLinkCount) - 1) * pageLinkCount + 1
	} else {
		startPageIndex = Math.floor(currentPageIndex / pageLinkCount) * pageLinkCount + 1
	}

	var endPageIndex = 0;
	if ((currentPageIndex % pageLinkCount) == 0) { //10, 20...맨마지막
		endPageIndex = currentPageIndex;
	} else {
		endPageIndex = Math.floor(currentPageIndex / pageLinkCount) * pageLinkCount + pageLinkCount;
	}

	// endPageIndex 가 전체 pageCount(페이지 전체 수) 보다 크면 페이지 전체 수로 보정 
	if (endPageIndex > pageCount) endPageIndex = pageCount

	var prev;
	if (currentPageIndex <= pageLinkCount) {
		prev = false;
	} else {
		prev = true;
	}

	var next;

	if (endPageIndex == pageCount) { // 위에서 더 큰 값은 보정했으므로 같은 지만 비교
		next = false;
	} else {
		next = true;
	}

	var paginationHtml =
		`<ul class="pagination pagination justify-content-center">`;
	if (prev) {
		paginationHtml +=
			`<li class="page-item">
         <a class="page-link" href="javascript:movePage( ${(startPageIndex - 1)} )" aria-label="Previous">
         <span aria-hidden="true">&laquo;</span>
         </a>
         </li>`;
	}


	for (var i = startPageIndex; i <= endPageIndex; i++) {

		if (i == currentPageIndex) {
			paginationHtml +=
				`<li class="page-item active"><a class="page-link" href="javascript:movePage(${i})">${i}</a></li>`;
		} else {
			paginationHtml +=
				`<li class="page-item"><a class="page-link" href="javascript:movePage(${i})">${i}</a></li>`;
		}
	}

	if (next) {
		paginationHtml +=
			`<li class="page-item">
         <a class="page-link" href="javascript:movePage( ${(endPageIndex + 1)} )" aria-label="Next">
         <span aria-hidden="true">&raquo;</span>
         </a>
         </li>`;
	}

	paginationHtml += `</ul>`;

	targetUI.innerHTML = paginationHtml;
}

function makeDateStr(year, month, day, type) {
	//2010.05.05
	return year + type + ((month < 10) ? '0' + month : month) + type + ((day < 10) ? '0' + day : day);
}

function makeTimeStr(hour, minute, second, type) {
	//07:25:33
	return ((hour < 10) ? '0' + hour : hour) + type + ((minute < 10) ? '0' + minute : minute) + type + ((second < 10) ? '0' + second : second);
}

function initUserInfo() {
	document.querySelector("#imgUserProfileImageUrl").setAttribute("src", sessionStorage.getItem("userProfileImageUrl"));
}

function destroyUserInfo() {
	sessionStorage.removeItem("userName");
	sessionStorage.removeItem("userProfileImageUrl");
}