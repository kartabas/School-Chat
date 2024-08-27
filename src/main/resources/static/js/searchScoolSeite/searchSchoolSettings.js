

$(document).ready(function () {
        let searchText = $("#searchSchool").val();
        if(searchText != ""){
            $("#schoolForm").css({
                display: "block"
            });
        }else{
            $("#schoolForm").css({
                display: "none"
            });

        }


});









