<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
	<%@ include file="../head.jsp"%>

	<link rel="stylesheet" type="text/css" href="resources/backend/css/lib/iview.css">
	<link rel="stylesheet" type="text/css" href="resources/css/lib/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resources/backend/css/src/main.css">
	
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="left">
		<%@ include file="menu.jsp"%>
	</div>
	<div class="right">
	    <div class="assignmentSubmissionManage" style="margin:20px 20px;" v-cloak>
	        <breadcrumb>
		        <breadcrumb-item to="curriculum/curriculumManage">课程管理</breadcrumb-item>
		        <breadcrumb-item>作业提交管理</breadcrumb-item>
		    </breadcrumb><br />
	        <i-button icon="person-stalker">所有学生</i-button>
	        <i-button icon="heart">已提交作业</i-button>
	        <i-button icon="heart-broken">未提交作业</i-button>
	        <div class="submissionList" style="margin: 20px auto;width: 100%;height: auto;background: white;">
	        	<div v-for="item in itemList" style="margin:10px;display: inline-block;">
	        		<img style="width:100px;height:100px;" src="resources/backend/images/userIcon.jpg"/>
	        		<span class="studentName" style="display:block;margin: 0 auto;text-align:center;">{{item.message}}</span>
	        	</div>
	        </div>
	        <page v-model="totalPage" :current="1" :total="totalPage" @on-change="pageChange" show-total style="margin-right:60px;margin-top:20px;text-align:right;"></page>
	    </div>
    </div>
    <script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/bootstrap.min.js" charset="utf-8"></script>
    <script src="resources/backend/js/lib/vue.min.js" charset="utf-8"></script>
    <script src="resources/backend/js/lib/iview.min.js" charset="utf-8"></script>
	<script src="resources/backend/js/src/config.js"></script>
    <script>
		var pageName = "curriculum";
        var assignmentSubmissionManage = new Vue({
            el: '.assignmentSubmissionManage',
            data: function(){
            	return{
            		totalPage:100,
            		aoData:{curriculumId:0,limit:10,offset:0},
            		itemList:[
            			{ message: 'Foo' },{ message: 'Bar' },{ message: 'Foo' },{ message: 'Bar' },
            			{ message: 'Foo' },{ message: 'Bar' },{ message: 'Foo' },{ message: 'Bar' },
            			{ message: 'Foo' },{ message: 'Bar' },{ message: 'Foo' },{ message: 'Bar' },
            			{ message: 'Foo' },{ message: 'Bar' },{ message: 'Foo' },{ message: 'Bar' },
            		]
            	}
            },
            methods: {
            	pageChange:function(index){
            		console.log(index);
            	}
            },
            created:function(){
            	var that = this;
            	this.aoData.curriculumId = window.location.search.split("curriculumId=")[1];	//获取课程id
            	$.ajax({
			        url:config.ajaxUrls.getAssignmentListByPageAndCurriculumId,
			        type:"GET",
			        data:this.aoData,
			        dataType:"json",
			        contentType :"application/json; charset=UTF-8",
			        success:function(res){
			            if(res.success){
			            	console.log(res);
	                    	that.$Loading.finish();
							that.dataList = res.object.list;
			            }else{
			            	that.$Notice.error({title:res.message});
			            }
			        },
			        error:function(){
	                	that.$Loading.error();
			        	that.$Notice.error({title:config.messages.loadDataError});
			        }
			    });
            }
        })
      </script>
</body>
</html>
