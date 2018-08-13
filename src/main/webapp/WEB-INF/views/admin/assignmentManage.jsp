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
	    <div class="assignmentManage" style="margin:20px 20px;" v-cloak>
	        <breadcrumb>
		        <breadcrumb-item to="curriculum/curriculumManage">课程管理</breadcrumb-item>
		        <breadcrumb-item>作业管理</breadcrumb-item>
		    </breadcrumb><br />
	        <i-table :columns="columns" :data="dataList" style="margin-top:20px;"></i-table>
	    </div>
    </div>
    <script>
		var pageName = "curriculum";
        var assignmentManage = new Vue({
            el: '.assignmentManage',
            data: function(){
            	return{
                    aoData:{curriculumId:0,limit:10,offset:0},
                    curriculumInfo:{curriculumId:"",curriculumName:""},
                    columns:[
                        { title: 'ID',key: 'id', align: 'center'},
                        { title: '学生id',key: 'userId', align: 'center'},
                        { title: '作业名称',key: 'name', align: 'center'},
                        { title: '所属课程',key: 'curriculumId', align: 'center',
                        	render: (h, params) => {
    	        			    return h('div',{
    	        			    	props: {
    		                              type: 'primary',
    		                              size: 'small'
    		                          },
    		                          style: {
    		                              marginRight: '5px'
    		                          }
    	        			    },this.curriculumInfo.curriculumName)
    		              	}	
                        },
                        { title: '评论',key: 'opt', align: 'center',
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
                                                this.chickComment(params.index)
                                            }
                                        }
                                    }, '查看')
                                ]);
                            }
                        },
                        { title: '打分',key: 'opt', align: 'center',
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
                                                this.grade(params.index)
                                            }
                                        }
                                    }, '查看')
                                ]);
                            }
                        }
                    ],
                    dataList:[]
            	}
            },
            methods: {
                chickComment:function(index){
                    console.log("chickComment:",index);
                    window.location.href="comment/commentManage/" + this.dataList[index].id;
                },
                grade:function(index){
                    console.log(" score:",index);
                }
            },
            created:function(){
            	var that = this;
            	this.$Loading.start();
            	this.curriculumInfo.curriculumId = window.location.search.split("curriculumId=")[1];
            	this.aoData.curriculumId = window.location.search.split("curriculumId=")[1];	//获取课程id
            	$.ajax({
			        url:config.ajaxUrls.getAssignmentListByPageAndCurriculumId,
			        type:"GET",
			        data:this.aoData,
			        dataType:"json",
			        contentType :"application/json; charset=UTF-8",
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
            	//课程数据
            	$.ajax({
			        url:config.ajaxUrls.getCurriculumListByPage,
			        type:"GET",
			        data:this.aoData,
			        dataType:"json",
			        contentType :"application/json; charset=UTF-8",	
			        success:function(res){
			            if(res.success){
	                    	that.$Loading.finish();
							for(var i = 0;i<res.object.list.length;i++){
								if(that.curriculumInfo.curriculumId == res.object.list[i].id){
									that.curriculumInfo.curriculumName = res.object.list[i].name;
								}
							}
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
