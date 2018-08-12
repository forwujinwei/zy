$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'api/resource/public/list/?statusCode=RS_001',
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
                           '<button class="btn btn-primary btn-padding-reset" @click="surePublicResource('+row.id
                          +')">确认</button>' +
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
        resource:{
            resourceId:null,
            typeDes:null,
            submitDate:null,
            fieldDes:null,
            phoneNumber:null,
            qqNumber:null,
            name:null,
            sourceDes:null
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
        surePublicResource:function(id){
            if(null==id||(id=='')||(id=='undefined')){
                alert("所选数据错误");
            };
            //调用public保存方法

        },
        back: function () {
            history.go(-1);
        }
    }
});

