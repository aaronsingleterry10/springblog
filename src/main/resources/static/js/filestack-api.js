(function () {
    const client = filestack.init(FILESTACK_KEY);
    let urlArray = [];
    let fileTypeArray = [];
    document.getElementById("upload").addEventListener("click", (e) => {
        e.preventDefault();
        const options = {
            imageMax: [400, 400],
            // onFileUploadFinished: res => {
            //     console.log(res.originalFile.type);
            //     $("#img").attr("value", res.url);
            //     $("#file-type").attr("value", res.originalFile.type)
            // }
            maxFiles: 4,
            onUploadDone: res => {
                for (let i = 0; i < res.filesUploaded.length; i++) {
                    urlArray.push(res.filesUploaded[i].url);
                    fileTypeArray.push(res.filesUploaded[i].originalFile.type);
                }
                $("#images").val(urlArray);
                $("#file-type").val(fileTypeArray);
            }
        };
        client.picker(options).open();
    });
})();

