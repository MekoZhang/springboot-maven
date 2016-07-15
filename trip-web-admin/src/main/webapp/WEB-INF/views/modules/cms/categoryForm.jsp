<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>栏目管理</title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/fileupload.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
            $('#inputForm').fileupload({
                autoUpload: false,
                replaceFileInput: false,
                maxFileSize: 5000000,
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
            }).bind('fileuploadprocessalways', function(e, data)
            {
                var canvas = data.files[0].preview;
                var error = data.files[0].error;
                var dataURL = canvas.toDataURL();
                $(".template-upload").show();
                $(".template-download").hide();
                $("#some-image")
                        .css("background-image", 'url(' + dataURL +')')
                        .css("height", canvas.height)
                        .css("width", canvas.width)
                ;
                if (error) {
                    $(".error").text(error);
                } else {
                    $(".error").text("");
                }

            });

            $(".remove").on('click', function() {
                $(".template-upload").hide();
                $(".template-download").hide();
                $("#image").val("");
                $("#file").val("");
            });

			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cms/category/">栏目列表</a></li>
		<li class="active"><a href="${ctx}/cms/category/form?id=${category.id}&parent.id=${category.parent.id}">栏目<shiro:hasPermission name="cms:category:edit">${not empty category.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:category:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsCategory" action="${ctx}/cms/category/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">上级栏目:</label>
			<div class="controls">
                <sys:treeselect id="category" name="parent.id" value="${category.parent.id}" labelName="parent.name" labelValue="${category.parent.name}"
					title="栏目" url="/cms/category/treeData" extId="${category.id}" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">栏目模型:</label>
			<div class="controls">
				<form:select path="module">
					<form:option value="" label="公共模型"/>
					<form:options items="${fns:getDictList('cms_module')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">栏目名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缩略图:</label>
			<div class="controls">
				<form:hidden id="image" path="image" htmlEscape="false"/>
                <!-- The table listing the files available for upload/download -->
                <table role="presentation"><tbody class="files">
                    <tr class="template-upload fade in" hidden>
                        <td>
                            <span class="preview">
                                <canvas id="some-image"></canvas>
                            </span>
                        </td>
                        <td>
                            <strong class="error text-danger"></strong>
                        </td>
                    </tr>
                    <tr class="template-download fade in" ${not empty file?'':'hidden'}>
                        <td>
                            <span class="preview">
                                <a href="${file}" data-gallery>
                                    <img style="width: 80px;margin-bottom: 10px" src="${file}">
                                </a>
                            </span>
                        </td>
                    </tr>
                </tbody></table>
                <span class="btn btn-mini btn-success fileinput-button">
                    <span>上传...</span>
                    <input id="file" type="file" name="file">
                </span>
                <span class="btn btn-mini btn-warning remove">
                    <span>移除</span>
                </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">链接:</label>
			<div class="controls">
				<form:input path="href" htmlEscape="false" maxlength="200"/>
				<span class="help-inline">栏目超链接地址，优先级“高”</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述:</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键字:</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="200"/>
				<span class="help-inline">填写描述及关键字，有助于搜索引擎优化</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11" class="required digits"/>
				<span class="help-inline">栏目的排列次序</span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cms:category:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
    <!-- The blueimp Gallery widget -->
    <div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
        <div class="slides"></div>
        <a class="close">×</a>
    </div>
</body>
</html>