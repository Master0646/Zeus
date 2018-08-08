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
		<div class="alterRoleMenu" style="margin: 20px 20px;" v-cloak>
	        <breadcrumb>
		        <breadcrumb-item>新建/修改菜单</breadcrumb-item>
		    </breadcrumb><br />
	         <div>
			    <i-form :model="dataSourse" :label-width="180" style="width:80%;">
	                 <form-item label="角色">
	                     <i-select v-model="dataSourse.roleId" style="width:200px" @on-change="selectChange">
	                         <i-option v-for="item in roleList" :value="item.value" :key="item.value">{{ item.label }}</i-option>
	                     </i-select>
			        </form-item>
	                 <form-item label="菜单选择">              
	                     <i-select v-model="roleArr" multiple style="width:200px" @on-change="checkboxChange">
	                         <i-option v-for="item in menuList" :value="item.id" :key="item.id">{{ item.name }}</i-option>
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
		 var pageName = "roleMenu";
         var alterRoleMenu = new Vue({
             el:".alterRoleMenu",
             data:function(){
            	 return{
                     //需要提交的数据
                     dataSourse:{
                     	 id:"",
                         menuId:"",
                         roleId:""
                     },
                     roleArr:[],
                     roleList: [],	//存放角色
                     menuList: [],	//存放菜单
                     redirectUrl:config.viewUrls.roleMenuManage
            	 }
             },
             methods:{
                 selectChange:function(value){
                     console.log("value",value);
                 },
                 checkboxChange:function(array){
                     /* this.dataSourse.menuId = array.toString(); */
                     this.dataSourse.menuId = array[0];
                 },
                 submit:function(){
                	 console.log("submit",this.dataSourse);
                	 this.$Loading.start();
                 	var that = this;
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
                 }
             },
             created:function(){
            	 var that = this;
            	 this.$Loading.start();
 				 var roleMenuId = window.location.pathname.split("/Zeus/roleMenu/alterRoleMenu/")[1];	//获取菜单id
 				 if(roleMenuId != 0){
 	            	this.$Loading.finish();
    	            	that.dataSourse.id = roleMenuId;
    	            	/* that.dataSourse.name = name;
    	            	that.dataSourse.url = url; */
 					this.submitUrl = config.ajaxUrls.updateRoleMenu;
 				 }else{
 	            	this.$Loading.finish();
 					this.submitUrl = config.ajaxUrls.createRoleMenu;
 				 }
            	 //获取角色数据
				 $.ajax({
          	        url:config.ajaxUrls.getAllRoles,
          	        type:"get",
          	        dataType:"json",
          	        contentType :"application/json; charset=UTF-8",
          	        success:function(res){
          	            if(res.success){
           	            	console.log("res",res);
          	            	//角色数据筛选
          	            	that.roleList = res.object;
          	            	var dataArr = res.object;
          	            	for(var i = 0;i<dataArr.length;i++){
          	            		that.roleList[i].value = dataArr[i].id;
          	            		that.roleList[i].label = dataArr[i].rolename;
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
				//获取菜单数据
            	 $.ajax({
           	        url:config.ajaxUrls.getMenuList,
           	        type:"get",
           	        dataType:"json",
           	        contentType :"application/json; charset=UTF-8",
           	        success:function(res){
           	            if(res.success){
           	            	console.log(res);
           	            	that.menuList = res.object;
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