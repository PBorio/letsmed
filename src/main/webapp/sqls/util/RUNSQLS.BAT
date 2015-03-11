cd\borius\vendas\tkl\src\main\webapp\sqls
REM Mysql deve estar no PATH
FOR %%f IN (*.sql) do (

    mysql -uroot -pborios < %%f

)

