<html>
<body>
<h2>Hello World!</h2>

springmvc上传文件

<form action="/manage/product/upload.do" name="form1" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" name="提交"/>
</form>
富文本上传文件
<form action="/manage/product/richtext_img_upload.do" name="form2" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" name="富文本提交"/>
</form>
</body>
</html>
