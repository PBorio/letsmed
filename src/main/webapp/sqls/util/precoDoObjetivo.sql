update produtos prod set preco_para_objetivo = round((select  avg(item.valorBruto) from item_vendas item 
left join vendas venda on venda.id = item.venda_id
where venda.dataVenda between 20140101 and 20141231 and item.produto_id = prod.id
group by produto_id),6) 