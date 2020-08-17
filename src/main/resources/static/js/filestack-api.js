(function () {
    const client = filestack.init(FILESTACK_KEY);
    document.getElementById("upload").addEventListener("click", (e) => {
        e.preventDefault();
        const options = {
            imageMax: [225, 225],
            onFileUploadFinished: res => {
                $("#img").attr("value", res.url);
            }
        };
        client.picker(options).open();
    });
})();

