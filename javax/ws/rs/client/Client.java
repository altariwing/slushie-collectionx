
javax.ws.rs.client.Client

var token = this.AuthToken.getToken();
$http.get('/api/me', { headers: {'x-access-token': token} });

//java restful Client
//may use Postman/Fiddler/charles for testing
//writing my own ._.b

//「MySql数据库连接超时」可以從資料庫設定解決
//    ↖(当较长时间没有去访问网站，再次打开时就会报一个数据库连接失败的错误)
// 但如果資料庫不是自己的~ 有個解法就是每過一段時間就去戳一次 例如這樣(O)

   //query
   private void testClient() throws Exception{
        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        String str = client.target("http://localhost:8080/myAppAPI/next-game-time")
            .queryParam("game_id",123)
            .request("application/json")
            .get(String.class);

        System.out.print(str);
        client.close();
    }

    //path+JWT 
    private void testClient2() throws Exception{
        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        String str = client.target("http://localhost:8080/myAppAPI/users/game-level/id")
            .path("{id}")
            .resolveTemplate("id",1)
            .request("application/json")
            .header("x-access-token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0aXRsZSI6IkxGLUxPTEkiLCJ3dXQiOiJyZWFsbHkgLl8uPyIsImlkayI6NDIsImhtbW0iOiJva2F5IHlvdSBzZWUgbWUifQ.M7K7Bf9PXH58xMfnlDVkQSE7U3CW0qxxBOkpqhZ1yGs")
            .get(String.class);

        System.out.print(str);
        client.close();
    }

    //custom Entity
    private void testClient3() throws Exception{
        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        Response res = client.target("http://localhost:8080/myAppAPI/users/game-level/id")
            .path("{id}")
            .resolveTemplate("id",1)
            .request("application/json")
            .header("x-access-token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0aXRsZSI6IkxGLUxPTEkiLCJ3dXQiOiJyZWFsbHkgLl8uPyIsImlkayI6NDIsImhtbW0iOiJva2F5IHlvdSBzZWUgbWUifQ.M7K7Bf9PXH58xMfnlDVkQSE7U3CW0qxxBOkpqhZ1yGs")
            .get();

        ResponseEntity responseEntity = res.readEntity(ResponseEntity.class);

        System.out.print(res.getStatus()+", "+responseEntity.getMsg()+", "+responseEntity.getData());
        client.close();
    }
