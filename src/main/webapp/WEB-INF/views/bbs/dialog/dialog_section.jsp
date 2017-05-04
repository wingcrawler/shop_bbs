<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="modal fade" id="modal-menu">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">${t.t_edit_section_1 }</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<input type="hidden" name="id" value="" />
								<label for="menuName" class="control-label">${t.t_section_1 }</label>
								<input type="text" name="name" value="" class="form-control" id="menuName" />
							</div>	
							<div class="form-group">
								<label for="menuStatus" class="control-label">${t.t_status }</label> 
								<select name="menuStatus" class="form-control" id="menuStatus">
									<option value="1">${t.t_on }</option>
									<option value="0">${t.t_off }</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">${t.b_close }</button>
					<button type="button" class="btn btn-info" data-dismiss="modal" onclick="saveMenu()">${t.b_submit }</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="modal-submenu">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">${t.t_edit_section_2 }</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<input type="hidden" name="id" value="" />
								<label for="menuName" class="control-label">${t.t_section_2 }</label>
								<input type="text" name="name" value="" class="form-control" id="menuName" />
							</div>	
							<div class="form-group">
								<label for="subMenuStatus" class="control-label">${t.t_status }</label> 
								<select name="subMenuStatus" class="form-control" id="subMenuStatus">
									<option value="1" selected>${t.t_on }</option>
									<option value="0">${t.t_off }</option>
								</select>
							</div>	
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">${t.b_close }</button>
					<button type="button" class="btn btn-info" data-dismiss="modal" onclick="saveSubMenu()">${t.b_submit }</button>
				</div>
			</div>
		</div>
	</div>