document.querySelector('form').addEventListener('submit', event => {
    event.preventDefault(); // 기본 제출 동작 방지

    const formData = {
        customerId: document.getElementById('customerId').value,
        customerName: document.getElementById('customerName').value,
        password: document.getElementById('password').value,
    };

    fetch('/customers', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData),
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text); });
            }
            return response.json();
        })
        .then(data => {
            alert('등록 성공!');
            window.location.href = '/bank';
        })
        .catch(error => {
            alert('오류: ' + error.message);
        });
});