DROP TABLE IF EXISTS public.all_requests;

WITH cust_accs AS (
	SELECT c.id AS customer_id,
			a.id AS account_id,
			floor(random() * 100) AS score
	FROM public.customer c
	JOIN public.customer_account ca ON ca.customer_id = c.id
	join public.account a ON a.id = ca.account_id
	LIMIT 10000
)
SELECT customer_id,
		account_id,
		'"{"amount" : 1.00}"' AS credit_request,
		'"{"accountId" : "' || account_id || '", "amount" : 1.00}"' AS debit_request,
		score
INTO public.all_requests
FROM cust_accs
ORDER BY random();

\COPY (SELECT customer_id,account_id,'{"amount" : 1.00}' AS debit_request,'{"accountId" : "' || account_id || '", "amount" : 1.00}' AS credit_request FROM public.all_requests WHERE score != 0 ORDER BY random()) TO './src/gatling/resources/other_requests.csv' DELIMITER ',' CSV HEADER;
\COPY (SELECT customer_id,account_id,'{"amount" : 1.00}' AS debit_request,'{"accountId" : "' || account_id || '", "amount" : 1.00}' AS credit_request FROM public.all_requests WHERE score = 0 ORDER BY random()) TO './src/gatling/resources/top_requests.csv' DELIMITER ',' CSV HEADER;
