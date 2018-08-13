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

</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="left">
		<%@ include file="menu.jsp"%>
	</div>
	<div class="right">
		<div class="schoolManage" style="margin: 20px 20px;" v-cloak>
	      	<breadcrumb>
		        <breadcrumb-item>院校管理</breadcrumb-item>
		    </breadcrumb><br />
			<modal v-model="deleteModal" @on-ok="ok" title="警告！！！">
				<p style="color: #ed3f14; text-align: center">
					<Icon type="information-circled"></Icon>
					<span style="font-size: 15px;">确定删除学校:{{schoolTitle}}？</span>
				</p>
			</modal>
			<i-button type="primary" @click="createSchool"><Icon type="plus"></Icon> 新建</i-button>
			<div style="display:inline-block;margin-left:60px;">
				<label>省份：</label>
				<i-select v-model="provinceModel" style="width:200px" @on-change="provinceCheck">
			        <i-option v-for="provinceItem in provinceList" :value="provinceItem.value" :key="provinceItem.value" >{{ provinceItem.label }}</i-option>
			    </i-select>
			</div>
			<div style="display:inline-block;margin-left:60px;">
				<label>院校：</label>
				<i-select v-model="schoolModel" style="width:200px" @on-change="schoolCheck">
			        <i-option v-for="schoolItem in schoolList" :value="schoolItem.id" :key="schoolItem.id" >{{ schoolItem.name }}</i-option>
			    </i-select>
			</div>
			<i-table :columns="columns" :data="dataList" style="margin-top:20px;"></i-table>
			<page v-model="totalPage" :current="1" :total="totalPage" @on-change="pageChange" show-total style="margin-right:60px;margin-top:20px;text-align:right;"></page>
		</div>
	</div>
	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/bootstrap.min.js" charset="utf-8"></script>
	<script src="resources/backend/js/lib/vue.min.js" charset="utf-8"></script>
	<script src="resources/backend/js/lib/iview.min.js" charset="utf-8"></script>
	<script src="resources/backend/js/src/config.js"></script>
	<script>
    		var pageName = "school";
            var schoolManage = new Vue({
                el: '.schoolManage',
                data: function(){
                    return{
                    		//省份下拉框数据
                    	provinceModel:"",
                    	provinceList:config.provinceList,
                    		//院校下拉框数据
                    	schoolModel:"",
                    	schoolList:[],
                    	aoData:{limit:10,offset:0},
                        index:"",
                        deleteModal: false,
                        schoolTitle:"",
                        totalPage:"",	//总数
                        columns:[
                            { title: 'ID',key: 'id', align: 'center'},
                            { title: '省份',key: 'province', align: 'center'},
                            { title: '院校或学院名称',key: 'name', align: 'center'},
                            { title: '操作',key: 'opt', align: 'center',
                        	   render: (h, params) => {
                                   return h('div', [
                                       /* h('Button', {
                                           props: {
                                               type: 'primary',
                                               size: 'small'
                                           },
                                           style: {
                                               marginRight: '5px'
                                           },
                                           on: {
                                               click: () => {
                                                   this.change(params.index)
                                               }
                                           }
                                       }, '修改'), */
                                       h('Button', {
                                           props: {
                                               type: 'error',
                                               size: 'small'
                                           },
                                           on: {
                                               click: () => {
                                                   this.remove(params.index)
                                               }
                                           }
                                       }, '删除')
                                   ]);
                               }
                           }
                        ],
                        dataList:[]
                    }
                },
                methods: {
                    ok: function () {
                    	var id = this.dataList[this.index].id;
                    	var that = this;
                    	this.$Loading.start();
                    	$.ajax({
                            "dataType":'json',
                            "type":"DELETE",
                            "data":{id:id},
                            "url":config.ajaxUrls.deleteSchool.replace(":id",id),
                            "success": function (res) {
                                if(res.success===false){
                                	that.$Notice.error({title:res.message});
                                }else{
                                	that.$Notice.success({title:config.messages.optSuccess});
                                	$.ajax({
                            	        url:config.ajaxUrls.getSchoolByPage,
                            	        type:"GET",
                            	        dataType:"json",
                            	        contentType :"application/json; charset=UTF-8",
                            	        data:that.aoData,
                            	        success:function(res){
                            	            if(res.success){
                			                	that.$Loading.finish();
                								that.dataList = res.object.list;
                            	            }else{
                            	            	that.$Notice.error({title:res.message});
                            	            }
                            	        },
                            	        error:function(){
                    	                	that.$Loading.error();
                            	        	that.$Notice.error({title:config.messages.loadDataError});
                            	        }
                            	    });
                                }
                            }
                        });
                    },
                    createSchool:function(){
                        window.location.href="school/alterSchool/0";
                    },
                    change:function(index){
                    	var that = this;
                    	window.location.href="school/alterSchool/"+this.dataList[index].id;
                    },
                    remove:function(index) {
                    	this.schoolTitle = this.dataList[index].name;
                        this.deleteModal = true;
                        this.index = index;
                    },
                    pageChange:function(index){
                    	this.$Loading.start();
            			var that = this;
                		this.aoData.offset = (index-1)*10;
                		$.ajax({
                	        url:config.ajaxUrls.getSchoolByPage,
                	        type:"GET",
                	        dataType:"json",
                	        contentType :"application/json; charset=UTF-8",
                	        data:this.aoData,
                	        success:function(res){
                	            if(res.success){
                	            	that.totalPage = res.object.count;
    								that.dataList = res.object.list;
    			                	that.$Loading.finish();
                	            }else{
                	            	that.$Notice.error({title:res.message});
                	            }
                	        },
                	        error:function(){
                	        	that.$Notice.error({title:config.messages.loadDataError});
        	                	that.$Loading.error();
                	        }
                	    });
                    },
                    //省份选择
                    provinceCheck:function(index){
                    	this.$Loading.start();
                    	if(index != undefined){
                    		this.schoolModel = "";
	                    	var that = this;
	                    	$.ajax({
	                	        url:config.ajaxUrls.getSchoolByProvince,
	                	        type:"GET",
	                	        data:{province:index},
	                	        success:function(res){
	                	            if(res.success){
    	        	                	that.$Loading.finish();
	                	            	that.dataList = res.object;
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
                    //学校选择
                    schoolCheck:function(index){
                    	this.$Loading.start();
                    	if(index != undefined){
                    		this.provinceModel = "";
                        	var that = this;
    	                	$.ajax({
    	            	        url:config.ajaxUrls.getAcademyBySchoolId,
    	            	        type:"GET",
    	            	        data:{schoolId:index},
    	            	        success:function(res){
    	            	            if(res.success){
    	        	                	that.$Loading.finish();
    	            	            	that.dataList = res.object;
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
                    }
                },
                created:function(){
                	this.$Loading.start();
            		this.provinceModel = "";
            		this.schoolModel = "";
                	var that = this;
                	//初始化所有数据
                	$.ajax({
            	        url:config.ajaxUrls.getSchoolByPage,
            	        type:"GET",
            	        dataType:"json",
            	        contentType :"application/json; charset=UTF-8",
            	        data:this.aoData,
            	        success:function(res){
            	            if(res.success){
            	            	that.totalPage = res.object.count;
								that.dataList = res.object.list;
			                	that.$Loading.finish();
            	            }else{
            	            	that.$Notice.error({title:res.message});
            	            }
            	        },
            	        error:function(){
            	        	that.$Notice.error({title:config.messages.loadDataError});
    	                	that.$Loading.error();
            	        }
            	    });
                	//初始化所有学校数据
                	$.ajax({
            	        url:config.ajaxUrls.getAllSchool,
            	        type:"GET",
            	        dataType:"json",
            	        contentType :"application/json; charset=UTF-8",
            	        success:function(res){
            	            if(res.success){
        	                	that.$Loading.finish();
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
                }
            })
    </script>
</body>
</html>
