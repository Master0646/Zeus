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
	        	<breadcrumb-item to="assignment/assignmentManage">作业管理</breadcrumb-item>
		        <breadcrumb-item>作业评论管理</breadcrumb-item>
		    </breadcrumb><br />
		    <i-table :columns="columns" :data="dataList" style="margin-top:20px;"></i-table>
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
            		columns:[
                         { title: 'ID',key: 'id', align: 'center'},
                         { title: '评论内容',key: 'name', align: 'center'},
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
                     dataList:[
                         {id:"1",name:"评论1",curriculumId:"1"},
                         {id:"2",name:"评论2",curriculumId:"1"},
                         {id:"3",name:"评论3",curriculumId:"2"}
                     ]
            	}
            },
            methods: {
            	pageChange:function(index){
            		console.log(index);
            	}
            }
        })
      </script>
</body>
</html>
