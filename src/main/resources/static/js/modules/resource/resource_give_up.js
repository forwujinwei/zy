$(function () {
    laydate.render({
        elem: '#submitStartDate' //指定元素
    });
    laydate.render({
        elem: '#submitEndDate' //指定元素
    });
    laydate.render({
        elem: '#sureStartDate' //指定元素
    });
    laydate.render({
        elem: '#sureEndDate' //指定元素
    });
    $("#jqGrid").jqGrid({
        url: baseURL + 'api/resource/RDSP_001/list/RS_003',
        datatype: "json",
        colModel: [
            { label: '资源ID', name: 'id', hidden:true},
            { label: '回访日期', name: 'endRevisitDate', width: 50, formatter: function(value, options, row) {
                    return (value == null || value == undefined) ? "" : value.trim().substring(0, 10);
                }},
            { label: '类型', name: 'typeDes', width: 30 },
            { label: '名称', name: 'name', width: 70 },
            { label: '领域', name: 'fieldDes', width: 60 },
            { label: '联系人', name: 'linkMan', width: 40 },
            { label: '电话', name: 'phoneNumber', width: 70 },
            { label: 'QQ', name: 'qqNumber', width: 60 },
            { label: '提交时间', name: 'submitDate', width: 50, formatter: function(value, options, row) {
                    return (value == null || value == undefined) ? "" : value.trim().substring(0, 10);
                }},
            { label: '确认时间', name: 'sureDate', width: 50, formatter: function(value, options, row) {
                    return (value == null || value == undefined) ? "" : value.trim().substring(0, 10);
                }},
            { label: '顾问', name: 'adviser', width: 40 },
            { label: '操作', name: 'option', width: 80,formatter: function(value, options, row) {
                    return '<div class="btn-group" data-toggle="buttons-radio">' +
                        '<button class="btn btn-primary btn-padding-reset">确认</button>'+
                        '</div>'
                }}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50,100,200],
        rownumbers: false,
        rownumWidth: 25,
        autowidth:true,
        /* multiselect: true,*/
        multiboxonly:true,
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
        baseURL:baseURL,
        condition:{
            intentCode: '00',
            queryTypeCode: '00',
            queryTypeValue: null,
            dataSourceCode:'RDSP_001',
            statusCode:'RS_002',
            typeCode: '00',
            infoRange: '00',
            acrossCode:'00',
            infoRangeCode:'00',
            submitStartDate: null,
            submitEndDate: null,
            sureStartDate: null,
            sureEndDate: null
        },
        title:null,
        showAddCustomer:false,
        showAddRecord:false,
        showAddAgreement:false,
        showList: true,
        resource:{
            id:null,
            name:null,
            typeDes:null,
            sourceDes:null,
            fieldDes:null,
            intentDes:null,
            linkMan:null,
            phoneNumber:null,
            qqNumber:null,
            enterWay:null,
            remarks:null,
            submitBy:null,
            submitDate:null,
            sureBy:null,
            sureDate:null,
            adviser:null,
            createBy:null,
            createDate:null,
            updateBy:null,
            updateDate:null,
            endRevisitDate:null
        },
        customerResource:{
            name:null,
            typeCode:null,
            sourceCode:null,
            fieldDes:null,
            linkMan:null,
            phoneNumber:null,
            qqNumber:null
        },
        revisit:{
            resourceId:null,
            revisitDate:null,
            revisitRemark:null,
            intentCode:'00'
        },
        agreement:{
            agreementId:null,
            resourceId:null,
            allcost:null,
            earnest:null,
            finalPayment:null,
            linkMan:null,
            firstParty:null,
            email:null,
            phoneNumber:null,
            address:null,
            agreementTypeCode:null
        }

    },

    methods: {
        addCustomer: function(){
            vm.showList = false;
            vm.showAddCustomer = true;
            vm.title = "添加客户";
            vm.customerResource = {
                name:null,
                typeCode:'00',
                sourceCode:null,
                fieldDes:null,
                linkMan:null,
                phoneNumber:null,
                qqNumber:null
            };
        },
        addAgreement: function(){
            vm.showList = false;
            vm.showAddAgreement = true;
            vm.title = "添加合同";
            /*是否选中校验*/
            var rowId=$("#jqGrid").jqGrid("getGridParam","selrow");
            if(rowId == null || rowId == '' || rowId == undefined){
                alert("请选择一条数据");
                return ;
            }
            vm.agreement = {
                agreementId:'ZY'+vm.getCurrentDate(),
                resourceId:rowId,
                allcost:null,
                earnest:null,
                finalPayment:null,
                linkMan:null,
                firstParty:null,
                email:null,
                phoneNumber:null,
                address:null,
                agreementTypeCode:'AGT_001'
            };
        },
        addRecord: function () {
            vm.showList = false;
            vm.showAddRecord = true;
            vm.title = "添加记录";
            var _this=this;
            /*是否选中校验*/
            var rowId=$("#jqGrid").jqGrid("getGridParam","selrow");
            if(rowId == null || rowId == '' || rowId == undefined){
                alert("请选择一条数据");
                return ;
            }
            vm.revisit.resourceId=rowId;
            /*设置弹窗*/
            laydate.render({
                elem: '#revisitDate' //指定元素
            });

            /*初始化记录表*/
            $("#recordGridTable").jqGrid({
                type: "GET",
                url: _this.baseURL+'api/revisit/list/'+ _this.revisit.resourceId,
                datatype: "json",
                colModel: [
                    { label: '回访记录', name: 'id', hidden:true},
                    { label: '回访日期', name: 'revisitDate', width: 95, formatter: function(value, options,row){
                            return (value == null || value == undefined) ? "" : value.trim().substring(0, 10);
                        }},
                    { label: '内容', name: 'revisitRemark', width: 420}
                ],
                viewrecords: false,
                height: 300,
                rowNum: 10,
                rowList : [10,30,50,100,200],
                shrinkToFit: true,
                rownumbers: false,
                autowidth:true,
                pager: "#recordGridPager",
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
                    $("#recordGrid").closest(".ui-jqgrid-bdiv").css({ 'overflow-y' : 'hidden','overflow-x':'hidden' });

                }
            });
            _this.recordReload();
        },
        saveRevisit: function () {
            var _this=this;
            _this.revisit.revisitDate=$("#revisitDate").val();
            var url = 'api/revisit/save';
            $.ajax({
                type: "POST",
                url: _this.baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(_this.revisit),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            _this.showAddRecord=false;
                            _this.reload();
                        });
                    } else{
                        alert(r.msg);
                    }
                }
            });
        },
        saveAgreement:function(){
            var url = 'api/resource/agreement/save';
            $.ajax({
                type: "POST",
                url: vm.baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.agreement),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.showAddAgreement=false;
                            vm.reload();
                        });
                    } else{
                        alert(r.msg);
                    }
                }
            });

        },
        query: function () {
            var _this=this;
            _this.condition.submitStartDate=$("#submitStartDate").val();
            _this.condition.submitEndDate=$("#submitEndDate").val();
            _this.condition.sureStartDate= $("#sureStartDate").val();
            _this.condition.sureEndDate=$("#sureEndDate").val();
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'intentCode': _this.condition.intentCode,
                    'queryTypeCode': _this.condition.queryTypeCode,
                    'queryTypeCode': _this.condition.queryTypeValue,
                    'typeCode': _this.condition.typeCode,
                    'acrossCode': _this.condition.acrossCode,
                    'submitStartDate': _this.condition.submitStartDate,
                    'submitEndDate': _this.condition.submitEndDate,
                    'sureStartDate': _this.condition.sureStartDate,
                    'sureEndDate': _this.condition.sureEndDate,
                    'infoRangeCode':_this.condition.infoRangeCode
                },
                page:1
            }).trigger("reloadGrid");
        },
        saveResource: function () {
            var _this=this;
            var url = "api/resource/save";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(_this.customerResource),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            _this.showAddCustomer=false;
                            _this.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
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
        update: function () {
            var userId = getSelectedRow();
            if(userId == null){
                return ;
            }

            vm.showList = false;
            vm.title = "修改";

            vm.getUser(userId);
            //获取角色信息
            this.getRoleList();
        },
        back: function () {
            history.go(-1);
        },
        reload: function () {
            vm.showList = true;
            vm.showAddCustomer=false;
            vm.showAddRecord=false;
            vm.showAddAgreement=false;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{},
                page:page
            }).trigger("reloadGrid");
        },
        recordReload: function(){
            vm.showAddRecord = true;
            var page = $("#recordGridTable").jqGrid('getGridParam','page');
            $("#recordGridTable").jqGrid('setGridParam',{
                postData:{},
                page:page
            }).trigger("reloadGrid");
        },
        getCurrentDate: function(){
            var date = new Date();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentdate = date.getFullYear() + month + strDate
                + date.getHours() +  date.getMinutes() + date.getSeconds();
            return currentdate;

        }
    }
});

