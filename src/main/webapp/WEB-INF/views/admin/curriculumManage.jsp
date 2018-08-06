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
		<div class="curriculumManage" style="margin: 20px 20px;" v-cloak>
			<i-col span="24">课程管理</i-col>
			<modal v-model="deleteModal" @on-ok="ok" title="警告！！！">
			<p style="color: #ed3f14; text-align: center">
				<Icon type="information-circled"></Icon>
				<span style="font-size: 15px;">确定删除课程:{{curriculumTitle}}？</span>
			</p>
			</modal>
			<i-button type="primary" @click="createCurriculum">
			<Icon type="plus"></Icon> 新建</i-button>
			<i-table :columns="columns" :data="dataList" style="margin-top:20px;"></i-table>
		</div>
	</div>
	<script>
    	var pageName = "curriculum";
        var curriculumManage = new Vue({
            el: '.curriculumManage',
            data: function(){
                return{
                    index:"",
                    deleteModal: false,
                    curriculumTitle:"",
                    columns:[
                        { title: 'ID',key: 'id', align: 'center'},
                        { title: '老师',key: 'teacherId', align: 'center'},
                        { title: '课程名',key: 'name', align: 'center'},
                        { title: '作业详情',key: 'opt', align: 'center',
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
                                                this.chick(params.index)
                                            }
                                        }
                                    }, '查看')
                                ]);
                            }
                        },
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
                                               this.change(params.index)
                                           }
                                       }
                                   }, '修改'),
                                   h('Button', {
                                       props: {
                                           type: 'error',
                                           size: 'small'
                                       },
                                       on: {
                                           click: () => {
                                               this.remove(params.index)
                                           }
                                       }
                                   }, '删除')
                               ]);
                           }
                       }
                    ],
                    dataList:[
                        {id:"1",name:"语文",teacherId:"1"},
                        {id:"2",name:"数学",teacherId:"2"},
                        {id:"3",name:"音乐",teacherId:"3"}
                    ]
                }
            },
            methods: {
                ok: function () {

                },
                //新建课程
                createCurriculum:function(){
                    window.location.href="curriculum/alterCurriculum";
                },
                chick:function(index){
                	window.location.href="assignment/assignmentManage";
                },
                change:function(index){
                    console.log("changechange:",index);
                },
                remove:function(index) {
                    console.log("removeremove:",index);
                }
            }
        })
      </script>
</body>
</html>
