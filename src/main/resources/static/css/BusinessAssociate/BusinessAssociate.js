function copyLink() {
    // Get the input field
    var copyText = document.getElementById("copyLinkInput");

    // Select the text in the input field
    copyText.select();
    copyText.setSelectionRange(0, 99999); // For mobile devices

    // Copy the text inside the input field to the clipboard
    document.execCommand("copy");

    // Alert the copied text
    alert("Copied the link: " + copyText.value);
}

function updateQRCodeLink() {
		var bsurl = $('#copyLinkInput').val();
		console.log(bsurl);
		
		
		var baId = $('#businessAssociateId').val();
		console.log(baId);
		
		var url = "http://localhost:8080/businessAssociate/businessAssociateDashboard/QRCodePage?partyid=" + baId;
		console.log(url);

        window.location=url;
    }
    
function transferRequest() {
    const transferButton = document.getElementById('transferAmount');
    const amount = document.querySelectorAll('.amount-checkbox');

    const selectedAmount = [];
    var key=0;
    amount.forEach(checkbox => {
        if (checkbox.checked) {
            selectedAmount[key]=checkbox.value;
            key+=1;
            //selectedAmount.push(checkbox.value);
        }
    });

    var amountToTransfer={amount:selectedAmount};

    console.log(selectedAmount);

    const data = {amount: selectedAmount};

fetch('http://localhost:8080/businessAssociate/businessAssociateDashboard/WalletRequest', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
})
.then(response => {
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json();
})
.then(data => {
    console.log('Response from server:', data);
     // Update the UI to display the total amount
     const totalAmountSpan = document.getElementById('totalAmount');
     totalAmountSpan.textContent = data.totalAmount;
})
.catch(error => {
    console.error('There was a problem with your fetch operation:', error);
});

}



function totalAmountTransfer() {
    const transferButton = document.getElementById('transferAmount');
    const amount = document.querySelectorAll('.amount-checkbox');

    const selectedAmount = [];
    var key=0;
    amount.forEach(checkbox => {
        if (checkbox.checked) {
            selectedAmount[key]=checkbox.value;
            key+=1;
            //selectedAmount.push(checkbox.value);
        }
    });

    var amountToTransfer={amount:selectedAmount};

    console.log(selectedAmount);

    const data = {amount: selectedAmount};

fetch('http://localhost:8080/businessAssociate/businessAssociateDashboard/totalAmount', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
})
.then(response => {
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json();
})
.then(data => {
    console.log('Response from server:', data);
     // Update the UI to display the total amount
     const totalAmountSpan = document.getElementById('totalAmount');
     totalAmountSpan.textContent = data.totalAmount;
})
.catch(error => {
    console.error('There was a problem with your fetch operation:', error);
});

}

