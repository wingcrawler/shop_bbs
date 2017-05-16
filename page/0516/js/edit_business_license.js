function editTextArea() {
    $("#describe").val("");
    $("#describe").removeAttr("disabled");
}

function saveTextAreaVal() {
    $("#describe").attr("disabled", "disabled");
}