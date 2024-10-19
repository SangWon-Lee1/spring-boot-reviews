document.querySelector('form').addEventListener('submit', event => {
    event.preventDefault();

    const formData = {
        accountId: document.getElementById('accountId').value,
        customerId: document.getElementById('customerId').value,
        balance: document.getElementById('balance').value
    };

    fetch('/accounts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.ok) {
                alert("계좌 생성이 완료되었습니다.");
                window.location.href = '/bank/menu';
            } else {
                alert("계좌 생성에 실패하였습니다.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
});