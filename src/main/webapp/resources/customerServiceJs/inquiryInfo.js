// 모달 열기
function openModal() {
	document.getElementById("answerModal").style.display = "block";
}

// 모달 닫기
function closeModal() {
	document.getElementById("answerModal").style.display = "none";
	document.getElementById("answerContent").value = ''; 
}

//답변 작성
function submitResponse() {
    var qnaNo = document.getElementById("qnaNo").value; // 문의 번호
    var qnaAnswer = document.getElementById("answerContent").value; // 작성한 답변

    if (!qnaAnswer.trim()) {
        alert("답변을 입력해주세요.");
        return;
    }

    var data = {
        qnaNo: qnaNo,
        qnaAnswer: qnaAnswer
    };
    
    fetch("/support/updateAnswer", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("답변 업데이트 중 오류가 발생했습니다.");
        }
        return response.text(); 
    })
    .then(result => {
        alert("답변이 성공적으로 업데이트되었습니다.");
        closeModal(); 
        location.reload(); 
    })
    .catch(error => {
        alert(error.message);
    });
}



// 모달 외부 클릭 시 닫기
window.onclick = function(event) {
	const modal = document.getElementById("responseModal");
	if (event.target == modal) {
		closeModal();
	}
}

// 문의 내용 삭제 함수
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("deleteInquiry").onclick = function() {
        if (confirm("정말로 이 문의를 삭제하시겠습니까?")) {
            document.getElementById("deleteForm").submit();
        }
    };
});


