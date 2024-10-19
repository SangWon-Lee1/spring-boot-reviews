function deleteCustomer(customerId) {
    if (confirm("정말로 회원 탈퇴를 하시겠습니까?")) {
        fetch(`/customers/${customerId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    alert("회원 탈퇴가 완료되었습니다.");
                    window.location.href = '/bank';
                } else {
                    alert("회원 탈퇴에 실패하였습니다.");
                }
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }
}