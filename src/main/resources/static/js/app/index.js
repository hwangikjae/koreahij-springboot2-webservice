var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-save2').on('click', function () {
            _this.save2();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-update2').on('click', function () {
            _this.update2();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-delete2').on('click', function () {
            _this.delete2();
        });

    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    save2 : function () {
        var data = {
            title2: $('#title2').val(),
            author2: $('#author2').val(),
            content2: $('#content2').val(),
            delyn: "N"
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts2',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function(){
        var data={
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert("글이 수정되었습니다.");
            window.location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    update2 : function(){
        var data={
            title2: $('#title2').val(),
            content2: $('#content2').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts2/update/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert("글이 수정되었습니다.");
            window.location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $("#id").val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(){
            alert("글이 삭제되었습니다.");
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    delete2 : function(){
        var data={
            delyn: "Y"
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts2/delete/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert("글이 삭제되었습니다.");
            window.location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();