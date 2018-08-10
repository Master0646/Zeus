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
	<script>
		var email = "${user.email}",realname = "${user.realname}",nickname = "${user.nickname}",
			mobile = "${user.mobile}",school = "${user.school}",academy = "${user.academy}",address = "${user.address}";
	</script>
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
		        <breadcrumb-item to="user/userManage">新建/修改用户</breadcrumb-item>
		        <breadcrumb-item>新建/修改用户</breadcrumb-item>
		    </breadcrumb><br />
	     	<div>
				<i-form :model="dataSourse" :label-width="180" style="width:80%;">
		            <form-item label="角色">
		                <i-select v-model="dataSourse.userRoles[0].role.id" style="width:200px" @on-change="groupCheck">
		     				<i-option v-for="roleItem in roleList" :value="roleItem.id" :key="roleItem.id" >{{ roleItem.rolename }}</i-option>
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
			            <i-select v-model="dataSourse.school" style="width:200px" @on-change="schoolCheck">
		     				<i-option v-for="schoolItem in schoolList" :value="schoolItem.id" :key="schoolItem.id" >{{ schoolItem.name }}</i-option>
		     			</i-select>
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
                    	id:"",
                    	email:"",		//账号
                    	password:"",	//密码
                    	realname:"",
                    	nickname:"",
                    	mobile:"",
                        school:"",
                    	academy:"",
                    	address:"",
                    	/* headPortrait:"", */
                    	userRoles:[
              	           {role:{id:""}}
              	        ],
                    	schoolCode:""
                    },
                    schoolList:[],
                    roleList:[],
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
                    this.dataSourse.userRoles[0].role.id = this.roleList[index-1].id;
                },
                schoolCheck:function(index){
                	console.log(this.schoolList[index-1].id,index);
                }
            },
            created:function(){
            	var that = this;
				//获取学校数据
				$.ajax({
          	        url:config.ajaxUrls.getSchoolByPage,
          	        type:"get",
          	        data:{limit:1000,offset:0},
          	        success:function(res){
          	            if(res.success){
        					that.schoolList = res.object.list;
        					console.log(res);
          	            }else{
          	            	that.$Notice.error({title:res.message});
          	            }
          	        }
          	    });
				//判断新建、修改
				var	userId = window.location.pathname.split("/Zeus/user/alterUser/")[1];	//获取用户id
				if(userId != 0){
					this.submitUrl = config.ajaxUrls.updateManageUser;
					$.ajax({
	          	        url:config.ajaxUrls.getAllRoles,
	          	        type:"get",
	          	        dataType:"json",
	          	        contentType :"application/json; charset=UTF-8",
	          	        success:function(res){
	          	            if(res.success){
	          	            	//角色数据筛选
	          	            	that.roleList = res.object;
	          	            	
	          	            	$.ajax({
	        	          	        url:config.ajaxUrls.findRolesByUserId,
	        	          	        type:"get",
	        	          	        data:{id:userId},
	        	          	        success:function(res){
	        	          	            if(res.success){
	        	        					that.dataSourse.userRoles[0].role.id = res.object[0];
	        	        					that.dataSourse.id = userId;
	        	        					that.dataSourse.email = email;
	        	        					that.dataSourse.realname = realname;
	        	        					that.dataSourse.nickname = nickname;
	        	        					that.dataSourse.mobile = mobile;
	        	        					that.dataSourse.school = school;
	        	        					that.dataSourse.academy = academy;
	        	        					that.dataSourse.address = address;
	        	          	            }else{
	        	          	            	that.$Notice.error({title:res.message});
	        	          	            }
	        	          	        },
	        	          	        error:function(err){
	        	                      	that.$Loading.error();
	        	          	        	that.$Notice.error({title:config.messages.loadDataError});
	        	          	        }
	        	          	    });
	          	            }else{
	          	            	that.$Notice.error({title:res.message});
	          	            }
	          	        },
	          	        error:function(err){
	                      	that.$Loading.error();
	          	        	that.$Notice.error({title:config.messages.loadDataError});
	          	        }
	          	    });
				}else{
					this.submitUrl = config.ajaxUrls.createManageUser;
					$.ajax({
	          	        url:config.ajaxUrls.getAllRoles,
	          	        type:"get",
	          	        dataType:"json",
	          	        contentType :"application/json; charset=UTF-8",
	          	        success:function(res){
	          	            if(res.success){
	          	            	//角色数据筛选
	          	            	that.roleList = res.object;
	          	            	console.log(that.roleList);
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
            }
        })
    </script>
</body>
</html>
