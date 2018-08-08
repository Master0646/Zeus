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
	    <div class="assignmentManage" style="margin:20px 20px;" v-cloak>
	        <breadcrumb>
		        <breadcrumb-item to="curriculum/curriculumManage">课程管理</breadcrumb-item>
		        <breadcrumb-item>作业管理</breadcrumb-item>
		    </breadcrumb><br />
	        <modal v-model="deleteModal" @on-ok="ok" title="警告！！！">
		      	<p style="color:#ed3f14;text-align:center">
		          	<Icon type="information-circled"></Icon>
		          	<span style="font-size: 15px;">确定删除作业:{{assignmentTitle}}？</span>
		      	</p>
		  	</modal>
	        <!-- <i-button type="primary" @click="createAssignment"><Icon type="plus"></Icon> 新建</i-button> -->
	        <i-table :columns="columns" :data="dataList" style="margin-top:20px;"></i-table>
	    </div>
    </div>
    <script>
		var pageName = "curriculum";
        var assignmentManage = new Vue({
            el: '.assignmentManage',
            data: function(){
            	return{
                    index:"",
                    deleteModal: false,
                    assignmentTitle:"",
                    columns:[
                        { title: 'ID',key: 'id', align: 'center'},
                        { title: '作业名称',key: 'name', align: 'center'},
                        { title: '所属课程',key: 'curriculumId', align: 'center'},
                        { title: '评论',key: 'opt', align: 'center',
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
                                                this.chickComment(params.index)
                                            }
                                        }
                                    }, '查看')
                                ]);
                            }
                        },
                        { title: '打分',key: 'opt', align: 'center',
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
                                    }, '查看')
                                ]);
                            }
                        }
                    ],
                    dataList:[
                        {id:"1",name:"作业1",curriculumId:"1"},
                        {id:"2",name:"作业2",curriculumId:"1"},
                        {id:"3",name:"作业3",curriculumId:"2"}
                    ]
            	}
            },
            methods: {
                ok: function () {
                    
                },
                /* createAssignment:function(){
                    window.location.href="assignment/alterAssignment/0";
                }, */
                chickComment:function(index){
                    console.log("chickComment:",index);
                    window.location.href="comment/commentManage/" + this.dataList[index].id;
                },
                grade:function(index){
                    console.log(" score:",index);
                }
            }
        })
      </script>
</body>
</html>
