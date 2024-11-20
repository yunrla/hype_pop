// form 요소 가져오기
const f = document.forms[0];

// 정규식
const idPattern = /^[a-zA-Z0-9]{4,8}$/;  // 아이디: 4~8자, 영문 대소문자와 숫자만 허용
const passwordPattern = /^[A-Za-z0-9!@#$%^&*()_+|<>?:{}~-]{8,16}$/;  // 비밀번호:
																		// 8~16자,
																		// 문자,
																		// 숫자,
																		// 특수문자
																		// 허용
const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;  // 이메일:
																			// @기호
																			// 포함한
																			// 도메인
																			// 형식
const phonePattern = /^\d{11}$/;  // 전화번호: 숫자 11자리만 허용



// 아이디 중복 확인 버튼 객체
let isIdDuple = false;
// 아이디 중복 확인
function checkUserId() {
	const userId = f.userId.value;
	
	// 아이디 빈 값 체크
	if (!userId) {
		alert("아이디를 입력하세요!");
		f.userId.focus();
		return;
	}
	// 아이디 정규식 체크
	if (!idPattern.test(userId)) {
        alert("아이디는 영문 대소문자와 숫자 조합 4~8자로 입력해 주세요.");
        return;
    }
	// 아이디 중복 검증 요청
	fetch('/member/api/checkUserId?userId=' + userId)
		.then(response => response.text())
		.then(data => {
			if(data === 'ok'){
				alert('사용가능한 아이디입니다.');
				isIdDuple = true;
			}else{
				alert('중복된 아이디입니다.');
				isIdDuple = false;
			}
		})
		.catch(err => console.log(err));
}
// 전화 번호 입력 패턴 이벤트
document.getElementById('userNumber').addEventListener('input', function(e) {
	// 숫자 외 데이터 방지
	e.target.value = e.target.value.replace(/[^0-9]/g, '');
	// 번호 중간에 하이픈(-) 넣기
	e.target.value = e.target.value.replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/\-{1,2}$/g, "");
});

let selectedInterests = []; // 선택된 관심사 배열
const MAX_INTERESTS = 3; // 최소 선택 관심사 개수 3개
// 관심사 선택 버튼 클릭 이벤트
document.getElementById('interestBtn').addEventListener('click', function() {
    // 모달 표시
    const interestModal = document.getElementById('userInterest');
    if(interestModal.style.display === 'none'){
    	interestModal.style.display = 'flex';
    }else{
    	interestModal.style.display = 'none'; 
    }
    // 모달이 열릴 때, 이미 선택된 관심사에 대해 스타일 업데이트
    // updateInterestStyles();
});
// 선택한 관심사 선택 이벤트
document.querySelectorAll('.interestBox').forEach((box) =>{
	box.addEventListener('click', e => {
		const interBox = e.currentTarget; // div 내에 속한 요소들을 눌러도 div 요소를 찾도록
		const ckBox = interBox.querySelector('input[type="checkbox"]');	// div
																		// 요소 내의
																		// checkbox
																		// 찾기
		
	     // 관심사 개수가 3개 이상인 경우 경고창
        if (selectedInterests.length >= MAX_INTERESTS && !interBox.classList.contains('selectedBox') && !ckBox.checked) {
        	const limitMessage = document.getElementById('limitMessage');
            limitMessage.textContent = `${MAX_INTERESTS}개의 관심사만 선택해 주세요!`;
            limitMessage.style.display = 'inline'; // 경고 메시지 표시
            return; // 추가 선택을 막음
        }
		
		if(!interBox.classList.contains('selectedBox')){
			interBox.classList.add('selectedBox'); // div 요소에 스타일 부여
			ckBox.checked = true;
			selectedInterests.push(ckBox.name);	// 선택한 관심사 배열에 추가
		}else{
			interBox.classList.remove('selectedBox'); // div 요소에 스타일 해제
			ckBox.checked = false;
			selectedInterests = selectedInterests.filter(item => item !== ckBox.name); // 선택한
																						// 관심사
																						// 제외
		}
	});
});

