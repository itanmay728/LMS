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

    window.location = url;
}

function transferRequest() {
    const transferButton = document.getElementById('transferAmount');
    const amount = document.querySelectorAll('.amount-checkbox');

    const selectedAmount = [];
    var key = 0;
    amount.forEach(checkbox => {
        if (checkbox.checked) {
            selectedAmount[key] = checkbox.value;
            key += 1;
            //selectedAmount.push(checkbox.value);
        }
    });

    var amountToTransfer = { amount: selectedAmount };

    console.log(selectedAmount);

    const data = { amount: selectedAmount };

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
    var key = 0;
    amount.forEach(checkbox => {
        if (checkbox.checked) {
            selectedAmount[key] = checkbox.value;
            key += 1;
            //selectedAmount.push(checkbox.value);
        }
    });

    var amountToTransfer = { amount: selectedAmount };

    console.log(selectedAmount);

    const data = { amount: selectedAmount };

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

// // Get the current date
// const currentDate = new Date();
// // Set the date the value was added to the wallet (assuming it's hardcoded here, but you'd get this dynamically)
// const valueAddedDate = new Date('2024-02-08'); // Change this date to the actual date the value was added to the wallet

// // Calculate the difference in days
// const diffInDays = Math.floor((currentDate - valueAddedDate) / (1000 * 60 * 60 * 24));

// // Enable the checkbox if the value has been in the wallet for 30 days
// if (diffInDays >= 30) {
//     //document.querySelectorAll('.amount-checkbox').disabled=false;
//    document.getElementById('myCheckbox').disabled = false;
//    document.getElementById('myCheckbox').disabled = false;
// }


// document.addEventListener("DOMContentLoaded", function () {
//         const currentDate = new Date();

//         // Assuming walletDetails is an array containing objects with a 'dateAdded' property representing the date the value was added to the wallet
//         const walletDetails = [...document.querySelectorAll('[th:each="wd, rowStat: ${walletDetails}"]')];

//         walletDetails.forEach((row, index) => {
//             const dateAddedString = row.querySelector('[th:text="${wd.date}"]').textContent;
//             const dateAdded = new Date(dateAddedString);

//             const diffInDays = Math.floor((currentDate - dateAdded) / (1000 * 60 * 60 * 24));

//             if (diffInDays >= 30) {
//                 const checkbox = row.querySelector('.amount-checkbox');
//                 checkbox.disabled = false;
//             }
//         });
//     });


document.addEventListener("DOMContentLoaded", function () {
    const currentDate = new Date();
    const walletRows = document.querySelectorAll('.wallet-row');

    walletRows.forEach((row) => {
        const dateAddedString = row.querySelector('td:nth-child(5)').textContent; // Assuming date is the 5th column
        const dateAdded = new Date(dateAddedString);

        const diffInDays = Math.floor((currentDate - dateAdded) / (1000 * 60 * 60 * 24));

        if (diffInDays >= 30) {
            const checkbox = row.querySelector('.amount-checkbox');
            checkbox.disabled = false;
        }
        else {
            const checkboxes = row.querySelectorAll('.checkbox');
            checkboxes.forEach((checkbox) => {
                checkbox.addEventListener('click', function () {

                    var inp = document.getElementById('myCheckbox');
                if (inp.disabled) {
                    //console.log('disabled input clicked!');
                    alert("It has not been 30 days");
                }
                });
            });
        }

    });
});




