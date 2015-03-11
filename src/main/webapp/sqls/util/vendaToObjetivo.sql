insert into tkl.objetivo (
select
    null as id,
	vendaToObj.mes as mes, 
	2015 as ano, 
	sum(vendaToObj.quantidade) as quantidade, 
	vendaToObj.produto_id, 
	vendaToObj.vendedor_id
from(
select 
    item.id as id,
	month(v.dataVenda) as mes, 
	2015 as ano, 
	item.quantidade, 
	item.produto_id, 
	v.vendedor_id
from tkl.item_vendas item
left join tkl.vendas v on v.id = item.venda_id
where v.dataVenda >= 20140101 and v.dataVenda <= 20141231
and v.id not in (select venda_id from cancelamentos)
union 
	select 
    item.id as id,
	month(dev.dataDevolucao) as mes, 
	2015 as ano, 
	item.quantidade*(-1) as quantidade, 
	item.produto_id, 
	v.vendedor_id
from tkl.item_devolucoes item
left join tkl.devolucoes dev on dev.id = item.devolucao_id
left join tkl.vendas v on v.id = item.venda_id
where dev.dataDevolucao >= 20140101 and dev.dataDevolucao <= 20141231) as vendaToObj
group by vendaToObj.vendedor_id, vendaToObj.produto_id, vendaToObj.ano, vendaToObj.mes);