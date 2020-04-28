<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>五、 业务员年度销售额柱子图</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/easyui/themes/metro/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/wu.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/echarts/js/echarts.min.js"></script>

</head>
<body>
<!-- 查询条件开始 -->
<div class="easyui-panel" data-options="title:'查询条件',iconCls:'icon-search',width:'100%'"
     style="margin-bottom: 3px;text-align: center;padding: 20px">
    <select class="easyui-combobox" id="search_year" name="year"
            data-options="label:'请选择处份:',labelAlign:'right',labelPosition:'left',width:'50%',editable:false">
        <c:forEach begin="2017" end="2030" var="sn">
            <option value="${sn }">${sn }</option>
        </c:forEach>
    </select>
    <a href="javascript:void(0)" class="easyui-linkbutton" id="doSearch" data-options="iconCls:'icon-search'">查询</a>
</div>
<div id="container" style="height: 400px"></div>

<!-- 查询条件结束 -->


<script type="text/javascript">

    $(function () {
        var date = new Date();
        var year = date.getFullYear();
        $("#search_year").combobox({value: year});
    })


    $("#doSearch").click(function () {
        var year = $("#search_year").val();
        $.post("${pageContext.request.contextPath}/stat/loadOpernameYearGradeStat", {year: year}, function (data) {
            var dom = document.getElementById("container");
            var myChart = echarts.init(dom);
            var app = {};
            option = null;
            app.title = '坐标轴刻度与标签对齐';

            option = {
                title: {
                    text: '业务员年度销售额柱子图',
                    subtext: '销售统计',
                    x: 'center'
                },
                color: ['#3398DB'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: data.name,
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '开单量',
                        type: 'bar',
                        barWidth: '60%',
                        data: data.values
                    }
                ]
            };

            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        })
    })
    $("#doSearch").click();
</script>

</body>
</html>