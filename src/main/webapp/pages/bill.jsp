<%@ include file="index.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	
	<title>Bill</title>
	
	<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/style.css' />
	<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/print.css' media="print" />
	<script type='text/javascript' src='${pageContext.request.contextPath}/scripts/jqueryBill.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/scripts/BillJS.js'></script>

</head>

<body>

	<div id="page-wrap">

		<textarea id="header">BILL</textarea>
		
		<div id="identity">
		
            <textarea id="address"></textarea>

            <div id="logo">

              <div id="logoctr">
                <a href="javascript:;" id="change-logo" title="Change logo">Change Logo</a>
                <a href="javascript:;" id="save-logo" title="Save changes">Save</a>
                |
                <a href="javascript:;" id="delete-logo" title="Delete logo">Delete Logo</a>
                <a href="javascript:;" id="cancel-logo" title="Cancel changes">Cancel</a>
              </div>

              <div id="logohelp">
                <input id="imageloc" type="text" size="50" value="" /><br />
                (max width: 540px, max height: 100px)
              </div>
     
            </div>
		
		</div>
		
		<div style="clear:both"></div>
		
		<div id="customer">

            <textarea id="customer-title"></textarea>

            <table id="meta">
		<tr>
                    <td class="meta-head">Client</td>
                    <td><textarea></textarea></td>
                </tr>
                <tr>
                    <td class="meta-head">Invoice #</td>
                    <td><textarea></textarea></td>
                </tr>
                <tr>

                    <td class="meta-head">Date</td>
                    <td><textarea id="date"></textarea></td>
                </tr>
                <tr>
                    <td class="meta-head">Amount Due</td>
                    <td><div class="due">$875.00</div></td>
                </tr>

            </table>
		
		</div>
		
		<table id="items">
		
		  <tr>
		      <th>Item</th>
		      <th>Description</th>
		      <th>Unit Cost</th>
		      <th>Quantity</th>
		      <th>Price</th>
		  </tr>
		  
		  <tr class="item-row">
		      <td class="item-name"><div class="delete-wpr"><textarea></textarea><a class="delete" href="javascript:;" title="Remove row">X</a></div></td>
		      <td class="description"><textarea></textarea></td>
		      <td><textarea class="cost"></textarea></td>
		      <td><textarea class="qty"></textarea></td>
		      <td><span class="price"></span></td>
		  </tr>
		  
				  
		  <tr id="hiderow">
		    <td colspan="5"><a id="addrow" href="javascript:;" title="Add a row">Add a row</a></td>
		  </tr>
		  
		 
		  <tr>

		      <td colspan="2" class="blank"> </td>
		      <td colspan="2" class="total-line">Total</td>
		      <td class="total-value"><div id="total"></div></td>
		  </tr>
		  <tr>
		      <td colspan="2" class="blank"> </td>
		      <td colspan="2" class="total-line">Amount Paid</td>

		      <td class="total-value"><textarea id="paid"></textarea></td>
		  </tr>
		  <tr>
		      <td colspan="2" class="blank"> </td>
		      <td colspan="2" class="total-line balance"></td>
		      <td class="total-value balance"><div class="due"></div></td>
		  </tr>
		
		</table>
		
		
	</div>
	
</body>

</html>