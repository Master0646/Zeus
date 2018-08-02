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
		<div class="schoolCOU">
			<i-col span="24">新建/修改院校</i-col>
			<br />
			<br />
			<div>
				<i-form :model="dataSourse" :label-width="180" style="width:80%;" v-cloak> 
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
					}
				}
			},
			methods : {
				submit : function() {
					console.log("submit");
				}
			}
		})
	</script>
</body>
</html>
