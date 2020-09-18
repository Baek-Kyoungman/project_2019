<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JavaScript Libraries -->
<script src="/lib/jquery/jquery.min.js"></script>
<script src="/lib/jquery/jquery-migrate.min.js"></script>
<script src="/lib/popper/popper.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="/lib/easing/easing.min.js"></script>
<script src="/lib/counterup/jquery.waypoints.min.js"></script>
<script src="/lib/counterup/jquery.counterup.js"></script>
<script src="/lib/owlcarousel/owl.carousel.min.js"></script>
<script src="/lib/lightbox/js/lightbox.min.js"></script>
<script src="/lib/typed/typed.min.js"></script>
<!-- Contact Form JavaScript File -->
<script src="/contactform/contactform.js"></script>

<!-- Template Main Javascript File -->
<script src="/js/main.js"></script>
<script src="/js/jquery.min.js" type="text/javascript"></script>
<script src="/js/prism.js" type="text/javascript"></script>
<script src="/js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="/js/jquery.section-scroll.js" type="text/javascript"></script>
<script src="/js/materialize.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('body').sectionScroll({
							easing : 'easeInOutQuart',
							scrollDuration : 1200
						});
						$('body')
								.on(
										'section-reached',
										function() {
											var section_title = $('body').sectionScroll.activeSection
													.data('section-title');
											Materialize.toast('In view: '
													+ section_title, 1000);
										})
					})
</script>