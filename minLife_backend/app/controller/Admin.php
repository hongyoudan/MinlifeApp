<?php


namespace app\controller;
use app\BaseController;
use think\facade\Db;
use think\facade\Request;
use think\facade\View;
use think\facade\Session;

class Admin
{
    public function index()
    {
        // http session 回话
        if(Session::get("logined") == true){
            return View::fetch('index');
        }else{
            View::assign('name','ThinkPHP');
            return View::fetch('login');
        }
    }
     

    public function login (Request  $request) {
        if(input('?post.username') && input("?post.password")) {
            $username =  input('post.username');
            $password =   input('post.password'); // mvc model  select * from tp_admin where id = 1?
            $realinfo  = Db::table("tp_admin")->where("id",1)->find();
            $name = $realinfo["username"];
            $pass = $realinfo["password"];
            if($username == $name &&  password_verify($password,$pass)){
                Session::set("logined",true);
                return redirect("/admin");
            }else{
//                return password_hash("123",PASSWORD_DEFAULT);
                return $username ."::".$password .": 登录错误";
            }
        }else{
            return "camsji: 登录错误";
        }
    }
    public function resetpass () {
        if(Session::get("logined") == true){
            if( input("?post.password")) {
                $pass = input("post.password");
                Session::set("logined",false);
                Db::table("tp_admin")->update(["id"=>1,"password"=>password_hash($pass,PASSWORD_DEFAULT)]);
                return json(["code"=>1]);
            }else{
                return json(["code"=>2]);
            }
        }
        return json(["code"=>0]);
    }
    
    public function resetcreatetime(){
        if(Session::get("logined") == true){
            $info =     Db::table("tp_numbers")->select();
            foreach ($info as $item){
                $item["createtime"] = str_replace( "-","/", $item["createtime"] );
                Db::table("tp_numbers")->update($item);
            }
            return json(["code"=>1]);
        }else{
            return json(["code"=>0]);

        }
    }
    public function logout () {
        Session::set("logined",false);
        return json(["code"=>1]);

    }
    
    public function getIsMainting()
    {
        $info = Db::table('tp_notify')->where("id",2)->find();
        if($info["info"] == "123")
            return json(["code"=>1]);
        else 
            return json(["code"=>0]);
    }
    public function setMainting()
    {
        if(Session::get("logined") == true) {
            if (input("?post.state") && input("post.state") == 1 )
            {
                $info = Db::table('tp_notify')->where("id",2)->update(["id"=>2,"info"=>"123"]);
                return json(["code"=>1]);
            }else if (input("?post.state") && input("post.state") == 0 ){
                $info = Db::table('tp_notify')->where("id",2)->update(["id"=>2,"info"=>"321"]);
                return json(["code"=>0]);
            }
        }
    }

}