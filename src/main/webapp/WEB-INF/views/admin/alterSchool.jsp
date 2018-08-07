<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
	<%@ include file="../head.jsp"%>
	
	<link rel="stylesheet" type="text/css" href="resources/backend/css/lib/iview.css">
	<link rel="stylesheet" type="text/css" href="resources/backend/css/src/main.css">
	<link rel="stylesheet" type="text/css" href="resources/css/lib/bootstrap.min.css">
	
	<script>
		var province = '${school.province}',
			name = '${school.name}',
			academy = '${school.academy}';
	</script>
	
	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/bootstrap.min.js" charset="utf-8"></script>
	<script src="resources/backend/js/lib/vue.min.js" charset="utf-8"></script>
	<script src="resources/backend/js/lib/iview.min.js" charset="utf-8"></script>
	<script src="resources/backend/js/src/config.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="left">
		<%@ include file="menu.jsp"%>
	</div>
	<div class="right">
		<div class="schoolCOU" style="margin: 20px 20px;" v-cloak>
			<breadcrumb>
		        <breadcrumb-item>新建/修改院校</breadcrumb-item>
		    </breadcrumb><br />
			<div>
				<i-form :model="dataSourse" :label-width="180" style="width:80%;"> 
					<form-item label="省份"> 
						<i-input v-model="dataSourse.province" placeholder="请输入省份"></i-input> 
					</form-item> 
					<form-item label="学校名称"> 
						<i-input v-model="dataSourse.name" placeholder="请输入学校名"></i-input> 
					</form-item> 
					<form-item label="系部名称">
						<i-input v-model="dataSourse.academy" placeholder="请输入系部名"></i-input>
					</form-item> 
					<form-item> 
						<i-button type="primary" v-on:click="submit" long>确定</i-button> 
					</form-item> 
				</i-form>
			</div>
		</div>
	</div>
	<script>
		var pageName = "school";
		var schoolCOU = new Vue({
			el : ".schoolCOU",
			data : function() {
				return {
					//需要提交的数据
					dataSourse : {
						id : "",
						province : "",
						name : "",
						academy : ""
					},
                    redirectUrl:config.viewUrls.schoolManage,
                    submitUrl:""
				}
			},
			methods : {
				submit : function() {
					var that = this;
					$.ajax({
            	        url:this.submitUrl,
            	        type:"post",
            	        dataType:"json",
            	        contentType :"application/json; charset=UTF-8",
            	        data:JSON.stringify(that.dataSourse),
            	        success:function(res){
            	            if(res.success){
            	            	if(that.redirectUrl){
            	                	that.$Notice.success({title:that.successMessage?that.successMessage:config.messages.optSuccRedirect});
	           	                    setTimeout(function(){
	               	                    window.location.href=that.redirectUrl;
	           	                    },3000);
            	            	}
            	            }else{
            	            	that.$Notice.error({title:res.message});
            	            }
            	        },
            	        error:function(err){
            	        	that.$Notice.error({title:config.messages.loadDataError});
            	        }
            	    });
				}
			},
			created:function(){
				var that = this;
				var	schoolId = window.location.pathname.split("/Zeus/school/alterSchool/")[1];	//获取院校id
				if(schoolId != 0){
   	            	that.dataSourse.id = schoolId;
   	            	that.dataSourse.province = province;
   	            	that.dataSourse.name = name;
   	            	that.dataSourse.academy = academy;
					this.submitUrl = config.ajaxUrls.updateSchool;
				}else{
					this.submitUrl = config.ajaxUrls.createSchool;
				}
			}
		})
	</script>
</body>
</html>
