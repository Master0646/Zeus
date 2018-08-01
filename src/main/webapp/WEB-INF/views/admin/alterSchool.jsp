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
	<div class="schoolCOU">
        <i-col span="24">新建/修改院校</i-col><br/><br/>
        <div>
		    <i-form :model="dataSourse" :label-width="180" style="width:80%;" v-cloak>
		        <form-item label="省份">
		            <i-input v-model="dataSourse.province" placeholder="请输入省份"></i-input>
		        </form-item>
                   <form-item label="学校名称">
		            <i-input v-model="dataSourse.schoolName" placeholder="请输入学校名"></i-input>
		        </form-item>
                   <form-item label="系部名称">
		            <i-input v-model="dataSourse.departmentName" placeholder="请输入系部名"></i-input>
		        </form-item>
		        <form-item>
		        	<i-button type="primary" v-on:click="submit" long>确定</i-button>
		        </form-item>
		    </i-form>
		</div>
    </div>
    <script>
        var schoolCOU = new Vue({
            el:".schoolCOU",
            data:function(){
                return{
                    //需要提交的数据
                    dataSourse:{
                    	id:"",
                    	province:"",
                        schoolName:"",
                        departmentName:""
                    }
                }
            },
            methods:{
                submit:function(){
                    console.log("submit");
                }
            }
        })
    </script>
</body>
</html>
