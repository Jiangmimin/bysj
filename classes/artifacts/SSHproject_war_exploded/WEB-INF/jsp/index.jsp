<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Doremi购物商城</title>

<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/myfocus-2.0.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mf-pattern/mF_fancy.js"></script>
<link href="${pageContext.request.contextPath}/js/mf-pattern/mF_fancy.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
myFocus.set({id:'picBox'})
</script>

</head>
<body>

<div class="container header">
		
	<%@ include file="menu.jsp" %>
 	
   <div class="ad" id="picBox">
      <div class="loading"><img alt="图片加载中" src="${pageContext.request.contextPath}/images/loading.gif"/>
      </div>
        <div class="pic" >
          <ul>
        <li><a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=4&page=1"><img src="${pageContext.request.contextPath}/images/pic1.jpg">
        <li><a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=4&page=1"><img src="${pageContext.request.contextPath}/images/pic2.jpg"><a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=4&page=1">
        <li><img src="${pageContext.request.contextPath}/images/pic3.jpg">
          </ul>
      </div>
   </div>
</div>

<div class="container index"> 
		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
					<div class="title">
						<strong>热门商品</strong>					
		           </div>
						<ul class="tabContent" style="display: block;">
						<s:iterator var="p" value="hList">
									<li>
									<!-- <a> 标签的 target 属性规定在何处打开链接文档，_blank浏览器总在一个新打开、未命名的窗口中载入目标文档 -->								
										<a href="${pageContext.request.contextPath}/product_findByPid.action?pid=<s:property value="#p.pid"/>"  target="_blank"><img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>"  style="display: block;"></a>
									</li>
							 </s:iterator>		
						</ul>						
			</div>
		</div>
		<div class="span24">
			<div id="newProduct" class="newProduct clearfix">
					<div class="title">
						<strong>最新商品</strong>
					</div>										
						 <ul class="tabContent" style="display: block;">
						    <s:iterator var="q" value="nList">
									<li>
										<a href="${pageContext.request.contextPath}/product_findByPid.action?pid=<s:property value="#q.pid"/>" target="_blank"><img src="${pageContext.request.contextPath}/<s:property value="#q.image"/>"  style="display: block;"></a>	
									</li>
							 </s:iterator>	
						</ul>						
           </div>
		 </div>
	<s:if test="#session.existUser!= null">
	<div class="span24">
		<div id="recommender" class="newProduct clearfix">
			<div class="title">
				<strong>推荐商品</strong>
			</div>
			<ul class="tabContent" style="display: block;">
				<s:iterator var="q" value="#session.rList">
					<li>
						<a href="${pageContext.request.contextPath}/product_findByPid.action?pid=<s:property value="#q.pid"/>" target="_blank"><img src="${pageContext.request.contextPath}/<s:property value="#q.image"/>"  style="display: block;"></a>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	</s:if>

	<div class="span24">
			<div class="friendLink">
				<dl>
					<dt>新手指南&nbsp;&nbsp;</dt>
							<dd>
								<a  target="_blank">支付方式&nbsp;&nbsp;</a>
								|
							</dd>
							<dd>
								<a  target="_blank">配送方式&nbsp;&nbsp;</a>
								|
							</dd>
							<dd>
								<a  target="_blank">售后服务&nbsp;&nbsp;</a>
								|
							</dd>
							<dd>
								<a  target="_blank">购物帮助&nbsp;&nbsp;</a>
								|
							</dd>						
							<dd>
								<a  target="_blank">礼品卡&nbsp;&nbsp;</a>
								|
							</dd>
							<dd>
								<a target="_blank">银联卡&nbsp;&nbsp;</a>
								|
							</dd>
							<dd>
								<a  target="_blank">会员卡&nbsp;&nbsp;</a>
								|
							</dd>
							
					<dd class="more">
						<a >更多</a>
					</dd>
				</dl>
			</div>
		</div>
	</div>
<%@ include file="down.jsp" %>
</body>
</html>