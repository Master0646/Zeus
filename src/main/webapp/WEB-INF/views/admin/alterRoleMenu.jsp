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
		<div class="alterRoleMenu">
	         <i-col span="24">新建/修改菜单</i-col><br/><br/>
	         <div>
			    <i-form :model="dataSourse" :label-width="180" style="width:80%;" v-cloak>
			                 <form-item label="角色">
			                     <i-select v-model="dataSourse.role" style="width:200px" @on-change="selectChange">
			                         <i-option v-for="item in roleList" :value="item.value" :key="item.value">{{ item.label }}</i-option>
			                     </i-select>
			        </form-item>
			                 <form-item label="菜单选择">
			                     <checkbox-group v-model="menuList" @on-change="checkboxChange">
			                         <checkbox label="user">
			                             <icon type="person-stalker"></icon>
			                             <span>用户</span>
			                         </checkbox>
			                         <checkbox label="school">
			                             <icon type="university"></icon>
			                             <span>学校</span>
			                         </checkbox>
			                         <checkbox label="curriculum">
			                             <icon type="cube"></icon>
			                             <span>课程</span>
			                         </checkbox>
			                         <checkbox label="work">
			                             <icon type="edit"></icon>
			                             <span>作业</span>
			                         </checkbox>
			                     </checkbox-group>
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
                         assignmentName:"",
                         description:"",
                         role:""
                     },
                     roleList: [
                         { value: '1', label: '角色1' }, { value: '2', label: '角色2' }, { value: '3', label: '角色3' }
                     ],
                     menuList: []
            	 }
             },
             methods:{
                 selectChange:function(value){
                     console.log("value",value);
                 },
                 checkboxChange:function(array){
                     console.log("array",array);
                 },
                 submit:function(){
                     console.log("submit");
                 }
             }
         })
     </script>
</body>
</html>