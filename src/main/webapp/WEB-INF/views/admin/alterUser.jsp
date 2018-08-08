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
		<div class="alterUser" style="margin: 20px 20px;" v-cloak>
			<breadcrumb>
		        <breadcrumb-item>新建/修改用户</breadcrumb-item>
		    </breadcrumb><br />
	     	<div>
				<i-form :model="dataSourse" :label-width="180" style="width:80%;">
		            <form-item label="角色">
		                <i-select v-model="dataSourse.userRoles.role" style="width:200px" @on-change="groupCheck">
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
                    	headPortrait: "",
                    	id:"",
                    	email:"",		//账号
                    	password:"",	//密码
                    	realname:"",
                    	nickname:"",
                    	mobile:"",
                        school:"",
                    	academy:"",
                    	address:"",
                    	userRoles:[{role:""},{user:1}]
                    },
                    GroupList:[{value:"0",label:"权限1"},{value:"1",label:"权限2"},{value:"2",label:"权限3"}],
                    redirectUrl:config.viewUrls.userManage,
                    submitUrl:""
                }
            },
            methods:{
                submit:function(){
                    var that = this;
                	this.$Loading.start();
					$.ajax({
            	        url:this.submitUrl,
            	        type:"post",
            	        dataType:"json",
            	        contentType :"application/json; charset=UTF-8",
            	        data:JSON.stringify(that.dataSourse),
            	        success:function(res){
            	            if(res.success){
                            	that.$Loading.finish();
            	            	if(that.redirectUrl){
            	                	that.$Notice.success({title:that.successMessage?that.successMessage:config.messages.optSuccRedirect});
	           	                    setTimeout(function(){
	               	                    window.location.href=that.redirectUrl;
	           	                    },3000);
            	            	}
            	            }else{
            	            	that.$Notice.error({title:res.message});
            	            }
            	        },
            	        error:function(err){
                        	that.$Loading.error();
            	        	that.$Notice.error({title:config.messages.loadDataError});
            	        }
            	    });
                },
                groupCheck:function(index){
                    this.dataSourse.userRoles[0].role = this.GroupList[index].value;
                }
            },
            created:function(){
            	/* var that = this;
				var	userId = window.location.pathname.split("/Zeus/user/alterUser/")[1];	//获取课程id
				if(userId != 0){
   	            	that.dataSourse.id = userId;
   	            	that.dataSourse.name = name;
   	            	that.dataSourse.remark = remark;
					this.submitUrl = config.ajaxUrls.updateUser; 
				}else{
					this.submitUrl = config.ajaxUrls.createUser;
				} */
				this.submitUrl = config.ajaxUrls.createUser;
            }
        })
    </script>
</body>
</html>
