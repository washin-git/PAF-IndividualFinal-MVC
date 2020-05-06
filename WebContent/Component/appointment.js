$(document).ready(function()
{
	$("#alertSuccess").hide();
	
	$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide(); 
	
	//Form validation-------------------
	var status = validateAppointmentForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	

	// If valid-------------------------
	
	//$("#formAppointment").submit();
	
	
	var type = ($("#hidAppointmentIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
			{
				url : "AppointmentAPI",
				type : type,
				data : $("#formAppointment").serialize(),
				dataType : "text",
				complete : function(response, status) {
					onAppointmentSaveComplete(response.responseText, status);
				}
			});
	
	
 });

	


function onAppointmentSaveComplete(response, status) {
	if (status == "success")
		{
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success")
				{
				$("#alertSuccess").text("Successfully saved.");
				$("#alertSuccess").show();
				
				$("#divAppointmentGrid").html(resultSet.data);
				}else if (resultSet.status.trim() == "error")
					{
					$("#alertError").text(resultSet.data);
					$("#alertError").show();
					
					}
		}else if (status == "error")
		{
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		}else
			{
			$("#alertError").text("UnKnown error while saving..");
			$("#alertError").show();
			}
	
		$("#hidAppointmentIDSave").val("");
		$("#formAppointment")[0].reset();
	
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidAppointmentIDSave").val($(this).closest("tr").find('#hidAppointmentIDUpdate').val());
	$("#Name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#Address").val($(this).closest("tr").find('td:eq(1)').text());
	$("#Gender").val($(this).closest("tr").find('td:eq(2)').text());
	$("#Doctor").val($(this).closest("tr").find('td:eq(3)').text());
	$("#Phone").val($(this).closest("tr").find('td:eq(4)').text());
	$("#Date").val($(this).closest("tr").find('td:eq(5)').text());
	$("#Email").val($(this).closest("tr").find('td:eq(6)').text());
	$("#Comments").val($(this).closest("tr").find('td:eq(7)').text());
});

// REMOVE=========================================================
$(document).on("click", ".btnRemove", function(event)
		{
			$.ajax(
					{
						url : "AppointmentAPI",
						type : "DELETE",
						data : "appointmentID=" + $(this).data("appointmentid"),
						dataType : "text",
						complete : function(response, status)
						{
							onAppointmentDeleteComplete(response.responseText, ststus);
						}
					});
		});

function onAppointmentDeleteComplete(response, status) {
	if (status == "success")
		{
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success")
				{
				$("#alertSuccess").text("Successfully Delete.");
				$("#alertSuccess").show();
				
				$("#divAppointmentGrid").html(resultSet.data);
				}else if (resultSet.status.trim() == "error")
					{
					$("#alertError").text(resultSet.data);
					$("#alertError").show();
					
					}
		}else if (status == "error")
		{
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
		}else
			{
			$("#alertError").text("UnKnown error while deleting..");
			$("#alertError").show();
			}
	
		$("#hidAppointmentIDSave").val("");
		$("#formAppointment")[0].reset();
	
}

//CLIENT-MODEL================================================================
function validateAppointmentForm()
{
	// name
	if ($("#Name").val().trim() == "")
	{
		return "Insert name.";
	}
	
	// address
	if ($("#Address").val().trim() == "")
	{
		return "Insert Address.";
	}	
	// gender
	if ($("#Gender").val().trim() == "")
	{
		return "Insert gender.";
	}
	
	// doctor
	if ($("#Doctor").val().trim() == "")
	{
		return "Insert doctor.";
	}
	// phone
	if ($("#Phone").val().trim() == "")
	{
		return "Insert phone.";
	}
	// date
	if ($("#Date").val().trim() == "")
	{
		return "Insert date.";
	}
	// email
	if ($("#Email").val().trim() == "")
	{
		return "Insert Email.";
	}
	
	// Comments
	if ($("#Comments").val().trim() == "")
	{
		return "Insert Comments.";
	}
	
	
	return true;
	}
