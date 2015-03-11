select 
	prod.id,
    prod.codigo,
    prod.descricao,
    coalesce(fx.valor,0) as precoUnitario,
    sum( if(mes = 1 and ano = 2015 and vendedor_id = 29,obj.quantidade,0)) as mes_01,
    sum( if(mes = 1 and ano = 2015 and vendedor_id = 29,obj.id,0))         as mes_01_id,
    
    sum( if(mes = 2 and ano = 2015 and vendedor_id = 29,obj.quantidade,0)) as mes_02,
    sum( if(mes = 2 and ano = 2015 and vendedor_id = 29,obj.id,0))         as mes_02_id,
 
    sum( if(mes = 3 and ano = 2015 and vendedor_id = 29,obj.quantidade,0)) as mes_03, 
    sum( if(mes = 3 and ano = 2015 and vendedor_id = 29,obj.id,0))         as mes_03_id, 

    sum( if(mes = 4 and ano = 2015 and vendedor_id = 29,obj.quantidade,0)) as mes_04, 
    sum( if(mes = 4 and ano = 2015 and vendedor_id = 29,obj.id,0))         as mes_04_id, 

    sum( if(mes = 5 and ano = 2015 and vendedor_id = 29,obj.quantidade,0)) as mes_05, 
    sum( if(mes = 5 and ano = 2015 and vendedor_id = 29,obj.id,0))         as mes_05_id, 

    sum( if(mes = 6 and ano = 2015 and vendedor_id = 29,obj.quantidade,0)) as mes_06, 
    sum( if(mes = 6 and ano = 2015 and vendedor_id = 29,obj.id,0))         as mes_06_id, 

    sum( if(mes = 7 and ano = 2015 and vendedor_id = 29,obj.quantidade,0)) as mes_07, 
    sum( if(mes = 7 and ano = 2015 and vendedor_id = 29,obj.id,0))         as mes_07_id, 

    sum( if(mes = 8 and ano = 2015 and vendedor_id = 29,obj.quantidade,0)) as mes_08, 
    sum( if(mes = 8 and ano = 2015 and vendedor_id = 29,obj.id,0))         as mes_08_id, 

    sum( if(mes = 9 and ano = 2015 and vendedor_id = 29,obj.quantidade,0)) as mes_09, 
    sum( if(mes = 9 and ano = 2015 and vendedor_id = 29,obj.id,0))         as mes_09_id, 

    sum( if(mes = 10 and ano = 2015 and vendedor_id = 29,obj.quantidade,0)) as mes_10, 
    sum( if(mes = 10 and ano = 2015 and vendedor_id = 29,obj.id,0))         as mes_10_id, 

    sum( if(mes = 11 and ano = 2015 and vendedor_id = 29, obj.quantidade,0)) as mes_11, 
    sum( if(mes = 11 and ano = 2015 and vendedor_id = 29, obj.id,0))         as mes_11_id, 

    sum( if(mes = 12 and ano = 2015 and vendedor_id = 29,obj.quantidade,0)) as mes_12,
    sum( if(mes = 12 and ano = 2015 and vendedor_id = 29,obj.id,0))         as mes_12_id
    #coalesce(fx.valor,0) as valor
from produtos prod
left join objetivo obj on obj.produto_id = prod.id
left outer join produtos_tabelas pt on pt.produto_id = obj.produto_id and tabelaDePreco_id = 1
left join faixa_comissao fx on fx.produtoTabela_id = pt.id and fx.percentual = 3 
where vendedor_id = 29
group by prod.id 
#order by total desc


