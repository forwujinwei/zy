$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/scheduleLog/list',
        datatype: "json",
        colModel: [
            { label: '合同编号', name: 'logId', width: 50, key: true },
            { label: '合同类型\t', name: 'jobId', width: 50},
            { label: '名称', name: 'beanName', width: 60 },
            { label: '总费用', name: 'methodName', width: 60 },
            { label: '定金', name: 'params', width: 60 },
            { label: '尾款', name: 'times', width: 70 },
            { label: '支出', name: 'times', width: 70 },
            { label: '状态', name: 'times', width: 70 },
            { label: '顾问', name: 'times', width: 70 },
            { label: '创建时间', name: 'times', width: 70 },
            { label: '操作', name: 'times', width: 70 },
            { label: '来源', name: 'times', width: 70 }
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

