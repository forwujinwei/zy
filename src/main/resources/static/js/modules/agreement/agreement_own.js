$(function () {
    laydate.render({
        elem: '#createStartDate' //指定元素
    });
    laydate.render({
        elem: '#createEndDate' //指定元素
    });
    $("#jqGrid").jqGrid({
        url: baseURL + 'api/resource/agreement/list',
        datatype: "json",
        colModel: [
            { label: '合同编号', name: 'agreementId', width: 80, key: true },
            { label: '类型', name: 'agreementTypeDes', width: 40},
            { label: '名称', name: 'tradeMarkName', width: 80 },
            { label: '总费用', name: 'allcost', width: 40 },
            { label: '定金', name: 'earnest', width:40 },
            { label: '尾款', name: 'finalPayment', width: 40 },
            { label: '支出', name: 'pay', width: 40 },
            { label: '状态', name: 'status', width: 40 },
            { label: '顾问', name: 'adviser', width: 50 },
            { label: '创建时间', name: 'createDate', width: 80 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50,100,200],
        rownumbers: false,
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
        showList:true,
        showAgreement:false,
        condition:{
            queryType: 'AQT_001',
            urgent:'00',
            queryTypeValue:null,
            createStartDate:null,
            createEndDate:null
        },
        agreement:{
            agreementId:null,
            allcost:null,
            earnest:null,
            finalPayment:null,
            pay:null
        },
        tradeMarkAgreement:{
            agreementId:null,
            serviceTypeCode:null,
            registerCost:null,
            designCost:null,
            queryCost:null,
            nameCost:null,
            hostCost:null,
            queryCost:null,
            specifyColor:null,
            applyTypeCode:null
        }
    },
    methods: {
        query: function () {
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'jobId': vm.q.jobId},
                page:1
            }).trigger("reloadGrid");
        },
        showUpdateAgreement:function () {
            vm.showList=false;
            vm.showAgreement=true;
            /*为商标信息页添加表格初始化事件*/
            $('#myTab a[href="#tradeMarkInfo"]').on('shown.bs.tab', function(e){
                $("#tradeMarkjqGrid").jqGrid({
                    url: baseURL + 'api/resource/agreement/list',
                    datatype: "json",
                    colModel: [
                        { label: '合同编号', name: 'agreementId', width: 60, key: true },
                        { label: '商标ID', name: 'tradeMarkId', width: 60, key: true },
                        { label: '商标名称', name: 'tradeMarkName', width: 60},
                        { label: '分类', name: 'tradeMarkType', width: 40 },
                        { label: '图片', name: 'tradeMarkImg', width: 60 },
                        { label: '商标局图片', name: 'governmentTradeMarkImg', width:60 }
                    ],
                    viewrecords: true,
                    height: 385,
                    rowNum: 10,
                    rowList : [10,30,50,100,200],
                    rownumbers: false,
                    autowidth:true,
                    multiselect: false,
                    pager: "#tradeMarkjqGridPager",
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
        },
        updateAgreement:function(){

        },
        reload: function () {
            vm.showList = true;
            vm.showAgreement=false;
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

