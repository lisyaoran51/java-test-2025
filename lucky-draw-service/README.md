# lucky-draw-service
amway test 1: lucky draw system

## How to start

1. ### start db
   ```
   127.0.0.1:3311
   root/123456
   ```
2. ### run sql script
    [sql](src/main/sql/20230726000000_create_table_prize.sql)
3. ### start the application

4. ### call api
   ```
   curl --location '127.0.0.1:8090/luckyDraw/multi' \
   --header 'Content-Type: application/json' \
   --data '{"count":5}'
   ```