// 선택한 관심사 업데이트 함수
// function updateInterestDisplay() {
// const interestsContainer = document.getElementById('selectedInterests');
// interestsContainer.innerHTML = ''; // 기존 내용을 비우고 새로 추가
//
// selectedInterests.forEach(function(interest) {
// const selectedBox = document.createElement('div');
// selectedBox.className = 'selectedInterestBox'; // 스타일 클래스 추가
// selectedBox.innerText = interest; // 관심사 이름 추가
// interestsContainer.appendChild(selectedBox); // 컨테이너에 추가
// console.log(interestsContainer);
// });
// }
// 모달 닫기 함수
function closeModal() {
    const interestModal = document.getElementById('userInterest');
    interestModal.style.display = 'none'; // 모달 숨기기
}
// 관심사 선택 모달 닫기
function closeInterestModal() {
    document.getElementById('userInterest').style.display = 'none';
}


function saveInterests() {
    const selectedInterests = [];  // 선택된 관심사를 저장할 배열

    // 모든 체크박스를 확인하여 체크된 항목만 배열에 추가
    document.querySelectorAll('.interestBox input[type="checkbox"]:checked').forEach(checkbox => {
        selectedInterests.push(checkbox.parentElement.textContent.trim());  // 체크된
																			// 관심사
																			// 텍스트
																			// 추가
    });

    // 선택된 관심사 배열을 #selectedInterests에 표시
    const interestsContainer = document.getElementById('selectedInterests');
    if (selectedInterests.length === 0) {
        interestsContainer.innerHTML = '선택된 관심사가 없습니다.';
    } else {
        interestsContainer.innerHTML = selectedInterests.join(', ');  // 관심사를
																		// 쉼표로
																		// 구분해서
																		// 표시
    }
    
    if (selectedInterests.length !== 3) {
    	 const limitMessage = document.getElementById('limitMessage');
         limitMessage.textContent = '관심사는 3개를 선택해 주세요!';
         limitMessage.style.display = 'inline'; // 경고 메시지 표시
	    return; // 경고 후 종료
	}


    // 모달을 닫기 (모달 창 숨기기)
    closeModal();
}
// 확인 버튼 클릭 이벤트
// document.getElementById('confirmButton').addEventListener('click', function()
// {
// if (selectedInterests.length < 1) {
// alert('최소 1개 이상 선택하세요');
// return; // 경고 후 종료
// }
// closeModal(); // 모달 닫기
// });

// ------------------비동기 정책 동의 안내서 띄우기-------------------------------
// 모달 열기
function openPolicyModal() {
	const modalBox= document.getElementById('policyModal');
	if (modalBox) {
		modalBox.style.display = "block";
	} else {
		console.error('ID에 해당하는 모달 요소를 찾을 수 없습니다');
	}
}
// 모달 닫기
function closePolicyModal() {
	const modalBox= document.getElementById('policyModal');
	if (modalBox) {
		modalBox.style.display = "none";
	} else {
		console.error('ID에 해당하는 모달 요소를 찾을 수 없습니다');
	}
}

function policyModal(type) {
    console.log("type: " + type);

    // REST Controller로 보내기
    fetch(`/member/api/getPolicyContent?type=${type}`, {
		        method: 'GET',
		        headers: {'Accept': 'application/json; charset=UTF-8'} // 서버에
																		// JSON
																		// 응답을
																		// 요청
    		}
    	)
        .then(response => {
            // 서버 응답이 ok일 때만 데이터 처리
            if (!response.ok) {
                throw new Error("네트워크 응답에 문제가 있습니다");
            }
            return response.text();
        })
        .then(data => {
            // 모달 내용 업데이트
        	console.log(data);
            document.getElementById('modalContent').innerHTML = data;
            openPolicyModal();
        })
        .catch(error => {
            console.error('데이터를 가져오는 중 오류가 발생했습니다:', error);
            document.getElementById('modalContent').innerHTML = "내용을 불러오는 데 실패했습니다.";
        });
}

// 모달 바깥쪽을 클릭하면 모달을 닫는 기능
window.onclick = function(event) {
    var policyModal = document.getElementById('policyModal');
    var locationModal = document.getElementById('locationModal');
    var notificationModal = document.getElementById('notificationModal');
    
    if (event.target == policyModal) {
        closePolicyModal();
    } else if (event.target == locationModal) {
        closeLocationModal();
    } else if (event.target == notificationModal) {
        closeNotificationModal();
    }
};

