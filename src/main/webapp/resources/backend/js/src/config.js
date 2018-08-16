/**
 * Created with JetBrains WebStorm. User: ty Date: 14-10-5 Time: 下午5:34 To
 * change this template use File | Settings | File Templates.
 */
var config = {
	uploader : {
		url : "file/uploadMultiFile",
		swfUrl : "resources/js/lib/Moxie.swf",
		sizes : {
			all : "5120m",
			img : "2m"
		},
		filters : {
			all : "*",
			zip : "zip,rar",
			img : "jpg,JPG,jpeg,JPEG,png,PNG"
		},
		qiNiu : {
			upTokenUrl : "qi-niu/up-token",
			uploadDomain : "http://qiniu-plupload.qiniudn.com/",
			bucketDomain : "http://7xplk9.com1.z0.glb.clouddn.com/"
		},
		aLiYun : {
			getSignatureUrl : "#",
			host : "",
			policy : "",
			accessKey : "",
			signature : "",
			expire : 0
		},
		fileType : {
			others : 100,
			attachFile : 1,
			newsImageFile : 2,
			productionFile : 3
		}
	},

	ajaxUrls : {
		imageGet : "file/image",
		// 菜单-链接
		createMenu : "menu/createMenu",
		deleteMenu : "menu/deleteMenu/:id",
		updateMenu : "menu/updateMenu",
		getMenuList : "menu/getMenuList",
		// 角色-菜单
		createRoleMenu:"roleMenu/createRoleMenu",
		updateRoleMenu : "roleMenu/updateRoleMenu",
		deleteRoleMenu : "roleMenu/deleteRoleMenuByRoleMenuId/:id",
		getRoleMenuList:"roleMenu/getRoleMenuList",
		// 院校
		getSchoolByPage:"school/getSchoolByPage",
		createSchool:"school/createSchool",
		deleteSchool:"school/deleteSchool/:id",
		updateSchool:"school/updateSchool",
		getSchoolByProvince:"school/getSchoolByProvince", //查询学校
		getAcademyBySchoolName:"school/getAcademyBySchoolName",	//查询系部
		getAcademyBySchoolId:"school/getAcademyBySchoolId",		//查询系部
		getAllSchool:"school/getAllSchool",						//查询学校
		// 课程
		createCurriculum:"curriculum/createCurriculum",
		deleteCurriculum:"curriculum/deleteCurriculum/:id",
		updateCurriculum:"curriculum/updateCurriculum",
		getCurriculumListByPage:"curriculum/getCurriculumListByPage",
		// 作业     Assignment   assignment
		createAssignment:"assignment/createAssignment",
		deleteAssignment:"assignment/deleteAssignment/:id",
		updateAssignment:"assignment/updateAssignment",
		getAssignmentListByPage:"assignment/getAssignmentListByPage",
		getAssignmentListByPageAndCurriculumId:"assignment/getAssignmentListByPageAndCurriculumId",
		// 用户
		createUser:"user/createUser",
		createManageUser:"user/createManageUser",
		deleteUser:"user/deleteUser/:id",
		updateUser:"user/updateUser",				//更新学生信息
		updateManageUser:"user/updateManageUser",	//更新其他用户信息
		getUserByPage:"user/getUserByPage",
		//角色			
		getAllRoles:"role/getAllRoles",
		findRolesByUserId:"user/findRolesByUserId",
		//评论
		getCommnetListByPageAndAssignmentId:"comment/getCommnetListByPageAndAssignmentId"
	},
	viewUrls : {
		newsMgr : "news/newsMgr",
		newsUpdate : "news/newsCOU/:id",
		// 菜单-链接
		menuManage : "menu/menuManage",
		alterMenu : "menu/alterMenu",
		// 角色-菜单
		roleMenuManage:"roleMenu/roleMenuManage",
		alterRoleMenu:"roleMenu/alterRoleMenu",
		// 院校
		schoolManage:"school/schoolManage",
		alterSchool:"school/alterSchool",
		// 作业
		assignmentManage:"assignment/assignmentManage",
		alterAssignment:"assignment/alterAssignment",
		// 课程
		curriculumManage:"curriculum/curriculumManage",
		alterCurriculum:"curriculum/alterCurriculum",
		// 用户
		userManage:"user/userManage",
		alterUser:"user/alterUser"
	},
	dataTable : {
		langUrl : "resources/backend/lang/de_DE.txt"
	},
	perLoadCounts : {
		table : 10
	},
	//省份列表
	/*provinceList:{
		"北京":"北京","天津":"天津","河北":"河北","山西":"山西","内蒙古":"内蒙古","辽宁":"辽宁","吉林":"吉林","黑龙江":"黑龙江","上海":"上海","江苏":"江苏","浙江":"浙江",
		"安徽":"安徽","福建":"福建","江西":"江西","山东":"山东","河南":"河南","湖北":"湖北","湖南":"湖南","广东":"广东","广西":"广西","海南":"海南","重庆":"重庆",
		"四川":"四川","贵州":"贵州","云南":"云南","西藏":"西藏","陕西":"陕西","甘肃":"甘肃","青海":"青海","宁夏":"宁夏","新疆":"新疆","台湾":"台湾","香港":"香港","澳门":"澳门"
	},*/
	provinceList:[
		{value:"北京",label:"北京"},{value:"天津",label:"天津"},{value:"河北",label:"河北"},{value:"山西",label:"山西"},{value:"内蒙古",label:"内蒙古"},{value:"辽宁",label:"辽宁"},
		{value:"吉林",label:"吉林"},{value:"黑龙江",label:"黑龙江"},{value:"上海",label:"上海"},{value:"江苏",label:"江苏"},{value:"浙江",label:"浙江"},{value:"安徽",label:"安徽"},
		{value:"福建",label:"福建"},{value:"江西",label:"江西"},{value:"山东",label:"山东"},{value:"河南",label:"河南"},{value:"湖北",label:"湖北"},{value:"湖南",label:"湖南"},
		{value:"广东",label:"广东"},{value:"广西",label:"广西"},{value:"海南",label:"海南"},{value:"重庆",label:"重庆"},{value:"四川",label:"四川"},{value:"贵州",label:"贵州"},
		{value:"云南",label:"云南"},{value:"西藏",label:"西藏"},{value:"陕西",label:"陕西"},{value:"甘肃",label:"甘肃"},{value:"青海",label:"青海"},{value:"宁夏",label:"宁夏"},
		{value:"新疆",label:"新疆"},{value:"台湾",label:"台湾"},{value:"香港",label:"香港"},{value:"澳门",label:"澳门"}				
	],
	workGroup : {
		"1" : "产品组",
		"2" : "概念组"
	},
	workType : {
		"1" : "生活辅助类",
		"2" : "智能养老类",
		"3" : "综合设计类"
	},
	workStatus : {
		"1" : "已提交",
		"2" : "审核未通过",
		"3" : "审核已通过",
		"4" : "初选入围",
		"5" : "初选未入围",
		"6" : "复选入围",
		"7" : "复选未入围"
	},
	userStatus : {
		"0" : "激活",
		"1" : "禁用"
	},
	validErrors : {
		required : "请输入此字段！",
		email : "请输入正确的邮箱格式！",
		emailExist : "邮箱已经存在！",
		uploadImg : "请上传图片！",
		number : "请输入数字",
		maxLength : "此字段最多输入${max}个字！",
		minLength : "此字段最少输入${min}个字！",
		rangLength : "此字段只能输入${min}-${max}个字！",
		rang : "此字段只能输入${min}-${max}之间的整数！",
		pwdNotEqual : "两次输入的密码不一样！"
	},
	messages : {
		successTitle : "成功提示",
		errorTitle : "错误提示",
		optSuccess : "操作成功！",
		noData : "没有数据",
		progress : "处理中...",
		uploaded : "上传完成！",
		confirmDelete : "确定删除吗？",
		confirm : "确定执行此操作吗？",
		noSelected : "没有选中任何记录！",
		notFound : "资源丢失！",
		loadDataError : "请求数据失败！",
		networkError : "网络异常，请稍后重试！",
		systemError : "系统错误，请稍后重试或者联系d_seniors@126.com！",
		optSuccRedirect : "操作成功,3秒后跳转到管理页！",
		timeout : "登录超时，3秒后自动跳到登陆页！",
		optError : "服务器端异常，请稍后重试！",
		uploadSizeError : "最大文件大小${value}！",
		uploadExtensionError : "只允许上传${value}！",
		uploadIOError : "上传出错，请稍后重试！",
		imageSizeError : "图片大小不符合！",
		scoreRefresh : "本轮作品分数已更新，请前往作品列表查看"
	}
};
$(document).ready(function() {
	$("input[type='text'],input[type='email']").blur(function() {
		$(this).val($(this).val().trim());
	});

	// 阻止form提交
	$("form").keydown(function(e) {
		if (e.keyCode == 13) {
			return false;
		}
	});

	if (pageName) {
		var target = $(".item[data-page-name='" + pageName + "']");
		if (target) {
			target.addClass("active");
		}
	}

});
