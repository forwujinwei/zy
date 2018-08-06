$(function () {
    $(".show-date").datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        startDate: "2013-02-14",
        minuteStep: 10
    });
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/scheduleLog/list',
        datatype: "json",
        colModel: [
            { label: '日志ID', name: 'logId', width: 50, key: true },
            { label: '回访日期', name: 'jobId', width: 50},
            { label: '状态\t', name: 'beanName', width: 60 },
            { label: '资源类型', name: 'methodName', width: 60 },
            { label: '名称', name: 'params', width: 60 },

            { label: '领域', name: 'times', width: 70 },
            { label: '联系人', name: 'times', width: 70 },
            { label: '电话', name: 'times', width: 70 },
            { label: 'QQ', name: 'times', width: 70 },
            { label: '提交时间', name: 'times', width: 70 },
            { label: '确认时间', name: 'times', width: 70 },
            { label: '顾问', name: 'createTime', width: 80 },
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

