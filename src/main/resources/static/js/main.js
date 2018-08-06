$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/scheduleLog/list',
        datatype: "json",
        colModel: [
            { label: '回访日期', name: 'logId', width: 50, key: true },
            { label: '资源类型', name: 'jobId', width: 50},
            { label: '名称', name: 'beanName', width: 60 },
            { label: '领域', name: 'methodName', width: 60 },
            { label: '联系人', name: 'params', width: 60 },
            { label: '电话', name: 'times', width: 70 },
            { label: 'QQ', name: 'times', width: 70 },
            { label: '提交时间', name: 'times', width: 70 },
            { label: '确认时间', name: 'times', width: 70 },
            { label: '顾问', name: 'times', width: 70 },
            { label: '操作', name: 'times', width: 70 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50,100,200],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: false,
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
/*上月打款信息查询*/
    /*$("#jqGrid_account_info").jqGrid({
        url: baseURL + 'sys/scheduleLog/list',
        datatype: "json",
        colModel: [
            { label: '时间', name: 'logId', width: 50, key: true },
            { label: '汇款地址(人)\t', name: 'jobId', width: 50},
            { label: '收款行', name: 'beanName', width: 60 },
            { label: '汇款金额', name: 'methodName', width: 60 },
            { label: '官费', name: 'params', width: 60 },
            { label: '合同号', name: 'times', width: 70 },
            { label: '代理费', name: 'times', width: 70 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50,100,200],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: false,
        pager: "#jqGridPager_account_info",
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
            $("#jqGrid_account_info").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });*/
});

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            jobId: null
        }
    },
    methods: {
        query: function () {
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'jobId': vm.q.jobId},
                page:1
            }).trigger("reloadGrid");
        },
        showError: function(logId) {
            $.get(baseURL + "sys/scheduleLog/info/"+logId, function(r){
                parent.layer.open({
                    title:'失败信息',
                    closeBtn:0,
                    content: r.log.error
                });
            });
        },
        back: function () {
            history.go(-1);
        }
    }
});
