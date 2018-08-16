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
	    <div class="commentManage" style="margin:20px 20px;" v-cloak>
	        <breadcrumb>
	        	<breadcrumb-item to="curriculum/curriculumManage">课程管理</breadcrumb-item>
		        <breadcrumb-item>作业评论管理</breadcrumb-item>
		    </breadcrumb><br />
		    <i-table :columns="columns" :data="dataList" style="margin-top:20px;"></i-table>
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
        var commentManage = new Vue({
            el: '.commentManage',
            data: function(){
            	return{
            		totalPage:"",
            		aoData:{assignmentId:"",limit:10,offset:0},
            		columns:[
                         { title: 'ID',key: 'userId', align: 'center'},
                         { title: '评论内容',key: 'content', align: 'center'},
                         { title: '操作',key: 'opt', align: 'center',
                         	render: (h, params) => {
                                 return h('div', [
                                     h('Button', {
                                         props: {
                                             type: 'primary',
                                             size: 'small'
                                         },
                                         style: {
                                             marginRight: '5px'
                                         },
                                         on: {
                                             click: () => {
                                                 this.grade(params.index)
                                             }
                                         }
                                     }, '操作')
                                 ]);
                             }
                         }
                     ],
                     dataList:[]
            	}
            },
            methods: {
            	pageChange:function(index){
                	this.aoData.offset = (index-1)*10;
                	var that = this;
                	$.ajax({
              	        url:config.ajaxUrls.getCommnetListByPageAndAssignmentId,
              	        type:"get",
              	        dataType:"json",
              	        data:this.aoData,
              	        contentType :"application/json; charset=UTF-8",
              	        success:function(res){
              	            if(res.success){
              	            	that.dataList = res.object.list;
              	            	that.totalPage = res.object.count;
              	            }else{
              	            	that.$Notice.error({title:res.message});
              	            }
              	        },
              	        error:function(err){
                          	that.$Loading.error();
              	        	that.$Notice.error({title:config.messages.loadDataError});
              	        }
              	    });
            	}
            },
            created:function(){
            	var that = this;
            	this.aoData.assignmentId = window.location.pathname.split("/Zeus/comment/commentManage/")[1];	//获取作业id
            	$.ajax({
          	        url:config.ajaxUrls.getCommnetListByPageAndAssignmentId,
          	        type:"get",
          	        dataType:"json",
          	        data:this.aoData,
          	        contentType :"application/json; charset=UTF-8",
          	        success:function(res){
          	            if(res.success){
          	            	that.dataList = res.object.list;
          	            	that.totalPage = res.object.count;
          	            }else{
          	            	that.$Notice.error({title:res.message});
          	            }
          	        },
          	        error:function(err){
                      	that.$Loading.error();
          	        	that.$Notice.error({title:config.messages.loadDataError});
          	        }
          	    });
            }
        })
      </script>
</body>
</html>
