<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<footer id="footer" class="footer footer-custom">
  <div>&copy; Mugurel Mirica</div>
</footer>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<script>
  // Align footer to bottom if page height is smaller then the screen
  if ($(document).height() > document.body.scrollHeight) {
    $("#footer").css("position", "absolute");
    $("#footer").css("bottom", "0");
  }

  // Assign calendars
  jQuery( function() {
    jQuery(".datepicker").datepicker();
  } );

</script>

</body>
</html>