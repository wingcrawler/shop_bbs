<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${t.lang =='en' }">
	<!-- footer -->
	<div class="footer">
		<!-- container -->
		<div class="container">
			<div class="col-md-3 stores-grid">
				<div class="stores">
					<h3>Our Stores</h3>
					<ul>
						<li>Feel free to visit our stores or contact us.</li>
						<li>1401 South Grand Avenue</li>
						<li>Los Angeles, CA 90015</li>
						<li>(213) 748-2411</li>

						<span>
							<li class="drive">100 Fairview Drive</li>
							<li>Franklin, VA 23851</li>
							<li>(757) 569-6100</li>
						</span>
					</ul>
				</div>
				<div class="social-icons white-icons">
					<ul>
						<li><a href="#" class="facebook"></a></li>
						<li><a href="#" class="twitter"></a></li>
						<li><a href="#" class="chrome"></a></li>
						<li><a href="#" class="vimeo"></a></li>
						<li><a href="#" class="rss"></a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-3 blog">
				<h3>Blog posts</h3>
				<a href="#">Justin Bieber confirmed that he is gay.</a>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Donec sed auctor elit.</p>
				<a href="#">New sexy sport clothes are here!</a>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Donec sed auctor elit.</p>
				<a href="#">Summer sales are coming!</a>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Donec sed auctor elit.</p>
			</div>
			<div class="col-md-3 support">
				<h3>Support</h3>
				<div class="support-grids">
					<div class="support-left">
						<ul>
							<li><a href="#">Terms & Conditions</a></li>
							<li><a href="#">FAQ</a></li>
							<li><a href="#">Payment</a></li>
							<li><a href="#">Refunds</a></li>
							<li><a href="#">Track Order</a></li>
							<li><a href="#">Services</a></li>
							<li><a href="#">Privacy & Security</a></li>
							<li><a href="#">Careers</a></li>
							<li><a href="#">Press</a></li>
							<li><a href="#">Corporate Information</a></li>
						</ul>
					</div>
					<div class="support-left support-right">
						<ul>
							<li><a href="#">Sizing</a></li>
							<li><a href="#">Ordering</a></li>
							<li><a href="#">Shipping</a></li>
							<li><a href="#">Return Policy</a></li>
							<li><a href="#">Affiliates</a></li>
							<li><a href="#">Find A Store </a></li>
							<li><a href="#">Site Map</a></li>
							<li><a href="#">Sign Up & Save</a></li>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="col-md-3 contact">
				<h3>Contact us</h3>
				<form>
					<input type="text" value="your e-mail..."
						onFocus="this.value = '';"
						onBlur="if (this.value == '') {this.value = 'your e-mail...';}"
						required="">
					<textarea value="your text...:"
						onFocus="if(this.value == 'your text...') this.value='';"
						onBlur="if(this.value == '') this.value='your text...';">your text...</textarea>
					<input type="submit" value="Send MESSAGE">
				</form>
			</div>
			<div class="clearfix"></div>
			<div class="copyright">
				<p>Copyright &copy; 2015.Company name All rights reserved.</p>
			</div>
			<!-- // container -->
		</div>
	</div>
	<!-- //footer -->
</c:if>
<c:if test="${t.lang =='zh' }">
	<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
	<!-- footer -->
	<div class="footer">
		<!-- container -->
		<div class="container">
			<div class="col-md-3 stores-grid">
				<div class="stores">
					<h3>我们的商店</h3>
					<ul>
						<li>随时访问我们的商店或与我们联系</li>
						<li>南大街1401号</li>
						<li>洛杉矶, CA 90015</li>
						<li>(213) 748-2411</li>

						<span>
							<li class="drive">100 Fairview Drive</li>
							<li>Franklin, VA 23851</li>
							<li>(757) 569-6100</li>
						</span>
					</ul>
				</div>
				<div class="social-icons white-icons">
					<ul>
						<li><a href="#" class="facebook"></a></li>
						<li><a href="#" class="twitter"></a></li>
						<li><a href="#" class="chrome"></a></li>
						<li><a href="#" class="vimeo"></a></li>
						<li><a href="#" class="rss"></a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-3 blog">
				<h3>博客帖子</h3>
				<a href="#">贾斯汀·比伯确认他是同性恋。</a>
				<p>乱数假文悲哀坐特，乱数假文悲哀坐特精英。据说SED卖主 精英.</p>
				<a href="#">新的性感的运动衣服在这里!</a>
				<p>乱数假文悲哀坐特，乱数假文悲哀坐特精英。据说SED卖主 精英.</p>
				<a href="#">夏季销售来了!</a>
				<p>乱数假文悲哀坐特，乱数假文悲哀坐特精英。据说SED卖主 精英.</p>
			</div>
			<div class="col-md-3 support">
				<h3>支持</h3>
				<div class="support-grids">
					<div class="support-left">
						<ul>
							<li><a href="#">条件与付款</a></li>
							<li><a href="#">常问问题</a></li>
							<li><a href="#">服务</a></li>
							<li><a href="#">隐私与安全</a></li>
							<li><a href="#">公司信息</a></li>
						</ul>
					</div>
					<div class="support-left support-right" style="">
						<ul>
							<li><a href="#">网站地图</a></li>
							<li><a href="#">注册并保存</a></li>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="col-md-3 contact">
				<h3>联系我们</h3>
				<form>
					<input type="text" value="你的邮箱..." onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = '你的邮箱...';}"
						required="">
					<textarea value="你的留言...:"
						onfocus="if(this.value == '你的留言...') this.value='';"
						onblur="if(this.value == '') this.value='你的留言...';">你的留言...</textarea>
					<input type="submit" value="发信息">
				</form>
			</div>
			<div class="clearfix"></div>
			<div class="copyright">
				<p>版权所有 © 2015公司名称版权所有。</p>
				<p></p>
			</div>
			<!-- // container -->
		</div>
		<!-- //footer -->
</c:if>


