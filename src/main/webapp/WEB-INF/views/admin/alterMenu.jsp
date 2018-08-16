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
		var name = '${menu.name}',
			url = '${menu.url}';
	</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="left">
		<%@ include file="menu.jsp"%>
	</div>
	<div class="right">
	    <div class="alterMenu" style="margin: 20px 20px;" v-cloak>
	        <breadcrumb>
		        <breadcrumb-item to="menu/menuManage">菜单管理</breadcrumb-item>
		        <breadcrumb-item>新建/修改菜单</breadcrumb-item>
		    </breadcrumb><br />
	        <div>
			   <i-form :model="dataSourse" :rules="ruleDataSourse" :label-width="180" style="width:80%;">
	                <form-item label="菜单名称" prop="name">
			           <i-input v-model="dataSourse.name" clearable="true" placeholder="请输入菜单名"></i-input>
			       </form-item>
	                <form-item label="链接" prop="url">
			           <i-input v-model="dataSourse.url" clearable="true" placeholder="请输入菜单对应链接"></i-input>
			        </form-item>
			        <form-item>
			        	<i-button type="primary" v-on:click="submit" long>确定</i-button>
			        </form-item>
			   </i-form>
			</div>
	     </div>
     </div>
     <script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/bootstrap.min.js" charset="utf-8"></script>
    <script src="resources/backend/js/lib/vue.min.js" charset="utf-8"></script>
    <script src="resources/backend/js/lib/iview.min.js" charset="utf-8"></script>
	<script src="resources/backend/js/src/config.js"></script>
	<script type="text/javascript">
     	var pageName = "menu";
        var alterMenu = new Vue({
            el:".alterMenu",
            data:function(){
                return{
                	ruleDataSourse:{
                		name:[{ required: true,type:"string",  min:2, message: '请输入至少2个字符', trigger: 'blur' }],
                		url:[{ required: true,type:"url",message: '请输入正确url格式', trigger: 'blur' }]
                	},
                    //需要提交的数据
                    dataSourse:{
                    	id:"",
                        name:"",
                        url:""
                    },
                    redirectUrl:config.viewUrls.menuManage
                }
            },
            methods:{
                submit:function(){
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
				var	menuId = window.location.pathname.split("/Zeus/menu/alterMenu/")[1];	//获取菜单id
				if(menuId != 0){
	            	this.$Loading.finish();
   	            	that.dataSourse.id = menuId;
   	            	that.dataSourse.name = name;
   	            	that.dataSourse.url = url;
					this.submitUrl = config.ajaxUrls.updateMenu;
				}else{
	            	this.$Loading.finish();
					this.submitUrl = config.ajaxUrls.createMenu;
				}
            }
        })
    </script>
</body>
</html>
