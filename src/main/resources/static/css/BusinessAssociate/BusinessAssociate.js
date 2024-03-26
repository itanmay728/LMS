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