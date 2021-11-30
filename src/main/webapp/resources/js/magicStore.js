function carrega(idCategoria){
	
	var ajax = new XMLHttpRequest();
	ajax.open("GET", "http://localhost:8080/magic-store/produto/lista/" + idCategoria, true);
	ajax.send();
	
	ajax.onreadystatechange = function(){
		if(ajax.status == 200 && ajax.responseText.length > 2){
			var data = ajax.responseText;
			var produtoResponse = JSON.parse(data);
			var cont = "";
			
			for (var i = 0; i < produtoResponse.length; i++) {
				 cont += "\
				<div class=\"carousel-inner\"><div class=\"active item\"><ul class=\"thumbnails listing-products\"><li class=\"span3\"><div class=\"product-box\"><span class=\"sale_tag\"></span>" +
						"<a class=\"title\">" + produtoResponse[i].nome + "</a><br/><a class=\"category\">" + produtoResponse[i].descricao + "</a><p class=\"price\">R$ " + produtoResponse[i].valor + "</p></div></li></ul></div></div>";
				
			}
			document.getElementById('principal').innerHTML = cont;
		}
	}
}


function cadastrarUsuario(){

	var formCadastro = document.getElementById("cadastroUsuario");
	var nome = formCadastro.elements.namedItem("nome").value;
	var email = formCadastro.elements.namedItem("email").value;
	var senha = formCadastro.elements.namedItem("senha").value;
	var cpf = formCadastro.elements.namedItem("cpf").value;
	var rg = formCadastro.elements.namedItem("rg").value;

	var json = new Object();
	json.nome = nome;
	json.email = email;
	json.senha = senha;
	json.cpf = cpf;
	json.rg = rg;

	var jsonString= JSON.stringify(json);

	$.ajax({
		type: "POST",
		url: "http://localhost:8080/magic-store/usuario",
		data: jsonString,
		contentType:"application/json; charset=utf-8",
		dataType: "json",
		success: function(data, text, xhr){
			login(email, senha);

		}
	})

}

function login(emailUsuario, senhaUsuario){
	
	var email;
	var senha;
	if(email == ''){
		console.log("VAZIO");
		var formLogin = document.getElementById("loginUsuario");
		email = formLogin.elements.namedItem("email").value;
		senha = formLogin.elements.namedItem("senha").value;
		var cookie = "";
	} else {
		email = emailUsuario;
		senha = senhaUsuario;
	}

	var json = new Object();
	json.email = email;
	json.senha = senha;
	json.idSessao = cookie;

	console.log(json);

	var jsonString = JSON.stringify(json);

	$.ajax({
		type: "POST",
		url: "http://localhost:8080/magic-store/usuario/login",
		data: jsonString,
		contentType:"application/json; charset=utf-8",
		dataType: "json",
		success: function(data, text, xhr){
			alert(xhr);
			// CADASTRA E REALIZA O LOGIN DO USUÁRIO

		}
	})

}

