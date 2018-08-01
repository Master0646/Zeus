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
    <div class="alterCurriculum">
        <i-col span="24">新建/修改课程</i-col><br/><br/>
        <div>
		   <i-form :model="dataSourse" :label-width="180" style="width:80%;" v-cloak>
                <form-item label="课程名称">
		           <i-input v-model="dataSourse.curriculumName" placeholder="请输入课程名"></i-input>
		       </form-item>
                <form-item label="简介">
		           <textarea v-model="dataSourse.description" placeholder="请输入简介" name="content" style="width:100%;"></textarea>
		        </form-item>
		        <form-item>
		        	<i-button type="primary" v-on:click="submit" long>确定</i-button>
		        </form-item>
		   </i-form>
		</div>
     </div>
     <script>
        var alterCurriculum = new Vue({
            el:".alterCurriculum",
            data:function(){
                return{
                    //需要提交的数据
                    dataSourse:{
                    	id:"",
                        curriculumName:"",
                        description:""
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
