<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="span14">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/index.action">
				<img src="${pageContext.request.contextPath}/images/logo.jpg" alt="Doremi"/>
			</a>
		</div>
	</div>
<div class="span10 last">
	<div class="topNav clearfix">
		<ul>
			<s:if test="#session.existUser == null">
			<li id="headerLogin" class="headerLogin" style="display: list-item;">
				<a href="${ pageContext.request.contextPath }/user_loginPage.action">登录</a>|</li>
			<li id="headerRegister" class="headerRegister"
				style="display: list-item;"><a href="${ pageContext.request.contextPath }/user_registPage.action">注册</a>|
			</li>
			</s:if>
			<s:else>
			<li id="headerLogin" class="headerLogin" style="display: list-item;">
				<s:property value="#session.existUser.username"/>
				|</li>
			<li id="headerLogin" class="headerLogin" style="display: list-item;">
				<a href="${ pageContext.request.contextPath }/order_findByUid.action?page=1">我的订单</a>
			|</li>
			<li id="headerRegister" class="headerRegister"
				style="display: list-item;"><a href="${ pageContext.request.contextPath }/user_quit.action">退出</a>|
			</li>
			</s:else>
			<li><a>关于我们</a></li>
		</ul>
	</div>
	<div class="cart">
		<a href="${ pageContext.request.contextPath }/cart_myCart.action">购物车   <span></span></a>
	</div>
	<div class="find">
		<form action="${pageContext.request.contextPath}/product_search.action">
			<input type="text" name="searchText" />
			<input type="hidden" name="page" value="1" >
			<input type="submit" value="搜索">
		</form>
	</div>
</div>
<div class="span24" background="${pageContext.request.contextPath}/images/lable1.jpg">
	<ul class="mainNav">
		<li><strong><a href="${pageContext.request.contextPath }/index.action">首页</a> |</strong></li>
		<s:iterator var="c" value="#session.cList">
		<li><strong><a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a> |</strong></li>
	    </s:iterator>

	</ul>
</div>
    
