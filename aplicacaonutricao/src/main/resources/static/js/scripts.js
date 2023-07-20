/*!
* Start Bootstrap - Simple Sidebar v6.0.5 (https://startbootstrap.com/template/simple-sidebar)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-simple-sidebar/blob/master/LICENSE)
*/
// 
// Scripts
// 

function responseCheck(xhr) {
	const contentType = xhr.getResponseHeader('Content-Type');
	if (contentType.split(";")[0] === 'text/html') {
		window.location.href = '/login';
	}
}

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


// AUXILIAR
function formatarData(data) {
	var dataAtual = new Date();
	if (data != '' && data !== undefined) {

		return data;
	}
	console.log(dataAtual)
	var dia = String(dataAtual.getDate()).padStart(2, '0');
	var mes = String(dataAtual.getMonth() + 1).padStart(2, '0'); // Lembre-se de que os meses são indexados de 0 a 11
	var ano = dataAtual.getFullYear();
	var dataFormatada = dia + '/' + mes + '/' + ano;


	return dataFormatada;
}





// PAGINAÇÂO ///

function paginacao(divpaginacao, dadospagina, func) {
	if (dadospagina.numberOfElements == 0) {
		return;
	}
	$("#" + divpaginacao).html('');


	var paginas = '';


	var anterior = `<li class="page-item "  ><a onclick="${func}(${dadospagina.number - 1})" class="page-link" >Voltar</a></li>`;
	var proximo = `<li class="page-item " ><a onclick="${func}(${dadospagina.number + 1})" class="page-link" >Proximo</a></li>`;

	if (dadospagina.first) {
		anterior = `<li class="page-item disabled"  ><a tabindex="-1" onclick="${func}(${dadospagina.number - 1})" class="page-link" >Voltar</a></li>`;
	}
	if (dadospagina.last) {
		proximo = `<li class="page-item disabled"><a class="page-link"  tabindex="-1" onclick="${func}(${dadospagina.number + 1})">Proximo</a></li>`;

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

	var ul = `<ul class="pagination justify-content-center">${anterior}${paginas}${proximo}</ul>`

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

function alertar(msg, id) {
	if (id === undefined) {
		id = "alerta";
	}
	$("#" + id).html('');
	$("#" + id).html(`<div class="alert alert-warning" role="alert">
  ${msg}
</div>`)
}

function error(msg, id) {
	if (id === undefined) {
		id = "alerta";
	}
	$("#" + id).html('');
	$("#" + id).html(`<div class="alert alert-danger" role="alert">
  ${msg}
</div>`)
}

function limparFormulario(id) {
	$("#" + id + " input").val('');
}

function tratarErro(xhr, id) {
	//	var erro = JSON.parse(xhr.responseText);

	if (xhr.status === 405 || xhr.status === 403) { //quando a sessão expira

		error("Problema de Autenticação, efetue o login novamente.", id)
		window.location.href = '/login';

	} else {//quando para a aplicação
		error(xhr.responseText, id);
	}

}
function minutos_faltando(date) {
	var MS_MINUTES=60*1000;
	var day = date.split("/")[0];
	var month = date.split("/")[1];
	var year = date.split("/")[2].split(" ")[0];
	var time=date.split("/")[2].split(" ")[1]
	console.log(date.split("/")[0]);
	var horario_agendado = new Date(`${month}/${day}/${year} ${time}`).getTime();
	var minutos_faltando=(horario_agendado-new Date().getTime())/MS_MINUTES;
	return minutos_faltando;

}

function sucesso(msg, id) {
	if (id === undefined) {
		id = "alerta";
	}

	$("#" + id).html('');

	$("#" + id).html(`<div class="alert alert-success" role="alert">
  ${msg}
</div>`)
}


/////// COMPONENTES

var modalAgendar = `<div class="modal"  tabindex="-1" role="dialog">
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



