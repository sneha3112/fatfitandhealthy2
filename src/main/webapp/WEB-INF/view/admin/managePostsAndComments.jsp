<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page isELIgnored="false" %>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/admin/table.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap-switch.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/datatables/dataTables.bootstrap.css" />
<jsp:include page="upperheader.jsp"></jsp:include>
<script src="${pageContext.request.contextPath }/resources/js/admin/table.js"></script>
<%-- <script src="${pageContext.request.contextPath }/resources/js/bootstrap-switch.min.js"></script> --%>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap-switch.js"></script>
<script src="${pageContext.request.contextPath }/resources/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
<style>
<!--
.modal-footer {
    
    padding: 15px !important;
    text-align: right !important;
    margin:0 !important;
}
-->
</style>
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
        <% } %>
        <li class="treeview">
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
        <li class="active treeview">
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
        Manage Posts And Comments
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-user"></i> Home</a></li>
        <li class="active">Manage Posts And Comments</li>
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
						<div class="box">
            <div class="box-header">
              <h3 class="box-title">Posts</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Image</th>
                  <th>Username</th>
                  <th>Post</th>
                  <th>Comments</th>
                  <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                
                <c:forEach items="${Post}" var="Post">
				<tr id=${Post[0] }>
					<td><img src="${pageContext.request.contextPath}/resources/image/user/${Post[8] }" class="img-circle" alt="Cinque Terre" width="30" height="30"></td>
					<td>${Post[6] } ${Post[7]}</td>
					<td>${Post[2] }</td>
					<td><a href="${pageContext.request.contextPath}/admin/commentdetail/${Post[0]}">See Comments</a></td>
					<td><a href="javascript:;" onclick="d(${Post[0]});"><i class="fa fa-remove" aria-hidden="true"></i></a></td>
				</tr>
			</c:forEach>
                
                </tbody>
                <tfoot>
                <tr>
                  <th>Image</th>
                  <th>Username</th>
                  <th>Post</th>
                  <th>Comments</th>
                  <th>Delete</th>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
						
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

function d(id) {

	var modal="<div class='modal'>";
	modal+="<div class='modal-dialog'>";
	modal+="<div class='modal-content'>";
	modal+="<div class='modal-header'>";
	modal+="<button type='button' class='close' data-dismiss='modal' aria-label='Close'>";
	modal+="<span aria-hidden='true'>&times;</span></button>";
	modal+="<h4 class='modal-title'>Delete Post</h4>";
	modal+="</div>";
	modal+="<div class='modal-body'>";
	modal+="<p>Are you sure you want to delete this post ?</p>";
	//modal+="<div class='img-push'><textarea id='eptext' class='form-control' rows='3' placeholder='Update Post'>"+posttext+"</textarea></div>";
	modal+="</div>";
	modal+="<div class='modal-footer'>";
	modal+="<button type='button' class='btn btn-primary pull-left' data-dismiss='modal'>No</button>";
	modal+="<button type='button' class='btn bg-maroon ep'>Yes</button>";
	modal+="</div>";
	modal+="</div>";
	modal+="<!-- /.modal-content -->";
	modal+="</div>";
	modal+="<!-- /.modal-dialog -->";
	modal+="</div>";
	//modal+="<!-- /.modal -->";
	var confirmmodal=$(modal);
	confirmmodal.modal('show');
	
	/*  */

	confirmmodal.find('.ep').click(function(event) {

		$.ajax({
			type : "POST",
			//contentType : "application/json",
			url : "${pageContext.request.contextPath }/deletepost",
			data : {"postid":id,},
			//dataType : 'json',
			timeout : 100000,
			success : function(data) {
				$("#"+id).remove();
			}
		});
	      confirmmodal.modal('toggle');
	      
	    });
}

$(function () {
    $("#example1").DataTable();
    $('#example2').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false
    });
  });
</script>