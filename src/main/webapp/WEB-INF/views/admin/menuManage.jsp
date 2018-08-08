<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
	<%@ include file="../head.jsp"%>

	<link rel="stylesheet" type="text/css" href="resources/css/lib/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resources/backend/css/lib/iview.css">
	<link rel="stylesheet" type="text/css" href="resources/backend/css/src/main.css">

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
		<div class="menuManage" style="margin:20px 20px;" v-cloak>
	      	<breadcrumb>
		        <breadcrumb-item>菜单管理</breadcrumb-item>
		    </breadcrumb><br />
	      	<modal v-model="deleteModal" @on-ok="ok" title="警告！！！">
			    <p style="color:#ed3f14;text-align:center">
			        <Icon type="information-circled"></Icon>
			        <span style="font-size: 15px;">确定删除菜单:{{menuTitle}}？</span>
			    </p>
			</modal>
	      	<i-button type="primary" @click="createCurriculum"><Icon type="plus"></Icon> 新建</i-button>
	      	<i-table :columns="columns" :data="dataList" style="margin-top:20px;"></i-table>
	  	</div>
  	</div>
  	<script>
  		var pageName = "menu";
      	var menuManage = new Vue({
          	el: '.menuManage',
          	data: function(){
          		return{
                 	index:"",
                	deleteModal: false,
                	menuTitle:"",
               	   	columns:[
                     	{ title: 'ID',key: 'id', align: 'center'},
                      	{ title: '菜单名',key: 'name', align: 'center'},
                      	{ title: 'URL',key: 'url', align: 'center'},
                      	{ title: '操作',key: 'opt', align: 'center',
                  	   	render: (h, params) => {
                             return h('div', [
                             	h('Button', {
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
                                 }, '修改'),
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
            	this.$Loading.start();
				var id = this.dataList[this.index].id;
            	var that = this;
            	$.ajax({
                    "dataType":'json',
                    "type":"DELETE",
                    "data":{id:id},
                    "url":config.ajaxUrls.deleteMenu.replace(":id",id),
                    "success": function (res) {
                        if(res.success===false){
                        	that.$Notice.error({title:res.message});
                        }else{
                        	that.$Notice.success({title:config.messages.optSuccess});
                        	$.ajax({
            			        url:config.ajaxUrls.getMenuList,
            			        type:"GET",
            			        dataType:"json",
            			        contentType :"application/json; charset=UTF-8",	
            			        success:function(res){
            			            if(res.success){
                	                	that.$Loading.finish();
            							that.dataList = res.object;
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
              //新建课程
              createCurriculum:function(){
                  window.location.href="menu/alterMenu/0";
              },
              change:function(index){
              		window.location.href="menu/alterMenu/"+this.dataList[index].id;
              },
              remove:function(index) {
                  this.index = index;
                  this.deleteModal = true;
              }
          },
          created:function(){
          		this.$Loading.start();
        	    var that = this;
			   	$.ajax({
			        url:config.ajaxUrls.getMenuList,
			        type:"GET",
			        dataType:"json",
			        contentType :"application/json; charset=UTF-8",	
			        success:function(res){
			            if(res.success){
	                    	that.$Loading.finish();
							that.dataList = res.object;
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
      })
    </script>
</body>
</html>