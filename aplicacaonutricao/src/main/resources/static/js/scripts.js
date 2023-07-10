/*!
* Start Bootstrap - Simple Sidebar v6.0.5 (https://startbootstrap.com/template/simple-sidebar)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-simple-sidebar/blob/master/LICENSE)
*/
// 
// Scripts
// 



window.addEventListener('DOMContentLoaded', event => {

	// Toggle the side navigation
	const sidebarToggle = document.body.querySelector('#sidebarToggle');
	if (sidebarToggle) {
		// Uncomment Below to persist sidebar toggle between refreshes
		// if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
		//     document.body.classList.toggle('sb-sidenav-toggled');
		// }
		sidebarToggle.addEventListener('click', event => {
			event.preventDefault();
			document.body.classList.toggle('sb-sidenav-toggled');
			localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
		});
	}

});








// PAGINAÇÂO ///

function paginacao(divpaginacao, dadospagina, func) {
	$("#" + divpaginacao).html('');


	var paginas = '';
	
	
	var anterior = `<li class="page-item "  ><a onclick="${func}(${dadospagina.number - 1})" class="page-link" >Voltar</a></li>`;
	var proximo = `<li class="page-item " ><a onclick="${func}(${dadospagina.number + 1})" class="page-link" >Proximo</a></li>`;
	
	if (dadospagina.first){
		anterior = `<li class="page-item disabled"  ><a tabindex="-1" onclick="${func}(${dadospagina.number - 1})" class="page-link" >Voltar</a></li>`;
	}
	if (dadospagina.last){
			proximo = `<li class="page-item disabled" "><a class="page-link"  tabindex="-1" onclick="${func}(${dadospagina.number + 1})>Proximo</a></li>`;

	}



	var nprimeira = 0;
	if (dadospagina.number % 3 == 0) {
		nprimeira = dadospagina.number;
	}
	else {
		nprimeira = Math.floor(dadospagina.number / 3) * 3;
	}


	var nultima = nprimeira + 2;
	if (dadospagina.totalPages < 3) {
		nultima = dadospagina.totalPages - 1;
	}

	for (var i = nprimeira; i <= nultima; i++) {
		if (i == dadospagina.number) {
			paginas += `<li class="page-item active" onclick="${func}(${i})"><a class="page-link" >${i + 1}</a></li>`;

		} else {
			paginas += `<li class="page-item" onclick="${func}(${i})"><a class="page-link">${i + 1}</a></li>`;

		}
	}

	var ul = `<ul class="pagination">${anterior}${paginas}${proximo}</ul>`

	$("#" + divpaginacao).append(ul);
}




/// GRAFICO
var meuGrafico = null;

var todasdatas = [];
var todosvalores = {
	imc: [],
	peso: [],
	percentual: [],

};
var tituloGrafico = "Grafico IMC";



function fazerGrafico(valores) {
	if (meuGrafico != null) {
		meuGrafico.destroy();
	}

	const ctx = document.getElementById('myChart');
	const data = {
		labels: todasdatas,
		datasets: [{
			label: tituloGrafico,
			data: valores,
			fill: false,
			borderColor: 'rgb(75, 192, 192)',
			tension: 0.1
		}]
	};

	const config = {
		type: 'line',
		data: data,
	};
	meuGrafico = new Chart(ctx, config);

}


function grafico() {

	$.get('/medicoes/' + id + "/data").done(function(response) {
		var pagina = JSON.parse(response);
		console.log(pagina);
		var content = pagina.content;

		todosvalores.imc = [];
		todosvalores.peso = [];
		todosvalores.percentual = [];
		todasdatas = [];


		content.forEach((e) => {
			todasdatas.push(e.data);
			todosvalores.imc.push(e.imc);
			todosvalores.peso.push(e.peso);
			todosvalores.percentual.push(e.percentualDeGordura);

		})
		$('#modalGrafico').modal('show')
		fazerGrafico(todosvalores.imc);
	}).fail(function(xhr) {
		tratarErro(xhr);
	});

}

function alertar(msg,id){
	if (id===undefined){
		id="alerta";
	}
	$("#"+id).html('');
	$("#"+id).html(`<div class="alert alert-warning" role="alert">
  ${msg}
</div>`)
}

function error(msg,id){
	if (id===undefined){
		id="alerta";
	}
	$("#"+id).html('');
	$("#"+id).html(`<div class="alert alert-danger" role="alert">
  ${msg}
</div>`)
}

function limparFormulario(id) {
	$("#" + id + " input").val('');
}

function tratarErro(xhr,id) {
//	var erro = JSON.parse(xhr.responseText);
	error(xhr.responseText,id);
}

function sucesso(msg,id){
		if (id===undefined){
			id="alerta";
		}
		
		$("#"+id).html('');

	$("#"+id).html(`<div class="alert alert-success" role="alert">
  ${msg}
</div>`)
}


/////// COMPONENTES

var modalAgendar=`<div class="modal"  tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>`;