// 주민등록번호 입력 패턴 이벤트
document.getElementById('idCardNum').addEventListener('input', function(e) {
	// 숫자 외 데이터 방지
	let val = e.target.value; 
	val = val.replace(/[^0-9]/g, '');
	if(val.length === 6) document.getElementById('idCardNum2').focus();
	
});
document.getElementById('idCardNum2').addEventListener('input', function(e) {
	// 숫자 외 데이터 방지
	let val = e.target.value; 
	val = val.replace(/[^0-9]/g, '');
});
let isVerifiedIdCard = false;  
// 주민등록번호 검증 클릭 이벤트
function confirmIdCardNum(){
	
	const sn = f.idCardNum.value + f.idCardNum2.value;
	
	// 주민등록번호 형식을 확인
    if (sn.length !== 13) {
    	alert("주민등록번호를 올바르게 입력하세요.");
    	return false;
    }

    // 가중치
    const weight = [2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5];
    let sum = 0;

    // 앞 12자리와 가중치를 곱하여 합산
    for (let i = 0; i < 12; i++) {
        sum += parseInt(sn[i]) * weight[i];
    }

    // 검증 번호 계산
    const checkDigit = (11 - (sum % 11)) % 10;

    // 마지막 자리(검증 번호)와 비교
    if(checkDigit === parseInt(sn[12])){
    	isVerifiedIdCard = true // submit 전 검증 완료 유무 판단
    	alert("검증이 완료되었습니다."); // 검증 완료 알림
    	f.idCardNum.value += f.idCardNum2.value.substr(0,1); // 던질 데이터 변경 앞
																// 6자리 + 뒤 1자리
    	// 검증 후 주민등록번호 변경 불가 - readonly
    	f.idCardNum.setAttribute('readonly', 'readonly');
    	f.idCardNum.classList.add('verified');
    	f.idCardNum2.setAttribute('readonly', 'readonly');
    	f.idCardNum2.classList.add('verified');
    }else{
    	alert("올바르지 않은 주민등록번호입니다.");
    	isVerifiedIdCard = false;
    }
}


document.getElementById('joinForm').addEventListener('submit', function(event) {
    event.preventDefault(); // form의 기본 submit 동작 막기
    checkForm(); // 폼 유효성 검사 후 submit
});

// 회원가입 버튼 클릭 이벤트
function formSubmit(){
	const userId = f.userId.value;
	const userPw = f.userPw.value;
	const passwordCheck = f.passwordCheck.value;
	const userEmail = f.userEmail.value;
	const userName = f.userName.value;
	const userNumber = f.userNumber.value;
	isVerifiedIdCard = true;
	
	if (!userId) {
		alert("아이디를 입력하세요!");
		f.userId.focus();
		return;
	}
	if (!userPw) {
		alert("비밀번호를 입력하세요!");
		f.userPw.focus();
		return;
	}
	if (passwordCheck !== userPw) {
		alert("비밀번호가 같지 않습니다!");
		f.passwordCheck.focus();
		return;
	}
	if (!userEmail) {
		alert("이메일을 입력하세요!");
		f.userEmail.focus();
		return;
	}
	if (!userName) {
		alert("이름을 입력하세요!");
		f.userName.focus();
		return;
	}
	if (!userNumber || userNumber.length < 13) {
		alert("전화번호를 정확히 입력하세요!");
		f.userNumber.focus();
		return;
	}
	
	
	if(!isIdDuple){
		alert("아이디 중복 확인을 해주세요.");
		return;
	}
	if(!isVerifiedIdCard){
		alert("주민등록번호를 검증을 해주세요.");
		return;
	}
	
	// 동의 확인
	if( !document.querySelector("#privacy").checked ||
		!document.querySelector("#location").checked ||
		!document.querySelector("#notification").checked){
		alert('모든 동의를 선택해주세요.') ;
		return;
	}

	document.getElementById("joinForm").submit();
}









// style이랑 클릭이벤트로 개인정보 처리방침 띄우기

// function openModal(modalId) {
// document.getElementById(modalId).style.display = "block";
// }
//
// function closeModal(modalId) {
// document.getElementById(modalId).style.display = "none";
// }
//
// // 모달 클릭 시 닫기
// window.onclick = function(event) {
// var modals = document.getElementsByClassName('modal');
// for (var i = 0; i < modals.length; i++) {
// if (event.target == modals[i]) {
// modals[i].style.display = "none";
// }
// }
// }
