<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/seckill/css/bootstrap.min.css">
	<script type="text/javascript" src="/seckill/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/seckill/js/bootstrap.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
	<script src="/seckill/js/seckill.js"></script>
	<title>秒杀详情页</title>
</head>
<body>
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="pannel-heading">
				<h1>${seckill.name}</h1>
			</div>
			<div class="panel-body">
				<h2 class="text-danger">
					<span id="seckill-box"></span>
				</h2>
			</div>
		</div>
	</div>
	<div id="killPhoneModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">秒杀电话：</h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killPhone" id="killPhoneKey" placeholder="填写手机号^o^" class="form-control" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<span id="killPhoneMessage"></span>
					<button type="button" id="killPhoneBtn" class="btn btn-success">Submit</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		seckill.detail.init({
			seckillId:${seckill.seckillId},
			startTime:${seckill.startTime.time},
			endTime:${seckill.endTime.time}
		});
	});
</script>
</body>
</html>
