window.onload = function() {
    var editor = ace.edit("editor");
    editor.session.setMode('java');
    
    $('#file-upload').on('change', function(event) {
        var output = document.getElementById('output');
        output.src = URL.createObjectURL(event.target.files[0]);//make one of these
        //download link 
        //javascript dynamic download
        
        var formdata = new FormData()
        formdata.append('text', event.target.files[0], 'whatever.java')
        jQuery.ajax({
            url:  '/path/to/ocr',
            data: formdata,
            processData: false,
            success: function(data, status, xhr) {
                editor.setValue(data);
                alert('Yay!')
            },
            error: function(data, status, xhr) {
                $('#wrapper').show();
                editor.setValue("This is some Java code!");
                alert('Hey!')
            }
        })
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
