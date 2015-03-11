select 
	prod.id, 
	obj.produto_id,
    obj.quantidade,
    obj.vendedor_id,
    obj.mes,
    obj.ano, 
    coalesce(fx.valor,0) as valor
from produtos prod
left join objetivo obj on obj.produto_id = prod.id
left join produtos_tabelas pt on pt.produto_id = obj.produto_id and tabelaDePreco_id = 1
left join faixa_comissao fx on fx.produtoTabela_id = pt.id and fx.percentual = 3 
where obj.ano = :ano and obj.vendedor_id = :vendedorId  
order by obj.vendedor_id,codigo,ano,mes

