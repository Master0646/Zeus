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
				<i-form :model="dataSourse" :label-width="180" style="width:80%;" :rules="ruleDataSourse">
		            <form-item label="角色">
		                <i-select v-model="dataSourse.userRoles[0].role.id" @on-change="groupCheck">
		     				<i-option v-for="roleItem in roleList" :value="roleItem.id" :key="roleItem.id" >{{ roleItem.rolename }}</i-option>
		     			</i-select>
			        </form-item>
			        <form-item label="账号" prop="email">
			            <i-input v-model="dataSourse.email" clearable="true" placeholder="请输入账号"></i-input>
			        </form-item>
			        <form-item label="密码" prop="password">
			            <i-input v-model="dataSourse.password" clearable="true" placeholder="请输入密码"></i-input>
			        </form-item>
			    	<form-item label="真实姓名" prop="realname">
			        	<i-input v-model="dataSourse.realname" clearable="true" placeholder="请输入姓名"></i-input>
			    	</form-item>
			    	<form-item label="绰号">
			        	<i-input v-model="dataSourse.nickname" clearable="true" placeholder="请输入绰号"></i-input>
			    	</form-item>
			    	<form-item label="手机号" prop="mobile">
			        	<i-input v-model="dataSourse.mobile" placeholder="请输入手机号"></i-input>
			    	</form-item>
			        <form-item label="学校名称">
			            <i-select v-model="dataSourse.school" @on-change="schoolCheck">
		     				<i-option v-for="schoolItem in schoolList" :value="schoolItem.id" :key="schoolItem.id" >{{ schoolItem.name }}</i-option>
		     			</i-select>
			        </form-item>
			        <form-item label="系部名称">
			            <i-select v-model="dataSourse.academy">
		     				<i-option v-for="academyItem in academyList" :value="academyItem.id" :key="academyItem.id" >{{ academyItem.name }}</i-option>
		     			</i-select>
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
                	ruleDataSourse:{	//表单验证
                		email:[{ required: true,type:'email',  message: '请输入正确邮箱格式', trigger: 'blur' }],
                		password:[{required: true,type:'string', min:6, message: '请输入至少6位数密码', trigger: 'blur'}],
                		realname:[{required: true,type:'string', min:2, message: '请输入至少2个字符', trigger: 'blur'}],
                		mobile:[{ required: true, type:'string', len: 11, message:'请输入正确手机号码', trigger:'blur'}]
                	},
                    dataSourse:{		//需要提交的数据
                    	id:"",
                    	email:"",		//账号
                    	password:"",	//密码
                    	realname:"",
                    	nickname:"",
                    	mobile:"",
                        school:"",
                    	academy:"",
                    	/* headPortrait:"", */
                    	userRoles:[
              	           {role:{id:""}}
              	        ],
                    	schoolCode:""
                    },
                    schoolList:[],
                    academyList:[],
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
                	var that = this;
                	$.ajax({
            	        url:config.ajaxUrls.getAcademyBySchoolId,
            	        type:"GET",
            	        data:{schoolId:index},
            	        success:function(res){
            	            if(res.success){
        	                	that.$Loading.finish();
            	            	that.academyList = res.object;
            	            }else{
            	            	that.$Notice.error({title:res.message});
            	            }
            	        },
            	        error:function(){
            	        	that.$Notice.error({title:config.messages.loadDataError});
    	                	that.$Loading.error();
            	        }
            	    });
                }
            },
            created:function(){
            	var that = this;
				//获取学校数据
				$.ajax({
            	        url:config.ajaxUrls.getAllSchool,
            	        type:"GET",
            	        dataType:"json",
            	        contentType :"application/json; charset=UTF-8",
            	        success:function(res){
            	            if(res.success){
            	            	that.schoolList = res.object;
            	            }else{
            	            	that.$Notice.error({title:res.message});
            	            }
            	        },
            	        error:function(){
            	        	that.$Notice.error({title:config.messages.loadDataError});
    	                	that.$Loading.error();
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
