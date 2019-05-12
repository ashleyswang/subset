window.onload = function() {
    var editor = ace.edit("editor");
    editor.session.setMode('ace/mode/java');
    
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
    
    $('#format').on('click', function(event) {
        jQuery.ajax({
            url:  'http://localhost:8000/format',
            method: 'POST',
            data: editor.getValue(),
            processData: false,
            success: function(data, status, xhr) {
                editor.setValue(data)
                //alert('Yay!')
            },
            error: function(data, status, xhr) {
                alert('F***!')
            }
        })
    })

    $('.radios input').change(function(event) {
        if(this.value == 'java') {
            editor.session.setMode('ace/mode/java')
            $('#download').attr('download', 'output.java')
        }
        else if(this.value == 'cpp') {
            editor.session.setMode('ace/mode/c_cpp')
            $('#download').attr('download', 'output.cpp')
        }
        else if(this.value == 'text') {
            editor.session.setMode('ace/mode/text')
            $('#download').attr('download', 'output.txt')
        }
    })

    $('#export').on('click', function(event) {
        var link = document.getElementById('download')
        var blob = new Blob([editor.getValue()])
        var href = window.URL.createObjectURL(blob)
        link.href = href
        link.click()

        setTimeout(function(){
            window.URL.revokeObjectURL(href)
        }, 0)
    })
}
