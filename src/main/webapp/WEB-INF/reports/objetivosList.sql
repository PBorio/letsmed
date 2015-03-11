select ObjPorAno.id,
       ObjPorAno.vendedor_id,   
       repres.nome as nome,
       ObjPorAno.totalAnoAtual,
       ObjPorAno.totalAnoAtualMaisUmAno,
       ObjPorAno.totalAnoAtualMaisDoisAnos
   from(
	  select 
		 obj.id as id,
	     obj.vendedor_id as vendedor_id,
         sum(quantidade * coalesce(prod.preco_para_objetivo,0)) as totalAnoAtual,
         0 as totalAnoAtualMaisUmAno, 
         0 as totalAnoAtualMaisDoisAnos 
      from objetivo obj
      left join produtos prod on prod.id = obj.produto_id
      where obj.ano = 2015
	  group by obj.vendedor_id
    union
	  select 
		 obj.id as id,
	     obj.vendedor_id as vendedor_id,
         0 as totalAnoAtual, 
         sum(quantidade * coalesce(prod.preco_para_objetivo,0)) as totalAnoAtualMaisUmAno,
         0 as totalAnoAtualMaisDoisAnos
      from objetivo obj
      left join produtos prod on prod.id = obj.produto_id
      where obj.ano = 2016
	  group by obj.vendedor_id 	
   union
      select 
		 obj.id as id,
	     obj.vendedor_id as vendedor_id,
         0 as totalAnoAtual, 
         0 as totalAnoAtualMaisUmAno, 
         sum(quantidade * coalesce(prod.preco_para_objetivo,0)) as totalAnoAtualMaisDoisAnos
      from objetivo obj
      left join produtos prod on prod.id = obj.produto_id
      where obj.ano = 2017
	  group by obj.vendedor_id 	
    )as ObjPorAno
left join vendedores repres on repres.id = ObjPorAno.vendedor_id   
