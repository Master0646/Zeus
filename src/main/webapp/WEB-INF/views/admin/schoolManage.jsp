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
		<div class="schoolManage" style="margin: 20px 20px;">
			<i-col span="24">院校管理</i-col>
			<modal v-model="deleteModal" @on-ok="ok" title="警告！！！">
			<p style="color: #ed3f14; text-align: center">
				<Icon type="information-circled"></Icon>
				<span style="font-size: 15px;">确定删除学校:{{schoolTitle}}？</span>
			</p>
			</modal>
			<i-button type="primary" @click="createSchool"> <Icon
				type="plus"></Icon> 新建</i-button>
			<i-table :columns="columns" :data="dataList" style="margin-top:20px;"></i-table>
		</div>
	</div>
	<script>
    		var pageName = "school";
            var schoolManage = new Vue({
                el: '.schoolManage',
                data: function(){
                    return{
                        index:"",
                        deleteModal: false,
                        schoolTitle:"",
                        columns:[
                            { title: 'ID',key: 'id', align: 'center'},
                            { title: '省份',key: 'province', align: 'center'},
                            { title: '院校名',key: 'name', align: 'center'},
                            { title: '系部名',key: 'academy', align: 'center'},
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
                            {id:"1",province:"湖南",name:"湖南大学",academy:"艺术与工业设计学院"},
                            {id:"2",province:"湖北",name:"湖北大学",academy:"艺术与工业设计学院"},
                            {id:"3",province:"四川",name:"四川大学",academy:"艺术与工业设计学院"}
                        ]
                    }
                },
                methods: {
                    ok: function () {

                    },
                    createSchool:function(){
                        console.log("!!!!!!!!!!!!!!");
                        window.location.href="school/alterSchool";
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
