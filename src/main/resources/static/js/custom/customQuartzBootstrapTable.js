$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
});


var TableInit = function () {

    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {

        /**
         * 初始化Table
         */
        //先销毁表格
        $('#tb_departments').bootstrapTable('destroy');

        $('#tb_departments').bootstrapTable({
            url: 'getAllJob',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            dataType: "json",
            contentType: 'application/json,charset=utf-8',
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: 'ID',
                sortable: true
            }, {
                field: 'jobName',
                title: '任务名称',
                sortable: true
            }, {
                field: 'jobGroup',
                title: '任务分组',
                sortable: true
            }, {
                field: 'jobStatus',
                title: '任务状态',
                sortable: true
            }, {
                field: 'cronExpression',
                title: 'cron表达式',
                sortable: true
            }, {
                field: 'beanClass',
                title: '包名&类名',
                sortable: true
            }, {
                field: 'methodName',
                title: '方法名',
                sortable: true
            }],
            onLoadSuccess: function (res) {//可不写
                //加载成功时
                console.log("加载成功");
                console.log(res);
            }, onLoadError: function (statusCode) {
                return "加载失败了"
            }, formatLoadingMessage: function () {
                //正在加载
                return "拼命加载中...";
            }, formatNoMatches: function () {
                //没有匹配的结果
                return '无符合条件的记录';
            }
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        console.log(params);
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            pageNumber: params.pageNumber,  //页码
            jobName: $("#jobName").val()
        };
        return temp;
    };
    return oTableInit;
};
