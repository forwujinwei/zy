$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/scheduleLog/list',
        datatype: "json",
        colModel: [
            { label: '国 家', name: 'logId', width: 50, key: true },
            { label: '地 区', name: 'jobId', width: 50},
            { label: '类 型', name: 'beanName', width: 60 },
            { label: '公正', name: 'methodName', width: 60 },
            { label: '认证', name: 'params', width: 60 },
            { label: '查询费用', name: 'times', width: 70 },
            { label: '首类申请费用', name: 'times', width: 70 },
            { label: '次类申请费用', name: 'times', width: 70 },
            { label: '注册费用', name: 'times', width: 70 },
            { label: '续展费用', name: 'times', width: 70 },
            { label: '转让费用', name: 'times', width: 70 },
            { label: '详情', name: 'times', width: 70 },
            { label: '跳转', name: 'times', width: 70 }
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

