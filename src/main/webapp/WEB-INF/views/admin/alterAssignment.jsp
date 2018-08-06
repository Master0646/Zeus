<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
	    <div class="alterAssignment" style="margin: 20px 20px;" v-cloak>
		     <breadcrumb>
		        <breadcrumb-item>新建/修改作业</breadcrumb-item>
		     </breadcrumb><br />
		     <div>
				<i-form :model="dataSourse" :label-width="180" style="width:80%;">
			        <form-item label="作业名称">
			       		<i-input v-model="dataSourse.name" placeholder="请输入作业名"></i-input>
			   		</form-item>
			   		<!-- <form-item label="内容">
			       		<textarea v-model="dataSourse.description" placeholder="请输入内容" name="content" style="width:100%;"></textarea>
			   		</form-item> -->
			   		<form-item>
			        	<i-button type="primary" v-on:click="submit" long>确定</i-button>
			   		</form-item>
			    </i-form>
			</div>
		</div>
	</div>
	<script>
		var pageName = "curriculum";
	    var alterAssignment = new Vue({
	        el:".alterAssignment",
	        data:function(){
	            return{
	                //需要提交的数据
	                dataSourse:{
	                	id:"",
	                	curriculumId:"",	//所属课程id
	                    name:""
	                }
	            }
	        },
	        methods:{
	            submit:function(){
	                console.log("submit");
	            }
	        }
	    })
	</script>
</body>
</html>
