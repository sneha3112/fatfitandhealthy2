<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page isELIgnored="false" %>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/admin/table.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap-switch.min.css" />
<jsp:include page="upperheader.jsp"></jsp:include>
<script src="${pageContext.request.contextPath }/resources/js/admin/table.js"></script>
<%-- <script src="${pageContext.request.contextPath }/resources/js/bootstrap-switch.min.js"></script> --%>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap-switch.js"></script>
<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${pageContext.request.contextPath }/resources/image/admin/<%=session.getAttribute("image") %>" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><%=session.getAttribute("aname") %></p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form" style="display:none;">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">MAIN NAVIGATION</li>
        <li class="treeview">
          <a href="${pageContext.request.contextPath }/admin/dashboard">
            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
            <span class="pull-right-container">

            </span>
          </a>

        </li>
        <%if(session.getAttribute("role").equals("superadmin")){ %>
        <li class="treeview">
          <a href="${pageContext.request.contextPath }/admin/manageadmin">
            <i class="fa fa-user"></i> <span>Manage Admin</span>
            <span class="pull-right-container">
<!--               <i class="fa fa-angle-left pull-right"></i> -->
            </span>
          </a>

        </li>
        <%} %>
        <li class="active treeview">
          <a href="${pageContext.request.contextPath }/admin/manageusers">
            <i class="fa fa-user"></i> <span>Manage Users</span>
            <span class="pull-right-container">
<!--               <i class="fa fa-angle-left pull-right"></i> -->
            </span>
          </a>

        </li>
		<li class="treeview">
          <a href="${pageContext.request.contextPath }/admin/managefooditems">
            <i class="fa fa-cutlery"></i> <span>Manage Food Items</span>
            <span class="pull-right-container">
<!--               <i class="fa fa-angle-left pull-right"></i> -->
            </span>
          </a>

        </li>
        <li class="treeview">
          <a href="${pageContext.request.contextPath }/admin/manageexerciseitems">
            <i class="fa fa-heartbeat"></i> <span>Manage Exercise Items</span>
            <span class="pull-right-container">
<!--               <i class="fa fa-angle-left pull-right"></i> -->
            </span>
          </a>

        </li>
        <li class="treeview">
          <a href="managePostsAndComments">
            <i class="fa fa-comments"></i> <span>Manage Posts And Comments</span>
            <span class="pull-right-container">
<!--               <i class="fa fa-angle-left pull-right"></i> -->
            </span>
          </a>

        </li>
        <!-- <li class="header">LABELS</li>
        <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li> -->
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Manage Users
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath }/admin/"><i class="fa fa-user"></i> Home</a></li>
        <li class="active">Manage Users</li>
      </ol>
    </section>

      <!-- Main row -->
      <div class="row">
<!--         Left col -->
<!--         <section class="col-lg-7 connectedSortable"> -->
          

          
<!--         </section> -->
<!--         /.Left col -->
<!--         right col (We are only adding the ID to make the widgets sortable) -->
<!--         <section class="col-lg-5 connectedSortable"> -->

                       
         

<!--         </section> -->
<!--         right col -->

			<section class="content">
			
			<div class="col-md-8 col-md-offset-2">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="pull-right">
							<div class="btn-group">
								<button type="button" class="btn btn-primary btn-filter" data-target="authorized">Authorized</button>
								<button type="button" class="btn btn-warning btn-filter" data-target="unauthorized">Unauthorized</button>
								<button type="button" class="btn btn-default btn-filter" data-target="all">All</button>
							</div>
						</div>
						<div class="table-container">
							<table class="table table-filter">
								<thead>
									<tr>
									<th>image</th>
									<th>email</th>
									<th>view</th>
									<th>permission</th>
									<th>update</th>
									<th>delete</th>
									</tr>	
								</thead>
								<tbody>
									<c:forEach items="${user}" var="user">
										<tr data-status="${user.status eq 'not varified'?'unauthorized':'authorized' }" id=${user.id }>
											<td> <img src="${pageContext.request.contextPath}/resources/image/user/${user.image }" class="img-circle" alt="Cinque Terre" width="50" height="50"></td>
											<td>${user.email }</td>
											<td><a href="${pageContext.request.contextPath}/admin/userdetail/${user.id}">details</a></td>
											
											<td><input type="checkbox" name="${user.id }"></td>
											<td><a href="${pageContext.request.contextPath}/admin/userupdate/${user.id}"><i class="fa fa-edit" aria-hidden="true"></i></a></td>
											<td><a href="javascript:;" onclick="d(${user.id},'${user.image }');"><i class="fa fa-remove" aria-hidden="true"></i></a></td>
											
										</tr>
									</c:forEach>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
				
			</div>
		</section>
      </div>
      <!-- /.row (main row) -->

    
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <jsp:include page="footer.jsp"></jsp:include>

  
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	
	<c:forEach items="${user}" var="user">
		$("[name='${user.id}']").bootstrapSwitch('state',${user.status eq 'not varified'?'false':'true' });
		$('input[name="${user.id}"]').on('switchChange.bootstrapSwitch', function(event,state) {
			 //alert(state);
			 if (state==true) {
				status='varified';
				//alert(permission);
				
			} else {
				status='not varified';
			}
			var id=$(this).parent().parent().parent().parent().attr('id');
			var row=$(this).parent().parent().parent().parent();
			 $.ajax({
					type : "POST",
					//contentType : "application/json",
					url : "${pageContext.request.contextPath }/admin/edituser",
					data : {"id":id,"status":status},
					//dataType : 'json',
					timeout : 100000,
					success : function(data) {
						console.log("SUCCESS: ", data);
						//alert("success function");
						if(status =='not varified')
							{
							
							row.attr("data-status","unauthorized");
							}
						else
							{
							row.attr("data-status","authorized");
							}

					}
				});
			});
	</c:forEach>
});

function d(id,image) {
	$.ajax({
		type : "POST",
		//contentType : "application/json",
		url : "${pageContext.request.contextPath }/admin/userdelete",
		data : {"id":id,"image":image},
		//dataType : 'json',
		timeout : 100000,
		success : function(data) {
			$("#"+id).remove();
		}
	});
}
</script>