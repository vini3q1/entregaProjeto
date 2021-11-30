function recarrega(){
	
	/*Não sei se tem a necessidade de vincular!*/
	
//	var idUsuario = document.getElementById("idUsuario");
//	var sessionId = document.getElementById("sessionID");
//	var idCarrinho = document.getElementById("idCarrinho");
//	if(idUsuario != null && sessionId != null && idCarrinho != null){
//		
//		console.log("VINCULA");
//		
//	} else {
//		
//		console.log("NÃO VINCULA");
//		
//	}
	
	
	
//	$.ajax({
//		type: "GET",
//		url: "http://localhost:8080/magic-store/carrinho/" + sessionId,
////		data: jsonString,
//		contentType:"application/json; charset=utf-8",
//		dataType: "json",
//		success: function(data, text, xhr){
//			
//			console.log("EXECUTOU");
//			
//
//		}
//	})
	
}

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

	var formCarrinho = document.getElementById("carrinhoCompras");
	var idProduto = formCarrinho.elements.namedItem("idProduto").value;
	var valorCarrinho = formCarrinho.elements.namedItem("valorCarrinho").value;
	var qtdCarrinho = formCarrinho.elements.namedItem("quantidadeCarrinho").value;
	
	var json = new Object();
	json.idProduto = parseInt(idProduto);
	json.valorCarrinho = parseFloat(valorCarrinho);
	json.quantidade = parseInt(qtdCarrinho);

	var jsonString= JSON.stringify(json);
//
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/magic-store/categoria/carrinho",
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

