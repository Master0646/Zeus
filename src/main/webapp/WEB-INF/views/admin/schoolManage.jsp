<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
	<%@ include file="../head.jsp"%>

	<link rel="stylesheet" type="text/css" href="resources/backend/css/lib/iview.css">
    <script src="resources/backend/js/lib/vue.min.js" charset="utf-8"></script>
    <script src="resources/backend/js/lib/iview.min.js" charset="utf-8"></script>
</head>
<body>
	<div class="schoolManage" style="margin:20px 20px;">
         <i-col span="24">院校管理</i-col>
         <modal v-model="deleteModal" @on-ok="ok" title="警告！！！">
		       <p style="color:#ed3f14;text-align:center">
		           <Icon type="information-circled"></Icon>
		           <span style="font-size: 15px;">确定删除学校:{{schoolTitle}}？</span>
		       </p>
   		 </modal>
         <i-button type="primary" @click="createSchool"><Icon type="plus"></Icon> 新建</i-button>
         <i-table :columns="columns" :data="dataList" style="margin-top:20px;"></i-table>
     </div>
        <script>
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
                            { title: '院校名',key: 'school', align: 'center'},
                            { title: '系部名',key: 'department', align: 'center'},
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
                            {id:"1",province:"湖南",school:"湖南大学",department:"艺术与工业设计学院"},
                            {id:"2",province:"湖北",school:"湖北大学",department:"艺术与工业设计学院"},
                            {id:"3",province:"四川",school:"四川大学",department:"艺术与工业设计学院"}
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
