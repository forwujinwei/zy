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
        url: baseURL + 'api/resource/list',
        datatype: "json",
        colModel: [
            { label: '资源ID', name: 'id', hidden:true},
            { label: '回访日期', name: 'endRevisitDate', width: 60, formatter: function(value, options, row) {
                    return (value == null || value == undefined) ? "" : value.trim().substring(0, 10);
              }},
            { label: '状态', name: 'intentDes', width: 60},
            { label: '资源类型', name: 'typeDes', width: 60 },
            { label: '名称', name: 'name', width: 60 },
            { label: '领域', name: 'fieldDes', width: 60 },
            { label: '联系人', name: 'linkMan', width: 70 },
            { label: '电话', name: 'phoneNumber', width: 70 },
            { label: 'QQ', name: 'qqNumber', width: 70 },
            { label: '提交时间', name: 'submitDate', width: 70, formatter: function(value, options, row) {
                    return (value == null || value == undefined) ? "" : value.trim().substring(0, 10);
                }},
            { label: '确认时间', name: 'sureDate', width: 70, formatter: function(value, options, row) {
                    return (value == null || value == undefined) ? "" : value.trim().substring(0, 10);
                }},
            { label: '顾问', name: 'adviser', width: 50 }
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

        revisit:{
            resourceId:null,
            revisitDate:null,
            revisitRemark:null,
            intentCode:'00'
        }

    },

    methods: {
        add: function(){
            vm.showList = false;
            vm.title = "添加客户";
            vm.roleList = {};
            vm.resource = {deptName:null, deptId:null, status:1, roleIdList:[]};
        },
        addRecord: function () {
            var _this=this;
            /*是否选中校验*/
            var rowId=$("#jqGrid").jqGrid("getGridParam","selrow");
            if(rowId == null || rowId == '' || rowId == undefined){
                alert("请选择一条数据");
                return ;
            }
            vm.revisit.resourceId=rowId;
            $('#addRecord').modal({show:true,backdrop:false})
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
                    { label: '回访日期', name: 'revisitDate', width: 90, formatter: function(value, options,row){
                            return (value == null || value == undefined) ? "" : value.trim().substring(0, 10);
                        }},
                    { label: '内容', name: 'revisitRemark', width: 300}
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
                    $("#recordGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
                }
            });
        },
        saveRvisit: function () {
            var _this=this;
            _this.revisit.revisitDate=$("#revisitDate").val();
            alert($("#revisitDate").val());
            var url = 'api/revisit/save';
            $.ajax({
                type: "POST",
                url: _this.baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(_this.revisit),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            //vm.resource.reload();
                            $("#addRecord").on("hidden.bs.model",function(e){$(this).removeData();});
                            $('#addRecord').modal('hide');

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
        saveOrUpdate: function () {
            var _this=this;
            var url = vm.resource.id == null ? "api/resource/save" : "api/resource/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(_this.resource),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            $('#addCustomer').modal('hide');
                            _this.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
            _this.reload();
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
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{},
                page:page
            }).trigger("reloadGrid");
        }


    }
});

