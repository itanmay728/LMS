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