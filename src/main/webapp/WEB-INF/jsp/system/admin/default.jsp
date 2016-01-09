<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="top.jsp"%>
<style type="text/css">
.markdown-body {
    font-family: "Helvetica Neue",Helvetica,"Segoe UI",Arial,freesans,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
    font-size: 16px;
    line-height: 1.6;
    word-wrap: break-word;
}
.markdown-body::before {
    content: "";
    display: table;
}
.markdown-body::after {
    clear: both;
    content: "";
    display: table;
}
.markdown-body > *:first-child {
    margin-top: 0 !important;
}
.markdown-body > *:last-child {
    margin-bottom: 0 !important;
}
.markdown-body a:not([href]) {
    color: inherit;
    text-decoration: none;
}
.markdown-body .absent {
    color: #c00;
}
.markdown-body .anchor {
    display: inline-block;
    margin-left: -18px;
    padding-right: 2px;
}
.markdown-body .anchor:focus {
    outline: medium none;
}
.markdown-body h1, .markdown-body h2, .markdown-body h3, .markdown-body h4, .markdown-body h5, .markdown-body h6 {
    font-weight: bold;
    line-height: 1.4;
    margin-bottom: 16px;
    margin-top: 1em;
}
.markdown-body h1 .octicon-link, .markdown-body h2 .octicon-link, .markdown-body h3 .octicon-link, .markdown-body h4 .octicon-link, .markdown-body h5 .octicon-link, .markdown-body h6 .octicon-link {
    color: #000;
    vertical-align: middle;
    visibility: hidden;
}
.markdown-body h1:hover .anchor, .markdown-body h2:hover .anchor, .markdown-body h3:hover .anchor, .markdown-body h4:hover .anchor, .markdown-body h5:hover .anchor, .markdown-body h6:hover .anchor {
    text-decoration: none;
}
.markdown-body h1:hover .anchor .octicon-link, .markdown-body h2:hover .anchor .octicon-link, .markdown-body h3:hover .anchor .octicon-link, .markdown-body h4:hover .anchor .octicon-link, .markdown-body h5:hover .anchor .octicon-link, .markdown-body h6:hover .anchor .octicon-link {
    visibility: visible;
}
.markdown-body h1 tt, .markdown-body h1 code, .markdown-body h2 tt, .markdown-body h2 code, .markdown-body h3 tt, .markdown-body h3 code, .markdown-body h4 tt, .markdown-body h4 code, .markdown-body h5 tt, .markdown-body h5 code, .markdown-body h6 tt, .markdown-body h6 code {
    font-size: inherit;
}
.markdown-body h1 {
    border-bottom: 1px solid #eee;
    font-size: 2.25em;
    line-height: 1.2;
    padding-bottom: 0.3em;
}
.markdown-body h1 .anchor {
    line-height: 1;
}
.markdown-body h2 {
    border-bottom: 1px solid #eee;
    font-size: 1.75em;
    line-height: 1.225;
    padding-bottom: 0.3em;
}
.markdown-body h2 .anchor {
    line-height: 1;
}
.markdown-body h3 {
    font-size: 1.5em;
    line-height: 1.43;
}
.markdown-body h3 .anchor {
    line-height: 1.2;
}
.markdown-body h4 {
    font-size: 1.25em;
}
.markdown-body h4 .anchor {
    line-height: 1.2;
}
.markdown-body h5 {
    font-size: 1em;
}
.markdown-body h5 .anchor {
    line-height: 1.1;
}
.markdown-body h6 {
    color: #777;
    font-size: 1em;
}
.markdown-body h6 .anchor {
    line-height: 1.1;
}
.markdown-body p, .markdown-body blockquote, .markdown-body ul, .markdown-body ol, .markdown-body dl, .markdown-body table, .markdown-body pre {
    margin-bottom: 16px;
    margin-top: 0;
}
.markdown-body hr {
    background-color: #e7e7e7;
    border: 0 none;
    height: 4px;
    margin: 16px 0;
    padding: 0;
}
.markdown-body ul, .markdown-body ol {
    padding-left: 2em;
}
.markdown-body ul.no-list, .markdown-body ol.no-list {
    list-style-type: none;
    padding: 0;
}
.markdown-body ul ul, .markdown-body ul ol, .markdown-body ol ol, .markdown-body ol ul {
    margin-bottom: 0;
    margin-top: 0;
}
.markdown-body li > p {
    margin-top: 16px;
}
.markdown-body dl {
    padding: 0;
}
.markdown-body dl dt {
    font-size: 1em;
    font-style: italic;
    font-weight: bold;
    margin-top: 16px;
    padding: 0;
}
.markdown-body dl dd {
    margin-bottom: 16px;
    padding: 0 16px;
}
.markdown-body blockquote {
    border-left: 4px solid #ddd;
    color: #777;
    padding: 0 15px;
}
.markdown-body blockquote > *:first-child {
    margin-top: 0;
}
.markdown-body blockquote > *:last-child {
    margin-bottom: 0;
}
.markdown-body table {
    display: block;
    overflow: auto;
    width: 100%;
    word-break: keep-all;
}
.markdown-body table th {
    font-weight: bold;
}
.markdown-body table th, .markdown-body table td {
    border: 1px solid #ddd;
    padding: 6px 13px;
}
.markdown-body table tr {
    background-color: #fff;
    border-top: 1px solid #ccc;
}
.markdown-body table tr:nth-child(2n) {
    background-color: #f8f8f8;
}
.markdown-body img {
    background-color: #fff;
    box-sizing: content-box;
    max-width: 100%;
}
.markdown-body img[align="right"] {
    padding-left: 20px;
}
.markdown-body img[align="left"] {
    padding-right: 20px;
}
.markdown-body .emoji {
    max-width: none;
}
.markdown-body span.frame {
    display: block;
    overflow: hidden;
}
.markdown-body span.frame > span {
    border: 1px solid #ddd;
    display: block;
    float: left;
    margin: 13px 0 0;
    overflow: hidden;
    padding: 7px;
    width: auto;
}
.markdown-body span.frame span img {
    display: block;
    float: left;
}
.markdown-body span.frame span span {
    clear: both;
    color: #333;
    display: block;
    padding: 5px 0 0;
}
.markdown-body span.align-center {
    clear: both;
    display: block;
    overflow: hidden;
}
.markdown-body span.align-center > span {
    display: block;
    margin: 13px auto 0;
    overflow: hidden;
    text-align: center;
}
.markdown-body span.align-center span img {
    margin: 0 auto;
    text-align: center;
}
.markdown-body span.align-right {
    clear: both;
    display: block;
    overflow: hidden;
}
.markdown-body span.align-right > span {
    display: block;
    margin: 13px 0 0;
    overflow: hidden;
    text-align: right;
}
.markdown-body span.align-right span img {
    margin: 0;
    text-align: right;
}
.markdown-body span.float-left {
    display: block;
    float: left;
    margin-right: 13px;
    overflow: hidden;
}
.markdown-body span.float-left span {
    margin: 13px 0 0;
}
.markdown-body span.float-right {
    display: block;
    float: right;
    margin-left: 13px;
    overflow: hidden;
}
.markdown-body span.float-right > span {
    display: block;
    margin: 13px auto 0;
    overflow: hidden;
    text-align: right;
}
.markdown-body code, .markdown-body tt {
    background-color: rgba(0, 0, 0, 0.04);
    border-radius: 3px;
    font-size: 85%;
    margin: 0;
    padding: 0.2em 0;
}
.markdown-body code::before, .markdown-body code::after, .markdown-body tt::before, .markdown-body tt::after {
    content: " ";
    letter-spacing: -0.2em;
}
.markdown-body code br, .markdown-body tt br {
    display: none;
}
.markdown-body del code {
    text-decoration: inherit;
}
.markdown-body pre > code {
    background: transparent none repeat scroll 0 0;
    border: 0 none;
    font-size: 100%;
    margin: 0;
    padding: 0;
    white-space: pre;
    word-break: normal;
}
.markdown-body .highlight {
    margin-bottom: 16px;
}
.markdown-body .highlight pre, .markdown-body pre {
    background-color: #f7f7f7;
    border-radius: 3px;
    font-size: 85%;
    line-height: 1.45;
    overflow: auto;
    padding: 16px;
}
.markdown-body .highlight pre {
    margin-bottom: 0;
    word-break: normal;
}
.markdown-body pre {
    word-wrap: normal;
}
.markdown-body pre code, .markdown-body pre tt {
    background-color: transparent;
    border: 0 none;
    display: inline;
    line-height: inherit;
    margin: 0;
    max-width: initial;
    overflow: initial;
    padding: 0;
    word-wrap: normal;
}
.markdown-body pre code::before, .markdown-body pre code::after, .markdown-body pre tt::before, .markdown-body pre tt::after {
    content: normal;
}
.markdown-body kbd {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    background-color: #fcfcfc;
    border-color: #ccc #ccc #bbb;
    border-image: none;
    border-radius: 3px;
    border-style: solid;
    border-width: 1px;
    box-shadow: 0 -1px 0 #bbb inset;
    color: #555;
    display: inline-block;
    font-size: 11px;
    line-height: 10px;
    padding: 3px 5px;
    vertical-align: middle;
}
</style>
</head>
<body>
	<div class="container-fluid" id="main-container">
			<div id="page-content" class="clearfix">
				<div class="page-header position-relative">
					<h1>
						后台首页 <small><i class="icon-double-angle-right"></i> </small>
					</h1>
				</div>
				<div class="row-fluid">
					<div class="space-6"></div>
					<div class="row-fluid">
						<%
							String strXML = "";
							strXML += "<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'>";
							strXML += "<set name='1' value='462' color='AFD8F8'/>";
							strXML += "<set name='2' value='857' color='F6BD0F'/>";
							strXML += "<set name='3' value='671' color='8BBA00'/>";
							strXML += "<set name='4' value='494' color='FF8E46'/>";
							strXML += "<set name='5' value='761' color='008E8E'/>";
							strXML += "<set name='6' value='960' color='D64646'/>";
							strXML += "<set name='7' value='629' color='8E468E'/>";
							strXML += "<set name='8' value='622' color='588526'/>";
							strXML += "<set name='9' value='376' color='B3AA00'/>";
							strXML += "<set name='10' value='494' color='008ED6'/>";
							strXML += "<set name='11' value='761' color='9D080D'/>";
							strXML += "<set name='12' value='960' color='A186BE'/>";
							strXML += "</graph>";
							//Create the chart - Column 3D Chart with data from strXML variable using dataXML method
						%>
						<div class="blob instapaper_body" id="readme" align="center" style="float: center;">
							<article itemprop="mainContentOfPage" class="markdown-body entry-content"><blockquote>
						<h5><a aria-hidden="true" href="#dont-forget-your-initiativeso-as-to-achieve-your-goal" class="anchor" id="user-content-dont-forget-your-initiativeso-as-to-achieve-your-goal"><span class="octicon octicon-link"></span></a>　Don't forget your initiative,so as to achieve your goal.</h5>
						<h5><a aria-hidden="true" href="#dont-forget-your-initiativeso-as-to-achieve-your-goal" class="anchor" id="user-content-dont-forget-your-initiativeso-as-to-achieve-your-goal"><span class="octicon octicon-link"></span></a>　勿忘初心，方得始终。</h5>
						</blockquote>
						
						<p><a target="_blank" href="https://camo.githubusercontent.com/ae02b96705d1d8454d4266d7da739a629c03a823/687474703a2f2f61332e717069632e636e2f7073623f2f563133514755797831356876714b2f7342312a46437441705755503141774d3937463474566778314a68304a653078412a38387a6d586f796f55212f622f64453041414141414141414126626f3d3941455a4151414141414143457473212672663d7669657765725f34"><img style="max-width:100%;" data-canonical-src="http://a3.qpic.cn/psb?/V13QGUyx15hvqK/sB1*FCtApWUP1AwM97F4tVgx1Jh0Je0xA*88zmXoyoU!/b/dE0AAAAAAAAA&amp;bo=9AEZAQAAAAACEts!&amp;rf=viewer_4" alt="Beginner's mind is always not forget." src="https://camo.githubusercontent.com/ae02b96705d1d8454d4266d7da739a629c03a823/687474703a2f2f61332e717069632e636e2f7073623f2f563133514755797831356876714b2f7342312a46437441705755503141774d3937463474566778314a68304a653078412a38387a6d586f796f55212f622f64453041414141414141414126626f3d3941455a4151414141414143457473212672663d7669657765725f34"></a></p>
						</article>
						</div>
						<!-- 柱状图 -->
						<%-- <div class="center">
							<div style="float:left;">
								<table border="0" width="50%">
									<tr>
										<td><jsp:include
												page="../../FusionChartsHTMLRenderer.jsp" flush="true">
												<jsp:param name="chartSWF" value="static/FusionCharts/Area2D.swf" />
												<jsp:param name="strURL" value="" />
												<jsp:param name="strXML" value="<%=strXML%>" />
												<jsp:param name="chartId" value="myNext" />
												<jsp:param name="chartWidth" value="500" />
												<jsp:param name="chartHeight" value="300" />
												<jsp:param name="debugMode" value="false" />
											</jsp:include></td>
									</tr>
								</table>
							</div>
							<div style="float:right;">
								<table border="0" width="50%">
									<tr>
										<td><jsp:include
												page="../../FusionChartsHTMLRenderer.jsp" flush="true">
												<jsp:param name="chartSWF" value="static/FusionCharts/Column3D.swf" />
												<jsp:param name="strURL" value="" />
												<jsp:param name="strXML" value="<%=strXML%>" />
												<jsp:param name="chartId" value="myNext" />
												<jsp:param name="chartWidth" value="500" />
												<jsp:param name="chartHeight" value="300" />
												<jsp:param name="debugMode" value="false" />
											</jsp:include></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="center">
							<div style="float:left;">
								<table border="0" width="50%">
									<tr>
										<td><jsp:include
												page="../../FusionChartsHTMLRenderer.jsp" flush="true">
												<jsp:param name="chartSWF" value="static/FusionCharts/Bar2D.swf" />
												<jsp:param name="strURL" value="" />
												<jsp:param name="strXML" value="<%=strXML%>" />
												<jsp:param name="chartId" value="myNext" />
												<jsp:param name="chartWidth" value="500" />
												<jsp:param name="chartHeight" value="300" />
												<jsp:param name="debugMode" value="false" />
											</jsp:include></td>
									</tr>
								</table>
							</div>
							<div style="float:right;">
								<table border="0" width="50%">
									<tr>
										<td><jsp:include
												page="../../FusionChartsHTMLRenderer.jsp" flush="true">
												<jsp:param name="chartSWF" value="static/FusionCharts/Column2D.swf" />
												<jsp:param name="strURL" value="" />
												<jsp:param name="strXML" value="<%=strXML%>" />
												<jsp:param name="chartId" value="myNext" />
												<jsp:param name="chartWidth" value="500" />
												<jsp:param name="chartHeight" value="300" />
												<jsp:param name="debugMode" value="false" />
											</jsp:include></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="center">
							<div style="float:left;">
								<table border="0" width="50%">
									<tr>
										<td><jsp:include
												page="../../FusionChartsHTMLRenderer.jsp" flush="true">
												<jsp:param name="chartSWF"
													value="static/FusionCharts/Doughnut2D.swf" />
												<jsp:param name="strURL" value="" />
												<jsp:param name="strXML" value="<%=strXML%>" />
												<jsp:param name="chartId" value="myNext" />
												<jsp:param name="chartWidth" value="500" />
												<jsp:param name="chartHeight" value="300" />
												<jsp:param name="debugMode" value="false" />
											</jsp:include></td>
									</tr>
								</table>
							</div>
							<div style="float:right;">
								<table border="0" width="50%">
									<tr>
										<td><jsp:include
												page="../../FusionChartsHTMLRenderer.jsp" flush="true">
												<jsp:param name="chartSWF"
													value="static/FusionCharts/Doughnut3D.swf" />
												<jsp:param name="strURL" value="" />
												<jsp:param name="strXML" value="<%=strXML%>" />
												<jsp:param name="chartId" value="myNext" />
												<jsp:param name="chartWidth" value="500" />
												<jsp:param name="chartHeight" value="300" />
												<jsp:param name="debugMode" value="false" />
											</jsp:include></td>
									</tr>
								</table>
							</div>
						</div> --%>
						<%-- <div class="center">
							<div style="float:left;">
								<table border="0" width="50%">
									<tr>
										<td><jsp:include
												page="../../FusionChartsHTMLRenderer.jsp" flush="true">
												<jsp:param name="chartSWF" value="static/FusionCharts/SSGrid.swf" />
												<jsp:param name="strURL" value="" />
												<jsp:param name="strXML" value="<%=strXML%>" />
												<jsp:param name="chartId" value="myNext" />
												<jsp:param name="chartWidth" value="500" />
												<jsp:param name="chartHeight" value="300" />
												<jsp:param name="debugMode" value="false" />
											</jsp:include></td>
									</tr>
								</table>
							</div>
							<div style="float:right;">
								<table border="0" width="50%">
									<tr>
										<td><jsp:include
												page="../../FusionChartsHTMLRenderer.jsp" flush="true">
												<jsp:param name="chartSWF" value="static/FusionCharts/Pie3D.swf" />
												<jsp:param name="strURL" value="" />
												<jsp:param name="strXML" value="<%=strXML%>" />
												<jsp:param name="chartId" value="myNext" />
												<jsp:param name="chartWidth" value="500" />
												<jsp:param name="chartHeight" value="300" />
												<jsp:param name="debugMode" value="false" />
											</jsp:include></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="center">
							<div style="float:left;">
								<table border="0" width="50%">
									<tr>
										<td><jsp:include
												page="../../FusionChartsHTMLRenderer.jsp" flush="true">
												<jsp:param name="chartSWF" value="static/FusionCharts/Pie2D.swf" />
												<jsp:param name="strURL" value="" />
												<jsp:param name="strXML" value="<%=strXML%>" />
												<jsp:param name="chartId" value="myNext" />
												<jsp:param name="chartWidth" value="500" />
												<jsp:param name="chartHeight" value="300" />
												<jsp:param name="debugMode" value="false" />
											</jsp:include></td>
									</tr>
								</table>
							</div>
							<div style="float:right;">
								<table border="0" width="50%">
									<tr>
										<td><jsp:include
												page="../../FusionChartsHTMLRenderer.jsp" flush="true">
												<jsp:param name="chartSWF" value="static/FusionCharts/Line.swf" />
												<jsp:param name="strURL" value="" />
												<jsp:param name="strXML" value="<%=strXML%>" />
												<jsp:param name="chartId" value="myNext" />
												<jsp:param name="chartWidth" value="500" />
												<jsp:param name="chartHeight" value="300" />
												<jsp:param name="debugMode" value="false" />
											</jsp:include></td>
									</tr>
								</table>
							</div>
						</div> --%>
					</div>
				</div>
				<!--/row-->
		</div>
		<!-- #main-content -->
	</div>
	<!--/.fluid-container#main-container-->
	<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse"> <i
		class="icon-double-angle-up icon-only"></i>
	</a>
	<script type="text/javascript">
		window.jQuery|| document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");
	</script>
	<script type="text/javascript">
		$(top.hangge());
	</script>
</body>
</html>
