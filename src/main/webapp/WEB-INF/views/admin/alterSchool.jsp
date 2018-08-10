<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
	<%@ include file="../head.jsp"%>
	
	<link rel="stylesheet" type="text/css" href="resources/backend/css/lib/iview.css">
	<link rel="stylesheet" type="text/css" href="resources/backend/css/src/main.css">
	<link rel="stylesheet" type="text/css" href="resources/css/lib/bootstrap.min.css">
	
	<script>
		var province = '${school.province}',
			name = '${school.name}',
			academy = '${school.academy}';
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
		<div class="schoolCOU" style="margin: 20px 20px;" v-cloak>
			<breadcrumb>
		        <breadcrumb-item to="school/schoolManage">院校管理</breadcrumb-item>
		        <breadcrumb-item>新建/修改院校</breadcrumb-item>
		    </breadcrumb><br />
			<div>
				<i-form :model="dataSourse" :label-width="180" style="width:80%;"> 
					<form-item label="省份"> 
						<i-select v-model="dataSourse.province" style="width:200px" @on-change="provinceCheck" @on-open-change="openProvince">
		     				<i-option v-for="provinceItem in provinceList" :value="provinceItem.value" :key="provinceItem.value" >{{ provinceItem.label }}</i-option>
		     			</i-select>
					</form-item> 
					<form-item label="选择该学校"> 
						<i-select v-model="dataSourse.parentId" style="width:200px" @on-change="schoolCheck">
		     				<i-option v-for="schoolItem in schoolList" :value="schoolItem.id" :key="schoolItem.id" >{{ schoolItem.name }}</i-option>
		     			</i-select>
					</form-item> 
					<form-item label="学校名称"> 
						<i-input v-model="dataSourse.name" placeholder="请输入学校名"></i-input>
					</form-item> 
					<form-item> 
						<i-button type="primary" v-on:click="submitSchool" :disabled="schoolDisable" long>确定</i-button> 
					</form-item> 
				</i-form>
			</div>
			<div v-if="showAcademy">
				<breadcrumb >
		        	<breadcrumb-item to="school/schoolManage">院校管理</breadcrumb-item>
			        <breadcrumb-item>新建/修改院系</breadcrumb-item>
			    </breadcrumb><br />
			    <div>
					<i-form :model="dataSourse" :label-width="180" style="width:80%;"> 
						<form-item label="选择该学校"> 
							<i-select v-model="dataSourse.parentId" style="width:200px" @on-change="schoolCheck">
			     				<i-option v-for="schoolItem in schoolList" :value="schoolItem.id" :key="schoolItem.id" >{{ schoolItem.name }}</i-option>
			     			</i-select>
						</form-item> 
						<form-item label="学院名称">
							<i-input v-model="academy" placeholder="请输入学院名"></i-input>
						</form-item> 
						<form-item> 
							<i-button type="primary" v-on:click="submitAcademy" long>确定</i-button> 
						</form-item> 
					</i-form>
				</div>
			</div>
		</div>
	</div>
	<script>
		var pageName = "school";
		var schoolCOU = new Vue({
			el : ".schoolCOU",
			data : function() {
				return {
					//需要提交的数据
					dataSourse : {
						id : "",
						parentId:"",
						province : "",
						name : ""
					},
					showAcademy:false,
					academy:"",
					schoolDisable:false,
					province:"",
					schoolList:[],
					provinceList:[],
                    redirectUrl:config.viewUrls.schoolManage,
                    submitUrl:""
				}
			},
			methods : {
				submitSchool : function() {
					var that = this;
                	this.$Loading.start();
                	
                	//学校部分提交
					$.ajax({
            	        url:this.submitUrl,
            	        type:"post",
            	        dataType:"json",
            	        contentType :"application/json; charset=UTF-8",
            	        data:JSON.stringify(that.dataSourse),
            	        success:function(res){
            	            if(res.success){
            	            	//学院部分提交
                            	that.$Loading.finish();
            	            	if(that.redirectUrl){
            	                	that.$Notice.success({title:"新建学校成功，请新建学院或系部"});
            	                	that.showAcademy = true;
            	                	that.province = that.dataSourse.province;
            	                	$.ajax({
            		           	        url:config.ajaxUrls.getSchoolByProvince,
            		           	        type:"get",
            		           	        data:{province:that.province},
            		           	        success:function(res){
            		           	            if(res.success){
            		           	            	that.schoolList = res.object;
            									that.schoolList.push({id:"0",name:"无选项"});
            									that.schoolDisable = true;
            		           	            }else{
            		           	            	that.$Notice.error({title:res.message});
            		           	            }
            		           	        }
            		           	    });
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
				submitAcademy:function(){
					console.log("parentId",this.parentId);
				},
				schoolCheck:function(index){
					if(index == 0){
						this.disableModel = false;
						this.parentId = 0;
					}else{
						this.disableModel = true;
						this.parentId = index;
					}
					console.log("parentId",this.parentId);
				},
				//省份选择
				provinceCheck:function(index){
					this.dataSourse.province = index;
					var that = this;
					$.ajax({
	           	        url:config.ajaxUrls.getSchoolByProvince,
	           	        type:"get",
	           	        data:{province:index},
	           	        success:function(res){
	           	            if(res.success){
	           	            	if(res.object.length != 0){
									that.schoolList = res.object;
									that.schoolList.push({id:"0",name:"无选项"});
	           	            	}else{
	           	            		that.schoolList.push({id:"0",name:"无选项"});
	           	            	}
	           	            }else{
	           	            	that.$Notice.error({title:res.message});
	           	            }
	           	        }
	           	    });
				},
				openProvince:function(){
					this.schoolList = [];
				}
			},
			created:function(){
				var that = this;
				this.provinceList = config.provinceList;
				//获取学校数据
				
				
				
				var	schoolId = window.location.pathname.split("/Zeus/school/alterSchool/")[1];	//获取院校id
				if(schoolId != 0){
   	            	that.dataSourse.id = schoolId;
   	            	that.dataSourse.province = province;
   	            	that.dataSourse.name = name;
   	            	that.dataSourse.academy = academy;
					this.submitUrl = config.ajaxUrls.updateSchool;
				}else{
					this.submitUrl = config.ajaxUrls.createSchool;
				}
			}
		})
	</script>
</body>
</html>
