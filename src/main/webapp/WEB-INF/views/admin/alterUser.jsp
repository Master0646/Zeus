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
		<div class="alterUser">
	     	<i-col span="24">新建/修改用户</i-col><br/><br/>
	     	<div>
				<i-form :model="dataSourse" :label-width="180" style="width:80%;" v-cloak>
		            <form-item label="权限">
		                <i-select v-model="dataSourse.groupModel" style="width:200px" @on-change="groupCheck">
		     				<i-option v-for="Groupitem in GroupList" :value="Groupitem.value" :key="Groupitem.value" >{{ Groupitem.label }}</i-option>
		     			</i-select>
			        </form-item>
			        <form-item label="账号">
			            <i-input v-model="dataSourse.email" placeholder="请输入账号"></i-input>
			        </form-item>
			        <form-item label="密码">
			            <i-input v-model="dataSourse.password" placeholder="请输入密码"></i-input>
			        </form-item>
			    	<form-item label="真实姓名">
			        	<i-input v-model="dataSourse.realname" placeholder="请输入姓名"></i-input>
			    	</form-item>
			    	<form-item label="绰号">
			        	<i-input v-model="dataSourse.nickname" placeholder="请输入绰号"></i-input>
			    	</form-item>
			    	<form-item label="手机号">
			        	<i-input v-model="dataSourse.mobile" placeholder="请输入手机号"></i-input>
			    	</form-item>
			        <form-item label="学校名称">
			            <i-input v-model="dataSourse.school" placeholder="请输入学校名"></i-input>
			        </form-item>
			        <form-item label="系部名称">
			            <i-input v-model="dataSourse.academy" placeholder="请输入系部名"></i-input>
			        </form-item>
			        <form-item label="地址">
			            <i-input v-model="dataSourse.address" placeholder="请输入地址"></i-input>
			        </form-item>
			        <form-item>
			        	<i-button type="primary" v-on:click="submit" long>确定</i-button>
			        </form-item>
			    </i-form>
			</div>
	    </div>
    </div>
    <script>
    	var pageName = "user";
        var alterUser = new Vue({
            el:".alterUser",
            data:function(){
                return{
                    //需要提交的数据
                    dataSourse:{
                    	// headicon: uploadImage,
                    	id:"",
                        groupModel:"",
                    	email:"",		//账号
                    	password:"",	//密码
                    	realname:"",
                    	nickname:"",
                    	mobile:"",
                        school:"",
                    	academy:"",
                    	address:"",
                    },
                    GroupList:[{value:"0",label:"权限1"},{value:"1",label:"权限2"},{value:"2",label:"权限3"}]
                }
            },
            methods:{
                submit:function(){
                    console.log("submit");
                },
                groupCheck:function(){
                    console.log("groupCheck");
                }
            }
        })
    </script>
</body>
</html>
