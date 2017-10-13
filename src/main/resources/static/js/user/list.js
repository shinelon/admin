"use strict"

$(function(){
	init();
});

function init(){
	$("#add").on("click",add);
	$("#modify").on("click",modify);
//	$("#disable").on("click",disable);
	$("#save").on("click",save);
	$("#reset").on("click",resetPassword);
	$("#cancel").on("click",function(){$('#userInfoModal').modal('toggle')});
}

function add(){
	alert("add")
}

function modify(){
	var rowData=$("#userTable").bootstrapTable('getSelections');
	if(rowData.length==0){
		layer.msg('请选择记录');
		return;
	}
//	console.info(rowData);
	initModalData(rowData[0]);
	$('#userInfoModal').modal('toggle')
}
function initModalData(rowData){
	$('#id-modal').text(rowData.id);
	$('#username-modal').val(rowData.username);
	$('#mobile-modal').val(rowData.mobile);
	$('#email-modal').val(rowData.email);
	$('#status-select').val(rowData.status);
	
}
function disable(){
	alert("disable");
}
function save(){
	var updateData={};
	updateData["id"]=$('#id-modal').text();
//	updateData["username"]=$('#username-modal').val();
	updateData["mobile"]=$('#mobile-modal').val();
	updateData["email"]=$('#email-modal').val();
	updateData["status"]=$('#status-select').val();
	console.info(updateData)
	if(!updateData.id){
		layer.msg('获取数据失败，请刷新后重试');
		return false;
	}
	
	$.ajax({
		type : 'POST',
		url : contextPath+'/user/update',
		data : updateData,
		success : function(result) {
			console.info(result);
			if (result.errorCode != 0) {
				layer.msg('提交失败！请稍后再试');
				return;
			} else {
				location=location;
				return;
			}
		},
		error : function(result) {
			layer.msg('提交失败！请检查网络后再试');
			return false;
		},
		dataType : "json"
	});
}
function resetPassword(){
	alert("alert resetPassword");
}

function statusFormatter(value, row, index){
	if(value==1){
		return '有效';
	}else{
		return '无效';
	}
	
}