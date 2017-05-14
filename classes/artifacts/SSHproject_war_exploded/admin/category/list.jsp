<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addUser(){
				window.location.href = "${pageContext.request.contextPath}/admin/category/add.jsp";
			}
		</script>
		
		
		
<script language="javascript"> 
    	function userDelete(u_id) { 
    	if (confirm("是否删除该分类？")) { 
					// 1.创建异步对象
					var xhr = createXmlHttp();
					// 2.设置监听
					xhr.onreadystatechange = function(){
						if(xhr.readyState == 4){
							if(xhr.status == 200){
								div1.innerHTML = xhr.responseText;
							}
						}
					}
					// 3.打开连接
					xhr.open("GET","${pageContext.request.contextPath}/adminCategory_delete.action?cid="+u_id,true);
					// 4.发送
					xhr.send(null);
					location.reload();
				}else{
					return false;
				}
			}
    	function createXmlHttp(){
				   var xmlHttp;
				   try{ 
				        xmlHttp=new XMLHttpRequest();
				    }
				    catch (e){
					   try{
					         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
					      }
					    catch (e){
					      try{
					         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
					      }
					      catch (e){}
				    }
			   }

			return xmlHttp;
		}
		
</script>
	</HEAD>
	<body>
		<br>
		<form action="${pageContext.request.contextPath}/adminCategory_findByKey.action" method="post">
				<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<tr>
				<td class="ta_01" align="left">
					<input type="hidden" name="page" value="1" >
					<input type="text" name="key" id="key"  placeholder="请输入关键字"/>
					<input type="submit" value="搜索" style="CURSOR: hand">
				</td>
				<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="添加" class="button_add" onclick="addUser()">
								&#28155;&#21152;</button>
				</td>
				</tr>
				</table>
			</form>
		
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>一级分类列表</strong>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="right">
						<strong></strong>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" >
										序号
									</td>
									<td align="center" >
										一级分类名称
									</td>
									<td align="center">
										编辑
									</td>
									<td align="center">
										删除
									</td>
								</tr>
								<s:iterator var="c" value="cList" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<s:property value="#c.cname"/>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/adminCategory_edit.action?cid=<s:property value="#c.cid"/>">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<!--  <a href="${pageContext.request.contextPath}/adminCategory_delete.action?cid=<s:property value="#c.cid"/>">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
												-->
												
												
													<input type="button" value="删除" id="but<s:property value="#c.cid"/>" 
												onclick="userDelete(<s:property value="#c.cid"/>)" style="CURSOR: hand"/>
											</td>
										</tr>
									</s:iterator>	
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

