(function( $ ){
   $.fn.borioSelect = function(opcoes) {
      this.select2({
		   minimumInputLength: 2,
		      ajax: {
		      url: opcoes.url,
		      dataType: 'json',
		      type: "GET",
		      quietMillis: 50,
		      data: function ( term ) { return { term: term }; },
		      results: function (data) {
		    	  return { results : $.map(data, function (item) { return guardaCampos(item,opcoes.selecao,opcoes.extras); } ) };}
		      },
		      initSelection: function (element, callback) {
	           callback({ id: opcoes.editar.id, text: opcoes.editar.texto });
		      }
	  }).one('select2-focus', select2Focus).on("select2-blur", function () {
		   $(this).one('select2-focus', select2Focus);
		});;
      
      return this;
   }; 
})( jQuery );

function select2Focus() { // Para abrir a pesquisa ao receber o foco
    var select2 = $(this).data('select2');
    setTimeout(function() {
        if (!select2.opened()) {
            select2.open();
        }
    }, 0);  
}

function guardaCampos(item,selecao,extras){
	
	var textoDaSelecao = montaStringSelecao(item,selecao);
	
	var data = {text : textoDaSelecao,id: item['id']};
	
	if(extras!==undefined){
		for(propriedade in extras){
			data[propriedade] = navegaPeloObjeto(item,extras[propriedade]);
		}
	}
	
	return data;
}

function montaStringSelecao(item,selecao){
	
	var resultado = "";
	
	for (propriedade in selecao) {
		  resultado += navegaPeloObjeto(item,selecao[propriedade]) + " - ";
	};

	return resultado.substring(0,resultado.length-3);
}

function navegaPeloObjeto(item,propriedade){
	
	var objects = propriedade.split(".");
	
	var cont = objects.length;
	
	if(cont==1)
		return item[objects[0]];
	
	if(cont==2)
		return item[objects[0]][objects[1]];
	
	if(cont==3)
		return item[objects[0]][objects[1]][objects[2]];
	
	if(cont==4)
		return item[objects[0]][objects[1]][objects[2]][objects[3]];
	
	if(cont==5)
		return item[objects[0]][objects[1]][objects[2]][objects[3]][objects[4]];
	
	if(cont==6)
		return item[objects[0]][objects[1]][objects[2]][objects[3]][objects[4]][objects[5]];
	
};
