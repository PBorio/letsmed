select ObjPorAno.vendedor_id as id,
       ObjPorAno.vendedor_id,   
       repres.nome as nome,
       ObjPorAno.totalAnoAtual,
       ObjPorAno.totalAnoAtualMaisUmAno,
       ObjPorAno.totalAnoAtualMaisDoisAnos
   from(
	  select 
		 obj.id as id,
	     obj.vendedor_id as vendedor_id,
         sum(quantidade * coalesce(fx.valor,0)) as totalAnoAtual,
         0 as totalAnoAtualMaisUmAno, 
         0 as totalAnoAtualMaisDoisAnos 
      from objetivo obj
      left join produtos_tabelas pt on pt.produto_id = obj.produto_id and tabelaDePreco_id = 1
      left join faixa_comissao fx on fx.produtoTabela_id = pt.id and fx.percentual = 3 
      where obj.ano = 2015
	  group by obj.vendedor_id
    union
	  select 
		 obj.id as id,
	     obj.vendedor_id as vendedor_id,
         0 as totalAnoAtual, 
         sum(quantidade * coalesce(fx.valor,0)) as totalAnoAtualMaisUmAno,
         0 as totalAnoAtualMaisDoisAnos
      from objetivo obj
      left join produtos_tabelas pt on pt.produto_id = obj.produto_id and tabelaDePreco_id = 1
      left join faixa_comissao fx on fx.produtoTabela_id = pt.id and fx.percentual = 3
      where obj.ano = 2016
	  group by obj.vendedor_id 	
   union
      select 
		 obj.id as id,
	     obj.vendedor_id as vendedor_id,
         0 as totalAnoAtual, 
         0 as totalAnoAtualMaisUmAno, 
         sum(quantidade * coalesce(fx.valor,0)) as totalAnoAtualMaisDoisAnos
      from objetivo obj
      left join produtos_tabelas pt on pt.produto_id = obj.produto_id and tabelaDePreco_id = 1
      left join faixa_comissao fx on fx.produtoTabela_id = pt.id and fx.percentual = 3
      where obj.ano = 2017
	  group by obj.vendedor_id 	
    )as ObjPorAno
left join vendedores repres on repres.id = ObjPorAno.vendedor_id   
order by ObjPorAno.totalAnoAtual desc