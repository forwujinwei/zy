$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'api/resource/personal/list/?statusCode=RS_001',
        datatype: "json",
        colModel: [
            { label: '资源ID', name: 'id', width: 50, hidden:true },
            { label: '名称', name: 'name', width: 70 ,formatter: function(value, options, row) {
                    return "******";
                }},
            { label: '资源类型', name: 'typeDes', width: 50 ,formatter: function(value, options, row) {
                    return "******";
                }},
            { label: '提交时间', name: 'submitDate', width: 50,formatter: function(value, options, row) {
                    return "******";
                }},
            { label: '领域', name: 'fieldDes', width: 60,formatter: function(value, options, row) {
                    return "******";
                } },
            { label: '电话', name: 'phoneNumber', width: 60 ,formatter: function(value, options, row) {
                    return "******";
                }},
            { label: 'QQ', name: 'qqNumber', width: 60 ,formatter: function(value, options, row) {
                    return "******";
                }},
            { label: '来源', name: 'sourceDes', width: 70,formatter: function(value, options, row) {
                    return "******";
                } },
            { label: '操作', name: 'option', width: 70,formatter: function(value, options, row) {
                    return '<div class="btn-group" data-toggle="buttons-radio">' +
                        '<button class="btn btn-primary btn-padding-reset" ' +
                        'onclick="javascript:surePersonalResource()">确认</button>' +
                        '</div>'
                }}],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50,100,200],
        autowidth:true,
        multiselect : false,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
    el:'#rrapp',
    data:{
        condition:{
            phoneNum: null
        },
        updateResource:{
            id:null,
            statusCode:'RS_002'
        }
    },
    methods: {
        query: function () {
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'phoneNum': vm.q.phoneNum},
                page:1
            }).trigger("reloadGrid");
        },
        sureResource:function(){
            /*第二步：获取选中行的所有id*/
            var ids = $("#jqGrid").jqGrid('getGridParam', 'selarrrow');
            if (null == ids || ids.length == 0) {
                alert("至少选择一个防洪看守点");
            } else {
                for (var i = 0; i < ids.length; i++) {
                    /*第三步：获取某一条选中行的数据*/
                    var rowData = $("#jqGrid").jqGrid('getRowData', ids[i]);
                    /*第四步：获取某一条选中行的某一列值*/
                    alert(rowData['id']);
                }
            }
        },
        reload: function () {
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{},
                page:page
            }).trigger("reloadGrid");
        },
        back: function () {
            history.go(-1);
        }
    }
});

function surePersonalResource (){
    var rowId=$("#jqGrid").jqGrid("getGridParam","selrow");
    if(rowId == null || rowId == '' || rowId == undefined){
        alert("请选择一条数据");
        return ;
    }
    var url = 'api/resource/personal/update';
    $.ajax({
        type: "POST",
        url: baseURL + url,
        contentType: "application/json",
        data: JSON.stringify({
            id:rowId,
            statusCode:'RS_002'
        }),
        success: function(r){
            if(r.code === 0){
                alert('操作成功', function(){
                    reload();
                });
            } else{
                alert(r.msg);
            }
        }
    });

};
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam','page');
    $("#jqGrid").jqGrid('setGridParam',{
        postData:{},
        page:page
    }).trigger("reloadGrid");
};

