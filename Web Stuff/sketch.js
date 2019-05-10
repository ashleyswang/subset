window.onload = function() {
    var editor = ace.edit("editor");
    editor.session.setMode('java');
    
    $('#file-upload').on('change', function(event) {
        var output = document.getElementById('output');
        output.src = URL.createObjectURL(event.target.files[0]);//make one of these
        //download link 
        //javascript dynamic download

        var xhr = new XMLHttpRequest()
        xhr.open('POST', 'http://localhost:8000/ocr')
        xhr.onreadystatechange = function() {
            if(this.readyState !== 4) return
            var code = this.responseText
            editor.setValue(code)
        }

        var reader = new FileReader();
        reader.onload = function(e) { 
            xhr.send(e.target.result) 
        }

        reader.readAsArrayBuffer(event.target.files[0])
    })
    
    $('#compile').on('click', function(event) {
        jQuery.ajax({
            url:  '/path/to/compile',
            data: {text: editor.getValue()},
            success: function(data, status, xhr) {
                alert('Compile Yay!')
            },
            error: function(data, status, xhr) {
                alert('Compile Hey!')
            }
        })
    })
}
