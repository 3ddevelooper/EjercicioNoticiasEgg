var fechaFooter = document.querySelector("#fecha");
fechaFooter.innerHTML = (new Date().getFullYear() + " ");


function eliminar(id){
	swal({
            title: "¿Está seguro que desea eliminar la publicación "+id+"?",
            text: "¡Una vez elimanado, no podrá recuperarla!",
            icon: "warning",
            buttons: {
                cancel: "Cancelar",
                success: "Aceptar"
            },
            dangerMode: true,
    })
    .then((OK) => {
            if (OK) {
	            $.ajax({
		        url:"/admin/eliminar?id=" + id,
		        success: function(){
			      swal("¡Su publicación se eliminó con exito!", {
                            icon: "success",
                            buttons: {
                	                success: "Aceptar"
                            }
                            }).then((ok)=>{
                	            if(ok){
                		            location.href="/";
                	            }
                            });
		        }
	            });
            } else {
                swal("¡Su publicación no se eliminó!",{
	                buttons:{succes: "Aceptar"}
            });
            }
});
}